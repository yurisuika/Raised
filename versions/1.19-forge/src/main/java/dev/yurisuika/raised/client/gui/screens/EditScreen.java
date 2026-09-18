package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.components.AdjustableSelectionList;
import dev.yurisuika.raised.client.gui.components.AdjustableWidgetSelectionList;
import dev.yurisuika.raised.client.gui.components.IntRangeSliderButton;
import dev.yurisuika.raised.client.gui.components.ScrollingWidget;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Icon;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.TooltipAccessor;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;

import java.util.*;

public class EditScreen extends AbstractListScreen {

    public static final Set<String> NAMESPACES = new HashSet<>();
    public String selectedNamespace = ResourceLocation.DEFAULT_NAMESPACE;
    public ArrayList<AbstractWidget> leftControls;
    public ArrayList<AbstractWidget> rightControls;
    public SelectedLayerList leftList;
    public AvailableLayerList rightList;
    public ArrayList<AbstractWidget> options;
    public AbstractWidget controlPrevious;
    public AbstractWidget controlNext;
    public AbstractWidget controlReturn;
    public AbstractWidget optionOffsetX;
    public AbstractWidget optionOffsetY;
    public int leftTopRowCount = 1;
    public int leftBottomRowCount = 1;
    public int rightTopRowCount = 1;
    public int rightBottomRowCount = 1;
    public int leftPanelX;
    public int leftPanelY;
    public int rightPanelX;
    public int rightPanelY;
    public int leftListHeight;
    public int rightListHeight;
    public int listWidth;
    public int leftListY;
    public int rightListY;

    public EditScreen(Screen parent) {
        super(parent, 320, 240);
    }

    public List<ResourceLocation> listSelectedLayers() {
        return Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getLayers().keySet().stream()
                .sorted(Comparator.comparing(String::toString))
                .filter(groupedLayerName -> LayerRegistry.LAYERS
                        .contains(ResourceLocation.tryParse(groupedLayerName)))
                .map(ResourceLocation::tryParse)
                .toList();
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
    public void setSizes() {
        super.setSizes();

        panelWidth = (containerWidth - (CONTAINER_GAP + (2 * CONTAINER_PADDING))) / 2;
        leftPanelX = containerX + CONTAINER_PADDING;
        leftPanelY = containerY + CONTAINER_PADDING;
        rightPanelX = leftPanelX + panelWidth + CONTAINER_GAP;
        rightPanelY = containerY + CONTAINER_PADDING;
        leftListHeight = panelHeight - (2 * LIST_BORDER) - ((leftTopRowCount + leftBottomRowCount) * WIDGET_AND_GAP_HEIGHT);
        rightListHeight = panelHeight - (2 * LIST_BORDER) - ((rightTopRowCount + rightBottomRowCount) * WIDGET_AND_GAP_HEIGHT);
        listWidth = panelWidth - (2 * LIST_BORDER) - (2 * PANEL_PADDING);
        leftListY = leftPanelY + (leftTopRowCount * WIDGET_AND_GAP_HEIGHT) + LIST_BORDER;
        rightListY = rightPanelY + (rightTopRowCount * WIDGET_AND_GAP_HEIGHT) + LIST_BORDER;
    }

    @Override
    public void init() {
        LayerRegistry.LAYERS.forEach(layerName -> NAMESPACES.add(layerName.getNamespace()));

        super.init();
    }

    @Override
    public void addContent() {
        addLeftControls();
        addRightControls();
        addLeftList();
        addRightList();
        addOptions();
    }

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

    public void addRightControls() {
        rightControls = new ArrayList<>();

        controlPrevious = new Button(
                rightPanelX,
                rightPanelY,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                Component.translatable("options.raised.control.previous"),
                button -> getPrevious());
        controlNext = new Button(
                rightPanelX + panelWidth - WIDGET_WIDTH_SQUARE,
                rightPanelY,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                Component.translatable("options.raised.control.next"),
                button -> getNext());

        rightControls.add(controlPrevious);
        rightControls.add(controlNext);

        rightControls.forEach(this::addRenderableWidget);
    }

    public void addLeftList() {
        leftList = new SelectedLayerList(minecraft, this, listWidth, leftListHeight, leftListY, leftListY + leftListHeight);

        addRenderableWidget(leftList);
    }

    public void addRightList() {
        rightList = new AvailableLayerList(minecraft, this, listWidth, rightListHeight, rightListY, rightListY + rightListHeight);

        addRenderableWidget(rightList);
    }

    public void addOptions() {
        options = new ArrayList<>();

        optionOffsetX = IntRangeSliderButton.builder(Component.translatable("options.raised.offset.x"), value -> Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setX(value)))
                .range(0, width)
                .initialValue(Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getX())
                .valueText(value -> value == 0 ? CommonComponents.OPTION_OFF : Component.literal(value + "px (" + Math.round(Math.ceil((value / ((float) width)) * 100)) + "%)"))
                .tooltip(value -> font.split(Component.translatable("options.raised.offset.x.tooltip"), 200))
                .size(panelWidth, WIDGET_HEIGHT)
                .pos(leftPanelX, leftPanelY + panelHeight - WIDGET_HEIGHT)
                .build();

        optionOffsetY = IntRangeSliderButton.builder(Component.translatable("options.raised.offset.y"), value -> Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setY(value)))
                .range(0, height)
                .initialValue(Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getY())
                .valueText(value -> value == 0 ? CommonComponents.OPTION_OFF : Component.literal(value + "px (" + Math.round(Math.ceil((value / ((float) height)) * 100)) + "%)"))
                .tooltip(value -> font.split(Component.translatable("options.raised.offset.y.tooltip"), 200))
                .size(panelWidth, WIDGET_HEIGHT)
                .pos(rightPanelX, rightPanelY + panelHeight - WIDGET_HEIGHT)
                .build();

