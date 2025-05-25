package dev.yurisuika.raised.util;

import dev.yurisuika.raised.config.Options;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;

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
        Layers.OTHER_LAYERS.forEach(id -> Option.addLayer(id.toString(), new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.NONE, Layer.Direction.Y.NONE), id.toString())));
    }

}