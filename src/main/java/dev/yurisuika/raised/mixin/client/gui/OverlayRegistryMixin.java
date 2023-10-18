package dev.yurisuika.raised.mixin.client.gui;

import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.yurisuika.raised.client.gui.RaisedGui.*;
import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Mixin(value = OverlayRegistry.class, remap = false)
public class OverlayRegistryMixin {

    // MOD BELOW
    @Inject(method = "registerOverlayBelow", at = @At("RETURN"))
    private static void addOverlayBelow(IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (hud.contains(other) && getSupport()) {
            mod.add(overlay);
        }
    }

    // MOD ABOVE
    @Inject(method = "registerOverlayAbove", at = @At("RETURN"))
    private static void addOverlayAbove(IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (hud.contains(other) && getSupport()) {
            mod.add(overlay);
        }
    }

}