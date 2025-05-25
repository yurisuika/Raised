package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.util.Layers;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class NeoForgeMixin {

    @Mixin(value = RegisterGuiLayersEvent.class, remap = false)
    public abstract static class RegisterGuiLayersEventMixin {

        @Inject(method = "registerBelowAll", at = @At("HEAD"))
        private void addLayerBelowAll(ResourceLocation id, LayeredDraw.Layer layer, CallbackInfo ci) {
            addLayer(id);
        }

        @Inject(method = "registerBelow", at = @At("HEAD"))
        private void addLayerBelow(ResourceLocation other, ResourceLocation id, LayeredDraw.Layer layer, CallbackInfo ci) {
            addLayer(id);
        }

        @Inject(method = "registerAbove", at = @At("HEAD"))
        private void addLayerAbove(ResourceLocation other, ResourceLocation id, LayeredDraw.Layer layer, CallbackInfo ci) {
            addLayer(id);
        }

        @Inject(method = "registerAboveAll", at = @At("HEAD"))
        private void addLayerAboveAll(ResourceLocation id, LayeredDraw.Layer layer, CallbackInfo ci) {
            addLayer(id);
        }

        @Unique
        public void addLayer(ResourceLocation id) {
            if (!id.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
                Layers.OTHER_LAYERS.add(id);
                String name = id.toString();
                if (!Option.getLayers().containsKey(name)) {
                    Option.addLayer(name, new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.NONE, Layer.Direction.Y.NONE), name));
                }
            }
        }

    }

}