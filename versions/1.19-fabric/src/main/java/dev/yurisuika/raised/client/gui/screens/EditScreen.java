package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.GuiComponentInterface;
import dev.yurisuika.raised.client.gui.components.IntRangeSliderButton;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;

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
        return Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getLayers().stream()
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

        controlReturn = new Button(
                leftPanelX,
                leftPanelY,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                Component.translatable("options.raised.control.return"),
                button -> onClose());

        leftControls.add(controlReturn);

        leftControls.forEach(this::addRenderableWidget);
    }

    public void addOptions() {
        options = new ArrayList<>();

        optionOffsetX = IntRangeSliderButton.builder(Component.translatable("options.raised.offset.x"), value -> Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setX(value)))
                .range(0, width / 4)
                .initialValue(Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getX())
                .valueText(value -> value == 0 ? CommonComponents.OPTION_OFF : Component.literal(value + "px (" + Math.round(Math.ceil((value / ((float) width / 4)) * 100)) + "%)"))
                .tooltip(value -> font.split(Component.translatable("options.raised.offset.x.tooltip"), 200))
                .size(panelWidth, WIDGET_HEIGHT)
                .pos(leftPanelX, leftPanelY + WIDGET_AND_GAP_HEIGHT)
                .build();

        optionOffsetY = IntRangeSliderButton.builder(Component.translatable("options.raised.offset.y"), value -> Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setY(value)))
                .range(0, height / 4)
                .initialValue(Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getY())
                .valueText(value -> value == 0 ? CommonComponents.OPTION_OFF : Component.literal(value + "px (" + Math.round(Math.ceil((value / ((float) height / 4)) * 100)) + "%)"))
                .tooltip(value -> font.split(Component.translatable("options.raised.offset.y.tooltip"), 200))
                .size(panelWidth, WIDGET_HEIGHT)
                .pos(leftPanelX, leftPanelY + (2 * WIDGET_AND_GAP_HEIGHT))
                .build();

        options.add(optionOffsetX);
        options.add(optionOffsetY);

        options.forEach(this::addRenderableWidget);
    }

    @Override
    public void addLeftList() {
        leftList = new SelectedLayerList(minecraft, this, listWidth, leftListHeight, leftListY, leftListY + leftListHeight);

        addRenderableWidget(leftList);
    }

    @Override
    public void addRightList() {
        rightList = new AvailableLayerList(minecraft, this, listWidth, rightListHeight, rightListY, rightListY + rightListHeight);

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
        leftList.updateSize(
                listWidth,
                leftListHeight,
                leftListY,
                leftListY + leftListHeight);
        leftList.setLeftPos(leftPanelX + LIST_BORDER);
        rightList.updateSize(
                listWidth,
                rightListHeight,
                rightListY,
                rightListY + rightListHeight);
        rightList.setLeftPos(rightPanelX + LIST_BORDER);
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        if (Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getX() > width / 4) {
            Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setX(width / 4));
        }
        if (Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getY() > height / 4) {
            Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setY(height / 4));
        }

        resetOptions();

        super.resize(minecraft, width, height);
    }

    @Override
    public void renderBackground(final PoseStack poseStack) {
        super.renderBackground(poseStack);

        RenderSystem.setShaderTexture(0, new ResourceLocation(Raised.MOD_ID, "textures/gui/sprites/edit/background.png"));
        blit(
                poseStack,
                containerX,
                containerY,
                0,
                0,
                containerWidth,
                containerHeight,
                320,
                240);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        super.render(poseStack, mouseX, mouseY, partialTick);

        GuiComponentInterface.renderScrollingString(
                poseStack,
                font,
                Component.literal(getCurrentGroup().getGroupName()),
                leftPanelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                rightPanelY,
                leftPanelX + panelWidth,
                rightPanelY + WIDGET_HEIGHT,
                -1);

        if (leftList.children().isEmpty()) {
            MutableComponent empty = Component.translatable("options.raised.list.selected_layers.empty");
            drawCenteredString(
                    poseStack,
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    leftPanelX + (panelWidth / 2),
                    leftPanelY + panelHeight - LIST_BORDER - (leftListHeight / 2) - (font.lineHeight / 2),
                    -1);
            NarratorChatListener.INSTANCE.sayNow(empty);
        }

        if (rightList.children().isEmpty()) {
            MutableComponent empty = Component.translatable("options.raised.list.available_layers.empty");
            drawCenteredString(
                    poseStack,
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    rightPanelX + (panelWidth / 2),
                    rightPanelY + panelHeight - LIST_BORDER - (rightListHeight / 2) - (font.lineHeight / 2),
                    -1);
            NarratorChatListener.INSTANCE.sayNow(empty);
        }

        options.forEach(widget -> {
            if (widget.isMouseOver(mouseX, mouseY)) {
                widget.renderToolTip(poseStack, mouseX, mouseY);
            }
        });

        List<FormattedCharSequence> leftTooltip = leftList.getHoveredTooltip(mouseX, mouseY);
        if (leftTooltip != null) {
            renderTooltip(poseStack, leftTooltip, mouseX, mouseY);
        }

        List<FormattedCharSequence> rightTooltip = rightList.getHoveredTooltip(mouseX, mouseY);
        if (rightTooltip != null) {
            renderTooltip(poseStack, rightTooltip, mouseX, mouseY);
        }
    }

    public class SelectedLayerList extends AbstractLayerList<SelectedLayerList.Entry> {

        public SelectedLayerList(Minecraft minecraft, EditScreen parent, int width, int height, int y0, int y1) {
            super(minecraft, parent, width, height, y0, y1);
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
            public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                super.render(poseStack, index, top, left, width, height, mouseX, mouseY, hovering, partialTick);

                int i = mouseX - left;
                if (isMouseOver(mouseX, mouseY)) {
                    RenderSystem.setShaderTexture(0, new ResourceLocation(Raised.MOD_ID, "textures/gui/sprites/transferable_list/unselect" + (i < ENTRY_HEIGHT ? "_highlighted" : "") + ".png"));
                    blit(
                            poseStack,
                            left,
                            top,
                            0,
                            0,
                            ENTRY_HEIGHT,
                            ENTRY_HEIGHT,
                            24,
                            24);
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
                    Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getLayers().remove(this.getLayerName().toString()));
                    resetLeftList();
                    resetRightList();
                    return true;
                }
                return false;
            }

            @Override
            public void setSelected(boolean selected) {
                if (selected) {
                    SelectedLayerList.this.setSelected(this);
                }
            }

        }

    }

    public class AvailableLayerList extends AbstractLayerList<AvailableLayerList.Entry> {

        public AvailableLayerList(Minecraft minecraft, EditScreen parent, int width, int height, int y0, int y1) {
            super(minecraft, parent, width, height, y0, y1);
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
            public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                super.render(poseStack, index, top, left, width, height, mouseX, mouseY, hovering, partialTick);

                int i = mouseX - left;
                if (isMouseOver(mouseX, mouseY)) {
                    RenderSystem.setShaderTexture(0, new ResourceLocation(Raised.MOD_ID, "textures/gui/sprites/transferable_list/select" + (i < ENTRY_HEIGHT ? "_highlighted" : "") + ".png"));
                    blit(
                            poseStack,
                            left,
                            top,
                            0,
                            0,
                            ENTRY_HEIGHT,
                            ENTRY_HEIGHT,
                            24,
                            24);
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
                    Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getLayers().add(this.getLayerName().toString()));
                    resetLeftList();
                    resetRightList();
                    return true;
                }
                return false;
            }

            @Override
            public void setSelected(boolean selected) {
                if (selected) {
                    AvailableLayerList.this.setSelected(this);
                }
            }

        }

    }

}