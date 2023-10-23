package dev.yurisuika.raised.client.gui;

import com.google.common.collect.Lists;
import net.minecraft.client.MinecraftClient;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType.*;

public class RaisedGui extends ForgeIngameGui {

    public static List<IIngameOverlay> hud = Lists.newArrayList(
            HOTBAR_ELEMENT,
            PLAYER_HEALTH_ELEMENT,
            ARMOR_LEVEL_ELEMENT,
            FOOD_LEVEL_ELEMENT,
            MOUNT_HEALTH_ELEMENT,
            AIR_LEVEL_ELEMENT,
            JUMP_BAR_ELEMENT,
            EXPERIENCE_BAR_ELEMENT,
            ITEM_NAME_ELEMENT,
            RECORD_OVERLAY_ELEMENT
    );
    public static List<RenderGameOverlayEvent.ElementType> chat = Lists.newArrayList(
            CHAT
    );
    public static List<RenderGameOverlayEvent.ElementType> all = Lists.newArrayList(
            ALL
    );
    public static List<IIngameOverlay> mod = Lists.newArrayList(
    );

    public RaisedGui() {
        super(MinecraftClient.getInstance());
    }

    // HUD
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startHudTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (hud.contains(event.getOverlay())) {
            event.getMatrixStack().translate(0, -getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endHudTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (hud.contains(event.getOverlay()) && event.isCanceled()) {
            event.getMatrixStack().translate(0, +getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endHudTranslate(RenderGameOverlayEvent.PostLayer event) {
        if (hud.contains(event.getOverlay())) {
            event.getMatrixStack().translate(0, +getHud(), 0);
        }
    }

    // CHAT
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startChatTranslate(RenderGameOverlayEvent.Pre event) {
        if (chat.contains(event.getType())) {
            event.getMatrixStack().translate(0, -(getSync() ? getHud() : getChat()), +300);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endChatTranslate(RenderGameOverlayEvent.Post event) {
        if (chat.contains(event.getType())) {
            event.getMatrixStack().translate(0, +(getSync() ? getHud() : getChat()), -300);
        }
    }

    // PRE MOD
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void startPreModTranslate(RenderGameOverlayEvent.Pre event) {
        if (all.contains(event.getType()) && getSupport()) {
            event.getMatrixStack().translate(0, -getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endPreModTranslate(RenderGameOverlayEvent.Pre event) {
        if (all.contains(event.getType()) && getSupport()) {
            event.getMatrixStack().translate(0, +getHud(), 0);
        }
    }

    // POST MOD
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void startPostModTranslate(RenderGameOverlayEvent.Post event) {
        if (all.contains(event.getType()) && getSupport()) {
            event.getMatrixStack().translate(0, -getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void endPostModTranslate(RenderGameOverlayEvent.Post event) {
        if (all.contains(event.getType()) && getSupport()) {
            event.getMatrixStack().translate(0, +getHud(), 0);
        }
    }

    // MOD
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void startModTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (mod.contains(event.getOverlay()) && getSupport()) {
            event.getMatrixStack().translate(0, -getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endModTranslate(RenderGameOverlayEvent.PreLayer event) {
        if (mod.contains(event.getOverlay()) && event.isCanceled() && getSupport()) {
            event.getMatrixStack().translate(0, +getHud(), 0);
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST, receiveCanceled = true)
    public void endModTranslate(RenderGameOverlayEvent.PostLayer event) {
        if (mod.contains(event.getOverlay()) && getSupport()) {
            event.getMatrixStack().translate(0, +getHud(), 0);
        }
    }

}