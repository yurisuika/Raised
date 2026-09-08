package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.components.IntRangeSliderButton;
import dev.yurisuika.raised.mixin.minecraft.client.gui.components.AbstractWidgetInvoker;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;

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
    public SelectedLayerList leftList;
    public AvailableLayerList rightList;

    public EditScreen(Screen parent) {
        super(parent, 3, 1);
    }

    public List<ResourceLocation> listSelectedLayers() {
        return Configure.Groups.getLayers(getCurrentGroup().getGroupName()).stream()
                .sorted(Comparator.comparing(String::toString))
                .filter(groupedLayerName -> LayerRegistry.LAYERS
                        .contains(ResourceLocation.tryParse(groupedLayerName)))
                .map(ResourceLocation::tryParse)
                .collect(Collectors.toList());
    }

    public List<ResourceLocation> listAvailableLayers() {
        return LayerRegistry.LAYERS.stream()
                .sorted(Comparator.comparing(ResourceLocation::toString))
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

        optionOffsetX = IntRangeSliderButton.builder(Component.translatable("options.raised.offset.x"), value -> Configure.Groups.setOffsetX(getCurrentGroup().getGroupName(), value))
                .range(0, width / 4)
                .initialValue(Configure.Groups.getOffsetX(getCurrentGroup().getGroupName()))
                .valueText(value -> value == 0 ? CommonComponents.OPTION_OFF : Component.literal(value + "px (" + Math.round(Math.ceil((value / ((float) width / 4)) * 100)) + "%)"))
                .tooltip(value -> Tooltip.create(Component.translatable("options.raised.offset.x.tooltip")))
                .size(panelWidth, WIDGET_HEIGHT)
                .pos(leftPanelX, leftPanelY + WIDGET_AND_GAP_HEIGHT)
                .build();

        optionOffsetY = IntRangeSliderButton.builder(Component.translatable("options.raised.offset.y"), value -> Configure.Groups.setOffsetY(getCurrentGroup().getGroupName(), value))
                .range(0, height / 4)
                .initialValue(Configure.Groups.getOffsetY(getCurrentGroup().getGroupName()))
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
        leftList = new SelectedLayerList(minecraft, this, listWidth, leftListHeight, leftListY);

        addRenderableWidget(leftList);
    }

    @Override
    public void addRightList() {
        rightList = new AvailableLayerList(minecraft, this, listWidth, rightListHeight, rightListY);

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
        leftList.setSize(listWidth, leftListHeight);
        leftList.setPosition(leftPanelX + LIST_BORDER, leftListY);
        rightList.setSize(listWidth, rightListHeight);
        rightList.setPosition(rightPanelX + LIST_BORDER, rightListY);
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        if (Configure.Groups.getOffsetX(getCurrentGroup().getGroupName()) > width / 4) {
            Configure.Groups.setOffsetX(getCurrentGroup().getGroupName(), width / 4);
        }
        if (Configure.Groups.getOffsetY(getCurrentGroup().getGroupName()) > height / 4) {
            Configure.Groups.setOffsetY(getCurrentGroup().getGroupName(), height / 4);
        }

        resetOptions();

        super.resize(minecraft, width, height);
    }

    @Override
    public void renderBackground(final GuiGraphics guiGraphics, final int mouseX, final int mouseY, final float a) {
        super.renderBackground(guiGraphics, mouseX, mouseY, a);

        guiGraphics.blitSprite(
                new ResourceLocation(Raised.MOD_ID, "edit/background"),
                containerX,
                containerY,
                containerWidth,
                containerHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        AbstractWidgetInvoker.invokeRenderScrollingString(
                guiGraphics,
                font,
                Component.literal(getCurrentGroup().getGroupName()),
                leftPanelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                rightPanelY,
                leftPanelX + panelWidth,
                rightPanelY + WIDGET_HEIGHT,
                -1);

        if (leftList.children().isEmpty()) {
            MutableComponent empty = Component.translatable("options.raised.list.selected_layers.empty");
            guiGraphics.drawCenteredString(
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    leftPanelX + (panelWidth / 2),
                    leftPanelY + panelHeight - LIST_BORDER - (leftListHeight / 2) - (font.lineHeight / 2),
                    -1);
            minecraft.getNarrator().sayNow(empty);
        }

        if (rightList.children().isEmpty()) {
            MutableComponent empty = Component.translatable("options.raised.list.available_layers.empty");
            guiGraphics.drawCenteredString(
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    rightPanelX + (panelWidth / 2),
                    rightPanelY + panelHeight - LIST_BORDER - (rightListHeight / 2) - (font.lineHeight / 2),
                    -1);
            minecraft.getNarrator().sayNow(empty);
        }
    }

    public class SelectedLayerList extends AbstractLayerList<SelectedLayerList.Entry> {

        public SelectedLayerList(Minecraft minecraft, EditScreen parent, int width, int height, int y) {
            super(minecraft, parent, width, height, y);
        }

        @Override
        public void setEntries() {
            clearEntries();
            listSelectedLayers().forEach(layerName -> addEntry(new Entry(layerName)));
        }

        public class Entry extends AbstractLayerList.Entry<Entry> {

            public Entry(ResourceLocation layerName) {
                super(layerName);
            }

            @Override
            public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                super.render(guiGraphics, index, top, left, width, height, mouseX, mouseY, hovering, partialTick);

                int i = mouseX - left;
                if (isMouseOver(mouseX, mouseY)) {
                    guiGraphics.blitSprite(
                            new ResourceLocation(Raised.MOD_ID, "transferable_list/unselect" + (i < ENTRY_HEIGHT ? "_highlighted" : "")),
                            left,
                            top,
                            ENTRY_HEIGHT,
                            ENTRY_HEIGHT);
                }
            }

            @Override
            public String entryText() {
                return Parse.parseNamespace(layerName.getNamespace()) + ": " + Parse.parsePath(layerName.getPath());
            }

            @Override
            public boolean handleClick(final double mouseX, final double mouseY, final int button) {
                int relX = (int) mouseX - getEntryX(this);
                int relY = (int) mouseY - getEntryY(this);
                if (relX >= 0 && relX < ENTRY_HEIGHT && relY >= 0 && relY < ENTRY_HEIGHT) {
                    Configure.Groups.removeLayer(getCurrentGroup().getGroupName(), this.getLayerName());
                    resetLeftList();
                    resetRightList();
                    return true;
                }
                return false;
            }

            @Override
            public void setSelected(boolean focused) {
                if (focused) {
                    SelectedLayerList.this.setSelected(this);
                }
            }

        }

    }

    public class AvailableLayerList extends AbstractLayerList<AvailableLayerList.Entry> {

        public AvailableLayerList(Minecraft minecraft, EditScreen parent, int width, int height, int y) {
            super(minecraft, parent, width, height, y);
        }

        @Override
        public void setEntries() {
            clearEntries();
            listAvailableLayers().forEach(layerName -> addEntry(new Entry(layerName)));
        }

        public class Entry extends AbstractLayerList.Entry<Entry> {

            public Entry(ResourceLocation layerName) {
                super(layerName);
            }

            @Override
            public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                super.render(guiGraphics, index, top, left, width, height, mouseX, mouseY, hovering, partialTick);

                int i = mouseX - left;
                if (isMouseOver(mouseX, mouseY)) {
                    guiGraphics.blitSprite(
                            new ResourceLocation(Raised.MOD_ID, "transferable_list/select" + (i < ENTRY_HEIGHT ? "_highlighted" : "")),
                            left,
                            top,
                            ENTRY_HEIGHT,
                            ENTRY_HEIGHT);
                }
            }

            @Override
            public String entryText() {
                return Parse.parsePath(layerName.getPath());
            }

            @Override
            public boolean handleClick(final double mouseX, final double mouseY, final int button) {
                int relX = (int) mouseX - getEntryX(this);
                int relY = (int) mouseY - getEntryY(this);
                if (relX >= 0 && relX < ENTRY_HEIGHT && relY >= 0 && relY < ENTRY_HEIGHT) {
                    Configure.Groups.addLayer(getCurrentGroup().getGroupName(), this.getLayerName());
                    resetLeftList();
                    resetRightList();
                    return true;
                }
                return false;
            }

            @Override
            public void setSelected(boolean focused) {
                if (focused) {
                    AvailableLayerList.this.setSelected(this);
                }
            }

        }

    }

}