package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.client.gui.GuiEvents;
import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.fml.ModLoadingContext;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public abstract class ForgeMixin {

    @Mixin(value = OverlayRegistry.class, remap = false)
    public abstract static class OverlayRegistryMixin {

        @Inject(method = "registerOverlayBottom", at = @At("HEAD"))
        private static void addLayerBelowAll(String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
            addLayer(ResourceLocation.tryParse(formatNamespace() + ":" + formatPath(displayName)), overlay);
        }

        @Inject(method = "registerOverlayBelow", at = @At("HEAD"))
        private static void addLayerBelow(IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
            addLayer(ResourceLocation.tryParse(formatNamespace() + ":" + formatPath(displayName)), overlay);
        }

        @Inject(method = "registerOverlayAbove", at = @At("HEAD"))
        private static void addLayerAbove(IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
            addLayer(ResourceLocation.tryParse(formatNamespace() + ":" + formatPath(displayName)), overlay);
        }

        @Inject(method = "registerOverlayTop", at = @At("HEAD"))
        private static void addLayerAboveAll(String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
            addLayer(ResourceLocation.tryParse(formatNamespace() + ":" + formatPath(displayName)), overlay);
        }

        @Unique
        private static void addLayer(ResourceLocation id, IIngameOverlay overlay) {
            if (!id.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
                GuiEvents.MODS.put(id, overlay);

                LayerRegistry.register(id, new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.NONE, Layer.Direction.Y.NONE), id.toString()));
            }
        }

        @Unique
        private static String formatPath(String displayName) {
            String path = displayName;
            path = StringUtils.replaceChars(path, ' ', '_');
            path = StringUtils.remove(path, ':');
            path = path.toLowerCase();
            return path;
        }

        @Unique
        private static String formatNamespace() {
            String namespace = ModLoadingContext.get().getActiveNamespace();
            return namespace.equals("raised") ? ResourceLocation.DEFAULT_NAMESPACE : namespace;
        }

    }

}