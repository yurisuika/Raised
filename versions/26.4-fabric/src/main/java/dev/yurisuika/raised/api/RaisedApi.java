package dev.yurisuika.raised.api;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.resources.Identifier;

public class RaisedApi {

    /**
     * <p>Retrieves the calculated horizontal offset for the given layer.
     *
     * <p>For each group containing this layer, the x-axis offset is sign mapped with the layer position. The sum from all
     * groups containing this layer is returned.
     *
     * @param layerName the {@link Layer} key as a {@link String}
     *
     * @return the horizontal offset int
     */
    public static int getX(String layerName) {
        return Translate.getX(layerName);
    }

    /**
     * <p>Retrieves the calculated horizontal offset for the given layer.
     *
     * <p>For each group containing this layer, the x-axis offset is sign mapped with the layer position. The sum from all
     * groups containing this layer is returned.
     *
     * @param layerName the {@link Layer} key as an {@link Identifier}
     *
     * @return the horizontal offset int
     */
    public static int getX(Identifier layerName) {
        return Translate.getX(layerName);
    }

    /**
     * <p>Retrieves the calculated vertical offset for the given layer.
     *
     * <p>For each group containing this layer, the y-axis offset is sign mapped with the layer position. The sum from all
     * groups containing this layer is returned.
     *
     * @param layerName the {@link Layer} key as a {@link String}
     *
     * @return the vertical offset int
     */
    public static int getY(String layerName) {
        return Translate.getY(layerName);
    }

    /**
     * <p>Retrieves the calculated vertical offset for the given layer.
     *
     * <p>For each group containing this layer, the y-axis offset is sign mapped with the layer position. The sum from all
     * groups containing this layer is returned.
     *
     * @param layerName the {@link Layer} key as an {@link Identifier}
     *
     * @return the vertical offset int
     */
    public static int getY(Identifier layerName) {
        return Translate.getY(layerName);
    }

    /**
     * <p>Registers a layer for the user to configure with default configuration.
     *
     * @param layerName the {@link Layer} key to register as a {@link String}
     */
    public static void register(String layerName) {
        LayerRegistry.register(layerName);
    }

    /**
     * <p>Registers a layer for the user to configure with default configuration.
     *
     * @param layerName the {@link Layer} key to register as an {@link Identifier}
     */
    public static void register(Identifier layerName) {
        LayerRegistry.register(layerName);
    }

    /**
     * <p>Registers a layer for the user to configure with full configuration.
     *
     * @param layerName the {@link Layer} key to register as a {@link String}
     * @param position the {@link Layer.Position} of the layer
     */
    public static void register(String layerName, Layer.Position position) {
        LayerRegistry.register(layerName, Layers.createLayer(position));
    }

    /**
     * <p>Registers a layer for the user to configure with full configuration.
     *
     * @param layerName the {@link Layer} key to register as an {@link Identifier}
     * @param position the {@link Layer.Position} of the layer
     */
    public static void register(Identifier layerName, Layer.Position position) {
        LayerRegistry.register(layerName, Layers.createLayer(position));
    }

}