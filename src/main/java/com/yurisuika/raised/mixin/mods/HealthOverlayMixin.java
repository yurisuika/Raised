package com.yurisuika.raised.mixin.mods;

import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import terrails.healthoverlay.render.HeartRenderer;

@Mixin(HeartRenderer.class)
public class HealthOverlayMixin {

    @Redirect(method = "renderPlayerHearts", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    private int modifyScaledHeight(Window instance) {
        return instance.getGuiScaledHeight() - 2;
    }

}
