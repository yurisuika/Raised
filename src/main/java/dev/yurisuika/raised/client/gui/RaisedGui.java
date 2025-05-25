package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.util.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

public class RaisedGui {

    public boolean translated = false;

    /**
     * Moves the {@code layer} for the respective {@link Layer} key.
     * */
    public RaisedGui() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startTranslate(RenderGuiLayerEvent.Pre event) {
        String name = formatOverlay(event.getName());
        if (name != null) {
            if (Option.getLayers().containsKey(name)) {
                if (!translated) {
                    translated = true;
                    Translate.start(event.getGuiGraphics().pose(), name);
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGuiLayerEvent.Pre event) {
        String name = formatOverlay(event.getName());
        if (name != null  && event.isCanceled()) {
            if (Option.getLayers().containsKey(name)) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGuiLayerEvent.Post event) {
        String name = formatOverlay(event.getName());
        if (name != null) {
            if (Option.getLayers().containsKey(name)) {
                if (translated) {
                    translated = false;
                    Translate.end(event.getGuiGraphics().pose());
                }
            }
        }
    }

    public static String formatOverlay(ResourceLocation id) {
        String name = null;
        if (Layers.HOTBAR_LAYERS.contains(id)) {
            name = Layers.HOTBAR.toString();
        } else if (Layers.CHAT_LAYERS.contains(id)) {
            name = Layers.CHAT.toString();
        } else if (Layers.BOSSBAR_LAYERS.contains(id)) {
            name = Layers.BOSSBAR.toString();
        } else if (Layers.SIDEBAR_LAYERS.contains(id)) {
            name = Layers.SIDEBAR.toString();
        } else if (Layers.EFFECTS_LAYERS.contains(id)) {
            name = Layers.EFFECTS.toString();
        } else if (Layers.PLAYERS_LAYERS.contains(id)) {
            name = Layers.PLAYERS.toString();
        } else if (Layers.TOASTS_LAYERS.contains(id)) {
            name = Layers.TOASTS.toString();
        } else if (Layers.OTHER_LAYERS.contains(id)) {
            name = id.toString();
        }
        return name;
    }

}