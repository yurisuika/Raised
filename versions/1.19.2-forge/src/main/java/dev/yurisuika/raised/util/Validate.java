package dev.yurisuika.raised.util;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.config.Options;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;

public class Validate {

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

    public static void validateSync(String name) {
        if (!LayerRegistry.LAYERS.containsKey(ResourceLocation.tryParse(Configure.getSync(name)))) {
            Configure.setSync(name, name);
           Raised.LOGGER.warn("Invalid sync for Raised layer '{}'", name);
        }
    }

    public static void validateLayers() {
        LayerRegistry.LAYERS.forEach((name, layer) -> validateSync(name.toString()));
    }

}