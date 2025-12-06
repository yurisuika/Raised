package dev.yurisuika.raised.mixin.neoforge.client.event;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.MappedLayers;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.gui.GuiLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RegisterGuiLayersEvent.class, remap = false)
public abstract class RegisterGuiLayersEventMixin {

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
        LayerRegistry.register(name, LayerRegistry.createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, name));

        MappedLayers.MAPPED_LAYERS.put(name, name);
    }

}