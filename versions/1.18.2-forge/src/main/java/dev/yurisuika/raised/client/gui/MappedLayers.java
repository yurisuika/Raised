package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.util.Translate;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;

public class MappedLayers {

    public static final HashMap<IIngameOverlay, ResourceLocation> MAPPED_LAYERS = new HashMap<>();
    public boolean translated = false;

    public MappedLayers() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (MAPPED_LAYERS.containsKey(event.getOverlay())) {
            if (!translated) {
                translated = true;
                Translate.start(event.getMatrixStack(), MAPPED_LAYERS.get(event.getOverlay()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (MAPPED_LAYERS.containsKey(event.getOverlay()) && event.isCanceled()) {
            if (translated) {
                translated = false;
                Translate.end(event.getMatrixStack(), MAPPED_LAYERS.get(event.getOverlay()));
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGameOverlayEvent.PostLayer event) {
        if (MAPPED_LAYERS.containsKey(event.getOverlay())) {
            if (translated) {
                translated = false;
                Translate.end(event.getMatrixStack(), MAPPED_LAYERS.get(event.getOverlay()));
            }
        }
    }

}