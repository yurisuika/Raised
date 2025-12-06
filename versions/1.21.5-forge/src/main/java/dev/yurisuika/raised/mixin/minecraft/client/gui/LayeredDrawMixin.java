package dev.yurisuika.raised.mixin.minecraft.client.gui;

import dev.yurisuika.raised.client.gui.MappedLayers;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;

@Mixin(value = LayeredDraw.class, priority = -999999999)
public abstract class LayeredDrawMixin {

    @Inject(method = "renderInner", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/LayeredDraw$Layer;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void startTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Iterator iterator, LayeredDraw.Layer layer) {
        if (MappedLayers.MAPPED_LAYERS.containsKey(layer)) {
            Translate.start(guiGraphics.pose(), MappedLayers.MAPPED_LAYERS.get(layer));
        }
    }

    @Inject(method = "renderInner", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/LayeredDraw$Layer;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void endTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Iterator iterator, LayeredDraw.Layer layer) {
        if (MappedLayers.MAPPED_LAYERS.containsKey(layer)) {
            Translate.end(guiGraphics.pose(), MappedLayers.MAPPED_LAYERS.get(layer));
        }
    }

}