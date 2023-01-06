package com.yurisuika.raised.mixin.mods;

import com.yurisuika.raised.Raised;
import org.anti_ad.mc.ipnext.event.LockSlotsHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

public class InventoryProfilesNextMixin {

    @Mixin(LockSlotsHandler.class)
    public static class LockSlotsHandlerMixin {

        @ModifyArg(method = "drawHotSprite", at = @At(value = "INVOKE", target = "Lorg/anti_ad/mc/common/math2d/Point;<init>(II)V", ordinal = 0), index = 1, remap = false)
        private int modifyDrawHotSprite(int value) {
            return value - Raised.getHud();
        }

    }

}