package dev.yurisuika.raised.util;

import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.config.Options;
import dev.yurisuika.raised.util.config.Config;
import dev.yurisuika.raised.util.config.Option;
import net.minecraft.resources.ResourceLocation;

public class Validate {

    public static void validateConfig() {
        Option.getLayers().forEach((name, layer) -> {
            if (layer.getDisplacement() == null) {
                resetConfig();
            }
        });
        validateLayers();
    }

    public static void reloadConfig() {
        Config.loadConfig();
        Layers.addLayersToConfig();
        validateLayers();
    }

    public static void resetConfig() {
        Option.setLayers(new Options().getLayers());
        Option.setResource(new Options().getResource());
        Layers.addLayersToConfig();
        validateLayers();
    }

    public static void validateLayers() {
        Option.getLayers().forEach((name, layer) -> {
            if (!Layers.LAYERS.containsKey(ResourceLocation.tryParse(layer.getSync()))) {
                Option.setSync(name, name);
            }
        });
    }

}