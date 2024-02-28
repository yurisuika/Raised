package dev.yurisuika.raised.mixin.client.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(DrawContext.class)
public interface DrawContextInvoker {

    @Invoker("drawTexturedQuad")
    void invokeDrawTexturedQuad(Identifier texture, int x1, int x2, int y1, int y2, int z, float u1, float u2, float v1, float v2);

}