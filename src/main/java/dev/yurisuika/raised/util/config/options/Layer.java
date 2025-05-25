package dev.yurisuika.raised.util.config.options;

import com.mojang.serialization.Codec;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

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
    
            LEFT(0, "options.raised.direction.x.left", -1),
            NONE(1, "options.raised.direction.x.none", 0),
            RIGHT(2, "options.raised.direction.x.right", 1);
    
            public static final Codec<X> CODEC = StringRepresentable.fromEnum(X::values, X::byName);
            public static final X[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(movement -> movement.id)).toArray(X[]::new);
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
    
            public static X byName(String name) {
                return Arrays.stream(X.values()).collect(Collectors.toMap(X::getSerializedName, element -> element)).get(name);
            }
    
            public static X byId(int id) {
                return VALUES[Mth.abs(id % VALUES.length)];
            }
    
            public String getSerializedName() {
                return name().toLowerCase();
            }
    
            public int getX() {
                return x;
            }
    
        }

        public enum Y implements StringRepresentable {
    
            UP(0, "options.raised.direction.y.up", -1),
            NONE(1, "options.raised.direction.y.none", 0),
            DOWN(2, "options.raised.direction.y.down", 1);
    
            public static final Codec<Y> CODEC = StringRepresentable.fromEnum(Y::values, Y::byName);
            public static final Y[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(movement -> movement.id)).toArray(Y[]::new);
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
    
            public static Y byName(String name) {
                return Arrays.stream(Y.values()).collect(Collectors.toMap(Y::getSerializedName, element -> element)).get(name);
            }
    
            public static Y byId(int id) {
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