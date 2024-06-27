package dev.yurisuika.raised.util.type;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;

public enum Texture implements TranslatableOption, StringIdentifiable {

    REPLACE(0, "options.raised.texture.replace"),
    PATCH(1, "options.raised.texture.patch"),
    AUTO(2, "options.raised.texture.auto"),
    NONE(3, "options.raised.texture.none");

    public static final EnumCodec<Texture> CODEC = StringIdentifiable.createCodec(Texture::values);
    public static final Texture[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(type -> type.id)).toArray(Texture[]::new);
    public final int id;
    public final String translationKey;

    Texture(int id, String translationKey) {
        this.id = id;
        this.translationKey = translationKey;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getTranslationKey() {
        return translationKey;
    }

    public static Texture byName(String name) {
        return CODEC.byId(name);
    }

    public static Texture byId(int id) {
        return VALUES[MathHelper.abs(id % VALUES.length)];
    }

    @Override
    public String asString() {
        return name().toLowerCase();
    }

}