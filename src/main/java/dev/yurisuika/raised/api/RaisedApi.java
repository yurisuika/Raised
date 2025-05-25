package dev.yurisuika.raised.api;

import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;

public class RaisedApi {

    /**
     * <p>Retrieves the horizontal x-axis displacement for the given layer.
     *
     * <p>The layer is translated on the x-axis by this displacement amount.
     *
     * @param name the given layer {@link Layer} Key
     *
     * @return the horizontal x-axis displacement int
     */
    public static int getDisplacementX(String name) {
        return Option.getDisplacementX(name);
    }

    /**
     * <p>Sets the horizontal x-axis displacement for the given layer.
     *
     * @param name the given layer {@link Layer} Key
     * @param x the horizontal x-axis displacement int
     *
     * @see #getDisplacementX(String)
     */
    public static void setDisplacementX(String name, int x) {
        Option.setDisplacementX(name, x);
    }

    /**
     * <p>Retrieves the vertical y-axis displacement for the given layer.
     *
     * <p>The layer is translated on the y-axis by this displacement amount.
     *
     * @param name the given layer {@link Layer} Key
     *
     * @return the vertical y-axis displacement int
     */
    public static int getDisplacementY(String name) {
        return Option.getDisplacementY(name);
    }

    /**
     * <p>Sets the vertical y-axis displacement for the given layer.
     *
     * @param name the given layer {@link Layer} Key
     * @param y the vertical y-axis displacement int
     *
     * @see #getDisplacementY(String)
     */
    public static void setDisplacementY(String name, int y) {
        Option.setDisplacementY(name, y);
    }

    /**
     * <p>Retrieves the horizontal x-axis direction for the given layer.
     *
     * <p>The layer is translated on the x-axis towards this direction.
     *
     * @param name the given layer {@link Layer} Key
     *
     * @return the horizontal x-axis direction int
     */
    public static Layer.Direction.X getDirectionX(String name) {
        return Option.getDirectionX(name);
    }

    /**
     * <p>Sets the horizontal x-axis direction for the given layer.
     *
     * @param name the given layer {@link Layer} Key
     * @param x the horizontal x-axis direction int
     *
     * @see #getDirectionX(String)
     */
    public static void setDirectionX(String name, Layer.Direction.X x) {
        Option.setDirectionX(name, x);
    }

    /**
     * <p>Retrieves the vertical y-axis direction for the given layer.
     *
     * <p>The layer is translated on the y-axis towards this direction.
     *
     * @param name the given layer {@link Layer} Key
     *
     * @return the vertical y-axis direction int
     */
    public static Layer.Direction.Y getDirectionY(String name) {
        return Option.getDirectionY(name);
    }

    /**
     * <p>Sets the vertical y-axis direction for the given layer.
     *
     * @param name the given layer {@link Layer} Key
     * @param y the vertical y-axis direction int
     *
     * @see #getDirectionY(String)
     */
    public static void setDirectionY(String name, Layer.Direction.Y y) {
        Option.setDirectionY(name, y);
    }

    /**
     * <p>Retrieves the synced layer for the given layer.
     *
     * <p>The layer is translated from its set position by the amount set for the synced layer.
     *
     * @param name the given layer {@link Layer} Key
     *
     * @return the synced layer key {@link String}
     */
    public static String getSync(String name) {
        return Option.getSync(name);
    }

    /**
     * <p>Sets the synced layer for the given layer.
     *
     * @param name the given layer {@link Layer} Key
     * @param sync the synced layer key {@link String}
     *
     * @see #getSync(String)
     */
    public static void setSync(String name, String sync) {
        Option.setSync(name, sync);
    }

}