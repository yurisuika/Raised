package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.anti_ad.mc.ipnext.event.LockSlotsHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class InventoryProfilesNextMixin {

    @Mixin(LockSlotsHandler.class)
    public static class LockSlotsHandlerMixin {

        @Redirect(method = "drawHotSprite", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private int redirectDrawHotSprite(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}