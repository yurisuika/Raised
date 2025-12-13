package dev.yurisuika.raised.util;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

import java.util.TreeMap;

public class Icon {

    public static final TreeMap<ResourceLocation, ResourceLocation> TEXTURES = new TreeMap<ResourceLocation, ResourceLocation>();

    public static void checkResources() {
        TEXTURES.clear();
        Minecraft.getInstance().getResourcePackRepository().openAllSelected().forEach(pack -> {
            LayerRegistry.LAYERS.forEach((name, layer) -> {
                ResourceLocation location = ResourceLocation.fromNamespaceAndPath(Raised.MOD_ID, "textures/gui/layer/" + name.getNamespace() + "/" + name.getPath() + ".png");
                if (pack.getResource(PackType.CLIENT_RESOURCES, location) != null) {
                    TEXTURES.put(name, location);
                }
            });
            pack.close();
        });
    }

    public static ResourceLocation getLayerIcon(ResourceLocation name) {
        return TEXTURES.getOrDefault(name, ResourceLocation.fromNamespaceAndPath(Raised.MOD_ID, "textures/gui/layer/default.png"));
    }

}