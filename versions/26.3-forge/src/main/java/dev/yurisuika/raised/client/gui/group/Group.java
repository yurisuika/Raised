package dev.yurisuika.raised.client.gui.group;

import dev.yurisuika.raised.client.gui.layer.Layer;

import java.util.TreeMap;

public class Group {

    public Offset offset;
    public TreeMap<String, Layer> layers;

    public Group(Offset offset, TreeMap<String, Layer> layers) {
        this.offset = offset;
        this.layers = layers;
    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public TreeMap<String, Layer> getLayers() {
        return layers;
    }

    public void setLayers(TreeMap<String, Layer> layers) {
        this.layers = layers;
    }

    public static class Offset {

        public int x;
        public int y;

        public Offset(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

    }

}