package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.components.AdjustableSelectionList;
import dev.yurisuika.raised.client.gui.components.DeferredTooltipButton;
import dev.yurisuika.raised.client.gui.components.ScrollingWidget;
import dev.yurisuika.raised.client.gui.screens.popup.AddScreen;
import dev.yurisuika.raised.client.gui.screens.popup.RemoveScreen;
import dev.yurisuika.raised.client.gui.screens.popup.RenameScreen;
import dev.yurisuika.raised.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SelectScreen extends AbstractListScreen {

    public ArrayList<AbstractWidget> controls;
    public GroupList list;
    public ArrayList<AbstractWidget> options;
    public AbstractWidget controlClose;
    public AbstractWidget optionEditGroup;
    public AbstractWidget optionRenameGroup;
    public AbstractWidget optionAddGroup;
    public AbstractWidget optionRemoveGroup;
    public int topRowCount = 1;
    public int bottomRowCount = 1;
    public int panelX;
    public int panelY;
    public int listHeight;
    public int listWidth;
    public int listY;
    public int widgetWidthWide;

    public SelectScreen(Screen parent) {
        super(parent, 164, 240);
    }

    public List<String> listGroups() {
        return Config.getOptions().getGroups().keySet().stream()
                .sorted(Comparator.comparing(String::toString))
                .collect(Collectors.toList());
    }

    @Override
    public void setSizes() {
        super.setSizes();

        panelWidth = (containerWidth - (2 * CONTAINER_PADDING));
        panelX = containerX + CONTAINER_PADDING;
        panelY = containerY + CONTAINER_PADDING;
        listHeight = panelHeight - (2 * LIST_BORDER) - ((topRowCount + bottomRowCount) * WIDGET_AND_GAP_HEIGHT);
        listWidth = panelWidth - (2 * LIST_BORDER) - (2 * PANEL_PADDING);
        listY = panelY + (topRowCount * WIDGET_AND_GAP_HEIGHT) + LIST_BORDER;

        widgetWidthWide = (panelWidth - ((PANEL_GAP) + (PANEL_GAP + WIDGET_WIDTH_SQUARE + PANEL_GAP + WIDGET_WIDTH_SQUARE))) / 2;
    }

    @Override
    public void addContent() {
        addControls();
        addLists();
        addOptions();
    }

    public void addControls() {
        controls = new ArrayList<>();

        controlClose = new DeferredTooltipButton(
                panelX,
                panelY,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.control.close"),
                button -> onClose());

        controls.add(controlClose);

        controls.forEach(this::addButton);
    }

    public void addLists() {
        list = new GroupList(minecraft, this, listWidth, listHeight, listY, listY + listHeight);

        addWidget(list);
    }

    public void addOptions() {
        options = new ArrayList<>();

        optionEditGroup = new DeferredTooltipButton(
                panelX,
                panelY + panelHeight - WIDGET_HEIGHT,
                widgetWidthWide,
                WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.control.edit"),
                button -> minecraft.setScreen(new EditScreen(this)),
                (button, poseStack, mouseX, mouseY) -> renderTooltip(
                        poseStack,
                        font.split(new TranslatableComponent("options.raised.control.edit.tooltip"), 200),
                        mouseX,
                        mouseY));
        optionRenameGroup = new DeferredTooltipButton(
                panelX + widgetWidthWide + PANEL_GAP,
                panelY + panelHeight - WIDGET_HEIGHT,
                widgetWidthWide,
                WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.control.rename"),
                button -> minecraft.setScreen(new RenameScreen(this)),
                (button, poseStack, mouseX, mouseY) -> renderTooltip(
                        poseStack,
                        font.split(new TranslatableComponent("options.raised.control.rename.tooltip"), 200),
                        mouseX,
                        mouseY));
        optionAddGroup = new DeferredTooltipButton(
                panelX + panelWidth - WIDGET_WIDTH_SQUARE - PANEL_GAP - WIDGET_WIDTH_SQUARE,
                panelY + panelHeight - WIDGET_HEIGHT,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.control.add"),
                button -> minecraft.setScreen(new AddScreen(this)),
                (button, poseStack, mouseX, mouseY) -> renderTooltip(
                        poseStack,
                        font.split(new TranslatableComponent("options.raised.control.add.tooltip"), 200),
                        mouseX,
                        mouseY));
        optionRemoveGroup = new DeferredTooltipButton(
                panelX + panelWidth - WIDGET_WIDTH_SQUARE,
                panelY + panelHeight - WIDGET_HEIGHT,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.control.remove"),
                button -> minecraft.setScreen(new RemoveScreen(this)),
                (button, poseStack, mouseX, mouseY) -> renderTooltip(
                        poseStack,
                        font.split(new TranslatableComponent("options.raised.control.remove.tooltip"), 200),
                        mouseX,
                        mouseY));

        options.add(optionEditGroup);
        options.add(optionRenameGroup);
        options.add(optionAddGroup);
        options.add(optionRemoveGroup);

        options.forEach(this::addButton);
    }

    public void resetControls() {
        if (controls != null) {
            controls.forEach(widget -> {
                buttons.remove(widget);
                children.remove(widget);
            });
            controls.clear();
            addControls();
        }
    }

    public void resetLists() {
        if (list != null) {
            list.setScrollAmount(0.0F);
            list.setEntries();
        }
    }

    public void resetOptions() {
        if (options != null) {
            options.forEach(widget -> {
                buttons.remove(widget);
                children.remove(widget);
            });
            options.clear();
            addOptions();
        }
    }

    @Override
    public void repositionElements() {
        list.updateSize(
                listWidth,
                listHeight,
                listY,
                listY + listHeight);
        list.setLeftPos(panelX + LIST_BORDER);
    }

    @Override
    public void tick() {
        super.tick();

        optionEditGroup.active = !(list.getSelected() == null);
        optionRenameGroup.active = !(list.getSelected() == null);
        optionRemoveGroup.active = !(list.getSelected() == null);
    }

    @Override
    public void renderBackground(PoseStack poseStack) {
        super.renderBackground(poseStack);

        Minecraft.getInstance().getTextureManager().bind(new ResourceLocation(Raised.MOD_ID, "textures/gui/sprites/select/background.png"));
        blit(
                poseStack,
                containerX,
                containerY,
                0,
                0,
                containerWidth,
                containerHeight,
                164,
                240);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        super.render(poseStack, mouseX, mouseY, partialTick);
        list.render(poseStack, mouseX, mouseY, partialTick);

        ScrollingWidget.renderScrolling(
                poseStack,
                font,
                title,
                panelX + (panelWidth / 2),
                panelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                panelY,
                panelX + panelWidth,
                panelY + WIDGET_HEIGHT,
                -1);

        if (list.children().isEmpty()) {
            MutableComponent empty = new TranslatableComponent("options.raised.list.groups.empty");
            drawCenteredString(
                    poseStack,
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    panelX + (panelWidth / 2),
                    listY + (listHeight / 2) - (font.lineHeight / 2),
                    -1);
            NarratorChatListener.INSTANCE.sayNow(empty.getString());
        }

        options.forEach(widget -> {
            if (widget.isMouseOver(mouseX, mouseY)) {
                ((DeferredTooltipButton) widget).renderDeferredTooltip(poseStack, mouseX, mouseY);
            }
        });
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

            protected final String groupName;

            public Entry(String groupName) {
                this.groupName = groupName;
            }

            public String getGroupName() {
                return groupName;
            }

            @Override
            public int getWidth() {
                return width - (getMaxScroll() > 0 ? 6 : 0);
            }

            @Override
            public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                ScrollingWidget.renderScrolling(
                        poseStack,
                        font,
                        new TextComponent(groupName),
                        left + (width / 2),
                        left + ENTRY_PADDING,
                        top,
                        left + width - ENTRY_PADDING,
                        top + height,
                        -1);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
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

}