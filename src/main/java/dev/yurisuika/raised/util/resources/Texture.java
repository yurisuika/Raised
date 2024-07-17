package dev.yurisuika.raised.util.resources;

import net.minecraft.util.Mth;
import net.minecraft.util.OptionEnum;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

public enum Texture implements OptionEnum, StringRepresentable {

    REPLACE(0, "options.raised.texture.replace"),
    PATCH(1, "options.raised.texture.patch"),
    AUTO(2, "options.raised.texture.auto"),
    NONE(3, "options.raised.texture.none");

    public static final EnumCodec<Texture> CODEC = StringRepresentable.fromEnum(Texture::values);
    public static final Texture[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(type -> type.id)).toArray(Texture[]::new);
    public final int id;
    public final String key;

    Texture(int id, String key) {
        this.id = id;
        this.key = key;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getKey() {
        return key;
    }

    public static Texture byName(String name) {
        return CODEC.byName(name);
    }

    public static Texture byId(int id) {
        return VALUES[Mth.abs(id % VALUES.length)];
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }

}