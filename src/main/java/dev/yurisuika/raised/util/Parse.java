package dev.yurisuika.raised.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class Parse {

    public static String parseNamespace(ResourceLocation name) {
        String namespace = name.getNamespace();
        return FabricLoader.getInstance().getModContainer(namespace).orElseThrow().getMetadata().getName();
    }

    public static String parsePath(ResourceLocation name) {
        String path = name.getPath();
        String layer = StringUtils.replaceChars(path, '_', ' ');
        layer = StringUtils.replaceChars(layer, '-', ' ');
        layer = WordUtils.capitalize(layer);
        return layer;
    }

    public static Component createLayerDisplay(ResourceLocation name) {
        return Component.literal(parseNamespace(name) + " - " + parsePath(name));
    }

}