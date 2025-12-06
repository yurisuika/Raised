package dev.yurisuika.raised.mixin.minecraft.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AbstractWidget.class)
public interface AbstractWidgetInvoker {

    @Invoker("renderScrollingString")
    static void invokeRenderScrollingString(PoseStack poseStack, Font font, Component text, int minX, int minY, int maxX, int maxY, int color) {
        throw new AssertionError();
    }

}