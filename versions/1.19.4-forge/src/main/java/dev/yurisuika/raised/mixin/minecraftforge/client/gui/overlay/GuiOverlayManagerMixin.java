package dev.yurisuika.raised.mixin.minecraftforge.client.gui.overlay;

import dev.yurisuika.raised.client.gui.MappedLayers;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.HashMap;

@Mixin(value = GuiOverlayManager.class, remap = false)
public abstract class GuiOverlayManagerMixin {

    @Inject(method = "preRegisterVanillaOverlays", at = @At(value = "INVOKE", target = "Ljava/util/HashMap;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void mapVanillaOverlays(HashMap<ResourceLocation, IGuiOverlay> overlays, ArrayList<ResourceLocation> orderedOverlays, CallbackInfo ci, VanillaGuiOverlay[] var2, int var3, int var4, VanillaGuiOverlay entry) {
        ResourceLocation name = formatName(entry.id());

        if (name != null) {
            MappedLayers.MAPPED_LAYERS.put(entry.id(), name);
        }
    }

    @Unique
    private static ResourceLocation formatName(ResourceLocation name) {
        if (name.equals(VanillaGuiOverlay.HOTBAR.id())) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.PLAYER_HEALTH.id())) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.ARMOR_LEVEL.id())) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.FOOD_LEVEL.id())) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.AIR_LEVEL.id())) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.MOUNT_HEALTH.id())) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.JUMP_BAR.id())) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.EXPERIENCE_BAR.id())) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.ITEM_NAME.id())) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.RECORD_OVERLAY.id())) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiOverlay.CHAT_PANEL.id())) {
            return LayerRegistry.CHAT;
        } else if (name.equals(VanillaGuiOverlay.BOSS_EVENT_PROGRESS.id())) {
            return LayerRegistry.BOSSBAR;
        } else if (name.equals(VanillaGuiOverlay.SCOREBOARD.id())) {
            return LayerRegistry.SIDEBAR;
        } else if (name.equals(VanillaGuiOverlay.POTION_ICONS.id())) {
            return LayerRegistry.EFFECTS;
        } else if (name.equals(VanillaGuiOverlay.PLAYER_LIST.id())) {
            return LayerRegistry.PLAYERS;
        } else if (name.equals(VanillaGuiOverlay.TITLE_TEXT.id())) {
            return LayerRegistry.TITLES;
        } else if (name.equals(VanillaGuiOverlay.SUBTITLES.id())) {
            return LayerRegistry.SUBTITLES;
        } else {
            return null;
        }
    }

}