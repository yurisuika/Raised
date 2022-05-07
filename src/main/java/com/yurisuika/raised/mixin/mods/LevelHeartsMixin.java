package com.yurisuika.raised.mixin.mods;

import com.firecontroller1847.levelhearts.gui.IngameGui;
import com.mojang.blaze3d.platform.Window;
import com.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(IngameGui.class)
public class LevelHeartsMixin {

    @Redirect(method = "redrawHealth", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    private int modifyHeartsScaledHeight(Window instance) {
        return instance.getGuiScaledHeight() - Raised.getDistance();
    }

    @Redirect(method = "redrawAir", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    private int modifyAirScaledHeight(Window instance) {
        return instance.getGuiScaledHeight() - Raised.getDistance();
    }

    @Redirect(method = "redrawArmor", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    private int modifyArmorScaledHeight(Window instance) {
        return instance.getGuiScaledHeight() - Raised.getDistance();
    }

}
