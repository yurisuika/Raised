package dev.yurisuika.raised.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resources.Identifier;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Optional;

public class Parse {

    public static String parseNamespace(Identifier name) {
        String namespace = name.getNamespace();
        Optional<ModContainer> optional = FabricLoader.getInstance().getModContainer(namespace);
        return optional.isPresent() ? optional.get().getMetadata().getName() : namespace;
    }

    public static String parsePath(Identifier name) {
        String path = name.getPath();
        String layer = StringUtils.replaceChars(path, '_', ' ');
        layer = StringUtils.replaceChars(layer, '-', ' ');
        layer = WordUtils.capitalize(layer);
        return layer;
    }

}