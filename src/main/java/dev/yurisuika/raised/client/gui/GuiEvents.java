package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class GuiEvents {

    public static final Map<ResourceLocation, IIngameOverlay> MODS = new HashMap<>();
    public boolean translated = false;

    /**
     * Moves the {@code layer} for the respective {@link Layer} key.
     */
    public GuiEvents() {}

    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startTranslate(RenderGameOverlayEvent.PreLayer event) {
        ResourceLocation name = formatOverlay(event.getOverlay());
        if (name != null && Layers.LAYERS.containsKey(name)) {
            if (!translated) {
                translated = true;
                Translate.start(event.getMatrixStack(), name);
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGameOverlayEvent.PreLayer event) {
        ResourceLocation name = formatOverlay(event.getOverlay());
        if (name != null && Layers.LAYERS.containsKey(name) && event.isCanceled()) {
            if (translated) {
                translated = false;
                Translate.end(event.getMatrixStack());
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGameOverlayEvent.PostLayer event) {
        ResourceLocation name = formatOverlay(event.getOverlay());
        if (name != null && Layers.LAYERS.containsKey(name)) {
            if (translated) {
                translated = false;
                Translate.end(event.getMatrixStack());
            }
        }
    }

    public static ResourceLocation formatOverlay(IIngameOverlay overlay) {
        if (overlay.equals(ForgeIngameGui.HOTBAR_ELEMENT)) {
            return Layers.HOTBAR;
        } else if (overlay.equals(ForgeIngameGui.PLAYER_HEALTH_ELEMENT)) {
            return Layers.HOTBAR;
        } else if (overlay.equals(ForgeIngameGui.ARMOR_LEVEL_ELEMENT)) {
            return Layers.HOTBAR;
        } else if (overlay.equals(ForgeIngameGui.FOOD_LEVEL_ELEMENT)) {
            return Layers.HOTBAR;
        } else if (overlay.equals(ForgeIngameGui.AIR_LEVEL_ELEMENT)) {
            return Layers.HOTBAR;
        } else if (overlay.equals(ForgeIngameGui.MOUNT_HEALTH_ELEMENT)) {
            return Layers.HOTBAR;
        } else if (overlay.equals(ForgeIngameGui.JUMP_BAR_ELEMENT)) {
            return Layers.HOTBAR;
        } else if (overlay.equals(ForgeIngameGui.EXPERIENCE_BAR_ELEMENT)) {
            return Layers.HOTBAR;
        } else if (overlay.equals(ForgeIngameGui.ITEM_NAME_ELEMENT)) {
            return Layers.HOTBAR;
        } else if (overlay.equals(ForgeIngameGui.RECORD_OVERLAY_ELEMENT)) {
            return Layers.HOTBAR;
        } else if (overlay.equals(ForgeIngameGui.CHAT_PANEL_ELEMENT)) {
            return Layers.CHAT;
        } else if (overlay.equals(ForgeIngameGui.BOSS_HEALTH_ELEMENT)) {
            return Layers.BOSSBAR;
        } else if (overlay.equals(ForgeIngameGui.SCOREBOARD_ELEMENT)) {
            return Layers.SIDEBAR;
        } else if (overlay.equals(ForgeIngameGui.POTION_ICONS_ELEMENT)) {
            return Layers.EFFECTS;
        } else if (overlay.equals(ForgeIngameGui.PLAYER_LIST_ELEMENT)) {
            return Layers.PLAYERS;
        } else if (overlay.equals(ForgeIngameGui.SUBTITLES_ELEMENT)) {
            return Layers.SUBTITLES;
        } else if (MODS.containsValue(overlay)) {
            return MODS.entrySet().stream().filter(entry -> entry.getValue().equals(overlay)).map(Map.Entry::getKey).findFirst().get();
        } else {
            return null;
        }
    }

}