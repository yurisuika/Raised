package dev.yurisuika.raised.util;

import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3x2fStack;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Translate {

    public static int getX(ResourceLocation layerName) {
        return getX(layerName.toString());
    }

    public static int getX(String layerName) {
        int offset = findGroupsWithLayer(layerName).stream().mapToInt(groupName -> Optional.of(Configure.Groups.getOffsetX(groupName)).orElse(0)).sum();
        int anchor = Configure.Layers.getAnchor(layerName).getX();
        return offset * anchor;
    }

    public static int getY(ResourceLocation layerName) {
        return getY(layerName.toString());
    }

    public static int getY(String layerName) {
        int offset = findGroupsWithLayer(layerName).stream().mapToInt(groupName -> Optional.of(Configure.Groups.getOffsetY(groupName)).orElse(0)).sum();
        int anchor = Configure.Layers.getAnchor(layerName).getY();
        return offset * anchor;
    }

    public static void start(Matrix3x2fStack matrix3x2fStack, ResourceLocation layerName) {
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

    public static void end(Matrix3x2fStack matrix3x2fStack, ResourceLocation layerName) {
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
        return Configure.Groups.getGroups().entrySet().stream()
                .filter(entry -> entry.getValue().getLayers().contains(layerName))
                .map(Map.Entry::getKey)
                .toList();
    }

}