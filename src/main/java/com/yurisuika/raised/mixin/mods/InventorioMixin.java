package com.yurisuika.raised.mixin.mods;

import com.yurisuika.raised.Raised;
import me.lizardofoz.inventorio.client.ui.HotbarHUDRenderer;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class InventorioMixin {

    @Mixin(HotbarHUDRenderer.class)
    public static class HotbarHUDRendererMixin {

        @Redirect(method = "renderHotbarAddons", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private int redirectRenderHotbarAddons(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

        @Redirect(method = "renderSegmentedHotbar", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private int redirectRenderSegmentedHotbar(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}