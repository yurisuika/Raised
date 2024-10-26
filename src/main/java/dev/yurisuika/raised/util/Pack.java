package dev.yurisuika.raised.util;

import net.minecraft.client.Minecraft;
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
                pack.listResources(PackType.CLIENT_RESOURCES, "raised", "textures/gui/sprites/hud/hotbar_selection.png", (id, supplier) -> exists.set(true));
            }
        });

        setPack(exists.get());
    }

}