package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;

public class MappedLayers {

    public static final HashMap<RenderGameOverlayEvent.ElementType, ResourceLocation> MAPPED_LAYERS = new HashMap<>();
    public boolean translated = false;

    public MappedLayers() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startTranslate(RenderGameOverlayEvent.Pre event) {
        if (MAPPED_LAYERS.containsKey(event.getType()) && !MAPPED_LAYERS.get(event.getType()).equals(LayerRegistry.OTHER)) {
            if (!translated) {
                translated = true;
                Translate.start(MAPPED_LAYERS.get(event.getType()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGameOverlayEvent.Pre event) {
        if (MAPPED_LAYERS.containsKey(event.getType()) && !MAPPED_LAYERS.get(event.getType()).equals(LayerRegistry.OTHER) && event.isCanceled()) {
            if (translated) {
                translated = false;
                Translate.end(MAPPED_LAYERS.get(event.getType()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGameOverlayEvent.Post event) {
        if (MAPPED_LAYERS.containsKey(event.getType()) && !MAPPED_LAYERS.get(event.getType()).equals(LayerRegistry.OTHER)) {
            if (translated) {
                translated = false;
                Translate.end(MAPPED_LAYERS.get(event.getType()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startAllPreTranslate(RenderGameOverlayEvent.Pre event) {
        if (MAPPED_LAYERS.containsKey(event.getType()) && MAPPED_LAYERS.get(event.getType()).equals(LayerRegistry.OTHER)) {
            if (!translated) {
                translated = true;
                Translate.start(MAPPED_LAYERS.get(event.getType()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endAllPreTranslate(RenderGameOverlayEvent.Pre event) {
        if (MAPPED_LAYERS.containsKey(event.getType()) && MAPPED_LAYERS.get(event.getType()).equals(LayerRegistry.OTHER)) {
            if (translated) {
                translated = false;
                Translate.end(MAPPED_LAYERS.get(event.getType()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startAllPostTranslate(RenderGameOverlayEvent.Post event) {
        if (MAPPED_LAYERS.containsKey(event.getType()) && MAPPED_LAYERS.get(event.getType()).equals(LayerRegistry.OTHER)) {
            if (!translated) {
                translated = true;
                Translate.start(MAPPED_LAYERS.get(event.getType()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endAllPostTranslate(RenderGameOverlayEvent.Post event) {
        if (MAPPED_LAYERS.containsKey(event.getType()) && !MAPPED_LAYERS.get(event.getType()).equals(LayerRegistry.OTHER) && event.isCanceled()) {
            if (translated) {
                translated = false;
                Translate.end(MAPPED_LAYERS.get(event.getType()));
            }
        }
    }

    public static void mapElementTypes() {
        for (RenderGameOverlayEvent.ElementType elementType : RenderGameOverlayEvent.ElementType.values()) {
            ResourceLocation name = formatName(elementType);

            if (name != null) {
                MappedLayers.MAPPED_LAYERS.put(elementType, name);
            }
        }
    }

    public static ResourceLocation formatName(RenderGameOverlayEvent.ElementType elementType) {
        if (elementType.equals(RenderGameOverlayEvent.ElementType.HOTBAR)) {
            return LayerRegistry.HOTBAR;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.HEALTH)) {
            return LayerRegistry.HOTBAR;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.ARMOR)) {
            return LayerRegistry.HOTBAR;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.FOOD)) {
            return LayerRegistry.HOTBAR;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.AIR)) {
            return LayerRegistry.HOTBAR;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.HEALTHMOUNT)) {
            return LayerRegistry.HOTBAR;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.JUMPBAR)) {
            return LayerRegistry.HOTBAR;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.EXPERIENCE)) {
            return LayerRegistry.HOTBAR;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.CHAT)) {
            return LayerRegistry.CHAT;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.BOSSHEALTH)) {
            return LayerRegistry.BOSSBAR;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.BOSSINFO)) {
            return LayerRegistry.BOSSBAR;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.POTION_ICONS)) {
            return LayerRegistry.EFFECTS;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.PLAYER_LIST)) {
            return LayerRegistry.PLAYERS;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.SUBTITLES)) {
            return LayerRegistry.SUBTITLES;
        } else if (elementType.equals(RenderGameOverlayEvent.ElementType.ALL)) {
            return LayerRegistry.OTHER;
        } else {
            return null;
        }
    }

}