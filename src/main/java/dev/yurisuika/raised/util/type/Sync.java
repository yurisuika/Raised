package dev.yurisuika.raised.util.type;

import net.minecraft.util.Mth;
import net.minecraft.util.OptionEnum;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

public enum Sync implements OptionEnum, StringRepresentable {

    HOTBAR(0, "options.raised.sync.hotbar"),
    CHAT(1, "options.raised.sync.chat"),
    BOSSBAR(2, "options.raised.sync.bossbar"),
    SIDEBAR(3, "options.raised.sync.sidebar"),
    EFFECTS(4, "options.raised.sync.effects"),
    PLAYERS(5, "options.raised.sync.players"),
    TOASTS(6, "options.raised.sync.toasts"),
    OTHER(7, "options.raised.sync.other"),
    NONE(8,"options.raised.sync.none");

    public static final EnumCodec<Sync> CODEC = StringRepresentable.fromEnum(Sync::values);
    public static final Sync[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(type -> type.id)).toArray(Sync[]::new);
    public final int id;
    public final String key;

    Sync(int id, String key) {
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

    public static Sync byName(String name) {
        return CODEC.byName(name);
    }

    public static Sync byId(int id) {
        return VALUES[Mth.abs(id % VALUES.length)];
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }

}