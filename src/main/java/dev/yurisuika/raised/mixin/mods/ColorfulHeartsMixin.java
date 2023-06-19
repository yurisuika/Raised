package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import terrails.colorfulhearts.render.HeartRenderer;

public class ColorfulHeartsMixin {

    @Pseudo
    @Mixin(HeartRenderer.class)
    public static class HeartRendererMixin {

        @ModifyVariable(method = "renderPlayerHearts", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
        private int redirectRenderPlayerHearts(int value) {
            return value - Raised.getHud();
        }

    }

}