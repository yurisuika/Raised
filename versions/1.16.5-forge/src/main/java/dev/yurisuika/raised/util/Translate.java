package dev.yurisuika.raised.util;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.config.Config;
import net.minecraft.resources.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Translate {

    public static int getX(ResourceLocation layerName) {
        return getX(layerName.toString());
    }

    public static int getX(String layerName) {
        return findGroupsWithLayer(layerName).stream().mapToInt(groupName -> {
            int offset = Config.getOptions().getGroups().get(groupName).getOffset().getX();
            int anchor = Config.getOptions().getGroups().get(groupName).getLayers().get(layerName).getAnchor().getX();

            return offset * anchor;
        }).sum();
    }

    public static int getY(ResourceLocation layerName) {
        return getY(layerName.toString());
    }

    public static int getY(String layerName) {
        return findGroupsWithLayer(layerName).stream().mapToInt(groupName -> {
            int offset = Config.getOptions().getGroups().get(groupName).getOffset().getY();
            int anchor = Config.getOptions().getGroups().get(groupName).getLayers().get(layerName).getAnchor().getY();

            return offset * anchor;
        }).sum();
    }

    public static void start(ResourceLocation layerName) {
        start(layerName.toString());
    }

    public static void start(String layerName) {
        int x = getX(layerName);
        int y = getY(layerName);

        if (!(x == 0 && y == 0)) {
            RenderSystem.pushMatrix();
            RenderSystem.translated(x, y, layerName.equals(Layers.CHAT.toString()) ? 300 : 0);
        }
    }

    public static void end(ResourceLocation layerName) {
        end(layerName.toString());
    }

    public static void end(String layerName) {
        int x = getX(layerName);
        int y = getY(layerName);

        if (!(x == 0 && y == 0)) {
            RenderSystem.popMatrix();
        }
    }

    public static List<String> findGroupsWithLayer(String layerName) {
        return Config.getOptions().getGroups().entrySet().stream()
                .filter(entry -> entry.getValue().getLayers().containsKey(layerName))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}