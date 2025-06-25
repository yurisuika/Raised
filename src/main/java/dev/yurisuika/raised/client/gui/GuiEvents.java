package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class GuiEvents {

    public boolean translated = false;

    /**
     * Moves the {@code layer} for the respective {@link Layer} key.
     */
    public GuiEvents() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startTranslate(RenderGameOverlayEvent.Pre event) {
        ResourceLocation name = formatOverlay(event.getType());
        if (name != null && !name.equals(LayerRegistry.OTHER) && LayerRegistry.LAYERS.containsKey(name)) {
            if (!translated) {
                translated = true;
                Translate.start(name);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGameOverlayEvent.Pre event) {
        ResourceLocation name = formatOverlay(event.getType());
        if (name != null && !name.equals(LayerRegistry.OTHER) && LayerRegistry.LAYERS.containsKey(name) && event.isCanceled()) {
            if (translated) {
                translated = false;
                Translate.end();
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGameOverlayEvent.Post event) {
        ResourceLocation name = formatOverlay(event.getType());
        if (name != null && !name.equals(LayerRegistry.OTHER) && LayerRegistry.LAYERS.containsKey(name)) {
            if (translated) {
                translated = false;
                Translate.end();
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startAllPreTranslate(RenderGameOverlayEvent.Pre event) {
        ResourceLocation name = formatOverlay(event.getType());
        if (name != null && name.equals(LayerRegistry.OTHER) && LayerRegistry.LAYERS.containsKey(name)) {
            if (!translated) {
                translated = true;
                Translate.start(name);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endAllPreTranslate(RenderGameOverlayEvent.Pre event) {
        ResourceLocation name = formatOverlay(event.getType());
        if (name != null && name.equals(LayerRegistry.OTHER) && LayerRegistry.LAYERS.containsKey(name)) {
            if (translated) {
                translated = false;
                Translate.end();
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startAllPostTranslate(RenderGameOverlayEvent.Post event) {
        ResourceLocation name = formatOverlay(event.getType());
        if (name != null && name.equals(LayerRegistry.OTHER) && LayerRegistry.LAYERS.containsKey(name)) {
            if (!translated) {
                translated = true;
                Translate.start(name);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endAllPostTranslate(RenderGameOverlayEvent.Post event) {
        ResourceLocation name = formatOverlay(event.getType());
        if (name != null && name.equals(LayerRegistry.OTHER) && LayerRegistry.LAYERS.containsKey(name)) {
            if (translated) {
                translated = false;
                Translate.end();
            }
        }
    }

    public static ResourceLocation formatOverlay(RenderGameOverlayEvent.ElementType elementType) {
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