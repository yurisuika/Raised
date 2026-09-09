package dev.yurisuika.raised.registry;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.config.Config;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class LayerRegistry {

    public static final Set<ResourceLocation> LAYERS = new HashSet<>();
    public static final TreeMap<ResourceLocation, Layer> DEFAULT_LAYERS = new TreeMap<ResourceLocation, Layer>();

    public static void register(String layerName) {
        register(layerName, new Layer(Layer.Anchor.NONE));
    }

    public static void register(ResourceLocation layerName) {
        register(layerName, new Layer(Layer.Anchor.NONE));
    }

    public static void register(String layerName, Layer layer) {
        register(ResourceLocation.tryParse(layerName), layer);
    }

    public static void register(ResourceLocation layerName, Layer layer) {
        LAYERS.add(layerName);
        DEFAULT_LAYERS.putIfAbsent(layerName, layer);
        Config.update(o -> o.getLayers().putIfAbsent(layerName.toString(), layer));
        Raised.LOGGER.info("Registering Raised layer '{}'", layerName);
    }

    public static void addDefaultLayersToConfig() {
        DEFAULT_LAYERS.forEach((layerName, layer) -> Config.update(o -> o.getLayers().putIfAbsent(layerName.toString(), layer)));
    }

}