        options.add(optionOffsetX);
        options.add(optionOffsetY);

        options.forEach(this::addRenderableWidget);
    }

    public void resetLeftControls() {
        if (leftControls != null) {
            leftControls.forEach(this::removeWidget);
            leftControls.clear();
            addLeftControls();
        }
    }

    public void resetRightControls() {
        if (rightControls != null) {
            rightControls.forEach(this::removeWidget);
            rightControls.clear();
            addRightControls();
        }
    }

    public void resetLeftList() {
        if (leftList != null) {
            leftList.setScrollAmount(0.0F);
            leftList.setEntries();
        }
    }

    public void resetRightList() {
        if (rightList != null) {
            rightList.setScrollAmount(0.0F);
            rightList.setEntries();
        }
    }

    public void resetOptions() {
        if (options != null) {
            options.forEach(this::removeWidget);
            options.clear();
            addOptions();
        }
    }

    public void getPrevious() {
        List<String> keys = NAMESPACES.stream().toList();
        int index = keys.indexOf(selectedNamespace) - 1;

        if (index < 0) {
            setMod(keys.get(keys.size() - 1));
        } else {
            setMod(keys.get(index));
        }
    }

    public void getNext() {
        List<String> keys = NAMESPACES.stream().toList();
        int index = keys.indexOf(selectedNamespace) + 1;

        if (index >= keys.size()) {
            setMod(keys.get(0));
        } else {
            setMod(keys.get(index));
        }
    }

    public void setMod(String mod) {
        selectedNamespace = mod;

        resetRightList();
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
        if (Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getX() > width) {
            Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setX(width));
        }
        if (Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getOffset().getY() > height) {
            Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getOffset().setY(height));
        }

        resetOptions();

        resetLeftControls();
        resetRightControls();

        super.resize(minecraft, width, height);
    }

    @Override
    public void tick() {
        super.tick();

        controlPrevious.active = NAMESPACES.size() > 1;
        controlNext.active = NAMESPACES.size() > 1;
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

        ScrollingWidget.renderScrolling(
                poseStack,
                font,
                Component.literal(getCurrentGroup().getGroupName()),
                leftPanelX + (panelWidth / 2),
                leftPanelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                rightPanelY,
                leftPanelX + panelWidth,
                rightPanelY + WIDGET_HEIGHT,
                -1);

        ScrollingWidget.renderScrolling(
                poseStack,
                font,
                Component.literal(Parse.parseNamespace(selectedNamespace)),
                rightPanelX + (panelWidth / 2),
                rightPanelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                rightPanelY,
                rightPanelX + panelWidth - WIDGET_WIDTH_SQUARE - PANEL_GAP,
                rightPanelY + WIDGET_HEIGHT,
                -1);

        if (leftList.children().isEmpty()) {
            MutableComponent empty = Component.translatable("options.raised.list.selected_layers.empty");
            drawCenteredString(
                    poseStack,
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    leftPanelX + (panelWidth / 2),
                    leftListY + (leftListHeight / 2) - (font.lineHeight / 2),
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
                    rightListY + (rightListHeight / 2) - (font.lineHeight / 2),
                    -1);
            NarratorChatListener.INSTANCE.sayNow(empty);
        }

        options.forEach(widget -> {
            if (widget.isMouseOver(mouseX, mouseY)) {
                widget.renderToolTip(poseStack, mouseX, mouseY);
            }
        });
        List<FormattedCharSequence> listTooltip = leftList.getHoveredTooltip(mouseX, mouseY);
        if (listTooltip != null) {
            renderTooltip(poseStack, listTooltip, mouseX, mouseY);
        }
    }

    public class SelectedLayerList extends AdjustableWidgetSelectionList<SelectedLayerList.Entry> {

        protected final EditScreen parent;

        public SelectedLayerList(Minecraft minecraft, EditScreen parent, int width, int height, int y0, int y1) {
            super(minecraft, width, height, y0, y1, ENTRY_HEIGHT, LIST_PADDING_X, LIST_PADDING_Y);
            this.parent = parent;
            setEntries();
        }

        @Override
        public void setEntries() {
            clearEntries();
            listSelectedLayers().forEach(layerName -> addEntry(new Entry(layerName)));
        }

        public Entry getHoveredEntry() {
            return getHovered();
        }

        public List<FormattedCharSequence> getHoveredTooltip(int mouseX, int mouseY) {
            Entry entry = getEntryAtPosition(mouseX, mouseY);

            return entry == null ? null : entry.getTooltip(mouseX, mouseY);
        }

        public class Entry extends AdjustableWidgetSelectionList.Entry<Entry> {

            protected final ResourceLocation layerName;
            protected final AbstractWidget optionAnchor;

            public Entry(ResourceLocation layerName) {
                this.layerName = layerName;

                optionAnchor = CycleButton.builder(Layer.Anchor::glyph)
                        .withInitialValue(Config.getOptions().getGroups().get(getCurrentGroup().getGroupName()).getLayers().get(layerName.toString()).getAnchor())
                        .withValues(Layer.Anchor.values())
                        .displayOnlyValue()
                        .withTooltip(value -> Minecraft.getInstance().font.split(Component.translatable("options.raised.anchor.tooltip", Component.translatable("options.raised.anchor." + value.getSerializedName())), 200))
                        .create(0,
                                0,
                                WIDGET_WIDTH_SQUARE,
                                WIDGET_HEIGHT,
                                Component.translatable("options.raised.anchor"),
                                (button, value) -> Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getLayers().get(layerName.toString()).setAnchor(value)));
            }

            public ResourceLocation getLayerName() {
                return layerName;
            }

            @Override
            public int getWidth() {
                return width - (getMaxScroll() > 0 ? 6 : 0);
            }

            @Override
            public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                RenderSystem.setShaderTexture(0, Icon.getLayerIcon(layerName));
                blit(
                        poseStack,
                        left + ENTRY_PADDING,
                        top + ENTRY_PADDING,
                        0,
                        0,
                        WIDGET_WIDTH_SQUARE,
                        WIDGET_HEIGHT,
                        20,
                        20);

                ScrollingWidget.renderScrolling(
                        poseStack,
                        font,
                        Component.literal(entryText()),
                        left + (width / 2),
                        left + ENTRY_PADDING + WIDGET_WIDTH_SQUARE + ENTRY_GAP,
                        top,
                        left + width - ENTRY_PADDING - WIDGET_WIDTH_SQUARE - ENTRY_GAP,
                        top + height,
                        -1);

                int i = mouseX - left;
                if (hovering) {
                    RenderSystem.setShaderTexture(0, new ResourceLocation(Raised.MOD_ID, "textures/gui/sprites/layer_list/unselect" + (i < ENTRY_HEIGHT ? "_highlighted" : "") + ".png"));
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

                optionAnchor.x = left + width - ENTRY_PADDING - WIDGET_WIDTH_SQUARE;
                optionAnchor.y = top + ENTRY_PADDING;
                optionAnchor.render(poseStack, mouseX, mouseY, partialTick);
            }

            public String entryText() {
                if (this == SelectedLayerList.this.getHoveredEntry()) {
                    return layerName.toString();
                } else {
                    return Parse.parsePath(layerName.getPath());
                }
            }

            @Override
            public List<? extends GuiEventListener> children() {
                return List.of(optionAnchor);
            }

            @Override
            public List<? extends NarratableEntry> narratables() {
                NarratableEntry narration = new NarratableEntry() {

                    @Override
                    public NarrationPriority narrationPriority() {
                        return NarrationPriority.NONE;
                    }

                    @Override
                    public void updateNarration(NarrationElementOutput output) {
                        output.add(NarratedElementType.TITLE, Component.literal(Parse.parsePath(layerName.getPath())));
                    }

                };

                return List.of(optionAnchor, narration);
            }

            public boolean handleTransfer(final double mouseX, final double mouseY, final int button) {
                int relX = (int) mouseX - getEntryX(this);
                int relY = (int) mouseY - getEntryY(this);
                if (relX >= 0 && relX < ENTRY_HEIGHT && relY >= 0 && relY < ENTRY_HEIGHT) {
                    Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getLayers().remove(getLayerName().toString()));
                    resetLeftList();
                    resetRightList();
                    return true;
                }
                return false;
            }

            public boolean handleWidget(final double mouseX, final double mouseY, final int button) {
                if (optionAnchor.mouseClicked(mouseX, mouseY, button)) {
                    setFocused(optionAnchor);
                    return true;
                }
                return false;
            }

            @Override
            public boolean mouseClicked(final double mouseX, final double mouseY, final int button) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                setSelected(true);

                if (handleTransfer(mouseX, mouseY, button)) {
                    return true;
                }

                if (handleWidget(mouseX, mouseY, button)) {
                    return true;
                }

                return true;
            }

            @Override
            public boolean mouseReleased(final double mouseX, final double mouseY, final int button) {
                return optionAnchor.mouseReleased(mouseX, mouseY, button);
            }

            public void setSelected(boolean selected) {
                if (selected) {
                    SelectedLayerList.this.setSelected(this);
                }
            }

            public List<FormattedCharSequence> getTooltip(int mouseX, int mouseY) {
                if (optionAnchor.isMouseOver(mouseX, mouseY)) {
                    return ((TooltipAccessor) optionAnchor).getTooltip();
                }

                return null;
            }

        }

    }

    public class AvailableLayerList extends AdjustableSelectionList<AvailableLayerList.Entry> {

        protected final EditScreen parent;

        public AvailableLayerList(Minecraft minecraft, EditScreen parent, int width, int height, int y0, int y1) {
            super(minecraft, width, height, y0, y1, ENTRY_HEIGHT, LIST_PADDING_X, LIST_PADDING_Y);
            this.parent = parent;
            setEntries();
        }

        @Override
        public void setEntries() {
            clearEntries();
            listAvailableLayers().forEach(layerName -> addEntry(new Entry(layerName)));
        }

        public Entry getHoveredEntry() {
            return getHovered();
        }

        public class Entry extends AdjustableSelectionList.Entry<Entry> {

            protected final ResourceLocation layerName;

            public Entry(ResourceLocation layerName) {
                this.layerName = layerName;
            }

            public ResourceLocation getLayerName() {
                return layerName;
            }

            @Override
            public int getWidth() {
                return width - (getMaxScroll() > 0 ? 6 : 0);
            }

            @Override
            public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                RenderSystem.setShaderTexture(0, Icon.getLayerIcon(layerName));
                blit(
                        poseStack,
                        left + ENTRY_PADDING,
                        top + ENTRY_PADDING,
                        0,
                        0,
                        WIDGET_WIDTH_SQUARE,
                        WIDGET_HEIGHT,
                        20,
                        20);

                ScrollingWidget.renderScrolling(
                        poseStack,
                        font,
                        Component.literal(entryText()),
                        left + (width / 2),
                        left + ENTRY_PADDING + WIDGET_WIDTH_SQUARE + ENTRY_GAP,
                        top,
                        left + width - ENTRY_PADDING,
                        top + height,
                        -1);

                int i = mouseX - left;
                if (hovering) {
                    RenderSystem.setShaderTexture(0, new ResourceLocation(Raised.MOD_ID, "textures/gui/sprites/layer_list/select" + (i < ENTRY_HEIGHT ? "_highlighted" : "") + ".png"));
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

            public String entryText() {
                if (this == AvailableLayerList.this.getHoveredEntry()) {
                    return layerName.toString();
                } else {
                    return Parse.parsePath(layerName.getPath());
                }
            }

            @Override
            public Component getNarration() {
                return Component.literal(Parse.parsePath(layerName.getPath()));
            }

            public boolean handleTransfer(final double mouseX, final double mouseY, final int button) {
                int relX = (int) mouseX - getEntryX(this);
                int relY = (int) mouseY - getEntryY(this);
                if (relX >= 0 && relX < ENTRY_HEIGHT && relY >= 0 && relY < ENTRY_HEIGHT) {
                    Config.update(o -> o.getGroups().get(getCurrentGroup().getGroupName()).getLayers().put(layerName.toString(), LayerRegistry.findDefaultLayer(layerName)));
                    resetLeftList();
                    resetRightList();
                    return true;
                }
                return false;
            }

            @Override
            public boolean mouseClicked(final double mouseX, final double mouseY, final int button) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                setSelected(true);

                if (handleTransfer(mouseX, mouseY, button)) {
                    return true;
                }

                return true;
            }

            public void setSelected(boolean selected) {
                if (selected) {
                    AvailableLayerList.this.setSelected(this);
                }
            }

        }

    }

}