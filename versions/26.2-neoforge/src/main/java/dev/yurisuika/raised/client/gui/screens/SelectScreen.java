package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.components.AdjustableSelectionList;
import dev.yurisuika.raised.client.gui.screens.popup.AddScreen;
import dev.yurisuika.raised.client.gui.screens.popup.RemoveScreen;
import dev.yurisuika.raised.client.gui.screens.popup.RenameScreen;
import dev.yurisuika.raised.client.gui.screens.popup.SettingsScreen;
import dev.yurisuika.raised.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvents;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SelectScreen extends AbstractListScreen {

    public ArrayList<AbstractWidget> controls;
    public GroupList list;
    public ArrayList<AbstractWidget> options;
    public AbstractWidget controlClose;
    public AbstractWidget controlSettings;
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
                .toList();
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

        controlClose = Button.builder(Component.translatable("options.raised.control.close"), button -> onClose())
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(panelX, panelY)
                .build();
        controlSettings = Button.builder(Component.translatable("options.raised.control.settings"), button -> minecraft.gui.setScreen(new SettingsScreen(this)))
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(panelX + panelWidth - WIDGET_WIDTH_SQUARE, panelY)
                .tooltip(Tooltip.create(Component.translatable("options.raised.control.settings.tooltip")))
                .build();

        controls.add(controlClose);
        controls.add(controlSettings);

        controls.forEach(this::addRenderableWidget);
    }

    public void addLists() {
        list = new GroupList(minecraft, this, listWidth, listHeight, listY);

        addRenderableWidget(list);
    }

    public void addOptions() {
        options = new ArrayList<>();

        optionEditGroup = Button.builder(Component.translatable("options.raised.control.edit"), button -> minecraft.gui.setScreen(new EditScreen(this)))
                .size(widgetWidthWide, WIDGET_HEIGHT)
                .pos(panelX, panelY + panelHeight - WIDGET_HEIGHT)
                .tooltip(Tooltip.create(Component.translatable("options.raised.control.edit.tooltip")))
                .build();
        optionRenameGroup = Button.builder(Component.translatable("options.raised.control.rename"), button -> minecraft.gui.setScreen(new RenameScreen(this)))
                .size(widgetWidthWide, WIDGET_HEIGHT)
                .pos(panelX + widgetWidthWide + PANEL_GAP, panelY + panelHeight - WIDGET_HEIGHT)
                .tooltip(Tooltip.create(Component.translatable("options.raised.control.rename.tooltip")))
                .build();
        optionAddGroup = Button.builder(Component.translatable("options.raised.control.add"), button -> minecraft.gui.setScreen(new AddScreen(this)))
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(panelX + panelWidth - WIDGET_WIDTH_SQUARE - PANEL_GAP - WIDGET_WIDTH_SQUARE, panelY + panelHeight - WIDGET_HEIGHT)
                .tooltip(Tooltip.create(Component.translatable("options.raised.control.add.tooltip")))
                .build();
        optionRemoveGroup = Button.builder(Component.translatable("options.raised.control.remove"), button -> minecraft.gui.setScreen(new RemoveScreen(this)))
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(panelX + panelWidth - WIDGET_WIDTH_SQUARE, panelY + panelHeight - WIDGET_HEIGHT)
                .tooltip(Tooltip.create(Component.translatable("options.raised.control.remove.tooltip")))
                .build();

        options.add(optionEditGroup);
        options.add(optionRenameGroup);
        options.add(optionAddGroup);
        options.add(optionRemoveGroup);

        options.forEach(this::addRenderableWidget);
    }

    public void resetControls() {
        if (controls != null) {
            controls.forEach(this::removeWidget);
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
            options.forEach(this::removeWidget);
            options.clear();
            addOptions();
        }
    }

    @Override
    public void repositionElements() {
        list.updateSizeAndPosition(
                listWidth,
                listHeight,
                panelX + LIST_BORDER,
                listY);
    }

    @Override
    public void tick() {
        super.tick();

        optionEditGroup.active = !(list.getSelected() == null);
        optionRenameGroup.active = !(list.getSelected() == null);
        optionRemoveGroup.active = !(list.getSelected() == null);
    }

    @Override
    public void extractBackground(final GuiGraphicsExtractor guiGraphics, final int mouseX, final int mouseY, final float a) {
        super.extractBackground(guiGraphics, mouseX, mouseY, a);

        guiGraphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                Identifier.fromNamespaceAndPath(Raised.MOD_ID, "select/background"),
                containerX,
                containerY,
                containerWidth,
                containerHeight);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.textRenderer().acceptScrolling(
                title,
                panelX + (panelWidth / 2),
                panelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                panelX + panelWidth - WIDGET_WIDTH_SQUARE - PANEL_GAP,
                panelY,
                panelY + WIDGET_HEIGHT);

        if (list.children().isEmpty()) {
            MutableComponent empty = Component.translatable("options.raised.list.groups.empty");
            guiGraphics.centeredText(
                    font,
                    empty.withStyle(ChatFormatting.GRAY),
                    panelX + (panelWidth / 2),
                    listY + (listHeight / 2) - (font.lineHeight / 2),
                    -1);
            minecraft.getNarrator().saySystemNow(empty);
        }
    }

    public class GroupList extends AdjustableSelectionList<GroupList.Entry> {

        protected final SelectScreen parent;

        public GroupList(Minecraft minecraft, SelectScreen parent, int width, int height, int y) {
            super(minecraft, width, height, y, ENTRY_HEIGHT, LIST_PADDING_X, LIST_PADDING_Y);
            this.parent = parent;
            setEntries();
        }

        @Override
        public void setEntries() {
            clearEntries();
            listGroups().forEach(groupName -> addEntry(new GroupList.Entry(groupName)));
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
                return super.getWidth() - (GroupList.this.scrollable() ? GroupList.this.scrollbarWidth() : 0);
            }

            @Override
            public void extractContent(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, boolean hovered, float partialTick) {
                guiGraphics.textRenderer().acceptScrolling(
                        Component.literal(groupName),
                        getX() + (getWidth() / 2),
                        getX() + ENTRY_PADDING,
                        getX() + getWidth() - ENTRY_PADDING,
                        getY(),
                        getY() + getContentHeight());
            }

            @Override
            public Component getNarration() {
                return Component.literal(groupName);
            }

            @Override
            public boolean mouseClicked(MouseButtonEvent mouseButtonEvent, boolean doubleClick) {
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