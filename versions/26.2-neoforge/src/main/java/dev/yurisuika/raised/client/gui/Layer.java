package dev.yurisuika.raised.client.gui;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

public class Layer {

    public Displacement displacement;
    public Direction direction;
    public String sync;

    public Layer(Displacement displacement, Direction direction, String sync) {
        this.displacement = displacement;
        this.direction = direction;
        this.sync = sync;
    }

    public Displacement getDisplacement() {
        return displacement;
    }

    public void setDisplacement(Displacement displacement) {
        this.displacement = displacement;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getSync() {
        return sync;
    }

    public void setSync(String sync) {
        this.sync = sync;
    }

    public static class Displacement {

        public int x;
        public int y;

        public Displacement(int x, int y) {
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

    public static class Direction {

        public X x;
        public Y y;

        public Direction(X x, Y y) {
            this.x = x;
            this.y = y;
        }

        public X getX() {
            return x;
        }

        public void setX(X x) {
            this.x = x;
        }

        public Y getY() {
            return y;
        }

        public void setY(Y y) {
            this.y = y;
        }

        public enum X implements StringRepresentable {

            LEFT("left", "options.raised.direction.x.left", -1),
            NONE("none", "options.raised.direction.x.none", 0),
            RIGHT("right", "options.raised.direction.x.right", 1);

            public static final EnumCodec<X> CODEC = StringRepresentable.fromEnum(X::values);
            public final String name;
            public final Component caption;
            public final int x;

            X(String name, String key, int x) {
                this.name = name;
                this.caption = Component.translatable(key);
                this.x = x;
            }

            public Component caption() {
                return caption;
            }

            public String getSerializedName() {
                return name;
            }

            public int getX() {
                return x;
            }

        }

        public enum Y implements StringRepresentable {

            UP("up", "options.raised.direction.y.up", -1),
            NONE("none", "options.raised.direction.y.none", 0),
            DOWN("down", "options.raised.direction.y.down", 1);

            public static final EnumCodec<Y> CODEC = StringRepresentable.fromEnum(Y::values);
            public final String name;
            public final Component caption;
            public final int y;

            Y(String name, String key, int y) {
                this.name = name;
                this.caption = Component.translatable(key);
                this.y = y;
            }

            public Component caption() {
                return caption;
            }

            public String getSerializedName() {
                return name;
            }

            public int getY() {
                return y;
            }

        }

    }

}