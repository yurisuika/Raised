package dev.yurisuika.raised.util;

import dev.yurisuika.raised.util.config.Option;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class Parse {

    public static String parseNamespace(String name) {
        String namespace = ResourceLocation.tryParse(name).getNamespace();
        return FabricLoader.getInstance().getModContainer(namespace).orElseThrow().getMetadata().getName();
    }

    public static String parsePath(String name) {
        String path = ResourceLocation.tryParse(name).getPath();
        String layer = StringUtils.replaceChars(path, '_', ' ');
        layer = StringUtils.replaceChars(layer, '-', ' ');
        layer = WordUtils.capitalize(layer);
        return layer;
    }

    public static Component createLayerDisplay(String name) {
        if (!Option.getLayers().containsKey(name)) {
            return Component.translatable("options.raised.layer.missing", name);
        }
        if (!listLoadedNames().contains(name)) {
            return Component.translatable("options.raised.layer.unloaded", name);
        }
        return Component.literal(parseNamespace(name) + " - " + parsePath(name));
    }

    public static List<ResourceLocation> listLoadedIds() {
        List<ResourceLocation> list = new ArrayList<>();
        Option.getLayers().forEach((name, layer) -> list.add(ResourceLocation.tryParse(name)));
        return list;
    }

    public static List<String> listLoadedNames() {
        List<String> list = new ArrayList<>();
        Option.getLayers().forEach((name, layer) -> list.add(name));
        return list;
    }

}