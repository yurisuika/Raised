package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import terrails.healthoverlay.render.HeartRenderer;
import terrails.healthoverlay.utilities.Vec2i;

public class HealthOverlayMixin {

    @Pseudo
    @Mixin(HeartRenderer.class)
    public static class HeartRendererMixin {

        @Redirect(method = "renderPlayerHearts", at = @At(value = "INVOKE", target = "Lterrails/healthoverlay/utilities/Vec2i;getY()I"))
        private int redirectRenderPlayerHearts(Vec2i instance) {
            return instance.getY() - Raised.getHud();
        }

    }

}