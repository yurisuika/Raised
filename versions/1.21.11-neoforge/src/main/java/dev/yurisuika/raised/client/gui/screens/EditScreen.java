package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.components.IntRangeSliderButton;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EditScreen extends AbstractLayersScreen {

    public ArrayList<AbstractWidget> options;
    public AbstractWidget controlReturn;
    public AbstractWidget optionOffsetX;
    public AbstractWidget optionOffsetY;
    public TransferableLayerList leftList;
    public TransferableLayerList rightList;

    public EditScreen(Screen parent) {
        super(parent, 3, 1);
    }

    public List<Identifier> listSelectedLayers() {
        return Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getLayers().stream()
                .sorted(Comparator.comparing(String::toString))
                .filter(groupedLayerName -> LayerRegistry.LAYERS
                        .contains(Identifier.tryParse(groupedLayerName)))
                .map(Identifier::tryParse)
                .collect(Collectors.toList());
    }

    public List<Identifier> listAvailableLayers() {
        return LayerRegistry.LAYERS.stream()
                .sorted(Comparator.comparing(Identifier::toString))
                .filter(layerName -> layerName.getNamespace()
                        .equals(selectedNamespace))
                .filter(layerName -> leftList.children().stream()
                        .noneMatch(groupedLayerName -> Objects.equals(groupedLayerName.getLayerName(), layerName)))
                .toList();
    }

    @Override
    public void addContent() {
        super.addContent();

        addOptions();
    }

    @Override
    public void addLeftControls() {
        leftControls = new ArrayList<>();

        controlReturn = Button.builder(Component.translatable("options.raised.control.return"), button -> onClose())
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(leftPanelX, leftPanelY)
                .build();

        leftControls.add(controlReturn);

        leftControls.forEach(this::addRenderableWidget);
    }

    public void addOptions() {
        options = new ArrayList<>();

        optionOffsetX = IntRangeSliderButton.builder(Component.translatable("options.raised.offset.x"), value -> Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setX(value)))
                .range(0, width / 4)
                .initialValue(Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getX())
                .valueText(value -> value == 0 ? CommonComponents.OPTION_OFF : Component.literal(value + "px (" + Math.round(Math.ceil((value / ((float) width / 4)) * 100)) + "%)"))
                .tooltip(value -> Tooltip.create(Component.translatable("options.raised.offset.x.tooltip")))
                .size(panelWidth, WIDGET_HEIGHT)
                .pos(leftPanelX, leftPanelY + WIDGET_AND_GAP_HEIGHT)
                .build();

        optionOffsetY = IntRangeSliderButton.builder(Component.translatable("options.raised.offset.y"), value -> Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setY(value)))
                .range(0, height / 4)
                .initialValue(Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getY())
                .valueText(value -> value == 0 ? CommonComponents.OPTION_OFF : Component.literal(value + "px (" + Math.round(Math.ceil((value / ((float) height / 4)) * 100)) + "%)"))
                .tooltip(value -> Tooltip.create(Component.translatable("options.raised.offset.y.tooltip")))
                .size(panelWidth, WIDGET_HEIGHT)
                .pos(leftPanelX, leftPanelY + (2 * WIDGET_AND_GAP_HEIGHT))
                .build();

        options.add(optionOffsetX);
        options.add(optionOffsetY);

        options.forEach(this::addRenderableWidget);
    }

    @Override
    public void addLeftList() {
        leftList = new TransferableLayerList(minecraft, this, false, listWidth, leftListHeight, leftListY);

        addRenderableWidget(leftList);
    }

    @Override
    public void addRightList() {
        rightList = new TransferableLayerList(minecraft, this, true, listWidth, rightListHeight, rightListY);

        addRenderableWidget(rightList);
    }

    public void resetOptions() {
        if (options != null) {
            options.forEach(this::removeWidget);
            options.clear();
            addOptions();
        }
    }

    @Override
    public void resetLeftList() {
        if (leftList != null) {
            leftList.setScrollAmount(0.0F);
            leftList.setEntries();
        }
    }

    @Override
    public void resetRightList() {
        if (rightList != null) {
            rightList.setScrollAmount(0.0F);
            rightList.setEntries();
        }
    }

    @Override
    public void repositionElements() {
        leftList.updateSizeAndPosition(
                listWidth,
                leftListHeight,
                leftPanelX + LIST_BORDER,
                leftListY);
        rightList.updateSizeAndPosition(
                listWidth,
                rightListHeight,
                rightPanelX + LIST_BORDER,
                rightListY);
    }

    @Override
    public void resize(int width, int height) {
        if (Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getX() > width / 4) {
            Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setX(width / 4));
        }
        if (Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getY() > height / 4) {
            Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setY(height / 4));
        }

        resetOptions();

        super.resize(width, height);
    }

    @Override
    public void renderBackground(final GuiGraphics guiGraphics, final int mouseX, final int mouseY, final float a) {
        super.renderBackground(guiGraphics, mouseX, mouseY, a);

        guiGraphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                Identifier.fromNamespaceAndPath(Raised.MOD_ID, "edit/background"),
                containerX,
                containerY,
                containerWidth,
                containerHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.textRenderer().acceptScrollingWithDefaultCenter(
                Component.literal(getCurrentGroup().getGroupName()),
                leftPanelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                leftPanelX + panelWidth,
                rightPanelY,
                rightPanelY + WIDGET_HEIGHT);

        if (leftList.children().isEmpty()) {
            MutableComponent empty = Component.translatable("options.raised.list.selected_layers.empty");
            guiGraphics.drawCenteredString(
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    leftPanelX + (panelWidth / 2),
                    leftPanelY + panelHeight - LIST_BORDER - (leftListHeight / 2) - (font.lineHeight / 2),
                    -1);
            minecraft.getNarrator().saySystemNow(empty);
        }

        if (rightList.children().isEmpty()) {
            MutableComponent empty = Component.translatable("options.raised.list.available_layers.empty");
            guiGraphics.drawCenteredString(
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    rightPanelX + (panelWidth / 2),
                    rightPanelY + panelHeight - LIST_BORDER - (rightListHeight / 2) - (font.lineHeight / 2),
                    -1);
            minecraft.getNarrator().saySystemNow(empty);
        }
    }

    public class TransferableLayerList extends AbstractLayerList<TransferableLayerList.Entry> {

        private final boolean available;

        public TransferableLayerList(Minecraft minecraft, EditScreen parent, boolean available, int width, int height, int y) {
            super(minecraft, parent, width, height, y);
            this.available = available;
            setEntries();
        }

        @Override
        public void setEntries() {
            clearEntries();
            (isAvailable() ? listAvailableLayers() : listSelectedLayers()).forEach(layerName -> addEntry(new Entry(layerName)));
        }

        public boolean isAvailable() {
            return available;
        }

        public class Entry extends AbstractLayerList.Entry<Entry> {

            public Entry(Identifier layerName) {
                super(layerName);
            }

            @Override
            public int getWidth() {
                return super.getWidth() - (TransferableLayerList.this.scrollbarVisible() ? 6 : 0);
            }

            @Override
            public void renderContent(GuiGraphics guiGraphics, int mouseX, int mouseY, boolean bl, float partialTick) {
                super.renderContent(guiGraphics, mouseX, mouseY, bl, partialTick);

                int i = mouseX - getX();
                if (isMouseOver(mouseX, mouseY)) {
                    guiGraphics.blitSprite(
                            RenderPipelines.GUI_TEXTURED,
                            Identifier.fromNamespaceAndPath(Raised.MOD_ID, "layer_list/" + (isAvailable() ? "" : "un") + "select" + (i < ENTRY_HEIGHT ? "_highlighted" : "")),
                            getContentX(),
                            getContentY(),
                            ENTRY_HEIGHT,
                            ENTRY_HEIGHT);
                    if (i < ENTRY_HEIGHT) {
                        TransferableLayerList.this.handleCursor(guiGraphics);
                    }
                }
            }

            @Override
            public String entryText() {
                return Parse.parseNamespace(layerName.getNamespace()) + ": " + Parse.parsePath(layerName.getPath());
            }

            @Override
            public boolean handleClick(final MouseButtonEvent event) {
                int relX = (int) event.x() - getContentX();
                int relY = (int) event.y() - getContentY();
                if (relX >= 0 && relX < ENTRY_HEIGHT && relY >= 0 && relY < ENTRY_HEIGHT) {
                    if (isAvailable()) {
                        Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getLayers().add(getLayerName().toString()));
                    } else {
                        Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getLayers().remove(getLayerName().toString()));
                    }
                    resetLeftList();
                    resetRightList();
                    return true;
                }
                return false;
            }

            @Override
            public void setSelected(boolean selected) {
                if (selected) {
                    TransferableLayerList.this.setSelected(this);
                }
            }

        }

    }

}