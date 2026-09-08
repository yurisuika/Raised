package dev.yurisuika.raised.util;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;

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

    public static void start(PoseStack poseStack, ResourceLocation layerName) {
        start(poseStack, layerName.toString());
    }

    public static void start(PoseStack poseStack, String layerName) {
        int x = getX(layerName);
        int y = getY(layerName);

        if (!(x == 0 && y == 0)) {
            poseStack.pushPose();
            poseStack.translate(x, y, 0);
        }
    }

    public static void end(PoseStack poseStack, ResourceLocation layerName) {
        end(poseStack, layerName.toString());
    }

    public static void end(PoseStack poseStack, String layerName) {
        int x = getX(layerName);
        int y = getY(layerName);

        if (!(x == 0 && y == 0)) {
            poseStack.popPose();
        }
    }

    public static List<String> findGroupsWithLayer(String layerName) {
        return Configure.Groups.getGroups().entrySet().stream()
                .filter(entry -> entry.getValue().getLayers().contains(layerName))
                .map(Map.Entry::getKey)
                .toList();
    }

}