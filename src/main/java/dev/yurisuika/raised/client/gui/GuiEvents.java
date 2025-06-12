package dev.yurisuika.raised.client.gui;

import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
    public void startTranslate(RenderGuiOverlayEvent.Pre event) {
        ResourceLocation name = formatOverlay(event.getOverlay().id());
        if (name != null && Layers.LAYERS.containsKey(name)) {
            if (!translated) {
                translated = true;
                Translate.start(event.getPoseStack(), name.toString());
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endTranslate(RenderGuiOverlayEvent.Pre event) {
        ResourceLocation name = formatOverlay(event.getOverlay().id());
        if (name != null && Layers.LAYERS.containsKey(name) && event.isCanceled()) {
            if (translated) {
                translated = false;
                Translate.end(event.getPoseStack());
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endTranslate(RenderGuiOverlayEvent.Post event) {
        ResourceLocation name = formatOverlay(event.getOverlay().id());
        if (name != null && Layers.LAYERS.containsKey(name)) {
            if (translated) {
                translated = false;
                Translate.end(event.getPoseStack());
            }
        }
    }

    public static ResourceLocation formatOverlay(ResourceLocation name) {
        if (name.equals(VanillaGuiOverlay.HOTBAR.id())) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.PLAYER_HEALTH.id())) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.ARMOR_LEVEL.id())) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.FOOD_LEVEL.id())) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.AIR_LEVEL.id())) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.MOUNT_HEALTH.id())) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.JUMP_BAR.id())) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.EXPERIENCE_BAR.id())) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.ITEM_NAME.id())) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.RECORD_OVERLAY.id())) {
            return Layers.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.CHAT_PANEL.id())) {
            return Layers.CHAT;
        } else if (name.equals(VanillaGuiOverlay.BOSS_EVENT_PROGRESS.id())) {
            return Layers.BOSSBAR;
        } else if (name.equals(VanillaGuiOverlay.SCOREBOARD.id())) {
            return Layers.SIDEBAR;
        } else if (name.equals(VanillaGuiOverlay.POTION_ICONS.id())) {
            return Layers.EFFECTS;
        } else if (name.equals(VanillaGuiOverlay.PLAYER_LIST.id())) {
            return Layers.PLAYERS;
        } else if (name.equals(VanillaGuiOverlay.SUBTITLES.id())) {
            return Layers.SUBTITLES;
        } else if (MODS.contains(name)) {
            return name;
        } else {
            return null;
        }
    }

}