package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.util.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RaisedGui {

    public boolean translated = false;

    /**
     * Moves the {@code layer} for the respective {@link Layer} key.
     */
    public RaisedGui() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startTranslate(RenderGameOverlayEvent.Pre event) {
        String name = formatOverlay(event.getType());
        if (name != null && !name.equals(Layers.OTHER.toString())) {
            if (Option.getLayers().containsKey(name)) {
                if (!translated) {
                    translated = true;
                    Translate.start(name);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGameOverlayEvent.Pre event) {
        String name = formatOverlay(event.getType());
        if (name != null && !name.equals(Layers.OTHER.toString()) && event.isCanceled()) {
            if (Option.getLayers().containsKey(name)) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGameOverlayEvent.Post event) {
        String name = formatOverlay(event.getType());
        if (name != null && !name.equals(Layers.OTHER.toString())) {
            if (Option.getLayers().containsKey(name)) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startAllPreTranslate(RenderGameOverlayEvent.Pre event) {
        String name = formatOverlay(event.getType());
        if (name != null && name.equals(Layers.OTHER.toString())) {
            if (Option.getLayers().containsKey(name)) {
                if (!translated) {
                    translated = true;
                    Translate.start(name);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endAllPreTranslate(RenderGameOverlayEvent.Pre event) {
        String name = formatOverlay(event.getType());
        if (name != null && name.equals(Layers.OTHER.toString())) {
            if (Option.getLayers().containsKey(name)) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startAllPostTranslate(RenderGameOverlayEvent.Post event) {
        String name = formatOverlay(event.getType());
        if (name != null && name.equals(Layers.OTHER.toString())) {
            if (Option.getLayers().containsKey(name)) {
                if (!translated) {
                    translated = true;
                    Translate.start(name);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endAllPostTranslate(RenderGameOverlayEvent.Post event) {
        String name = formatOverlay(event.getType());
        if (name != null && name.equals(Layers.OTHER.toString())) {
            if (Option.getLayers().containsKey(name)) {
                if (translated) {
                    translated = false;
                    Translate.end();
                }
            }
        }
    }

    public static String formatOverlay(RenderGameOverlayEvent.ElementType elementType) {
        String name = null;
        if (Layers.HOTBAR_LAYERS.containsValue(elementType)) {
            name = Layers.HOTBAR.toString();
        } else if (Layers.CHAT_LAYERS.containsValue(elementType)) {
            name = Layers.CHAT.toString();
        } else if (Layers.BOSSBAR_LAYERS.containsValue(elementType)) {
            name = Layers.BOSSBAR.toString();
        } else if (Layers.SIDEBAR_LAYERS.containsValue(elementType)) {
            name = Layers.SIDEBAR.toString();
        } else if (Layers.EFFECTS_LAYERS.containsValue(elementType)) {
            name = Layers.EFFECTS.toString();
        } else if (Layers.PLAYERS_LAYERS.containsValue(elementType)) {
            name = Layers.PLAYERS.toString();
        } else if (Layers.TOASTS_LAYERS.containsValue(elementType)) {
            name = Layers.TOASTS.toString();
        } else if (Layers.OTHER_LAYERS.containsValue(elementType)) {
            name = Layers.OTHER.toString();
        }
        return name;
    }

}