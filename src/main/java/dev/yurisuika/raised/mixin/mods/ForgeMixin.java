package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.client.gui.GuiEvents;
import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.fml.ModLoadingContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class ForgeMixin {

    @Mixin(value = RegisterGuiOverlaysEvent.class, remap = false)
    public abstract static class RegisterGuiOverlaysEventMixin {

        @Inject(method = "registerBelowAll", at = @At("HEAD"))
        private void addLayerBelowAll(String id, IGuiOverlay overlay, CallbackInfo ci) {
            addLayer(ResourceLocation.tryParse(formatNamespace() + ":" + id));
        }

        @Inject(method = "registerBelow", at = @At("HEAD"))
        private void addLayerBelow(ResourceLocation other, String id, IGuiOverlay overlay, CallbackInfo ci) {
            addLayer(ResourceLocation.tryParse(formatNamespace() + ":" + id));
        }

        @Inject(method = "registerAbove", at = @At("HEAD"))
        private void addLayerAbove(ResourceLocation other, String id, IGuiOverlay overlay, CallbackInfo ci) {
            addLayer(ResourceLocation.tryParse(formatNamespace() + ":" + id));
        }

        @Inject(method = "registerAboveAll", at = @At("HEAD"))
        private void addLayerAboveAll(String id, IGuiOverlay overlay, CallbackInfo ci) {
            addLayer(ResourceLocation.tryParse(formatNamespace() + ":" + id));
        }

        @Unique
        public void addLayer(ResourceLocation name) {
            if (!name.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
                GuiEvents.MODS.add(name);

                LayerRegistry.register(name, LayerRegistry.createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, name));
            }
        }

        @Unique
        private static String formatNamespace() {
            String namespace = ModLoadingContext.get().getActiveNamespace();
            return namespace.equals("raised") ? ResourceLocation.DEFAULT_NAMESPACE : namespace;
        }

    }

}