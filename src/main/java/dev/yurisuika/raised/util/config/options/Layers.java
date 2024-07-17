package dev.yurisuika.raised.util.config.options;

public class Layers {

    public Properties hotbar;
    public Properties chat;
    public Properties bossbar;
    public Properties sidebar;
    public Properties effects;
    public Properties players;
    public Properties toasts;
    public Properties other;

    public Layers(Properties hotbar, Properties chat, Properties bossbar, Properties sidebar, Properties effects, Properties players, Properties toasts, Properties other) {
        this.hotbar = hotbar;
        this.chat = chat;
        this.bossbar = bossbar;
        this.sidebar = sidebar;
        this.effects = effects;
        this.players = players;
        this.toasts = toasts;
        this.other = other;
    }

    public Properties getHotbar() {
        return hotbar;
    }

    public void setHotbar(Properties properties) {
        this.hotbar = properties;
    }

    public Properties getChat() {
        return chat;
    }

    public void setChat(Properties properties) {
        this.chat = properties;
    }

    public Properties getBossbar() {
        return bossbar;
    }

    public void setBossbar(Properties properties) {
        this.bossbar = properties;
    }

    public Properties getSidebar() {
        return sidebar;
    }

    public void setSidebar(Properties properties) {
        this.sidebar = properties;
    }

    public Properties getEffects() {
        return effects;
    }

    public void setEffects(Properties properties) {
        this.effects = properties;
    }

    public Properties getPlayers() {
        return players;
    }

    public void setPlayers(Properties properties) {
        this.players = properties;
    }

    public Properties getToasts() {
        return toasts;
    }

    public void setToasts(Properties properties) {
        this.toasts = properties;
    }

    public Properties getOther() {
        return other;
    }

    public void setOther(Properties properties) {
        this.other = properties;
    }

}