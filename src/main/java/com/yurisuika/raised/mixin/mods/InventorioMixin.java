package com.yurisuika.raised.mixin.mods;

import com.mojang.blaze3d.platform.Window;
import com.yurisuika.raised.Raised;
import me.lizardofoz.inventorio.client.ui.HotbarHUDRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HotbarHUDRenderer.class)
public class InventorioMixin {

    @Redirect(method = "renderSegmentedHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    private int modifyScaledHeightA(Window instance) {
        return instance.getGuiScaledHeight() - Raised.getDistance();
    }

    @Redirect(method = "renderHotbarAddons", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    private int modifyScaledHeightB(Window instance) {
        return instance.getGuiScaledHeight() - Raised.getDistance();
    }

}