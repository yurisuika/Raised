package dev.yurisuika.raised.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.ModList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.Optional;

public class Parse {

    public static String parseNamespace(ResourceLocation name) {
        String namespace = name.getNamespace();
        Optional<? extends ModContainer> optional = ModList.get().getModContainerById(namespace);
        return optional.isPresent() ? optional.get().getModInfo().getDisplayName() : namespace;
    }

    public static String parsePath(ResourceLocation name) {
        String path = name.getPath();
        String layer = StringUtils.replaceChars(path, '_', ' ');
        layer = StringUtils.replaceChars(layer, '-', ' ');
        layer = WordUtils.capitalize(layer);
        return layer;
    }

}