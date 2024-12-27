package dev.yurisuika.raised.util.properties;

import com.mojang.serialization.Codec;
import net.minecraft.util.Mth;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public enum Position implements StringRepresentable {

    TOP(0, "options.raised.position.top", 0, 1),
    TOP_LEFT(1, "options.raised.position.top_left", 1, 1),
    TOP_RIGHT(2, "options.raised.position.top_right", -1, 1),
    LEFT(3, "options.raised.position.left", 1, 0),
    RIGHT(4, "options.raised.position.right", -1, 0),
    BOTTOM(5, "options.raised.position.bottom", 0 , -1),
    BOTTOM_LEFT(6, "options.raised.position.bottom_left", 1, -1),
    BOTTOM_RIGHT(7, "options.raised.position.bottom_right", -1, -1);

    public static final Codec<Position> CODEC = StringRepresentable.fromEnum(Position::values, Position::byName);
    public static final Position[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(position -> position.id)).toArray(Position[]::new);
    public final int id;
    public final String key;
    public final int x;
    public final int y;

    Position(int id, String key, int x, int y) {
        this.id = id;
        this.key = key;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public static Position byName(String name) {
        return Arrays.stream(Position.values()).collect(Collectors.toMap(Position::getSerializedName, element -> element)).get(name);
    }

    public static Position byId(int id) {
        return VALUES[Mth.abs(id % VALUES.length)];
    }

    public String getSerializedName() {
        return name().toLowerCase();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}