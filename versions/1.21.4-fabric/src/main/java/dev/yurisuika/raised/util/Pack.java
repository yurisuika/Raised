package dev.yurisuika.raised.util;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

import java.util.concurrent.atomic.AtomicBoolean;

public class Pack {

    public static boolean pack = false;

    public static boolean getPack() {
        return pack;
    }

    public static void setPack(boolean pack) {
        Pack.pack = pack;
    }

    public static void checkResources() {
        AtomicBoolean exists = new AtomicBoolean(false);

        Minecraft.getInstance().getResourcePackRepository().openAllSelected().forEach(pack -> {
            if (!pack.packId().contentEquals("raised")) {
                if (pack.getResource(PackType.CLIENT_RESOURCES, ResourceLocation.fromNamespaceAndPath("raised", "textures/gui/sprites/hud/hotbar_selection.png")) != null) {
                    exists.set(true);
                }
            }
            pack.close();
        });

        setPack(exists.get());
    }

}