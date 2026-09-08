package dev.yurisuika.raised.client.gui.group;

import java.util.TreeSet;

public class Group {

    public Offset offset;
    public TreeSet<String> layers;

    public Group(Offset offset, TreeSet<String> layers) {
        this.offset = offset;
        this.layers = layers;
    }

    public Offset getOffset() {
        return offset;
    }

    public void setOffset(Offset offset) {
        this.offset = offset;
    }

    public TreeSet<String> getLayers() {
        return layers;
    }

    public void setLayers(TreeSet<String> layers) {
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