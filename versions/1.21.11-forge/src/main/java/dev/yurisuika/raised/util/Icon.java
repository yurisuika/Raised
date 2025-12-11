package dev.yurisuika.raised.util;

import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.PackType;

import java.util.TreeMap;

public class Icon {

    public static final TreeMap<Identifier, Identifier> TEXTURES = new TreeMap<Identifier, Identifier>();

    public static void checkResources() {
        TEXTURES.clear();
        Minecraft.getInstance().getResourcePackRepository().openAllSelected().forEach(pack -> {
            LayerRegistry.LAYERS.forEach((name, layer) -> {
                Identifier location = Identifier.fromNamespaceAndPath("raised", "textures/gui/layer/" + name.getNamespace() + "/" + name.getPath() + ".png");
                if (pack.getResource(PackType.CLIENT_RESOURCES, location) != null) {
                    TEXTURES.put(name, location);
                }
            });
            pack.close();
        });
    }

    public static Identifier getLayerIcon(Identifier name) {
        return TEXTURES.getOrDefault(name, Identifier.fromNamespaceAndPath("raised", "textures/gui/layer/default.png"));
    }

}