package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.util.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.Map;

public class RaisedGui {

    public boolean translated = false;

    /**
     * Moves the {@code layer} for the respective {@link Layer} key.
     */
    public RaisedGui() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startTranslate(RenderGameOverlayEvent.PreLayer event) {
        String name = formatOverlay(event.getOverlay());
        if (name != null) {
            if (Option.getLayers().containsKey(name)) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getMatrixStack(), name);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGameOverlayEvent.PreLayer event) {
        String name = formatOverlay(event.getOverlay());
        if (name != null && event.isCanceled()) {
            if (Option.getLayers().containsKey(name)) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGameOverlayEvent.PostLayer event) {
        String name = formatOverlay(event.getOverlay());
        if (name != null) {
            if (Option.getLayers().containsKey(name)) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getMatrixStack());
                }
            }
        }
    }

    public static String formatOverlay(IIngameOverlay overlay) {
        String name = null;
        if (Layers.HOTBAR_LAYERS.containsValue(overlay)) {
            name = Layers.HOTBAR.toString();
        } else if (Layers.CHAT_LAYERS.containsValue(overlay)) {
            name = Layers.CHAT.toString();
        } else if (Layers.BOSSBAR_LAYERS.containsValue(overlay)) {
            name = Layers.BOSSBAR.toString();
        } else if (Layers.SIDEBAR_LAYERS.containsValue(overlay)) {
            name = Layers.SIDEBAR.toString();
        } else if (Layers.EFFECTS_LAYERS.containsValue(overlay)) {
            name = Layers.EFFECTS.toString();
        } else if (Layers.PLAYERS_LAYERS.containsValue(overlay)) {
            name = Layers.PLAYERS.toString();
        } else if (Layers.TOASTS_LAYERS.containsValue(overlay)) {
            name = Layers.TOASTS.toString();
        } else if (Layers.OTHER_LAYERS.containsValue(overlay)) {
            name = Layers.OTHER_LAYERS.entrySet().stream().filter(entry -> entry.getValue().equals(overlay)).map(Map.Entry::getKey).findFirst().get().toString();
        }
        return name;
    }

}