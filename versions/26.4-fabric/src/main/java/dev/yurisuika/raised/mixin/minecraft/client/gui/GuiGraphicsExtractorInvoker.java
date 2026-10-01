package dev.yurisuika.raised.mixin.minecraft.client.gui;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GuiGraphicsExtractor.class)
public interface GuiGraphicsExtractorInvoker {

    @Invoker("innerBlit")
    void invokeInnerBlit(RenderPipeline renderPipeline, Identifier identifier, int x1, int x2, int y1, int y2, float minU, float maxU, float minV, float maxV, int blitOffset);

}