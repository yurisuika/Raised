package dev.yurisuika.raised.util.config;

import dev.yurisuika.raised.util.config.options.Layer;
import dev.yurisuika.raised.util.config.options.Resource;

import java.util.TreeMap;

public class Option {

    public static TreeMap<String, Layer> getLayers() {
        return Config.getOptions().getLayers();
    }

    public static void setLayers(TreeMap<String, Layer> layers) {
        Config.getOptions().setLayers(layers);
        Config.saveConfig();
    }

    public static Layer getLayer(String name) {
        return getLayers().get(name);
    }

    public static void setLayer(String name, Layer layer) {
        setDisplacementX(name, layer.getDisplacement().getX());
        setDisplacementY(name, layer.getDisplacement().getY());
        setDirectionX(name, layer.getDirection().getX());
        setDirectionY(name, layer.getDirection().getY());
        setSync(name, layer.getSync());
        Config.saveConfig();
    }

    public static void addLayer(String name, Layer layer) {
        getLayers().put(name, layer);
        Config.saveConfig();
    }

    public static void removeLayer(String name, Layer layer) {
        getLayers().remove(name, layer);
        Config.saveConfig();
    }

    public static Layer.Displacement getDisplacement(String name) {
        return getLayer(name).getDisplacement();
    }

    public static void setDisplacement(String name, int x, int y) {
        getLayer(name).setDisplacement(new Layer.Displacement(x, y));
        Config.saveConfig();
    }

    public static int getDisplacementX(String name) {
        return getLayer(name).getDisplacement().getX();
    }

    public static void setDisplacementX(String name, int x) {
        getLayer(name).getDisplacement().setX(x);
        Config.saveConfig();
    }

    public static int getDisplacementY(String name) {
        return getLayer(name).getDisplacement().getY();
    }

    public static void setDisplacementY(String name, int y) {
        getLayer(name).getDisplacement().setY(y);
        Config.saveConfig();
    }

    public static Layer.Direction getDirection(String name) {
        return getLayer(name).getDirection();
    }

    public static void setDirection(String name, Layer.Direction.X x, Layer.Direction.Y y) {
        getLayer(name).setDirection(new Layer.Direction(x, y));
        Config.saveConfig();
    }

    public static Layer.Direction.X getDirectionX(String name) {
        return getLayer(name).getDirection().getX();
    }

    public static void setDirectionX(String name, Layer.Direction.X x) {
        getLayer(name).getDirection().setX(x);
        Config.saveConfig();
    }

    public static Layer.Direction.Y getDirectionY(String name) {
        return getLayer(name).getDirection().getY();
    }

    public static void setDirectionY(String name, Layer.Direction.Y y) {
        getLayer(name).getDirection().setY(y);
        Config.saveConfig();
    }

    public static String getSync(String name) {
        return getLayer(name).getSync();
    }

    public static void setSync(String name, String sync) {
        getLayer(name).setSync(sync);
        Config.saveConfig();
    }

    public static Resource getResource() {
        return Config.getOptions().getResource();
    }

    public static void setResource(Resource resource) {
        Config.getOptions().setResource(resource);
        Config.saveConfig();
    }

    public static Resource.Texture getTexture() {
        return getResource().getTexture();
    }

    public static void setTexture(Resource.Texture texture) {
        getResource().setTexture(texture);
        Config.saveConfig();
    }

}