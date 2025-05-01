package dev.yurisuika.raised.util;

import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.properties.Element;
import dev.yurisuika.raised.util.properties.Sync;
import org.joml.Matrix3x2fStack;

public class Translate {

    public static int getX(Element element) {
        return Option.getX(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element) * Option.getPosition(element).getX();
    }

    public static int getY(Element element) {
        return Option.getY(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element) * Option.getPosition(element).getY();
    }

    public static void start(Matrix3x2fStack matrix3x2fStack, Element element) {
        matrix3x2fStack.pushMatrix();
        matrix3x2fStack.translate(getX(element), getY(element));
    }

    public static void end(Matrix3x2fStack matrix3x2fStack) {
        matrix3x2fStack.popMatrix();
    }

}