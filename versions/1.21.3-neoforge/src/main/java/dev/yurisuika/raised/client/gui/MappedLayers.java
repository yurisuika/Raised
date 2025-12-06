package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.util.Translate;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;

import java.util.HashMap;

public class MappedLayers {

    public static final HashMap<ResourceLocation, ResourceLocation> MAPPED_LAYERS = new HashMap<>();
    public boolean translated = false;

    public MappedLayers() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startTranslate(RenderGuiLayerEvent.Pre event) {
        if (MAPPED_LAYERS.containsKey(event.getName())) {
            if (!translated) {
                translated = true;
                Translate.start(event.getGuiGraphics().pose(), MAPPED_LAYERS.get(event.getName()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGuiLayerEvent.Pre event) {
        if (MAPPED_LAYERS.containsKey(event.getName()) && event.isCanceled()) {
            if (translated) {
                translated = false;
                Translate.end(event.getGuiGraphics().pose(), MAPPED_LAYERS.get(event.getName()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGuiLayerEvent.Post event) {
        if (MAPPED_LAYERS.containsKey(event.getName())) {
            if (translated) {
                translated = false;
                Translate.end(event.getGuiGraphics().pose(), MAPPED_LAYERS.get(event.getName()));
            }
        }
    }

}