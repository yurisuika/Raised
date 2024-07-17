package dev.yurisuika.raised.config;

import dev.yurisuika.raised.util.config.options.Layers;
import dev.yurisuika.raised.util.config.options.Properties;
import dev.yurisuika.raised.util.config.options.Resources;
import dev.yurisuika.raised.util.properties.Position;
import dev.yurisuika.raised.util.properties.Sync;
import dev.yurisuika.raised.util.resources.Texture;

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
    public Resources resources = new Resources(Texture.AUTO);

    public Layers getLayers() {
        return layers;
    }

    public void setLayers(Layers layers) {
        this.layers = layers;
    }

    public Resources getResources() {
        return resources;
    }

    public void setResources(Resources resources) {
        this.resources = resources;
    }

}