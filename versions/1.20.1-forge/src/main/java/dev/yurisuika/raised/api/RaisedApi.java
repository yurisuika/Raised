package dev.yurisuika.raised.api;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.resources.ResourceLocation;

public class RaisedApi {

    /**
     * <p>Retrieves the horizontal offset for the given layer.
     *
     * <p>The displacement for the layer is modified with the direction of movement on the x-axis. If the layer is
     * synced with another layer, the synced layer's displacement is used instead.
     *
     * @param name the {@link Layer} key as a {@link String}
     *
     * @return the horizontal offset int
     */
    public static int getX(String name) {
        return Translate.getX(name);
    }

    /**
     * <p>Retrieves the horizontal offset for the given layer.
     *
     * <p>The displacement for the layer is modified with the direction of movement on the x-axis. If the layer is
     * synced with another layer, the synced layer's displacement is used instead.
     *
     * @param name the {@link Layer} key as a {@link ResourceLocation}
     *
     * @return the horizontal offset int
     */
    public static int getX(ResourceLocation name) {
        return Translate.getX(name);
    }

    /**
     * <p>Retrieves the vertical offset for the given layer.
     *
     * <p>The displacement for the layer is modified with the direction of movement on the y-axis. If the layer is
     * synced with another layer, the synced layer's displacement is used instead.
     *
     * @param name the {@link Layer} key as a {@link String}
     *
     * @return the vertical offset int
     */
    public static int getY(String name) {
        return Translate.getY(name);
    }

    /**
     * <p>Retrieves the vertical offset for the given layer.
     *
     * <p>The displacement for the layer is modified with the direction of movement on the y-axis. If the layer is
     * synced with another layer, the synced layer's displacement is used instead.
     *
     * @param name the {@link Layer} key as a {@link ResourceLocation}
     *
     * @return the vertical offset int
     */
    public static int getY(ResourceLocation name) {
        return Translate.getY(name);
    }

    /**
     * <p>Registers a layer for the user to configure.
     *
     * <p>A default layer configuration is written to Raised's config under the provided key if the key does not exist.
     *
     * @param name the {@link Layer} key to register as a {@link String}
     */
    public static void register(String name) {
        LayerRegistry.register(name);
    }

    /**
     * <p>Registers a layer for the user to configure.
     *
     * <p>A default layer configuration is written to Raised's config under the provided key if the key does not exist.
     *
     * @param name the {@link Layer} key to register as a {@link ResourceLocation}
     */
    public static void register(ResourceLocation name) {
        LayerRegistry.register(name);
    }

}