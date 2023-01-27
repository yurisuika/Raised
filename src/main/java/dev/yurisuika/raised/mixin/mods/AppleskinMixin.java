package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import squeek.appleskin.client.HUDOverlayHandler;

public class AppleskinMixin {

    @Mixin(HUDOverlayHandler.class)
    public static class HUDOverlayHandlerMixin {

        @Redirect(method = "onPreRender", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private int redirectOnPreRender(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

        @Redirect(method = "onRender", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private int redirectOnRender(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}