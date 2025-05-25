package dev.yurisuika.raised.config;

import dev.yurisuika.raised.util.Layers;
import dev.yurisuika.raised.util.config.options.Layer;
import dev.yurisuika.raised.util.config.options.Resource;

import java.util.TreeMap;

public class Options {

    public TreeMap<String, Layer> layers = new TreeMap<>() {
        {
            put(Layers.HOTBAR.toString(), new Layer(new Layer.Displacement(0, 2), new Layer.Direction(Layer.Direction.X.NONE, Layer.Direction.Y.UP), Layers.HOTBAR.toString()));
            put(Layers.CHAT.toString(), new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.NONE, Layer.Direction.Y.UP), Layers.CHAT.toString()));
            put(Layers.BOSSBAR.toString(), new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.NONE, Layer.Direction.Y.DOWN), Layers.BOSSBAR.toString()));
            put(Layers.SIDEBAR.toString(), new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.LEFT, Layer.Direction.Y.NONE), Layers.SIDEBAR.toString()));
            put(Layers.EFFECTS.toString(), new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.LEFT, Layer.Direction.Y.DOWN), Layers.EFFECTS.toString()));
            put(Layers.PLAYERS.toString(), new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.NONE, Layer.Direction.Y.DOWN), Layers.PLAYERS.toString()));
            put(Layers.TOASTS.toString(), new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.LEFT, Layer.Direction.Y.DOWN), Layers.TOASTS.toString()));
            put(Layers.OTHER.toString(), new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.NONE, Layer.Direction.Y.NONE), Layers.OTHER.toString()));
        }
    };
    public Resource resource = new Resource(Resource.Texture.AUTO);

    public TreeMap<String, Layer> getLayers() {
        return layers;
    }

    public void setLayers(TreeMap<String, Layer> layers) {
        this.layers = layers;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

}