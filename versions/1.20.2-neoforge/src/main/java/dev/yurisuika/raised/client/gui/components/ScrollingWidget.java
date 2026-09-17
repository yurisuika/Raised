package dev.yurisuika.raised.client.gui.components;

import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public interface ScrollingWidget {

    static void renderScrollingWithDefaultCenter(GuiGraphics guiGraphics, Font font, Component text, int minX, int minY, int maxX, int maxY, int color) {
        renderScrolling(guiGraphics, font, text, (minX + maxX) / 2, minX, minY, maxX, maxY, color);
    }

    static void renderScrolling(GuiGraphics guiGraphics, Font font, Component text, int centerX, int minX, int minY, int maxX, int maxY, int color) {
        int lineWidth = font.width(text);
        int lineHeight = 9;
        int textTop = (minY + maxY - lineHeight) / 2 + 1;
        int availableMessageWidth = maxX - minX;
        if (lineWidth > availableMessageWidth) {
            int maxPosition = lineWidth - availableMessageWidth;
            double time = (double) Util.getMillis() / 1000.0D;
            double period = Math.max((double) maxPosition * 0.5D, 3.0F);
            double alpha = Math.sin((Math.PI / 2D) * Math.cos((Math.PI * 2D) * time / period)) / 2.0D + 0.5D;
            double pos = Mth.lerp(alpha, 0.0F, maxPosition);
            guiGraphics.enableScissor(minX, minY, maxX, maxY);
            guiGraphics.drawString(font, text, minX - (int) pos, textTop, color);
            guiGraphics.disableScissor();
        } else {
            int textX = Mth.clamp(centerX, minX + lineWidth / 2, maxX - lineWidth / 2);
            guiGraphics.drawCenteredString(font, text, textX, textTop, color);
        }

    }

}