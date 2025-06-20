package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class GuiEvents {

    public static final List<ResourceLocation> MODS = Lists.newArrayList();
    public boolean translated = false;

    /**
     * Moves the {@code layer} for the respective {@link Layer} key.
     * */
    public GuiEvents() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startTranslate(RenderGuiLayerEvent.Pre event) {
        ResourceLocation name = formatOverlay(event.getName());
        if (name != null && Layers.LAYERS.containsKey(name)) {
            if (!translated) {
                translated = true;
                Translate.start(event.getGuiGraphics().pose(), name);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGuiLayerEvent.Pre event) {
        ResourceLocation name = formatOverlay(event.getName());
        if (name != null && Layers.LAYERS.containsKey(name) && event.isCanceled()) {
            if (translated) {
                translated = false;
                Translate.end(event.getGuiGraphics().pose());
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGuiLayerEvent.Post event) {
        ResourceLocation name = formatOverlay(event.getName());
        if (name != null && Layers.LAYERS.containsKey(name)) {
            if (translated) {
                translated = false;
                Translate.end(event.getGuiGraphics().pose());
            }
        }
    }

    public static ResourceLocation formatOverlay(ResourceLocation name) {
        if (name.equals(VanillaGuiLayers.HOTBAR)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.PLAYER_HEALTH)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.ARMOR_LEVEL)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.FOOD_LEVEL)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.AIR_LEVEL)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.VEHICLE_HEALTH)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.JUMP_METER)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.EXPERIENCE_BAR)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.EXPERIENCE_LEVEL)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.SELECTED_ITEM_NAME)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.SPECTATOR_TOOLTIP)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.OVERLAY_MESSAGE)) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.CHAT)) {
            return Layers.CHAT;
        } else if (name.equals(VanillaGuiLayers.BOSS_OVERLAY)) {
            return Layers.BOSSBAR;
        } else if (name.equals(VanillaGuiLayers.SCOREBOARD_SIDEBAR)) {
            return Layers.SIDEBAR;
        } else if (name.equals(VanillaGuiLayers.EFFECTS)) {
            return Layers.EFFECTS;
        } else if (name.equals(VanillaGuiLayers.TAB_LIST)) {
            return Layers.PLAYERS;
        } else if (name.equals(VanillaGuiLayers.SUBTITLE_OVERLAY)) {
            return Layers.SUBTITLES;
        } else if (MODS.contains(name)) {
            return name;
        } else {
            return null;
        }
    }

}