package dev.yurisuika.raised.util.type;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;

public enum Sync implements TranslatableOption, StringIdentifiable {

    HOTBAR(0, "options.raised.sync.hotbar"),
    CHAT(1, "options.raised.sync.chat"),
    BOSSBAR(2, "options.raised.sync.bossbar"),
    SIDEBAR(3, "options.raised.sync.sidebar"),
    EFFECTS(4, "options.raised.sync.effects"),
    PLAYERS(5, "options.raised.sync.players"),
    TOASTS(6, "options.raised.sync.toasts"),
    OTHER(7, "options.raised.sync.other"),
    NONE(8,"options.raised.sync.none");

    public static final EnumCodec<Sync> CODEC = StringIdentifiable.createCodec(Sync::values);
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

    @Override
    public String getTranslationKey() {
        return translationKey;
    }

    public static Sync byName(String name) {
        return CODEC.byId(name);
    }

    public static Sync byId(int id) {
        return VALUES[MathHelper.abs(id % VALUES.length)];
    }

    @Override
    public String asString() {
        return name().toLowerCase();
    }

}