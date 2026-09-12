package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.components.AdjustableSelectionList;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.mixin.minecraft.client.gui.components.AbstractWidgetInvoker;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
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
        return Config.getOptions().getGroups().keySet().stream()
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

        controlClose = Button.builder(Component.translatable("options.raised.control.close"), button -> onClose())
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(leftPanelX, leftPanelY)
                .build();
        controlEditGroup = Button.builder(Component.translatable("options.raised.control.edit"), button -> minecraft.setScreen(new EditScreen(this)))
                .size(widgetWidthWide, WIDGET_HEIGHT)
                .pos(leftPanelX, leftPanelY + WIDGET_AND_GAP_HEIGHT)
                .tooltip(Tooltip.create(Component.translatable("options.raised.control.edit.tooltip")))
                .build();
        controlRenameGroup = Button.builder(Component.translatable("options.raised.control.rename"), button -> minecraft.setScreen(new RenameScreen(this)))
                .size(widgetWidthWide, WIDGET_HEIGHT)
                .pos(leftPanelX + widgetWidthWide + PANEL_GAP, leftPanelY + WIDGET_AND_GAP_HEIGHT)
                .tooltip(Tooltip.create(Component.translatable("options.raised.control.rename.tooltip")))
                .build();
        controlAddGroup = Button.builder(Component.translatable("options.raised.control.add"), button -> minecraft.setScreen(new AddScreen(this)))
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(leftPanelX + panelWidth - WIDGET_WIDTH_SQUARE - PANEL_GAP - WIDGET_WIDTH_SQUARE, leftPanelY + WIDGET_AND_GAP_HEIGHT)
                .tooltip(Tooltip.create(Component.translatable("options.raised.control.add.tooltip")))
                .build();
        controlRemoveGroup = Button.builder(Component.translatable("options.raised.control.remove"), button -> minecraft.setScreen(new RemoveScreen(this)))
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(leftPanelX + panelWidth - WIDGET_WIDTH_SQUARE, leftPanelY + WIDGET_AND_GAP_HEIGHT)
                .tooltip(Tooltip.create(Component.translatable("options.raised.control.remove.tooltip")))
                .build();

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
    public void renderBackground(final GuiGraphics guiGraphics) {
        super.renderBackground(guiGraphics);

        guiGraphics.blit(
                new ResourceLocation(Raised.MOD_ID, "textures/gui/sprites/select/background.png"),
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
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        AbstractWidgetInvoker.invokeRenderScrollingString(
                guiGraphics,
                font,
                title,
                leftPanelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                rightPanelY,
                leftPanelX + panelWidth,
                rightPanelY + WIDGET_HEIGHT,
                -1);

        if (leftList.children().isEmpty()) {
            MutableComponent empty = Component.translatable("options.raised.list.groups.empty");
            guiGraphics.drawCenteredString(
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    leftPanelX + (panelWidth / 2),
                    leftPanelY + panelHeight - LIST_BORDER - (leftListHeight / 2) - (font.lineHeight / 2),
                    -1);
            minecraft.getNarrator().sayNow(empty);
        }
    }

    public class GroupList extends AdjustableSelectionList<GroupList.Entry> {

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

        public class Entry extends AdjustableSelectionList.Entry<Entry> {

            private final String groupName;

            public Entry(String groupName) {
                this.groupName = groupName;
            }

            public String getGroupName() {
                return groupName;
            }

            @Override
            public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                AbstractWidgetInvoker.invokeRenderScrollingString(
                        guiGraphics,
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

            public void setSelected(boolean selected) {
                if (selected) {
                    parent.setCurrentGroup(this);
                    GroupList.this.setSelected(this);
                }
            }

        }

    }

    public class LayerList extends AbstractLayerList<LayerList.Entry> {

        public LayerList(Minecraft minecraft, SelectScreen parent, int width, int height, int y0, int y1) {
            super(minecraft, parent, width, height, y0, y1);
            setEntries();
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
            public void setSelected(boolean selected) {
                if (selected) {
                    LayerList.this.setSelected(this);
                }
            }

        }

    }

}