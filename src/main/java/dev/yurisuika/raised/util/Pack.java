package dev.yurisuika.raised.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.resource.ResourceType;

import java.util.concurrent.atomic.AtomicBoolean;

public class Pack {

    public static boolean pack = false;

    public static boolean getPack() {
        return pack;
    }

    public static void checkForResource() {
        AtomicBoolean resource = new AtomicBoolean(false);

        MinecraftClient.getInstance().getResourcePackManager().createResourcePacks().forEach(pack -> {
            if (!pack.getId().contentEquals("raised")) {
                pack.findResources(ResourceType.CLIENT_RESOURCES, "raised", "textures/gui/sprites/hud/hotbar_selection.png", (id, supplier) -> {
                    resource.set(true);
                });
            }
        });

        pack = resource.get();
    }

}