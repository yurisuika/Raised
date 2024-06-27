package dev.yurisuika.raised.util.type;

import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.TranslatableOption;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.Comparator;

public enum Element implements TranslatableOption, StringIdentifiable {

    HOTBAR(0, "options.raised.element.hotbar"),
    CHAT(1, "options.raised.element.chat"),
    BOSSBAR(2, "options.raised.element.bossbar"),
    SIDEBAR(3, "options.raised.element.sidebar"),
    EFFECTS(4, "options.raised.element.effects"),
    PLAYERS(5, "options.raised.element.players"),
    TOASTS(6, "options.raised.element.toasts"),
    OTHER(7, "options.raised.element.other");

    public static final EnumCodec<Element> CODEC = StringIdentifiable.createCodec(Element::values);
    public static final Element[] VALUES = Arrays.stream(values()).sorted(Comparator.comparingInt(type -> type.id)).toArray(Element[]::new);
    public final int id;
    public final String translationKey;

    Element(int id, String translationKey) {
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

    public static Element byName(String name) {
        return CODEC.byId(name);
    }

    public static Element byId(int id) {
        return VALUES[MathHelper.abs(id % VALUES.length)];
    }

    @Override
    public String asString() {
        return name().toLowerCase();
    }

}