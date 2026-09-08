package dev.yurisuika.raised.mixin.minecraftforge.client.event;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
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

@Mixin(value = RegisterGuiOverlaysEvent.class, remap = false)
public abstract class RegisterGuiOverlaysEventMixin {

    @Inject(method = "registerBelowAll", at = @At("HEAD"))
    private void addLayerBelowAll(String id, IGuiOverlay overlay, CallbackInfo ci) {
        addLayer(id);
    }

    @Inject(method = "registerBelow", at = @At("HEAD"))
    private void addLayerBelow(ResourceLocation other, String id, IGuiOverlay overlay, CallbackInfo ci) {
        addLayer(id);
    }

    @Inject(method = "registerAbove", at = @At("HEAD"))
    private void addLayerAbove(ResourceLocation other, String id, IGuiOverlay overlay, CallbackInfo ci) {
        addLayer(id);
    }

    @Inject(method = "registerAboveAll", at = @At("HEAD"))
    private void addLayerAboveAll(String id, IGuiOverlay overlay, CallbackInfo ci) {
        addLayer(id);
    }

    /**
     * <p>Vanilla layers get registered as Raised, so those are ignored.
     */
    @Unique
    public void addLayer(String path) {
        String namespace = ModLoadingContext.get().getActiveNamespace();
        namespace = namespace.equals(Raised.MOD_ID) ? ResourceLocation.DEFAULT_NAMESPACE : namespace;
        ResourceLocation name = new ResourceLocation(namespace, path);

        if (!name.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
            LayerRegistry.register(name, new Layer(Layer.Anchor.NONE));
            Layers.Curated.CURATED_LAYERS.put(name, name);
        }
    }

}