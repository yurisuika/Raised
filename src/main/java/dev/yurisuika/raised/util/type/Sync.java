package dev.yurisuika.raised.util.type;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public enum Sync implements StringIdentifiable {

    HOTBAR(0, "options.raised.sync.hotbar"),
    CHAT(1, "options.raised.sync.chat"),
    BOSSBAR(2, "options.raised.sync.bossbar"),
    SIDEBAR(3, "options.raised.sync.sidebar"),
    EFFECTS(4, "options.raised.sync.effects"),
    PLAYERS(5, "options.raised.sync.players"),
    TOASTS(6, "options.raised.sync.toasts"),
    OTHER(7, "options.raised.sync.other"),
    NONE(8,"options.raised.sync.none");

    public static final Codec<Sync> CODEC = StringIdentifiable.createCodec(Sync::values, Sync::byName);
    public static final Sync[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(type -> type.id)).toArray(Sync[]::new);
    public final int id;
    public final String translationKey;

    Sync(int id, String translationKey) {
        this.id = id;
        this.translationKey = translationKey;
    }

    public int getId() {
        return id;
    }

    public String getTranslationKey() {
        return translationKey;
    }

    public static Sync byName(String name) {
        return Arrays.stream(Sync.values()).collect(Collectors.toMap(Sync::asString, element -> element)).get(name);
    }

    public static Sync byId(int id) {
        return VALUES[MathHelper.abs(id % VALUES.length)];
    }

    @Override
    public String asString() {
        return name().toLowerCase();
    }

}