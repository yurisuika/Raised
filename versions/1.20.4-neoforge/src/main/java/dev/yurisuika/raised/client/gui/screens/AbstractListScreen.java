package dev.yurisuika.raised.client.gui.screens;

import net.minecraft.client.gui.screens.Screen;

public abstract class AbstractListScreen extends AbstractScreen {

    public static SelectScreen.GroupList.Entry currentGroup = null;
    public static final int LIST_PADDING_X = 0;
    public static final int LIST_PADDING_Y = 0;
    public static final int LIST_BORDER = 1;
    public static final int ENTRY_GAP = 2;
    public static final int ENTRY_PADDING = 2;
    public static final int ENTRY_HEIGHT = 24;

    public AbstractListScreen(Screen parent, int containerWidth, int containerHeight) {
        super(parent, containerWidth, containerHeight);
    }

    public void setCurrentGroup(SelectScreen.GroupList.Entry entry) {
        currentGroup = entry;
    }

    public SelectScreen.GroupList.Entry getCurrentGroup() {
        return currentGroup;
    }

}