package dev.yurisuika.raised.client.gui;

import net.minecraft.client.gui.DrawContext;

public class RaisedGui {

    public static void start(DrawContext context, int x, int y, int z) {
        context.getMatrices().translate(x, -y, +z);
    }

    public static void end(DrawContext context, int x, int y, int z) {
        context.getMatrices().translate(x, +y, -z);
    }

}