package com.yurisuika.raised.mixin.mods;

import com.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import terrails.healthoverlay.render.HeartRenderer;

@Mixin(HeartRenderer.class)
public class HealthOverlayMixin {

    @Redirect(method = "renderPlayerHearts", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
    private int modifyScaledHeight(Window instance) {
        return instance.getScaledHeight() - Raised.getDistance();
    }

}
