package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.client.gui.GuiEvents;
import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class NeoForgeMixin {

    @Mixin(value = RegisterGuiOverlaysEvent.class, remap = false)
    public abstract static class RegisterGuiOverlaysEventMixin {

        @Inject(method = "registerBelowAll(Lnet/minecraft/resources/ResourceLocation;Lnet/neoforged/neoforge/client/gui/overlay/IGuiOverlay;)V", at = @At("HEAD"))
        private void addLayerBelowAll(ResourceLocation id, IGuiOverlay overlay, CallbackInfo ci) {
            addLayer(id);
        }

        @Inject(method = "registerBelow(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/resources/ResourceLocation;Lnet/neoforged/neoforge/client/gui/overlay/IGuiOverlay;)V", at = @At("HEAD"))
        private void addLayerBelow(ResourceLocation other, ResourceLocation id, IGuiOverlay overlay, CallbackInfo ci) {
            addLayer(id);
        }

        @Inject(method = "registerAbove(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/resources/ResourceLocation;Lnet/neoforged/neoforge/client/gui/overlay/IGuiOverlay;)V", at = @At("HEAD"))
        private void addLayerAbove(ResourceLocation other, ResourceLocation id, IGuiOverlay overlay, CallbackInfo ci) {
            addLayer(id);
        }

        @Inject(method = "registerAboveAll(Lnet/minecraft/resources/ResourceLocation;Lnet/neoforged/neoforge/client/gui/overlay/IGuiOverlay;)V", at = @At("HEAD"))
        private void addLayerAboveAll(ResourceLocation id, IGuiOverlay overlay, CallbackInfo ci) {
            addLayer(id);
        }

        @Unique
        public void addLayer(ResourceLocation name) {
            if (!name.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
                GuiEvents.MODS.add(name);

                Layers.register(name, Layers.createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, name));
            }
        }

    }

}