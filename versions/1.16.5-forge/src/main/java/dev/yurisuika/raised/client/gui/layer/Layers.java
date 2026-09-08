package dev.yurisuika.raised.client.gui.layer;

import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.spongepowered.asm.mixin.Unique;

import java.util.HashMap;
import java.util.Map;

public class Layers {

    public static final ResourceLocation HOTBAR = new ResourceLocation("hotbar");
    public static final ResourceLocation CHAT = new ResourceLocation("chat");
    public static final ResourceLocation ACTION_BAR = new ResourceLocation("action_bar");
    public static final ResourceLocation BOSS_BAR = new ResourceLocation("boss_bar");
    public static final ResourceLocation SCOREBOARD = new ResourceLocation("scoreboard");
    public static final ResourceLocation EFFECTS = new ResourceLocation("effects");
    public static final ResourceLocation PLAYER_LIST = new ResourceLocation("player_list");
    public static final ResourceLocation TITLES = new ResourceLocation("titles");
    public static final ResourceLocation SUBTITLES = new ResourceLocation("subtitles");
    public static final ResourceLocation TOASTS = new ResourceLocation("toasts");
    public static final ResourceLocation UNKNOWN = new ResourceLocation("unknown");

    public Layers() {}

    public static void boostrap() {
        LayerRegistry.register(HOTBAR, new Layer(Layer.Anchor.BOTTOM));
        LayerRegistry.register(CHAT, new Layer(Layer.Anchor.NONE));
        LayerRegistry.register(ACTION_BAR, new Layer(Layer.Anchor.BOTTOM));
        LayerRegistry.register(BOSS_BAR, new Layer(Layer.Anchor.TOP));
        LayerRegistry.register(SCOREBOARD, new Layer(Layer.Anchor.RIGHT));
        LayerRegistry.register(EFFECTS, new Layer(Layer.Anchor.TOP_RIGHT));
        LayerRegistry.register(PLAYER_LIST, new Layer(Layer.Anchor.TOP));
        LayerRegistry.register(TITLES, new Layer(Layer.Anchor.NONE));
        LayerRegistry.register(SUBTITLES, new Layer(Layer.Anchor.BOTTOM_RIGHT));
        LayerRegistry.register(TOASTS, new Layer(Layer.Anchor.TOP_RIGHT));
        LayerRegistry.register(UNKNOWN, new Layer(Layer.Anchor.NONE));
    }

    public static class Curated {

        public static final HashMap<RenderGameOverlayEvent.ElementType, ResourceLocation> CURATED_LAYERS = new HashMap<>();
        public boolean translated = false;

        public Curated() {}

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startTranslate(RenderGameOverlayEvent.Pre event) {
            if (CURATED_LAYERS.containsKey(event.getType()) && !CURATED_LAYERS.get(event.getType()).equals(Layers.UNKNOWN)) {
                if (!translated) {
                    translated = true;
                    Translate.start(CURATED_LAYERS.get(event.getType()));
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endTranslate(RenderGameOverlayEvent.Pre event) {
            if (CURATED_LAYERS.containsKey(event.getType()) && !CURATED_LAYERS.get(event.getType()).equals(Layers.UNKNOWN) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(CURATED_LAYERS.get(event.getType()));
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST)
        public void endTranslate(RenderGameOverlayEvent.Post event) {
            if (CURATED_LAYERS.containsKey(event.getType()) && !CURATED_LAYERS.get(event.getType()).equals(Layers.UNKNOWN)) {
                if (translated) {
                    translated = false;
                    Translate.end(CURATED_LAYERS.get(event.getType()));
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startAllPreTranslate(RenderGameOverlayEvent.Pre event) {
            if (CURATED_LAYERS.containsKey(event.getType()) && CURATED_LAYERS.get(event.getType()).equals(Layers.UNKNOWN)) {
                if (!translated) {
                    translated = true;
                    Translate.start(CURATED_LAYERS.get(event.getType()));
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endAllPreTranslate(RenderGameOverlayEvent.Pre event) {
            if (CURATED_LAYERS.containsKey(event.getType()) && CURATED_LAYERS.get(event.getType()).equals(Layers.UNKNOWN)) {
                if (translated) {
                    translated = false;
                    Translate.end(CURATED_LAYERS.get(event.getType()));
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
        public void startAllPostTranslate(RenderGameOverlayEvent.Post event) {
            if (CURATED_LAYERS.containsKey(event.getType()) && CURATED_LAYERS.get(event.getType()).equals(Layers.UNKNOWN)) {
                if (!translated) {
                    translated = true;
                    Translate.start(CURATED_LAYERS.get(event.getType()));
                }
            }
        }

        @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
        public void endAllPostTranslate(RenderGameOverlayEvent.Post event) {
            if (CURATED_LAYERS.containsKey(event.getType()) && !CURATED_LAYERS.get(event.getType()).equals(Layers.UNKNOWN) && event.isCanceled()) {
                if (translated) {
                    translated = false;
                    Translate.end(CURATED_LAYERS.get(event.getType()));
                }
            }
        }

        public static void mapElementTypes() {
            for (RenderGameOverlayEvent.ElementType elementType : RenderGameOverlayEvent.ElementType.values()) {
                ResourceLocation name = curateName(elementType);

                if (name != null) {
                    Curated.CURATED_LAYERS.put(elementType, name);
                }
            }
        }

        @Unique
        private static ResourceLocation curateName(RenderGameOverlayEvent.ElementType elementType) {
            Map<RenderGameOverlayEvent.ElementType, ResourceLocation> map = new HashMap<>();
            map.put(RenderGameOverlayEvent.ElementType.HOTBAR, Layers.HOTBAR);
            map.put(RenderGameOverlayEvent.ElementType.HEALTH, Layers.HOTBAR);
            map.put(RenderGameOverlayEvent.ElementType.ARMOR, Layers.HOTBAR);
            map.put(RenderGameOverlayEvent.ElementType.FOOD, Layers.HOTBAR);
            map.put(RenderGameOverlayEvent.ElementType.AIR, Layers.HOTBAR);
            map.put(RenderGameOverlayEvent.ElementType.HEALTHMOUNT, Layers.HOTBAR);
            map.put(RenderGameOverlayEvent.ElementType.JUMPBAR, Layers.HOTBAR);
            map.put(RenderGameOverlayEvent.ElementType.EXPERIENCE, Layers.HOTBAR);
            map.put(RenderGameOverlayEvent.ElementType.CHAT, Layers.CHAT);
            map.put(RenderGameOverlayEvent.ElementType.BOSSHEALTH, Layers.BOSS_BAR);
            map.put(RenderGameOverlayEvent.ElementType.BOSSINFO, Layers.BOSS_BAR);
            map.put(RenderGameOverlayEvent.ElementType.POTION_ICONS, Layers.EFFECTS);
            map.put(RenderGameOverlayEvent.ElementType.PLAYER_LIST, Layers.PLAYER_LIST);
            map.put(RenderGameOverlayEvent.ElementType.SUBTITLES, Layers.SUBTITLES);
            map.put(RenderGameOverlayEvent.ElementType.ALL, Layers.UNKNOWN);

            return map.getOrDefault(elementType, null);
        }

    }

}