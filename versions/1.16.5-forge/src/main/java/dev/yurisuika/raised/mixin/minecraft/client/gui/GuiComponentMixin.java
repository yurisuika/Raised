package dev.yurisuika.raised.mixin.minecraft.client.gui;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.GuiComponentInterface;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(GuiComponent.class)
public abstract class GuiComponentMixin implements GuiComponentInterface {

    @Override
    public void renderScrollingString(PoseStack poseStack, Font font, Component text, int minX, int minY, int maxX, int maxY, int color) {
        int i = font.width(text);
        int j = (minY + maxY - 9) / 2 + 1;
        int k = maxX - minX;
        if (i > k) {
            int l = i - k;
            double d0 = Util.getMillis() / 1000.0D;
            double d1 = Math.max(l * 0.5D, 3.0D);
            double d2 = Math.sin((Math.PI / 2) * Math.cos((Math.PI * 2) * d0 / d1)) / 2.0D + 0.5D;
            double d3 = Mth.lerp(d2, 0.0D, l);
            enableScissor(minX, minY, maxX, maxY);
            font.drawShadow(poseStack, text.getString(), (float) (minX - (int) d3), (float) j, color);
            disableScissor();
        } else {
            font.drawShadow(poseStack, text.getString(), (float) (((minX + maxX) / 2) - font.width(text.getString()) / 2), (float) j, color);
        }
    }

    @Override
    public void enableScissor(int minX, int minY, int maxX, int maxY) {
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

    @Override
    public void disableScissor() {
        RenderSystem.assertThread(RenderSystem::isOnRenderThreadOrInit);
        GL11.glDisable(3089);
    }

}