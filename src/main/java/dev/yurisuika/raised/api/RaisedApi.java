package dev.yurisuika.raised.api;

import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.properties.Element;
import dev.yurisuika.raised.util.properties.Position;
import dev.yurisuika.raised.util.properties.Sync;
import dev.yurisuika.raised.util.resources.Texture;

public class RaisedApi {

    /**
     * <p>Retrieves the horizontal x-axis offset for the given element.
     *
     * <p>The element is translated on the x-axis by this amount.
     *
     * @param element the given element {@link Element}
     *
     * @return the horizontal x-axis offset int
     */
    public static int getX(Element element) {
        return Option.getX(element);
    }

    /**
     * <p>Sets the horizontal x-axis offset for the given element.
     *
     * @param element the given element {@link Element}
     * @param x the horizontal x-axis offset int
     *
     * @see #getX(Element)
     */
    public static void setX(Element element, int x) {
        Option.setX(element, x);
    }

    /**
     * <p>Retrieves the vertical y-axis offset for the given element.
     *
     * <p>The element is translated on the y-axis by this amount.
     *
     * @param element the given element {@link Element}
     *
     * @return the vertical y-axis offset int
     */
    public static int getY(Element element) {
        return Option.getY(element);
    }

    /**
     * <p>Sets the vertical y-axis offset for the given element.
     *
     * @param element the given element {@link Element}
     * @param y the vertical y-axis offset int
     *
     * @see #getY(Element)
     */
    public static void setY(Element element, int y) {
        Option.setY(element, y);
    }

    /**
     * <p>Retrieves the position for the given element.
     *
     * <p>The element is translated in the direction away from this position.
     *
     * @param element the given element {@link Element}
     *
     * @return the synchronized element {@link Sync}
     */
    public static Position getPosition(Element element) {
        return Option.getPosition(element);
    }

    /**
     * <p>Sets the position for the given element.
     *
     * @param element the given element {@link Element}
     * @param position the position {@link Position}
     *
     * @see #getPosition(Element)
     */
    public static void setPosition(Element element, Position position) {
        Option.setPosition(element, position);
    }

    /**
     * <p>Retrieves the synchronized element for the given element.
     *
     * <p>The element is translated from its set position by the amount set for the synchronized element.
     *
     * @param element the given element {@link Element}
     *
     * @return the synchronized element {@link Sync}
     */
    public static Sync getSync(Element element) {
        return Option.getSync(element);
    }

    /**
     * <p>Sets the synchronized element for the given element.
     *
     * @param element the given element {@link Element}
     * @param sync the synchronized element {@link Sync}
     *
     * @see #getSync(Element)
     */
    public static void setSync(Element element, Sync sync) {
        Option.setSync(element, sync);
    }

    /**
     * <p>Retrieves the texture modification method for the hotbar selector.
     *
     * <p>{@code Texture.REPLACE} - replaces the hotbar selector with a new square asset found under the "raised" namespace.
     *
     * <p>{@code Texture.PATCH} - draws a vertically mirrored row taken from the top of the asset below the unmodified selector.
     *
     * <p>{@code Texture.AUTO} - fixes based on whether resource pack support is present or not.
     *
     * <p>{@code Texture.NONE} - the selector is not modified.
     *
     * @return the texture modification method {@link Texture}
     */
    public static Texture getTexture() {
        return Option.getTexture();
    }

    /**
     * <p>Sets the texture modification method for the hotbar selector.
     *
     * @param texture the texture modification method {@link Texture}
     *
     * @see #getTexture()
     */
    public static void setTexture(Texture texture) {
        Option.setTexture(texture);
    }

}