package dev.yurisuika.raised.util;

import dev.yurisuika.raised.config.Options;
import dev.yurisuika.raised.util.config.Option;

public class Validate {

    public static void checkForOldConfig() {
        Option.getLayers().forEach((name, layer) -> {
            if (layer.getDisplacement() == null) {
                resetConfig();
            }
        });
    }

    public static void resetConfig() {
        Option.setLayers(new Options().getLayers());
    }

}