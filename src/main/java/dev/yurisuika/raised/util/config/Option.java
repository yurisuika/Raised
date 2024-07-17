package dev.yurisuika.raised.util.config;

import dev.yurisuika.raised.util.config.options.Layers;
import dev.yurisuika.raised.util.config.options.Properties;
import dev.yurisuika.raised.util.properties.Element;
import dev.yurisuika.raised.util.properties.Position;
import dev.yurisuika.raised.util.properties.Sync;

public class Option {

    public static Layers getLayers() {
        return Config.getOptions().getLayers();
    }

    public static void setLayers(Layers layers) {
        Config.getOptions().setLayers(layers);
        Config.saveConfig();
    }

    public static Properties getProperties(Element element) {
        if (element.equals(Element.HOTBAR)) {
            return getLayers().getHotbar();
        } else if (element.equals(Element.CHAT)) {
            return getLayers().getChat();
        } else if (element.equals(Element.BOSSBAR)) {
            return getLayers().getBossbar();
        } else if (element.equals(Element.SIDEBAR)) {
            return getLayers().getSidebar();
        } else if (element.equals(Element.EFFECTS)) {
            return getLayers().getEffects();
        } else if (element.equals(Element.PLAYERS)) {
            return getLayers().getPlayers();
        } else if (element.equals(Element.TOASTS)) {
            return getLayers().getToasts();
        } else {
            return getLayers().getOther();
        }
    }

    public static void setProperties(Element element, Properties properties) {
        if (element.equals(Element.HOTBAR)) {
            getLayers().setHotbar(properties);
        } else if (element.equals(Element.CHAT)) {
            getLayers().setChat(properties);
        } else if (element.equals(Element.BOSSBAR)) {
            getLayers().setBossbar(properties);
        } else if (element.equals(Element.SIDEBAR)) {
            getLayers().setSidebar(properties);
        } else if (element.equals(Element.EFFECTS)) {
            getLayers().setEffects(properties);
        } else if (element.equals(Element.PLAYERS)) {
            getLayers().setPlayers(properties);
        } else if (element.equals(Element.TOASTS)) {
            getLayers().setToasts(properties);
        } else {
            getLayers().setOther(properties);
        }
        Config.saveConfig();
    }

    public static int getX(Element element) {
        return getProperties(element).getX();
    }

    public static void setX(Element element, int x) {
        getProperties(element).setX(x);
        Config.saveConfig();
    }

    public static int getY(Element element) {
        return getProperties(element).getY();
    }

    public static void setY(Element element, int y) {
        getProperties(element).setY(y);
        Config.saveConfig();
    }

    public static Position getPosition(Element element) {
        return getProperties(element).getPosition();
    }

    public static void setPosition(Element element, Position position) {
        getProperties(element).setPosition(position);
        Config.saveConfig();
    }

    public static Sync getSync(Element element) {
        return getProperties(element).getSync();
    }

    public static void setSync(Element element, Sync sync) {
        getProperties(element).setSync(sync);
        Config.saveConfig();
    }

}