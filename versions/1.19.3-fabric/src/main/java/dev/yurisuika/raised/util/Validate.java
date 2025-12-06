package dev.yurisuika.raised.util;

import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.config.Options;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;

public class Validate {

    public static void validateConfig() {
        Configure.getLayers().forEach((name, layer) -> {
            if (layer.getDisplacement() == null) {
                resetConfig();
            }
        });
        validateLayers();
    }

    public static void reloadConfig() {
        Config.loadConfig();
        LayerRegistry.addLayersToConfig();
        validateLayers();
    }

    public static void resetConfig() {
        Configure.setLayers(new Options().getLayers());
        LayerRegistry.addLayersToConfig();
        validateLayers();
    }

    public static void validateLayers() {
        Configure.getLayers().forEach((name, layer) -> {
            if (!LayerRegistry.LAYERS.containsKey(ResourceLocation.tryParse(layer.getSync()))) {
                Configure.setSync(name, name);
            }
        });
    }

}