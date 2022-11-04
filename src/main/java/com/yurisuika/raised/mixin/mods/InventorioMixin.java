package com.yurisuika.raised.mixin.mods;

import com.mojang.blaze3d.platform.Window;
import com.yurisuika.raised.Raised;
import me.lizardofoz.inventorio.client.ui.HotbarHUDRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class InventorioMixin {

    @Mixin(HotbarHUDRenderer.class)
    public static class HotbarHUDRendererMixin {

        @Redirect(method = "renderSegmentedHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
        private int modifyRenderSegmentedHotbar(Window instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

        @Redirect(method = "renderHotbarAddons", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
        private int modifyRenderHotbarAddons(Window instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

    }

}