package dev.yurisuika.raised.util;

import net.minecraft.resources.Identifier;
import org.joml.Matrix3x2fStack;

public class Translate {

    public static int getX(Identifier name) {
        return getX(name.toString());
    }

    public static int getX(String name) {
        return Configure.getDisplacementX(Configure.getLayer(Configure.getSync(name)) == null ? name : Configure.getSync(name)) * Configure.getDirectionX(name).getX();
    }

    public static int getY(Identifier name) {
        return getY(name.toString());
    }

    public static int getY(String name) {
        return Configure.getDisplacementY(Configure.getLayer(Configure.getSync(name)) == null ? name : Configure.getSync(name)) * Configure.getDirectionY(name).getY();
    }

    public static void start(Matrix3x2fStack matrix3x2fStack, Identifier name) {
        start(matrix3x2fStack, name.toString());
    }

    public static void start(Matrix3x2fStack matrix3x2fStack, String name) {
        if (should(name)) {
            matrix3x2fStack.pushMatrix();
            matrix3x2fStack.translate(getX(name), getY(name));
        }
    }

    public static void end(Matrix3x2fStack matrix3x2fStack, Identifier name) {
        end(matrix3x2fStack, name.toString());
    }

    public static void end(Matrix3x2fStack matrix3x2fStack, String name) {
        if (should(name)) {
            matrix3x2fStack.popMatrix();
        }
    }

    public static boolean should(String name) {
        return !(getX(name) == 0 && getY(name) == 0);
    }

}