package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import terrails.healthoverlay.render.HeartRenderer;

public class HealthOverlayMixin {

    @Pseudo
    @Mixin(HeartRenderer.class)
    public static class HeartRendererMixin {

        @Redirect(method = "renderPlayerHearts", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private int redirectRenderPlayerHearts(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}