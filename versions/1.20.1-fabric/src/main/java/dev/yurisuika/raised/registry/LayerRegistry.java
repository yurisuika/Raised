package dev.yurisuika.raised.registry;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.util.Configure;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public class LayerRegistry {

    public static final Set<ResourceLocation> LAYERS = new HashSet<>();

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
        Configure.Layers.addLayer(layerName.toString(), layer);
        Raised.LOGGER.info("Registering Raised layer '{}'", layerName);
    }

    public static void addLayersToConfig() {
        LAYERS.forEach(layerName -> Configure.Layers.addLayer(layerName.toString(), new Layer(Layer.Anchor.NONE)));
    }

}