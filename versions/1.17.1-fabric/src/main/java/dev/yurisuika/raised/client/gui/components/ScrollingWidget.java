package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

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
            withIntersectedScissor(minX, minY, maxX, maxY, () -> GuiComponent.drawString(poseStack, font, text, minX - (int) pos, textTop, color));
        } else {
            int textX = Mth.clamp(centerX, minX + lineWidth / 2, maxX - lineWidth / 2);
            GuiComponent.drawCenteredString(poseStack, font, text, textX, textTop, color);
        }
    }

    static void enableScissor(int minX, int minY, int maxX, int maxY) {
        Window window = Minecraft.getInstance().getWindow();
        int m = window.getHeight();
        double d = window.getGuiScale();
        double e = minX * d;
        double f = m - maxY * d;
        double g = (maxX - minX) * d;
        double h = (maxY - minY) * d;
        RenderSystem.enableScissor((int) e, (int) f, Math.max(0, (int) g), Math.max(0, (int) h));
    }

    static void disableScissor() {
        RenderSystem.disableScissor();
    }

    static void withIntersectedScissor(int minX, int minY, int maxX, int maxY, Runnable draw) {
        Minecraft minecraft = Minecraft.getInstance();
        Window window = minecraft.getWindow();

        try (MemoryStack stack = MemoryStack.stackPush()) {
            IntBuffer oldScissor = stack.mallocInt(4);

            GL11.glGetIntegerv(GL11.GL_SCISSOR_BOX, oldScissor);

            boolean scissorWasEnabled = GL11.glIsEnabled(GL11.GL_SCISSOR_TEST);

            int oldX = oldScissor.get(0);
            int oldY = oldScissor.get(1);
            int oldWidth = oldScissor.get(2);
            int oldHeight = oldScissor.get(3);

            double scale = window.getGuiScale();

            int textX = (int) Math.floor(minX * scale);
            int textY = (int) Math.floor(window.getHeight() - maxY * scale);
            int textWidth = (int) Math.ceil((maxX - minX) * scale);
            int textHeight = (int) Math.ceil((maxY - minY) * scale);

            int scissorX;
            int scissorY;
            int scissorWidth;
            int scissorHeight;

            if (scissorWasEnabled) {
                int left = Math.max(oldX, textX);
                int bottom = Math.max(oldY, textY);
                int right = Math.min(oldX + oldWidth, textX + textWidth);
                int top = Math.min(oldY + oldHeight, textY + textHeight);

                scissorX = left;
                scissorY = bottom;
                scissorWidth = Math.max(0, right - left);
                scissorHeight = Math.max(0, top - bottom);
            } else {
                scissorX = textX;
                scissorY = textY;
                scissorWidth = textWidth;
                scissorHeight = textHeight;
            }

            RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);

            try {
                draw.run();
            } finally {
                if (scissorWasEnabled) {
                    RenderSystem.enableScissor(oldX, oldY, oldWidth, oldHeight);
                } else {
                    RenderSystem.disableScissor();
                }
            }
        }
    }

}