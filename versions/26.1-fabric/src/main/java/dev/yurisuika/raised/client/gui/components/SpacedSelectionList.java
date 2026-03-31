package dev.yurisuika.raised.client.gui.components;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.ObjectSelectionList;

public abstract class SpacedSelectionList<E extends SpacedSelectionList.Entry<E>> extends ObjectSelectionList<E> {

    public int padding;

    public SpacedSelectionList(Minecraft minecraft, int width, int height, int y, int itemHeight, int padding) {
        super(minecraft, width, height, y, itemHeight);
        this.padding = padding;
    }

    @Override
    public int getFirstEntryY() {
        return getY() + padding;
    }

    @Override
    public int getNextY() {
        int i = (getY() + padding) - (int) scrollAmount();

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

        return i + (padding * 2);
    }

    @Override
    public void extractWidgetRenderState(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY, float partialTick) {
        hovered = isMouseOver(mouseX, mouseY) ? getEntryAtPosition(mouseX, mouseY) : null;
        enableScissor(guiGraphicsExtractor);
        extractListItems(guiGraphicsExtractor, mouseX, mouseY, partialTick);
        guiGraphicsExtractor.disableScissor();
        extractScrollbar(guiGraphicsExtractor, mouseX, mouseY);
    }

    @Override
    public void scrollToEntry(E entry) {
        int i = entry.getY() - getY() - padding;
        if (i < 0) {
            setScrollAmount(scrollAmount() + (double) i);
        }

        int j = getBottom() - entry.getY() - entry.getHeight() - padding;
        if (j < 0) {
            setScrollAmount(scrollAmount() + (double) -j);
        }
    }

    @Override
    public int scrollBarX() {
        return getRowRight() + padding - 6;
    }

    public void extractListItemsRenderState(GuiGraphicsExtractor guiGraphicsExtractor, int mouseX, int mouseY, float partialTick) {
        for (E entry : children()) {
            if (entry.getY() + entry.getHeight() >= getY() && entry.getY() <= getBottom()) {
                extractItem(guiGraphicsExtractor, mouseX, mouseY, partialTick, entry);
            }
        }
    }

    @Override
    public int getRowLeft() {
        return getX() + padding;
    }

    @Override
    public int getRowWidth() {
        return width - (padding * 2);
    }

    public abstract static class Entry<E extends SpacedSelectionList.Entry<E>> extends ObjectSelectionList.Entry<E> {

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