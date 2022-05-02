package com.yurisuika.raised.mixin.mods;

import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import squeek.appleskin.client.HUDOverlayHandler;

@Mixin(HUDOverlayHandler.class)
public class AppleskinMixin {

    @Redirect(method = "onPreRender", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
    private int modifyScaledHeightA(Window instance) {
        return instance.getScaledHeight() - 2;
    }

    @Redirect(method = "onRender", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
    private int modifyScaledHeightB(Window instance) {
        return instance.getScaledHeight() - 2;
    }

}