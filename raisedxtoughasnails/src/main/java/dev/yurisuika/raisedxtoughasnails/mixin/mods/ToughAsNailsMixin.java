package dev.yurisuika.raisedxtoughasnails.mixin.mods;

import dev.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import toughasnails.temperature.TemperatureOverlayHandler;

public class ToughAsNailsMixin {

    @Pseudo
    @Mixin(value = TemperatureOverlayHandler.class, remap = false)
    public static class TemperatureOverlayHandlerMixin {

        @ModifyVariable(method = "drawTemperature", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private static int modifyDrawTemperature(int value) {
            return value - Raised.getHud();
        }

    }

}