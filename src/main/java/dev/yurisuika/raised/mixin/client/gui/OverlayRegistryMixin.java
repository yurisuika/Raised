package dev.yurisuika.raised.mixin.client.gui;

import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static dev.yurisuika.raised.client.gui.RaisedGui.*;

@Mixin(value = OverlayRegistry.class, remap = false)
public class OverlayRegistryMixin {

    @Inject(method = "registerOverlayBelow", at = @At("RETURN"))
    private static void translateOverlayBelow(IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (hud.contains(other)) {
            hud.add(overlay);
        }
    }

    @Inject(method = "registerOverlayAbove", at = @At("RETURN"))
    private static void translateOverlayAbove(IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        if (hud.contains(other)) {
            hud.add(overlay);
        }
    }

}