package dev.yurisuika.raised.client.gui;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static net.minecraftforge.client.gui.overlay.VanillaGuiOverlay.*;

public class RaisedGui extends ForgeGui {

    public boolean translated = false;

    public static List<Identifier> hud = Lists.newArrayList(
            HOTBAR.id(),
            PLAYER_HEALTH.id(),
            ARMOR_LEVEL.id(),
            FOOD_LEVEL.id(),
            AIR_LEVEL.id(),
            MOUNT_HEALTH.id(),
            JUMP_BAR.id(),
            EXPERIENCE_BAR.id(),
            ITEM_NAME.id(),
            RECORD_OVERLAY.id()
    );
    public static List<Identifier> chat = Lists.newArrayList(
            CHAT_PANEL.id()
    );
    public static List<Identifier> mod = Lists.newArrayList(
    );

    public RaisedGui() {
        super(MinecraftClient.getInstance());
    }

    public void start(RenderGuiOverlayEvent event) {
        if(!translated) {
            translated = true;
            event.getPoseStack().translate(0, -getHud(), 0);
        }
    }

    public void end(RenderGuiOverlayEvent event) {
        if(translated) {
            translated = false;
            event.getPoseStack().translate(0, +getHud(), 0);
        }
    }

    // HUD
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startHudTranslate(RenderGuiOverlayEvent.Pre event) {
        if (hud.contains(event.getOverlay().id())) {
            start(event);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endHudTranslate(RenderGuiOverlayEvent.Pre event) {
        if (hud.contains(event.getOverlay().id()) && event.isCanceled()) {
            end(event);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endHudTranslate(RenderGuiOverlayEvent.Post event) {
        if (hud.contains(event.getOverlay().id())) {
            end(event);
        }
    }

    // CHAT
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startChatTranslate(RenderGuiOverlayEvent.Pre event) {
        if (chat.contains(event.getOverlay().id())) {
            event.getPoseStack().translate(0, -(getSync() ? getHud() : getChat()), +300);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endChatTranslate(RenderGuiOverlayEvent.Post event) {
        if (chat.contains(event.getOverlay().id())) {
            event.getPoseStack().translate(0, +(getSync() ? getHud() : getChat()), -300);
        }
    }

    // MOD
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startModTranslate(RenderGuiOverlayEvent.Pre event) {
        if (mod.contains(event.getOverlay().id()) && getSupport()) {
            start(event);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endModTranslate(RenderGuiOverlayEvent.Pre event) {
        if (mod.contains(event.getOverlay().id()) && event.isCanceled() && getSupport()) {
            end(event);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endModTranslate(RenderGuiOverlayEvent.Post event) {
        if (mod.contains(event.getOverlay().id()) && getSupport()) {
            end(event);
        }
    }

}