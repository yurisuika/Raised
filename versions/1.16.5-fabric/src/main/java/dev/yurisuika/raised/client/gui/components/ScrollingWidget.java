package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

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
            enableScissor(minX, minY, maxX, maxY);
            font.drawShadow(poseStack, text.getString(), minX - (int) pos, textTop, color);
            disableScissor();
        } else {
            int textX = Mth.clamp(centerX, minX + lineWidth / 2, maxX - lineWidth / 2);
            font.drawShadow(poseStack, text.getVisualOrderText(), (float)(textX - font.width(text.getVisualOrderText()) / 2), textTop, color);
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
        RenderSystem.assertThread(RenderSystem::isOnGameThreadOrInit);
        RenderSystem.assertThread(RenderSystem::isOnRenderThreadOrInit);
        GL11.glEnable(3089);
        GL20.glScissor((int) e, (int) f, Math.max(0, (int) g), Math.max(0, (int) h));
    }

    static void disableScissor() {
        RenderSystem.assertThread(RenderSystem::isOnRenderThreadOrInit);
        GL11.glDisable(3089);
    }

}