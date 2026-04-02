package dev.yurisuika.raised.config;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.Resource;

import java.util.TreeMap;

public class Options {

    public TreeMap<String, Layer> layers = new TreeMap<String, Layer>() {};
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