package dev.yurisuika.raised.api;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.resources.ResourceLocation;

public class RaisedApi {

    /**
     * <p>Retrieves the calculated horizontal offset for the given layer.
     *
     * <p>The sum of x-axis offsets from all groups containing this layer is sign mapped based on its anchor.
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
     * <p>The sum of x-axis offsets from all groups containing this layer is sign mapped based on its anchor.
     *
     * @param layerName the {@link Layer} key as an {@link ResourceLocation}
     *
     * @return the horizontal offset int
     */
    public static int getX(ResourceLocation layerName) {
        return Translate.getX(layerName);
    }

    /**
     * <p>Retrieves the calculated vertical offset for the given layer.
     *
     * <p>The sum of y-axis offsets from all groups containing this layer is sign mapped based on its anchor.
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
     * <p>The sum of y-axis offsets from all groups containing this layer is sign mapped based on its anchor.
     *
     * @param layerName the {@link Layer} key as an {@link ResourceLocation}
     *
     * @return the vertical offset int
     */
    public static int getY(ResourceLocation layerName) {
        return Translate.getY(layerName);
    }

    /**
     * <p>Registers a layer for the user to configure with default anchor.
     *
     * @param layerName the {@link Layer} key to register as a {@link String}
     */
    public static void register(String layerName) {
        LayerRegistry.register(layerName);
    }

    /**
     * <p>Registers a layer for the user to configure with default anchor.
     *
     * @param layerName the {@link Layer} key to register as an {@link ResourceLocation}
     */
    public static void register(ResourceLocation layerName) {
        LayerRegistry.register(layerName);
    }

    /**
     * <p>Registers a layer for the user to configure.
     *
     * @param layerName the {@link Layer} key to register as a {@link String}
     * @param anchor the {@link Layer.Anchor} of the layer, used when generating an entry in the config
     */
    public static void register(String layerName, Layer.Anchor anchor) {
        LayerRegistry.register(layerName, new Layer(anchor));
    }

    /**
     * <p>Registers a layer for the user to configure.
     *
     * @param layerName the {@link Layer} key to register as an {@link ResourceLocation}
     * @param anchor the {@link Layer.Anchor} of the layer, used when generating an entry in the config
     */
    public static void register(ResourceLocation layerName, Layer.Anchor anchor) {
        LayerRegistry.register(layerName, new Layer(anchor));
    }

}