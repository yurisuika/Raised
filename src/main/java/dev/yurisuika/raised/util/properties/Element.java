package dev.yurisuika.raised.util.properties;

import net.minecraft.util.Mth;
import net.minecraft.util.OptionEnum;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Comparator;

public enum Element implements OptionEnum, StringRepresentable {

    HOTBAR(0, "options.raised.element.hotbar"),
    CHAT(1, "options.raised.element.chat"),
    BOSSBAR(2, "options.raised.element.bossbar"),
    SIDEBAR(3, "options.raised.element.sidebar"),
    EFFECTS(4, "options.raised.element.effects"),
    PLAYERS(5, "options.raised.element.players"),
    TOASTS(6, "options.raised.element.toasts"),
    OTHER(7, "options.raised.element.other");

    public static final EnumCodec<Element> CODEC = StringRepresentable.fromEnum(Element::values);
    public static final Element[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(type -> type.id)).toArray(Element[]::new);
    public final int id;
    public final String key;

    Element(int id, String key) {
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

    public static Element byName(String name) {
        return CODEC.byName(name);
    }

    public static Element byId(int id) {
        return VALUES[Mth.abs(id % VALUES.length)];
    }

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }

}