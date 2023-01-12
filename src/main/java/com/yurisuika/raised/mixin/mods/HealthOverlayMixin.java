package com.yurisuika.raised.mixin.mods;

import com.mojang.blaze3d.platform.Window;
import com.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import terrails.healthoverlay.render.HeartRenderer;

public class HealthOverlayMixin {

    @Mixin(HeartRenderer.class)
    public static class HeartRendererMixin {

        @Redirect(method = "renderPlayerHearts", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
        private int redirectRenderPlayerHearts(Window instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

}