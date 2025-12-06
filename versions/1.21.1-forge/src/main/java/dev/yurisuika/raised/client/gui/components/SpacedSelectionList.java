package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public abstract class SpacedSelectionList<E extends SpacedSelectionList.Entry<E>> extends ObjectSelectionList<E> {

    public int padding;

    public SpacedSelectionList(Minecraft minecraft, int width, int height, int y, int itemHeight, int padding) {
        super(minecraft, width, height, y, itemHeight);
        this.padding = padding;
    }

    @Override
    public E getEntryAtPosition(double mouseX, double mouseY) {
        int i = getRowWidth() / 2;
        int j = getX() + width / 2;
        int m = Mth.floor(mouseY - (double) getY()) + (int) getScrollAmount() - padding;
        int n = m / itemHeight;
        return mouseX >= (double) (j - i) && mouseX <= (double) (j + i) && n >= 0 && m >= 0 && n < getItemCount() ? children().get(n) : null;
    }

    @Override
    public int getMaxScroll() {
        return Math.max(0, getMaxPosition() - height);
    }

    @Override
    public int getMaxPosition() {
        return padding + (getItemCount() * itemHeight) + padding;
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
            guiGraphics.blitSprite(ResourceLocation.withDefaultNamespace("widget/scroller_background"), i, getY(), 6, getHeight());
            guiGraphics.blitSprite(ResourceLocation.withDefaultNamespace("widget/scroller"), i, k, 6, j);
            RenderSystem.disableBlend();
        }
    }

    @Override
    public void ensureVisible(E entry) {
        int i = getRowTop(children().indexOf(entry));
        int j = i - getY() - padding - itemHeight;
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
        return getRowRight() + padding - 6;
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
        int i = getX() + (this.width - width) / 2;
        int j = getX() + (this.width + width) / 2;
        guiGraphics.fill(i, top, j, top + itemHeight, outerColor);
        guiGraphics.fill(i + 1, top + 1, j - 1, top + itemHeight - 1, innerColor);
    }

    @Override
    public int getRowLeft() {
        return getX() + padding;
    }

    @Override
    public int getRowTop(int index) {
        return getY() + padding - (int) getScrollAmount() + index * itemHeight;
    }

    @Override
    public int getRowWidth() {
        return width - (padding * 2);
    }

    public abstract static class Entry<E extends Entry<E>> extends ObjectSelectionList.Entry<E> {}

}