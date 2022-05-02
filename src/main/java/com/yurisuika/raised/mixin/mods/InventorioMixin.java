package com.yurisuika.raised.mixin.mods;

import me.lizardofoz.inventorio.client.ui.HotbarHUDRenderer;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HotbarHUDRenderer.class)
public class InventorioMixin {

    @Redirect(method = "renderSegmentedHotbar", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
    private int modifyScaledHeightA(Window instance) {
        return instance.getScaledHeight() - 1;
    }

    @Redirect(method = "renderHotbarAddons", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
    private int modifyScaledHeightB(Window instance) {
        return instance.getScaledHeight() - 2;
    }

}
