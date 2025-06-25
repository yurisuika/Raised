package dev.yurisuika.raised.client.gui;

import net.minecraft.util.Mth;
import net.minecraft.util.OptionEnum;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

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

        public enum X implements OptionEnum, StringRepresentable {
    
            LEFT(0, "options.raised.direction.x.left", -1),
            NONE(1, "options.raised.direction.x.none", 0),
            RIGHT(2, "options.raised.direction.x.right", 1);
    
            public static final EnumCodec<Direction.X> CODEC = StringRepresentable.fromEnum(Direction.X::values);
            public static final Direction.X[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(movement -> movement.id)).toArray(Direction.X[]::new);
            public final int id;
            public final String key;
            public final int x;
    
            X(int id, String key, int x) {
                this.id = id;
                this.key = key;
                this.x = x;
            }
    
            public int getId() {
                return id;
            }
    
            public String getKey() {
                return key;
            }
    
            public static Direction.X byName(String name) {
                return CODEC.byName(name);
            }
    
            public static Direction.X byId(int id) {
                return VALUES[Mth.abs(id % VALUES.length)];
            }
    
            public String getSerializedName() {
                return name().toLowerCase();
            }
    
            public int getX() {
                return x;
            }
    
        }

        public enum Y implements OptionEnum, StringRepresentable {
    
            UP(0, "options.raised.direction.y.up", -1),
            NONE(1, "options.raised.direction.y.none", 0),
            DOWN(2, "options.raised.direction.y.down", 1);
    
            public static final EnumCodec<Direction.Y> CODEC = StringRepresentable.fromEnum(Direction.Y::values);
            public static final Direction.Y[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(movement -> movement.id)).toArray(Direction.Y[]::new);
            public final int id;
            public final String key;
            public final int y;
    
            Y(int id, String key, int y) {
                this.id = id;
                this.key = key;
                this.y = y;
            }
    
            public int getId() {
                return id;
            }
    
            public String getKey() {
                return key;
            }
    
            public static Direction.Y byName(String name) {
                return CODEC.byName(name);
            }
    
            public static Direction.Y byId(int id) {
                return VALUES[Mth.abs(id % VALUES.length)];
            }
    
            public String getSerializedName() {
                return name().toLowerCase();
            }
    
            public int getY() {
                return y;
            }
    
        }

    }

}