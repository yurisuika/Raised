package dev.yurisuika.raised.util;

import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.config.Option;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

public class Parse {

    public static String parseNamespace(ResourceLocation name) {
        String namespace = name.getNamespace();
        return FabricLoader.getInstance().getModContainer(namespace).get().getMetadata().getName();
    }

    public static String parsePath(ResourceLocation name) {
        String path = name.getPath();
        String layer = StringUtils.replaceChars(path, '_', ' ');
        layer = StringUtils.replaceChars(layer, '-', ' ');
        layer = WordUtils.capitalize(layer);
        return layer;
    }

    public static Component createLayerDisplay(ResourceLocation name) {
        if (!Option.getLayers().containsKey(name.toString())) {
            return new TranslatableComponent("options.raised.layer.missing", name);
        }
        if (!Layers.LAYERS.containsKey(name)) {
            return new TranslatableComponent("options.raised.layer.unloaded", name);
        }
        return new TextComponent(parseNamespace(name) + " - " + parsePath(name));
    }

}