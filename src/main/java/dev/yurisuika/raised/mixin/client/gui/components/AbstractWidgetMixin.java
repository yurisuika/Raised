package dev.yurisuika.raised.mixin.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.components.AbstractWidgetInterface;
import net.minecraft.Util;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AbstractWidget.class)
public abstract class AbstractWidgetMixin extends GuiComponent implements AbstractWidgetInterface {

    @Shadow
    private int x;
    @Shadow
    private int y;
    @Shadow
    protected int width;
    @Shadow
    protected int height;

    @Redirect(method = "renderButton", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/AbstractWidget;drawCenteredString(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;III)V"))
    private void drawScrollingString(PoseStack poseStack, Font font, Component component, int stringX, int stringY, int color) {
        renderScrollingString(poseStack, font, component, x + 2, y, x + width - 2, y + height, color);
    }

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
            drawString(poseStack, font, text, minX - (int) d3, j, color);
            disableScissor();
        } else {
            drawCenteredString(poseStack, font, text, (minX + maxX) / 2, j, color);
        }
    }

}