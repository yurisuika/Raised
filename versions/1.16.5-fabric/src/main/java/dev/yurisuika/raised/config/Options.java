package dev.yurisuika.raised.config;

import dev.yurisuika.raised.client.gui.Layer;

import java.util.TreeMap;

public class Options {

    public TreeMap<String, Layer> layers = new TreeMap<String, Layer>() {};

    public TreeMap<String, Layer> getLayers() {
        return layers;
    }

    public void setLayers(TreeMap<String, Layer> layers) {
        this.layers = layers;
    }

}