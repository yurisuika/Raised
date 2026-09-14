package dev.yurisuika.raised.client.gui.layer;

import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;

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

    public static void boostrap() {
        LayerRegistry.register(HOTBAR, new Layer(Layer.Anchor.BOTTOM));
        LayerRegistry.register(CHAT, new Layer(Layer.Anchor.BOTTOM_LEFT));
        LayerRegistry.register(CHAT_INPUT, new Layer(Layer.Anchor.BOTTOM_LEFT));
        LayerRegistry.register(ACTION_BAR, new Layer(Layer.Anchor.BOTTOM));
        LayerRegistry.register(BOSS_BAR, new Layer(Layer.Anchor.TOP));
        LayerRegistry.register(SCOREBOARD, new Layer(Layer.Anchor.RIGHT));
        LayerRegistry.register(EFFECTS, new Layer(Layer.Anchor.TOP_RIGHT));
        LayerRegistry.register(PLAYER_LIST, new Layer(Layer.Anchor.TOP));
        LayerRegistry.register(TITLES, new Layer(Layer.Anchor.NONE));
        LayerRegistry.register(CLOSED_CAPTIONS, new Layer(Layer.Anchor.BOTTOM_RIGHT));
        LayerRegistry.register(TOASTS, new Layer(Layer.Anchor.TOP_RIGHT));
        LayerRegistry.register(UNKNOWN, new Layer(Layer.Anchor.NONE));
    }

}