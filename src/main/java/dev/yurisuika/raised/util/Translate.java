package dev.yurisuika.raised.util;

import dev.yurisuika.raised.util.config.Option;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3x2fStack;

public class Translate {

    public static int getX(ResourceLocation name) {
        return getX(name.toString());
    }

    public static int getX(String name) {
        if (Option.getLayer(name) == null) {
            return 0;
        }
        if (Option.getLayer(Option.getSync(name)) == null) {
            return Option.getDisplacementX(name) * Option.getDirectionX(name).getX();
        }
        return Option.getDisplacementX(Option.getSync(name)) * Option.getDirectionX(name).getX();
    }

    public static int getY(ResourceLocation name) {
        return getY(name.toString());
    }

    public static int getY(String name) {
        if (Option.getLayer(name) == null) {
            return 0;
        }
        if (Option.getLayer(Option.getSync(name)) == null) {
            return Option.getDisplacementY(name) * Option.getDirectionX(name).getX();
        }
        return Option.getDisplacementY(Option.getSync(name)) * Option.getDirectionY(name).getY();
    }

    public static void start(Matrix3x2fStack matrix3x2fStack, ResourceLocation name) {
        start(matrix3x2fStack, name.toString());
    }

    public static void start(Matrix3x2fStack matrix3x2fStack, String name) {
        matrix3x2fStack.pushMatrix();
        matrix3x2fStack.translate(getX(name), getY(name));
    }

    public static void end(Matrix3x2fStack matrix3x2fStack) {
        matrix3x2fStack.popMatrix();
    }

}