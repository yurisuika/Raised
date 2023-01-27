package dev.yurisuika.raised.mixin.mods;

import com.mojang.blaze3d.platform.Window;
import dev.yurisuika.raised.Raised;
import org.anti_ad.mc.ipnext.event.LockSlotsHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class InventoryProfilesNextMixin {

    @Mixin(LockSlotsHandler.class)
    public static class LockSlotsHandlerMixin {

        @Redirect(method = "drawHotSprite", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
        private int redirectDrawHotSprite(Window instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

}