package dev.yurisuika.raised.mixin.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.components.AbstractWidgetInterface;
import dev.yurisuika.raised.client.gui.Scissor;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
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
        Scissor.renderScrollingString(poseStack, font, text, minX, minY, maxX, maxY, color);
    }

}