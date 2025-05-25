package dev.yurisuika.raised.util;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.yurisuika.raised.util.config.Option;

public class Translate {

    public static int getX(String name) {
        if (Option.getLayer(name) == null) {
            return 0;
        }
        if (Option.getLayer(Option.getSync(name)) == null) {
            return Option.getDisplacementX(name) * Option.getDirectionX(name).getX();
        }
        return Option.getDisplacementX(Option.getSync(name)) * Option.getDirectionX(name).getX();
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

    public static void start(String name) {
        RenderSystem.pushMatrix();
        RenderSystem.translated(getX(name), getY(name), name.equals(Layers.CHAT.toString()) ? 300 : 0);
    }

    public static void end() {
        RenderSystem.popMatrix();
    }

}