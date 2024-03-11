package dev.yurisuika.raised.client.gui;

import net.minecraft.client.gui.DrawContext;

public class RaisedGui {

    public static void start(DrawContext context, int x, int y, int z) {
        context.getMatrices().push();
        context.getMatrices().translate(x, -y, +z);
    }

    public static void end(DrawContext context) {
        context.getMatrices().pop();
    }

}