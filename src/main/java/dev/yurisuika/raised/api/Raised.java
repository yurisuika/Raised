package dev.yurisuika.raised.api;

import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.type.*;

public class Raised {

    /**
     * Retrieves the horizontal x-axis offset for the given element.
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
     * Sets the horizontal x-axis offset for the given element.
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
     * Retrieves the vertical y-axis offset for the given element.
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
     * Sets the vertical y-axis offset for the given element.
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
     * Retrieves the position for the given element.
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
     * Sets the position for the given element.
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
     * Retrieves the synchronized element for the given element.
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
     * Sets the synchronized element for the given element.
     *
     * @param element the given element {@link Element}
     * @param sync the synchronized element {@link Sync}
     *
     * @see #getSync(Element)
     */
    public static void setSync(Element element, Sync sync) {
        Option.setSync(element, sync);
    }

}