package dev.yurisuika.raised.client.gui.layer;

import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;

public class Layers {

    public static final ResourceLocation HOTBAR = ResourceLocation.withDefaultNamespace("hotbar");
    public static final ResourceLocation CHAT = ResourceLocation.withDefaultNamespace("chat");
    public static final ResourceLocation CHAT_INPUT = ResourceLocation.withDefaultNamespace("chat_input");
    public static final ResourceLocation ACTION_BAR = ResourceLocation.withDefaultNamespace("action_bar");
    public static final ResourceLocation BOSS_BAR = ResourceLocation.withDefaultNamespace("boss_bar");
    public static final ResourceLocation SCOREBOARD = ResourceLocation.withDefaultNamespace("scoreboard");
    public static final ResourceLocation EFFECTS = ResourceLocation.withDefaultNamespace("effects");
    public static final ResourceLocation PLAYER_LIST = ResourceLocation.withDefaultNamespace("player_list");
    public static final ResourceLocation TITLES = ResourceLocation.withDefaultNamespace("titles");
    public static final ResourceLocation CLOSED_CAPTIONS = ResourceLocation.withDefaultNamespace("closed_captions");
    public static final ResourceLocation TOASTS = ResourceLocation.withDefaultNamespace("toasts");
    public static final ResourceLocation UNKNOWN = ResourceLocation.withDefaultNamespace("unknown");

    public Layers() {}

    public static Layer createLayer(Layer.Position position) {
        return new Layer(position);
    }

    public static Layer createDefaultLayer() {
        return new Layer(Layer.Position.NONE);
    }

    public static void boostrap() {
        LayerRegistry.register(HOTBAR, createLayer(Layer.Position.BOTTOM));
        LayerRegistry.register(CHAT, createLayer(Layer.Position.BOTTOM_LEFT));
        LayerRegistry.register(CHAT_INPUT, createLayer(Layer.Position.BOTTOM_LEFT));
        LayerRegistry.register(ACTION_BAR, createLayer(Layer.Position.BOTTOM));
        LayerRegistry.register(BOSS_BAR, createLayer(Layer.Position.TOP));
        LayerRegistry.register(SCOREBOARD, createLayer(Layer.Position.RIGHT));
        LayerRegistry.register(EFFECTS, createLayer(Layer.Position.TOP_RIGHT));
        LayerRegistry.register(PLAYER_LIST, createLayer(Layer.Position.TOP));
        LayerRegistry.register(TITLES, createLayer(Layer.Position.NONE));
        LayerRegistry.register(CLOSED_CAPTIONS, createLayer(Layer.Position.BOTTOM_RIGHT));
        LayerRegistry.register(TOASTS, createLayer(Layer.Position.TOP_RIGHT));
        LayerRegistry.register(UNKNOWN, createLayer(Layer.Position.NONE));
    }

    public static class Curated {

        public static final HashMap<LayeredDraw.Layer, ResourceLocation> CURATED_LAYERS = new HashMap<>();

        public Curated() {}

    }

}