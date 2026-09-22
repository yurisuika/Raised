package dev.yurisuika.raised.client.gui.layer;

import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.resources.Identifier;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

import java.util.HashMap;

public class Layers {

    public static final Identifier HOTBAR = Identifier.withDefaultNamespace("hotbar");
    public static final Identifier CHAT = Identifier.withDefaultNamespace("chat");
    public static final Identifier CHAT_INPUT = Identifier.withDefaultNamespace("chat_input");
    public static final Identifier ACTION_BAR = Identifier.withDefaultNamespace("action_bar");
    public static final Identifier BOSSBAR = Identifier.withDefaultNamespace("bossbar");
    public static final Identifier SCOREBOARD = Identifier.withDefaultNamespace("scoreboard");
    public static final Identifier EFFECTS = Identifier.withDefaultNamespace("effects");
    public static final Identifier PLAYER_LIST = Identifier.withDefaultNamespace("player_list");
    public static final Identifier TITLES = Identifier.withDefaultNamespace("titles");
    public static final Identifier CLOSED_CAPTIONS = Identifier.withDefaultNamespace("closed_captions");
    public static final Identifier TOASTS = Identifier.withDefaultNamespace("toasts");
    public static final Identifier UNKNOWN = Identifier.withDefaultNamespace("unknown");

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

    public static class Curated {

        public static final HashMap<Identifier, Identifier> CURATED_LAYERS = new HashMap<>();
        public boolean translated = false;

        public Curated() {}

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startTranslate(RenderGuiLayerEvent.Pre event) {
            if (Curated.CURATED_LAYERS.containsKey(event.getName())) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getGuiGraphics().pose(), Curated.CURATED_LAYERS.get(event.getName()));
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endTranslate(RenderGuiLayerEvent.Pre event) {
            if (Curated.CURATED_LAYERS.containsKey(event.getName()) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose(), Curated.CURATED_LAYERS.get(event.getName()));
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endTranslate(RenderGuiLayerEvent.Post event) {
            if (Curated.CURATED_LAYERS.containsKey(event.getName())) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose(), Curated.CURATED_LAYERS.get(event.getName()));
                }
            }
        }

    }

}