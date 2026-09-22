package dev.yurisuika.raised.util;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.config.Config;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;

public class Translate {

    public static int getX(ResourceLocation layerName) {
        return getX(layerName.toString());
    }

    public static int getX(String layerName) {
        return findGroupsWithLayer(layerName).stream().mapToInt(groupName -> {
            int offset = Config.getOptions().getGroups().get(groupName).getOffset().getX();
            int position = Config.getOptions().getGroups().get(groupName).getLayers().get(layerName).getPosition().getX();

            return offset * position;
        }).sum();
    }

    public static int getY(ResourceLocation layerName) {
        return getY(layerName.toString());
    }

    public static int getY(String layerName) {
        return findGroupsWithLayer(layerName).stream().mapToInt(groupName -> {
            int offset = Config.getOptions().getGroups().get(groupName).getOffset().getY();
            int position = Config.getOptions().getGroups().get(groupName).getLayers().get(layerName).getPosition().getY();

            return offset * position;
        }).sum();
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
        return Config.getOptions().getGroups().entrySet().stream()
                .filter(entry -> entry.getValue().getLayers().containsKey(layerName))
                .map(Map.Entry::getKey)
                .toList();
    }

}