package dev.yurisuika.raised.util;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.properties.Element;
import dev.yurisuika.raised.util.properties.Sync;

public class Translate {

    public static int getX(Element element) {
        return Option.getX(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element) * Option.getPosition(element).getX();
    }

    public static int getY(Element element) {
        return Option.getY(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element) * Option.getPosition(element).getY();
    }

    public static void start(Element element) {
        RenderSystem.pushMatrix();
        RenderSystem.translated(getX(element), getY(element), element == Element.CHAT ? 300 : 0);
    }

    public static void end() {
        RenderSystem.popMatrix();
    }

}