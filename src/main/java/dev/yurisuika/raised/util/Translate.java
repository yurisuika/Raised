package dev.yurisuika.raised.util;

import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.type.Element;
import dev.yurisuika.raised.util.type.Sync;
import net.minecraft.client.util.math.MatrixStack;

public class Translate {

    public static void start(MatrixStack matrixStack, Element element) {
        matrixStack.push();
        matrixStack.translate(Option.getX(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element) * Option.getPosition(element).getX(), Option.getY(Option.getSync(element) != Sync.NONE ? Element.byId(Option.getSync(element).getId()) : element) * Option.getPosition(element).getY(), 0);
    }

    public static void end(MatrixStack matrixStack) {
        matrixStack.pop();
    }

}