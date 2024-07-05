package dev.yurisuika.raised.util.config;

import dev.yurisuika.raised.util.config.option.Elements;
import dev.yurisuika.raised.util.config.option.Properties;
import dev.yurisuika.raised.util.config.option.Toggle;
import dev.yurisuika.raised.util.type.Element;
import dev.yurisuika.raised.util.type.Position;
import dev.yurisuika.raised.util.type.Sync;
import dev.yurisuika.raised.util.type.Texture;

public class Option {

    public static Elements getElements() {
        return Config.config.elements;
    }

    public static void setElements(Elements elements) {
        Config.config.elements = elements;
        Config.saveConfig();
    }

    public static Properties getProperties(Element element) {
        return switch (element) {
            case HOTBAR -> getElements().getHotbar();
            case CHAT -> getElements().getChat();
            case BOSSBAR -> getElements().getBossbar();
            case SIDEBAR -> getElements().getSidebar();
            case EFFECTS -> getElements().getEffects();
            case PLAYERS -> getElements().getPlayers();
            case TOASTS -> getElements().getToasts();
            case OTHER -> getElements().getOther();
        };
    }

    public static int getX(Element element) {
        return getProperties(element).getX();
    }

    public static void setX(Element element, int x) {
        getProperties(element).x = x;
        Config.saveConfig();
    }

    public static int getY(Element element) {
        return getProperties(element).getY();
    }

    public static void setY(Element element, int y) {
        getProperties(element).y = y;
        Config.saveConfig();
    }

    public static Position getPosition(Element element) {
        return getProperties(element).getPosition();
    }

    public static void setPosition(Element element, Position position) {
        getProperties(element).position = position;
        Config.saveConfig();
    }

    public static Sync getSync(Element element) {
        return getProperties(element).getSync();
    }

    public static void setSync(Element element, Sync sync) {
        getProperties(element).sync = sync;
        Config.saveConfig();
    }

    public static Toggle getToggle() {
        return Config.config.toggle;
    }

    public static void setToggle(Toggle toggle) {
        Config.config.toggle = toggle;
        Config.saveConfig();
    }

    public static Texture getTexture() {
        return getToggle().texture;
    }

    public static void setTexture(Texture value) {
        getToggle().texture = value;
        Config.saveConfig();
    }

}