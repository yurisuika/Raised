package dev.yurisuika.raised.util;

import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.type.Element;
import dev.yurisuika.raised.util.type.Sync;
import net.minecraft.client.util.math.MatrixStack;

public class Translate {

    public static int getX(Element element) {
        return Option.getX(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element) * Option.getPosition(element).getX();
    }

    public static int getY(Element element) {
        return Option.getY(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element) * Option.getPosition(element).getY();
    }

    public static void start(MatrixStack matrixStack, Element element) {
        matrixStack.push();
        matrixStack.translate(getX(element), getY(element), 0);
    }

    public static void end(MatrixStack matrixStack) {
        matrixStack.pop();
    }

}