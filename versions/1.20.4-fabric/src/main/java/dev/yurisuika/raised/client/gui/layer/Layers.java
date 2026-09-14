package dev.yurisuika.raised.client.gui.layer;

import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;

public class Layers {

    public static final ResourceLocation HOTBAR = new ResourceLocation("hotbar");
    public static final ResourceLocation CHAT = new ResourceLocation("chat");
    public static final ResourceLocation CHAT_INPUT = new ResourceLocation("chat_input");
    public static final ResourceLocation ACTION_BAR = new ResourceLocation("action_bar");
    public static final ResourceLocation BOSS_BAR = new ResourceLocation("boss_bar");
    public static final ResourceLocation SCOREBOARD = new ResourceLocation("scoreboard");
    public static final ResourceLocation EFFECTS = new ResourceLocation("effects");
    public static final ResourceLocation PLAYER_LIST = new ResourceLocation("player_list");
    public static final ResourceLocation TITLES = new ResourceLocation("titles");
    public static final ResourceLocation CLOSED_CAPTIONS = new ResourceLocation("closed_captions");
    public static final ResourceLocation TOASTS = new ResourceLocation("toasts");
    public static final ResourceLocation UNKNOWN = new ResourceLocation("unknown");

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