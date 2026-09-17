package dev.yurisuika.raised.registry;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class LayerRegistry {

    public static final Set<ResourceLocation> LAYERS = new HashSet<>();
    public static final TreeMap<ResourceLocation, Layer> DEFAULT_LAYERS = new TreeMap<>();

    public static void register(String layerName) {
        register(layerName, Layers.createDefaultLayer());
    }

    public static void register(ResourceLocation layerName) {
        register(layerName, Layers.createDefaultLayer());
    }

    public static void register(String layerName, Layer layer) {
        register(ResourceLocation.tryParse(layerName), layer);
    }

    public static void register(ResourceLocation layerName, Layer layer) {
        LAYERS.add(layerName);
        DEFAULT_LAYERS.putIfAbsent(layerName, layer);
        Raised.LOGGER.info("Registering Raised layer '{}'", layerName);
    }
    
    public static Layer findDefaultLayer(ResourceLocation layerName) {
        return hasLayer(layerName) ? DEFAULT_LAYERS.get(layerName) : Layers.createDefaultLayer();
    }

    public static boolean hasLayer(ResourceLocation layerName) {
        return LAYERS.contains(layerName);
    }

}