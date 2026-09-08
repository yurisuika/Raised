package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.GuiComponentInterface;
import dev.yurisuika.raised.client.gui.components.DeferredTooltipButton;
import dev.yurisuika.raised.client.gui.components.SpacedSelectionList;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SelectScreen extends AbstractLayersScreen {

    public AbstractWidget controlClose;
    public AbstractWidget controlEditGroup;
    public AbstractWidget controlRenameGroup;
    public AbstractWidget controlAddGroup;
    public AbstractWidget controlRemoveGroup;
    public GroupList leftList;
    public LayerList rightList;
    public int widgetWidthWide;

    public SelectScreen(Screen parent) {
        super(parent, 2, 1);
    }

    public List<String> listGroups() {
        return Configure.Groups.getGroups().keySet().stream()
                .sorted(Comparator.comparing(String::toString))
                .toList();
    }

    public List<ResourceLocation> listLayers() {
        return LayerRegistry.LAYERS.stream()
                .sorted(Comparator.comparing(ResourceLocation::toString))
                .filter(layerName -> layerName.getNamespace().equals(selectedNamespace))
                .toList();
    }

    @Override
    public void setSizes() {
        super.setSizes();

        widgetWidthWide = (panelWidth - ((PANEL_GAP) + (PANEL_GAP + WIDGET_WIDTH_SQUARE + PANEL_GAP + WIDGET_WIDTH_SQUARE))) / 2;
    }

    @Override
    public void addLeftControls() {
        leftControls = new ArrayList<>();

        controlClose = new DeferredTooltipButton(
                leftPanelX,
                leftPanelY,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                Component.translatable("options.raised.control.close"),
                button -> onClose());
        controlEditGroup = new DeferredTooltipButton(
                leftPanelX,
                leftPanelY + WIDGET_AND_GAP_HEIGHT,
                widgetWidthWide,
                WIDGET_HEIGHT,
                Component.translatable("options.raised.control.edit"),
                button -> minecraft.setScreen(new EditScreen(this)),
                (button, poseStack, mouseX, mouseY) -> renderTooltip(
                        poseStack,
                        font.split(Component.translatable("options.raised.control.edit.tooltip"), 200),
                        mouseX,
                        mouseY));
        controlRenameGroup = new DeferredTooltipButton(
                leftPanelX + widgetWidthWide + PANEL_GAP,
                leftPanelY + WIDGET_AND_GAP_HEIGHT,
                widgetWidthWide,
                WIDGET_HEIGHT,
                Component.translatable("options.raised.control.rename"),
                button -> minecraft.setScreen(new RenameScreen(this)),
                (button, poseStack, mouseX, mouseY) -> renderTooltip(
                        poseStack,
                        font.split(Component.translatable("options.raised.control.rename.tooltip"), 200),
                        mouseX,
                        mouseY));
        controlAddGroup = new DeferredTooltipButton(
                leftPanelX + panelWidth - WIDGET_WIDTH_SQUARE - PANEL_GAP - WIDGET_WIDTH_SQUARE,
                leftPanelY + WIDGET_AND_GAP_HEIGHT,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                Component.translatable("options.raised.control.add"),
                button -> minecraft.setScreen(new AddScreen(this)),
                (button, poseStack, mouseX, mouseY) -> renderTooltip(
                        poseStack,
                        font.split(Component.translatable("options.raised.control.add.tooltip"), 200),
                        mouseX,
                        mouseY));
        controlRemoveGroup = new DeferredTooltipButton(
                leftPanelX + panelWidth - WIDGET_WIDTH_SQUARE,
                leftPanelY + WIDGET_AND_GAP_HEIGHT,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                Component.translatable("options.raised.control.remove"),
                button -> minecraft.setScreen(new RemoveScreen(this)),
                (button, poseStack, mouseX, mouseY) -> renderTooltip(
                        poseStack,
                        font.split(Component.translatable("options.raised.control.remove.tooltip"), 200),
                        mouseX,
                        mouseY));

        leftControls.add(controlClose);
        leftControls.add(controlEditGroup);
        leftControls.add(controlRenameGroup);
        leftControls.add(controlAddGroup);
        leftControls.add(controlRemoveGroup);

        leftControls.forEach(this::addRenderableWidget);
    }

    @Override
    public void addLeftList() {
        leftList = new GroupList(minecraft, this, listWidth, leftListHeight, leftListY, leftListY + leftListHeight);

        addRenderableWidget(leftList);
    }

    @Override
    public void addRightList() {
        rightList = new LayerList(minecraft, this, listWidth, rightListHeight, rightListY, rightListY + rightListHeight);

        addRenderableWidget(rightList);
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
    public void tick() {
        super.tick();

        controlEditGroup.active = !(leftList.getSelected() == null);
        controlRenameGroup.active = !(leftList.getSelected() == null);
        controlRemoveGroup.active = !(leftList.getSelected() == null);
    }

    @Override
    public void renderBackground(final PoseStack poseStack) {
        super.renderBackground(poseStack);

        RenderSystem.setShaderTexture(0, new ResourceLocation(Raised.MOD_ID, "textures/gui/sprites/select/background.png"));
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
                title,
                leftPanelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                rightPanelY,
                leftPanelX + panelWidth,
                rightPanelY + WIDGET_HEIGHT,
                -1);

        if (leftList.children().isEmpty()) {
            MutableComponent empty = Component.translatable("options.raised.list.groups.empty");
            drawCenteredString(
                    poseStack,
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    leftPanelX + (panelWidth / 2),
                    leftPanelY + panelHeight - LIST_BORDER - (leftListHeight / 2) - (font.lineHeight / 2),
                    -1);
            NarratorChatListener.INSTANCE.sayNow(empty);
        }

        leftControls.forEach(widget -> {
            if (widget.isMouseOver(mouseX, mouseY)) {
                ((DeferredTooltipButton) widget).renderDeferredTooltip(poseStack, mouseX, mouseY);
            }
        });

        List<FormattedCharSequence> rightTooltip = rightList.getHoveredTooltip(mouseX, mouseY);
        if (rightTooltip != null) {
            renderTooltip(poseStack, rightTooltip, mouseX, mouseY);
        }
    }

    public class GroupList extends SpacedSelectionList<GroupList.Entry> {

        protected final SelectScreen parent;

        public GroupList(Minecraft minecraft, SelectScreen parent, int width, int height, int y0, int y1) {
            super(minecraft, width, height, y0, y1, ENTRY_HEIGHT, LIST_PADDING_X, LIST_PADDING_Y);
            this.parent = parent;
            setEntries();
        }

        @Override
        public void setEntries() {
            clearEntries();
            listGroups().forEach(groupName -> addEntry(new Entry(groupName)));
        }

        public class Entry extends SpacedSelectionList.Entry<Entry> {

            private final String groupName;

            public Entry(String groupName) {
                this.groupName = groupName;
                setSelected(getCurrentGroup() == null);
            }

            public String getGroupName() {
                return groupName;
            }

            @Override
            public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                GuiComponentInterface.renderScrollingString(
                        poseStack,
                        font,
                        Component.literal(groupName),
                        left + ENTRY_PADDING,
                        top,
                        left + width - ENTRY_PADDING,
                        top + ENTRY_HEIGHT,
                        -1);
            }

            @Override
            public List<? extends GuiEventListener> children() {
                return List.of();
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
                        output.add(NarratedElementType.TITLE, Component.literal(groupName));
                    }

                };

                return List.of(narration);
            }

            @Override
            public boolean mouseClicked(final double mouseX, final double mouseY, final int button) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                setSelected(true);
                return true;
            }

            public void setSelected(boolean focused) {
                if (focused) {
                    parent.setCurrentGroup(this);
                    GroupList.this.setSelected(this);
                }
            }

        }

    }

    public class LayerList extends AbstractLayerList<LayerList.Entry> {

        public LayerList(Minecraft minecraft, SelectScreen parent, int width, int height, int y0, int y1) {
            super(minecraft, parent, width, height, y0, y1);
        }

        @Override
        public void setEntries() {
            clearEntries();
            listLayers().forEach(layerName -> addEntry(new Entry(layerName)));
        }

        public class Entry extends AbstractLayerList.Entry<Entry> {

            public Entry(ResourceLocation layerName) {
                super(layerName);
            }

            @Override
            public String entryText() {
                return Parse.parsePath(layerName.getPath());
            }

            @Override
            public boolean handleClick(final double mouseX, final double mouseY, final int button) {
                return false;
            }

            @Override
            public void setSelected(boolean focused) {
                if (focused) {
                    LayerList.this.setSelected(this);
                }
            }

        }

    }

}