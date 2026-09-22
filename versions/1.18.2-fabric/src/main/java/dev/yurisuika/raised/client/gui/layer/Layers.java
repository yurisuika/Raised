package dev.yurisuika.raised.client.gui.layer;

import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;

public class Layers {

    public static final ResourceLocation HOTBAR = new ResourceLocation("hotbar");
    public static final ResourceLocation CHAT = new ResourceLocation("chat");
    public static final ResourceLocation CHAT_INPUT = new ResourceLocation("chat_input");
    public static final ResourceLocation ACTION_BAR = new ResourceLocation("action_bar");
    public static final ResourceLocation BOSSBAR = new ResourceLocation("bossbar");
    public static final ResourceLocation SCOREBOARD = new ResourceLocation("scoreboard");
    public static final ResourceLocation EFFECTS = new ResourceLocation("effects");
    public static final ResourceLocation PLAYER_LIST = new ResourceLocation("player_list");
    public static final ResourceLocation TITLES = new ResourceLocation("titles");
    public static final ResourceLocation CLOSED_CAPTIONS = new ResourceLocation("closed_captions");
    public static final ResourceLocation TOASTS = new ResourceLocation("toasts");
    public static final ResourceLocation UNKNOWN = new ResourceLocation("unknown");

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
        LayerRegistry.register(BOSSBAR, createLayer(Layer.Position.TOP));
        LayerRegistry.register(SCOREBOARD, createLayer(Layer.Position.RIGHT));
        LayerRegistry.register(EFFECTS, createLayer(Layer.Position.TOP_RIGHT));
        LayerRegistry.register(PLAYER_LIST, createLayer(Layer.Position.TOP));
        LayerRegistry.register(TITLES, createLayer(Layer.Position.NONE));
        LayerRegistry.register(CLOSED_CAPTIONS, createLayer(Layer.Position.BOTTOM_RIGHT));
        LayerRegistry.register(TOASTS, createLayer(Layer.Position.TOP_RIGHT));
        LayerRegistry.register(UNKNOWN, createLayer(Layer.Position.NONE));
    }

}