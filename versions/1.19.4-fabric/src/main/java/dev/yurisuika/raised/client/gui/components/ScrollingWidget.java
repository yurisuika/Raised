package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

public interface ScrollingWidget {

    static void renderScrollingWithDefaultCenter(PoseStack poseStack, Font font, Component text, int minX, int minY, int maxX, int maxY, int color) {
        renderScrolling(poseStack, font, text, (minX + maxX) / 2, minX, minY, maxX, maxY, color);
    }

    static void renderScrolling(PoseStack poseStack, Font font, Component text, int centerX, int minX, int minY, int maxX, int maxY, int color) {
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
            GuiComponent.enableScissor(minX, minY, maxX, maxY);
            GuiComponent.drawString(poseStack, font, text, minX - (int) pos, textTop, color);
            GuiComponent.disableScissor();
        } else {
            int textX = Mth.clamp(centerX, minX + lineWidth / 2, maxX - lineWidth / 2);
            GuiComponent.drawCenteredString(poseStack, font, text, textX, textTop, color);
        }
    }

}