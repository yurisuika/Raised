package dev.yurisuika.raised.util;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;

public class Translate {

    public static int getX(ResourceLocation name) {
        return getX(name.toString());
    }

    public static int getX(String name) {
        return Configure.getDisplacementX(Configure.getLayer(Configure.getSync(name)) == null ? name : Configure.getSync(name)) * Configure.getDirectionX(name).getX();
    }

    public static int getY(ResourceLocation name) {
        return getY(name.toString());
    }

    public static int getY(String name) {
        return Configure.getDisplacementY(Configure.getLayer(Configure.getSync(name)) == null ? name : Configure.getSync(name)) * Configure.getDirectionY(name).getY();
    }

    public static void start(ResourceLocation name) {
        start(name.toString());
    }

    public static void start(String name) {
        if (should(name)) {
            RenderSystem.pushMatrix();
            RenderSystem.translated(getX(name), getY(name), name.equals(LayerRegistry.CHAT.toString()) ? 300 : 0);
        }
    }

    public static void end(ResourceLocation name) {
        end(name.toString());
    }

    public static void end(String name) {
        if (should(name)) {
            RenderSystem.popMatrix();
        }
    }

    public static boolean should(String name) {
        return !(getX(name) == 0 && getY(name) == 0);
    }

}