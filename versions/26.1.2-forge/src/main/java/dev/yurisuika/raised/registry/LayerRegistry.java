package dev.yurisuika.raised.registry;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import net.minecraft.resources.Identifier;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class LayerRegistry extends Layers {

    public static final Set<Identifier> LAYERS = new HashSet<>();
    public static final TreeMap<Identifier, Layer> DEFAULT_LAYERS = new TreeMap<>();

    public static void register(String layerName) {
        register(layerName, Layers.createDefaultLayer());
    }

    public static void register(Identifier layerName) {
        register(layerName, Layers.createDefaultLayer());
    }

    public static void register(String layerName, Layer layer) {
        register(Identifier.tryParse(layerName), layer);
    }

    public static void register(Identifier layerName, Layer layer) {
        LAYERS.add(layerName);
        DEFAULT_LAYERS.putIfAbsent(layerName, layer);
        Raised.LOGGER.info("Registering Raised layer '{}'", layerName);
    }
    
    public static Layer findDefaultLayer(Identifier layerName) {
        return hasLayer(layerName) ? DEFAULT_LAYERS.get(layerName) : Layers.createDefaultLayer();
    }

    public static boolean hasLayer(Identifier layerName) {
        return LAYERS.contains(layerName);
    }

}