package dev.yurisuika.raised.client.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;

public abstract class SpacedSelectionList<E extends SpacedSelectionList.Entry<E>> extends ContainerObjectSelectionList<E> {

    public int paddingX;
    public int paddingY;
    public int entryWidth;

    public SpacedSelectionList(Minecraft minecraft, int width, int height, int y, int itemHeight, int paddingX, int paddingY) {
        super(minecraft, width, height, y, itemHeight);
        this.paddingX = paddingX;
        this.paddingY = paddingY;
        this.entryWidth = width - (2 * paddingX);
    }

    public abstract void setEntries();

    @Override
    public boolean entriesCanBeSelected() {
        return true;
    }

    @Override
    public int getFirstEntryY() {
        return getY() + paddingY;
    }

    @Override
    public int getNextY() {
        int i = (getY() + paddingY) - (int) scrollAmount();

        for (E entry : children()) {
            i += entry.getHeight();
        }

        return i;
    }

    @Override
    public int contentHeight() {
        int i = 0;

        for (E entry : children()) {
            i += entry.getHeight();
        }

        return i + (paddingY * 2);
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        hovered = isMouseOver(mouseX, mouseY) ? getEntryAtPosition(mouseX, mouseY) : null;
        enableScissor(guiGraphics);
        renderListItems(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.disableScissor();
        renderScrollbar(guiGraphics, mouseX, mouseY);
    }

    @Override
    public void scrollToEntry(E entry) {
        int i = entry.getY() - getY() - paddingY;
        if (i < 0) {
            setScrollAmount(scrollAmount() + (double) i);
        }

        int j = getBottom() - entry.getY() - entry.getHeight() - paddingY;
        if (j < 0) {
            setScrollAmount(scrollAmount() + (double) -j);
        }
    }

    @Override
    public int scrollBarX() {
        return getRowRight() + paddingX - 6;
    }

    @Override
    public void renderListItems(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        for (E entry : children()) {
            if (entry.getY() + entry.getHeight() >= getY() && entry.getY() <= getBottom()) {
                renderItem(guiGraphics, mouseX, mouseY, partialTick, entry);
            }
        }
    }

    @Override
    public int getRowLeft() {
        return getX() + paddingX;
    }

    @Override
    public int getRowWidth() {
        return width - (paddingX * 2);
    }

    public abstract static class Entry<E extends Entry<E>> extends ContainerObjectSelectionList.Entry<E> {

        @Override
        public int getContentX() {
            return getX();
        }

        @Override
        public int getContentY() {
            return getY();
        }

        @Override
        public int getContentHeight() {
            return getHeight();
        }

        @Override
        public int getContentWidth() {
            return getWidth();
        }

    }

}