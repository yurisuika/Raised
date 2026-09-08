package dev.yurisuika.raised.util;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;

import java.util.TreeMap;

public class Icon {

    public static final TreeMap<Identifier, Identifier> LAYER_TEXTURES = new TreeMap<Identifier, Identifier>();

    public static void checkResources() {
        LAYER_TEXTURES.clear();

        LayerRegistry.LAYERS.forEach(layerName -> {
            Identifier identifier = Identifier.fromNamespaceAndPath(Raised.MOD_ID, "textures/gui/layer/" + layerName.getNamespace() + "/" + layerName.getPath() + ".png");
            if (Minecraft.getInstance().getResourceManager().getResource(identifier).isPresent()) {
                LAYER_TEXTURES.put(layerName, identifier);
            }
        });
    }

    public static Identifier getLayerIcon(Identifier layerName) {
        return LAYER_TEXTURES.getOrDefault(layerName, Identifier.fromNamespaceAndPath(Raised.MOD_ID, "textures/gui/layer/default.png"));
    }

}