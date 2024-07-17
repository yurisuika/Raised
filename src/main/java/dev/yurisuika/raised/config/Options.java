package dev.yurisuika.raised.config;

import dev.yurisuika.raised.util.config.options.Layers;
import dev.yurisuika.raised.util.config.options.Properties;
import dev.yurisuika.raised.util.properties.Position;
import dev.yurisuika.raised.util.properties.Sync;

public class Options {

    public Layers layers = new Layers(
            new Properties(0, 2, Position.BOTTOM, Sync.NONE),
            new Properties(0, 0, Position.BOTTOM, Sync.NONE),
            new Properties(0, 0, Position.TOP, Sync.NONE),
            new Properties(0, 0, Position.RIGHT, Sync.NONE),
            new Properties(0, 0, Position.TOP_RIGHT, Sync.NONE),
            new Properties(0, 0, Position.TOP, Sync.NONE),
            new Properties(0, 0, Position.TOP_RIGHT, Sync.NONE),
            new Properties(0, 0, Position.BOTTOM, Sync.NONE)
    );

    public Layers getLayers() {
        return layers;
    }

    public void setLayers(Layers layers) {
        this.layers = layers;
    }

}