package dev.yurisuika.raised.client.gui.layer;

import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.Identifier;

public class Layers {

    public static final Identifier HOTBAR = Identifier.withDefaultNamespace("hotbar");
    public static final Identifier CHAT = Identifier.withDefaultNamespace("chat");
    public static final Identifier CHAT_INPUT = Identifier.withDefaultNamespace("chat_input");
    public static final Identifier ACTION_BAR = Identifier.withDefaultNamespace("action_bar");
    public static final Identifier BOSS_BAR = Identifier.withDefaultNamespace("boss_bar");
    public static final Identifier SCOREBOARD = Identifier.withDefaultNamespace("scoreboard");
    public static final Identifier EFFECTS = Identifier.withDefaultNamespace("effects");
    public static final Identifier PLAYER_LIST = Identifier.withDefaultNamespace("player_list");
    public static final Identifier TITLES = Identifier.withDefaultNamespace("titles");
    public static final Identifier CLOSED_CAPTIONS = Identifier.withDefaultNamespace("closed_captions");
    public static final Identifier TOASTS = Identifier.withDefaultNamespace("toasts");
    public static final Identifier UNKNOWN = Identifier.withDefaultNamespace("unknown");

    public Layers() {}

    public static Layer createLayer(Layer.Anchor anchor) {
        return new Layer(anchor);
    }

    public static Layer createDefaultLayer() {
        return new Layer(Layer.Anchor.NONE);
    }

    public static void boostrap() {
        LayerRegistry.register(HOTBAR, createLayer(Layer.Anchor.BOTTOM));
        LayerRegistry.register(CHAT, createLayer(Layer.Anchor.BOTTOM_LEFT));
        LayerRegistry.register(CHAT_INPUT, createLayer(Layer.Anchor.BOTTOM_LEFT));
        LayerRegistry.register(ACTION_BAR, createLayer(Layer.Anchor.BOTTOM));
        LayerRegistry.register(BOSS_BAR, createLayer(Layer.Anchor.TOP));
        LayerRegistry.register(SCOREBOARD, createLayer(Layer.Anchor.RIGHT));
        LayerRegistry.register(EFFECTS, createLayer(Layer.Anchor.TOP_RIGHT));
        LayerRegistry.register(PLAYER_LIST, createLayer(Layer.Anchor.TOP));
        LayerRegistry.register(TITLES, createLayer(Layer.Anchor.NONE));
        LayerRegistry.register(CLOSED_CAPTIONS, createLayer(Layer.Anchor.BOTTOM_RIGHT));
        LayerRegistry.register(TOASTS, createLayer(Layer.Anchor.TOP_RIGHT));
        LayerRegistry.register(UNKNOWN, createLayer(Layer.Anchor.NONE));
    }

}