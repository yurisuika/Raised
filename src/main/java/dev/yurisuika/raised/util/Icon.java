package dev.yurisuika.raised.util;

import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

import java.util.TreeMap;

public class Icon {

    public static final TreeMap<ResourceLocation, ResourceLocation> TEXTURES = new TreeMap<ResourceLocation, ResourceLocation>();

    public static void checkResources() {
        Minecraft.getInstance().getResourcePackRepository().openAllSelected().forEach(pack -> LayerRegistry.LAYERS.forEach((name, layer) -> pack.listResources(PackType.CLIENT_RESOURCES, "raised", "textures/gui/layer/" + name.getNamespace() + "/" + name.getPath() + ".png", (location, supplier) -> TEXTURES.put(name, location))));
    }

    public static ResourceLocation getLayerIcon(ResourceLocation name) {
        return TEXTURES.getOrDefault(name, ResourceLocation.fromNamespaceAndPath("raised", "textures/gui/layer/default.png"));
    }

}