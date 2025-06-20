package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.client.gui.GuiEvents;
import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.GuiLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class NeoForgeMixin {

    @Mixin(value = RegisterGuiLayersEvent.class, remap = false)
    public abstract static class RegisterGuiLayersEventMixin {

        @Inject(method = "registerBelowAll", at = @At("HEAD"))
        private void addLayerBelowAll(ResourceLocation id, GuiLayer layer, CallbackInfo ci) {
            addLayer(id);
        }

        @Inject(method = "registerBelow", at = @At("HEAD"))
        private void addLayerBelow(ResourceLocation other, ResourceLocation id, GuiLayer layer, CallbackInfo ci) {
            addLayer(id);
        }

        @Inject(method = "registerAbove", at = @At("HEAD"))
        private void addLayerAbove(ResourceLocation other, ResourceLocation id, GuiLayer layer, CallbackInfo ci) {
            addLayer(id);
        }

        @Inject(method = "registerAboveAll", at = @At("HEAD"))
        private void addLayerAboveAll(ResourceLocation id, GuiLayer layer, CallbackInfo ci) {
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