package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

import java.util.Objects;

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
    public boolean isSelectedItem(int index) {
        return Objects.equals(getSelected(), children().get(index));
    }

    @Override
    public E getEntryAtPosition(double mouseX, double mouseY) {
        int i = getRowWidth() / 2;
        int j = getX() + width / 2;
        int m = Mth.floor(mouseY - (double) getY()) + (int) getScrollAmount() - paddingY;
        int n = m / itemHeight;
        return mouseX >= (double) (j - i) && mouseX <= (double) (j + i) && n >= 0 && m >= 0 && n < getItemCount() ? children().get(n) : null;
    }

    @Override
    public int getMaxScroll() {
        return Math.max(0, getMaxPosition() - height);
    }

    @Override
    public int getMaxPosition() {
        return paddingY + (getItemCount() * itemHeight) + paddingY;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        hovered = isMouseOver(mouseX, mouseY) ? getEntryAtPosition(mouseX, mouseY) : null;
        enableScissor(guiGraphics);
        renderListItems(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.disableScissor();
        if (scrollbarVisible()) {
            int i = getScrollbarPosition();
            int j = (int) ((float) (height * height) / (float) getMaxPosition());
            j = Mth.clamp(j, 32, height - 8);
            int k = (int) getScrollAmount() * (height - j) / getMaxScroll() + getY();
            if (k < getY()) {
                k = getY();
            }

            RenderSystem.enableBlend();
            guiGraphics.blitSprite(new ResourceLocation("widget/scroller_background"), i, getY(), 6, getHeight());
            guiGraphics.blitSprite(new ResourceLocation("widget/scroller"), i, k, 6, j);
            RenderSystem.disableBlend();
        }
    }

    @Override
    public void ensureVisible(E entry) {
        int i = getRowTop(children().indexOf(entry));
        int j = i - getY() - paddingY - itemHeight;
        if (j < 0) {
            setScrollAmount(getScrollAmount() + (double) j);
        }

        int k = getBottom() - i - itemHeight - itemHeight;
        if (k < 0) {
            setScrollAmount(getScrollAmount() + (double) -k);
        }
    }

    @Override
    public int getScrollbarPosition() {
        return getRowRight() + paddingX;
    }

    @Override
    public void renderListItems(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        for (int m = 0; m < getItemCount(); ++m) {
            int n = getRowTop(m);
            int o = getRowBottom(m);
            if (o >= getY() && n <= getBottom()) {
                renderItem(guiGraphics, mouseX, mouseY, partialTick, m, getRowLeft(), n, getRowWidth(), itemHeight);
            }
        }
    }

    @Override
    public void renderSelection(GuiGraphics guiGraphics, int top, int width, int height, int outerColor, int innerColor) {
        int i = getRowLeft();
        int j = getRowRight();
        guiGraphics.fill(i, top, j, top + itemHeight, outerColor);
        guiGraphics.fill(i + 1, top + 1, j - 1, top + itemHeight - 1, innerColor);
    }

    @Override
    public int getRowLeft() {
        return getX() + paddingX;
    }

    @Override
    public int getRowTop(int index) {
        return getY() + paddingY - (int) getScrollAmount() + index * itemHeight;
    }

    @Override
    public int getRowWidth() {
        return width - (paddingX * 2) - (scrollbarVisible() ? 6 : 0);
    }

    public int getEntryX(E entry) {
        return getRowLeft();
    }

    public int getEntryY(E entry) {
        int index = children().indexOf(entry);
        return getRowTop(index);
    }

    public abstract static class Entry<E extends Entry<E>> extends ContainerObjectSelectionList.Entry<E> {}

}