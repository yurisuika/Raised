package dev.yurisuika.raised.util;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;

import java.util.TreeMap;

public class Icon {

    public static final TreeMap<ResourceLocation, ResourceLocation> LAYER_TEXTURES = new TreeMap<ResourceLocation, ResourceLocation>();

    public static void checkResources() {
        LAYER_TEXTURES.clear();

        LayerRegistry.LAYERS.forEach(layerName -> {
            ResourceLocation identifier = ResourceLocation.fromNamespaceAndPath(Raised.MOD_ID, "textures/gui/layer/" + layerName.getNamespace() + "/" + layerName.getPath() + ".png");
            if (Minecraft.getInstance().getResourceManager().getResource(identifier).isPresent()) {
                LAYER_TEXTURES.put(layerName, identifier);
            }
        });
    }

    public static ResourceLocation getLayerIcon(ResourceLocation layerName) {
        return LAYER_TEXTURES.getOrDefault(layerName, ResourceLocation.fromNamespaceAndPath(Raised.MOD_ID, "textures/gui/layer/default.png"));
    }

}