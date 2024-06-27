package dev.yurisuika.raised.util.type;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;

public enum Position implements TranslatableOption, StringIdentifiable {

    TOP(0, "options.raised.position.top", 0, 1),
    TOP_LEFT(1, "options.raised.position.top_left", 1, 1),
    TOP_RIGHT(2, "options.raised.position.top_right", -1, 1),
    LEFT(3, "options.raised.position.left", 1, 0),
    RIGHT(4, "options.raised.position.right", -1, 0),
    BOTTOM(5, "options.raised.position.bottom", 0 , -1),
    BOTTOM_LEFT(6, "options.raised.position.bottom_left", 1, -1),
    BOTTOM_RIGHT(7, "options.raised.position.bottom_right", -1, -1);

    public static final EnumCodec<Position> CODEC = StringIdentifiable.createCodec(Position::values);
    public static final Position[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(position -> position.id)).toArray(Position[]::new);
    public final int id;
    public final String translationKey;
    public final int x;
    public final int y;

    Position(int id, String translationKey, int x, int y) {
        this.id = id;
        this.translationKey = translationKey;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getTranslationKey() {
        return translationKey;
    }

    public static Position byName(String name) {
        return CODEC.byId(name);
    }

    public static Position byId(int id) {
        return VALUES[MathHelper.abs(id % VALUES.length)];
    }

    @Override
    public String asString() {
        return name().toLowerCase();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}