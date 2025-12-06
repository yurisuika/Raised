package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.util.Translate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;

public class MappedLayers {

    public static final HashMap<ResourceLocation, ResourceLocation> MAPPED_LAYERS = new HashMap<>();
    public boolean translated = false;

    public MappedLayers() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startTranslate(RenderGuiOverlayEvent.Pre event) {
        if (MAPPED_LAYERS.containsKey(event.getOverlay().id())) {
            if (!translated) {
                translated = true;
                Translate.start(event.getPoseStack(), MAPPED_LAYERS.get(event.getOverlay().id()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGuiOverlayEvent.Pre event) {
        if (MAPPED_LAYERS.containsKey(event.getOverlay().id()) && event.isCanceled()) {
            if (translated) {
                translated = false;
                Translate.end(event.getPoseStack(), MAPPED_LAYERS.get(event.getOverlay().id()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGuiOverlayEvent.Post event) {
        if (MAPPED_LAYERS.containsKey(event.getOverlay().id())) {
            if (translated) {
                translated = false;
                Translate.end(event.getPoseStack(), MAPPED_LAYERS.get(event.getOverlay().id()));
            }
        }
    }

}