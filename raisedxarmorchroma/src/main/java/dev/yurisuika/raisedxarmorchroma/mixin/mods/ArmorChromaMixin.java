package dev.yurisuika.raisedxarmorchroma.mixin.mods;

import dev.yurisuika.raised.Raised;
import nukeduck.armorchroma.GuiArmor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

public class ArmorChromaMixin {

    @Pseudo
    @Mixin(GuiArmor.class)
    public static class GuiArmorMixin {

        @ModifyVariable(method = "draw", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
        private int modifyDraw(int value) {
            return value - Raised.getHud();
        }

    }

}