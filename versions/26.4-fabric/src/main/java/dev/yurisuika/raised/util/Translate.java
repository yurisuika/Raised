package dev.yurisuika.raised.util;

import dev.yurisuika.raised.config.Config;
import net.minecraft.resources.Identifier;
import org.joml.Matrix3x2fStack;

import java.util.List;
import java.util.Map;

public class Translate {

    public static int getX(Identifier layerName) {
        return getX(layerName.toString());
    }

    public static int getX(String layerName) {
        return findGroupsWithLayer(layerName).stream().mapToInt(groupName -> {
            int offset = Config.getOptions().getGroups().get(groupName).getOffset().getX();
            int position = Config.getOptions().getGroups().get(groupName).getLayers().get(layerName).getPosition().getX();

            return offset * position;
        }).sum();
    }

    public static int getY(Identifier layerName) {
        return getY(layerName.toString());
    }

    public static int getY(String layerName) {
        return findGroupsWithLayer(layerName).stream().mapToInt(groupName -> {
            int offset = Config.getOptions().getGroups().get(groupName).getOffset().getY();
            int position = Config.getOptions().getGroups().get(groupName).getLayers().get(layerName).getPosition().getY();

            return offset * position;
        }).sum();
    }

    public static void start(Matrix3x2fStack matrix3x2fStack, Identifier layerName) {
        start(matrix3x2fStack, layerName.toString());
    }

    public static void start(Matrix3x2fStack matrix3x2fStack, String layerName) {
        int x = getX(layerName);
        int y = getY(layerName);

        if (!(x == 0 && y == 0)) {
            matrix3x2fStack.pushMatrix();
            matrix3x2fStack.translate(x, y);
        }
    }

    public static void end(Matrix3x2fStack matrix3x2fStack, Identifier layerName) {
        end(matrix3x2fStack, layerName.toString());
    }

    public static void end(Matrix3x2fStack matrix3x2fStack, String layerName) {
        int x = getX(layerName);
        int y = getY(layerName);

        if (!(x == 0 && y == 0)) {
            matrix3x2fStack.popMatrix();
        }
    }

    public static List<String> findGroupsWithLayer(String layerName) {
        return Config.getOptions().getGroups().entrySet().stream()
                .filter(entry -> entry.getValue().getLayers().containsKey(layerName))
                .map(Map.Entry::getKey)
                .toList();
    }

}