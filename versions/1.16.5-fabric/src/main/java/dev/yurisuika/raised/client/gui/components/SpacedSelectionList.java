package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.GuiComponentInterface;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.util.Mth;

import java.util.Objects;

public abstract class SpacedSelectionList<E extends SpacedSelectionList.Entry<E>> extends ObjectSelectionList<E> {

    public int padding;

    public SpacedSelectionList(Minecraft minecraft, int width, int height, int y0, int y1, int itemHeight, int padding) {
        super(minecraft, width, height, y0, y1, itemHeight);
        this.padding = padding;
    }

    @Override
    public E getEntryAtPosition(double mouseX, double mouseY) {
        int i = getRowWidth() / 2;
        int j = x0 + width / 2;
        int m = Mth.floor(mouseY - (double) y0) + (int) getScrollAmount() - padding;
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
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        ((GuiComponentInterface) this).enableScissor(x0, y0, x1, y1);
        renderList(poseStack, getRowLeft(), y0 - (int) getScrollAmount(), mouseX, mouseY, partialTick);
        ((GuiComponentInterface) this).disableScissor();
        if (getMaxScroll() > 0) {
            int i = getScrollbarPosition();
            int j = (int) ((float) (height * height) / (float) getMaxPosition());
            j = Mth.clamp(j, 32, height - 8);
            int k = (int) getScrollAmount() * (height - j) / getMaxScroll() + y0;
            if (k < y0) {
                k = y0;
            }

            RenderSystem.enableBlend();
            fill(poseStack, i, y0, i + 6, y1, -16777216);
            fill(poseStack, i, k, i + 6, k + j, -8355712);
            fill(poseStack, i, k, i + 6 - 1, k + j - 1, -4144960);
            RenderSystem.disableBlend();
        }
    }

    @Override
    public void ensureVisible(E entry) {
        int i = getRowTop(children().indexOf(entry));
        int j = i - y0 - padding - itemHeight;
        if (j < 0) {
            setScrollAmount(getScrollAmount() + (double) j);
        }

        int k = y1 - i - itemHeight - itemHeight;
        if (k < 0) {
            setScrollAmount(getScrollAmount() + (double) -k);
        }
    }

    @Override
    public int getScrollbarPosition() {
        return getRowLeft() + getRowWidth() + padding - 6;
    }

    @Override
    public void renderList(PoseStack poseStack, int i, int j, int mouseX, int mouseY, float partialTick) {
        for (int m = 0; m < getItemCount(); ++m) {
            int n = getRowTop(m);
            int o = n + itemHeight;
            if (o >= y0 && n <= y1) {
                renderItem(poseStack, mouseX, mouseY, partialTick, m, getRowLeft(), n, getRowWidth(), itemHeight);
            }
        }
    }

    public void renderItem(PoseStack poseStack, int mouseX, int mouseY, float partialTick, int index, int left, int top, int width, int height) {
        E entry = getEntry(index);
        if (renderSelection && isSelectedItem(index)) {
            renderSelection(poseStack, top, width, height, isFocused() ? -1 : -8355712, -16777216);
        }

        entry.render(poseStack, index, top, left, width, height, mouseX, mouseY, Objects.equals(isMouseOver(mouseX, mouseY) ? getEntryAtPosition(mouseX, mouseY) : null, entry), partialTick);
    }

    public void renderSelection(PoseStack poseStack, int top, int width, int height, int outerColor, int innerColor) {
        int i = x0 + (this.width - width) / 2;
        int j = x0 + (this.width + width) / 2;
        fill(poseStack, i, top, j, top + itemHeight, outerColor);
        fill(poseStack, i + 1, top + 1, j - 1, top + itemHeight - 1, innerColor);
    }

    @Override
    public int getRowLeft() {
        return x0 + padding;
    }

    @Override
    public int getRowTop(int index) {
        return y0 + padding - (int) getScrollAmount() + index * itemHeight;
    }

    @Override
    public int getRowWidth() {
        return width - (padding * 2);
    }

    public abstract static class Entry<E extends Entry<E>> extends ObjectSelectionList.Entry<E> {}

}