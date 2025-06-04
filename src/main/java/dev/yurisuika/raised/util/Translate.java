package dev.yurisuika.raised.util;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

public class Translate {

    public static int getX(ResourceKey<Layer> name) {
        return getX(name.location().toString());
    }

    public static int getX(ResourceLocation name) {
        return getX(name.toString());
    }

    public static int getX(String name) {
        if (Option.getLayer(name) == null) {
            return 0;
        }
        if (Option.getLayer(Option.getSync(name)) == null) {
            return Option.getDisplacementX(name) * Option.getDirectionX(name).getX();
        }
        return Option.getDisplacementX(Option.getSync(name)) * Option.getDirectionX(name).getX();
    }

    public static int getY(ResourceKey<Layer> name) {
        return getY(name.location().toString());
    }

    public static int getY(ResourceLocation name) {
        return getY(name.toString());
    }

    public static int getY(String name) {
        if (Option.getLayer(name) == null) {
            return 0;
        }
        if (Option.getLayer(Option.getSync(name)) == null) {
            return Option.getDisplacementY(name) * Option.getDirectionX(name).getX();
        }
        return Option.getDisplacementY(Option.getSync(name)) * Option.getDirectionY(name).getY();
    }

    public static void start(ResourceKey<Layer> name) {
        start(name.location().toString());
    }

    public static void start(ResourceLocation name) {
        start(name.toString());
    }

    public static void start(String name) {
        RenderSystem.pushMatrix();
        RenderSystem.translated(getX(name), getY(name), name.equals(Layers.CHAT) ? 300 : 0);
    }

    public static void end() {
        RenderSystem.popMatrix();
    }

}