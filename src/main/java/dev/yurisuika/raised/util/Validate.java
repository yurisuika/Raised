package dev.yurisuika.raised.util;

import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.config.Options;
import dev.yurisuika.raised.util.config.Config;
import dev.yurisuika.raised.util.config.Option;

public class Validate {

    public static void checkForOldConfig() {
        Option.getLayers().forEach((name, layer) -> {
            if (layer.getDisplacement() == null) {
                resetConfig();
            }
        });
    }

    public static void reloadConfig() {
        Config.loadConfig();
        Layers.addLayersToConfig();
    }

    public static void resetConfig() {
        Option.setLayers(new Options().getLayers());
        Option.setResource(new Options().getResource());
        Layers.addLayersToConfig();
    }

}