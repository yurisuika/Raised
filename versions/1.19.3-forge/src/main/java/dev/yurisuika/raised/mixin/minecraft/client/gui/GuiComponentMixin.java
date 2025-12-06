package dev.yurisuika.raised.mixin.minecraft.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.GuiComponentInterface;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
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
            GuiComponent.enableScissor(minX, minY, maxX, maxY);
            GuiComponent.drawString(poseStack, font, text, minX - (int) d3, j, color);
            GuiComponent.disableScissor();
        } else {
            GuiComponent.drawCenteredString(poseStack, font, text, (minX + maxX) / 2, j, color);
        }
    }

}