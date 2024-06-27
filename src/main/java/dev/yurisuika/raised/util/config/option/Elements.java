package dev.yurisuika.raised.util.config.option;

public class Elements {

    public Properties.Hotbar hotbar;
    public Properties.Chat chat;
    public Properties.Bossbar bossbar;
    public Properties.Sidebar sidebar;
    public Properties.Effects effects;
    public Properties.Players players;
    public Properties.Toasts toasts;
    public Properties.Other other;

    public Elements(Properties.Hotbar hotbar, Properties.Chat chat, Properties.Bossbar bossbar, Properties.Sidebar sidebar, Properties.Effects effects, Properties.Players players, Properties.Toasts toasts, Properties.Other other) {
        this.hotbar = hotbar;
        this.chat = chat;
        this.bossbar = bossbar;
        this.sidebar = sidebar;
        this.effects = effects;
        this.players = players;
        this.toasts = toasts;
        this.other = other;
    }

    public Properties.Hotbar getHotbar() {
        return hotbar;
    }

    public Properties.Chat getChat() {
        return chat;
    }

    public Properties.Bossbar getBossbar() {
        return bossbar;
    }

    public Properties.Sidebar getSidebar() {
        return sidebar;
    }

    public Properties.Effects getEffects() {
        return effects;
    }

    public Properties.Players getPlayers() {
        return players;
    }

    public Properties.Toasts getToasts() {
        return toasts;
    }

    public Properties.Other getOther() {
        return other;
    }

}