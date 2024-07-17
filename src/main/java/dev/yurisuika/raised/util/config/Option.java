package dev.yurisuika.raised.util.config;

import dev.yurisuika.raised.util.config.options.Layers;
import dev.yurisuika.raised.util.config.options.Properties;
import dev.yurisuika.raised.util.config.options.Resources;
import dev.yurisuika.raised.util.properties.Element;
import dev.yurisuika.raised.util.properties.Position;
import dev.yurisuika.raised.util.properties.Sync;
import dev.yurisuika.raised.util.resources.Texture;

public class Option {

    public static Layers getLayers() {
        return Config.getOptions().getLayers();
    }

    public static void setLayers(Layers layers) {
        Config.getOptions().setLayers(layers);
        Config.saveConfig();
    }

    public static Properties getProperties(Element element) {
        return switch (element) {
            case HOTBAR -> getLayers().getHotbar();
            case CHAT -> getLayers().getChat();
            case BOSSBAR -> getLayers().getBossbar();
            case SIDEBAR -> getLayers().getSidebar();
            case EFFECTS -> getLayers().getEffects();
            case PLAYERS -> getLayers().getPlayers();
            case TOASTS -> getLayers().getToasts();
            case OTHER -> getLayers().getOther();
        };
    }

    public static void setProperties(Element element, Properties properties) {
        switch (element) {
            case HOTBAR -> getLayers().setHotbar(properties);
            case CHAT -> getLayers().setChat(properties);
            case BOSSBAR -> getLayers().setBossbar(properties);
            case SIDEBAR -> getLayers().setSidebar(properties);
            case EFFECTS -> getLayers().setEffects(properties);
            case PLAYERS -> getLayers().setPlayers(properties);
            case TOASTS -> getLayers().setToasts(properties);
            case OTHER -> getLayers().setOther(properties);
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

    public static Resources getResources() {
        return Config.getOptions().getResources();
    }

    public static void setResources(Resources resources) {
        Config.getOptions().setResources(resources);
        Config.saveConfig();
    }

    public static Texture getTexture() {
        return getResources().getTexture();
    }

    public static void setTexture(Texture texture) {
        getResources().setTexture(texture);
        Config.saveConfig();
    }

}