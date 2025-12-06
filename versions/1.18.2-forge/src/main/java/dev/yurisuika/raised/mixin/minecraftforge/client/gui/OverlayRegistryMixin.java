package dev.yurisuika.raised.mixin.minecraftforge.client.gui;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.MappedLayers;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.fml.ModLoadingContext;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = OverlayRegistry.class, remap = false)
public abstract class OverlayRegistryMixin {

    @Inject(method = "registerOverlay", at = @At("RETURN"))
    private static void addLayer(int sort, IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        addLayer(new ResourceLocation(formatNamespace(), formatPath(displayName)), overlay);
    }

    @Unique
    private static void addLayer(ResourceLocation name, IIngameOverlay overlay) {
        if (!name.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
            LayerRegistry.register(name, new Layer(new Layer.Displacement(0, 0), new Layer.Direction(Layer.Direction.X.NONE, Layer.Direction.Y.NONE), name.toString()));
        } else {
            name = formatName(name);
        }

        if (name != null) {
            MappedLayers.MAPPED_LAYERS.put(overlay, name);
        }
    }

    @Unique
    private static String formatPath(String displayName) {
        String path = displayName;
        path = StringUtils.replaceChars(path, ' ', '_');
        path = StringUtils.remove(path, ':');
        path = path.toLowerCase();
        return path;
    }

    @Unique
    private static String formatNamespace() {
        String namespace = ModLoadingContext.get().getActiveNamespace();
        return namespace.equals("raised") ? ResourceLocation.DEFAULT_NAMESPACE : namespace;
    }

    @Unique
    private static ResourceLocation formatName(ResourceLocation name) {
        if (name.equals(new ResourceLocation("hotbar"))) {                // ForgeIngameGui.HOTBAR
            return LayerRegistry.HOTBAR;
        } else if (name.equals(new ResourceLocation("player_health"))) {  // ForgeIngameGui.PLAYER_HEALTH
            return LayerRegistry.HOTBAR;
        } else if (name.equals(new ResourceLocation("armor_level"))) {    // ForgeIngameGui.ARMOR_LEVEL_ELEMENT
            return LayerRegistry.HOTBAR;
        } else if (name.equals(new ResourceLocation("food_level"))) {     // ForgeIngameGui.FOOD_LEVEL_ELEMENT
            return LayerRegistry.HOTBAR;
        } else if (name.equals(new ResourceLocation("air_level"))) {      // ForgeIngameGui.AIR_LEVEL_ELEMENT
            return LayerRegistry.HOTBAR;
        } else if (name.equals(new ResourceLocation("mount_health"))) {   // ForgeIngameGui.MOUNT_HEALTH_ELEMENT
            return LayerRegistry.HOTBAR;
        } else if (name.equals(new ResourceLocation("jump_bar"))) {       // ForgeIngameGui.JUMP_BAR_ELEMENT
            return LayerRegistry.HOTBAR;
        } else if (name.equals(new ResourceLocation("experience_bar"))) { // ForgeIngameGui.EXPERIENCE_BAR_ELEMENT
            return LayerRegistry.HOTBAR;
        } else if (name.equals(new ResourceLocation("item_name"))) {      // ForgeIngameGui.ITEM_NAME_ELEMENT
            return LayerRegistry.HOTBAR;
        } else if (name.equals(new ResourceLocation("record"))) {         // ForgeIngameGui.RECORD_OVERLAY_ELEMENT
            return LayerRegistry.HOTBAR;
        } else if (name.equals(new ResourceLocation("chat_history"))) {   // ForgeIngameGui.CHAT_PANEL_ELEMENT
            return LayerRegistry.CHAT;
        } else if (name.equals(new ResourceLocation("boss_health"))) {    // ForgeIngameGui.BOSS_HEALTH_ELEMENT
            return LayerRegistry.BOSSBAR;
        } else if (name.equals(new ResourceLocation("scoreboard"))) {     // ForgeIngameGui.SCOREBOARD_ELEMENT
            return LayerRegistry.SIDEBAR;
        } else if (name.equals(new ResourceLocation("potion_icons"))) {   // ForgeIngameGui.POTION_ICONS_ELEMENT
            return LayerRegistry.EFFECTS;
        } else if (name.equals(new ResourceLocation("player_list"))) {    // ForgeIngameGui.PLAYER_LIST_ELEMENT
            return LayerRegistry.PLAYERS;
        } else if (name.equals(new ResourceLocation("title_text"))) {     // ForgeIngameGui.TITLE_TEXT_ELEMENT
            return LayerRegistry.TITLES;
        } else if (name.equals(new ResourceLocation("subtitles"))) {      // ForgeIngameGui.SUBTITLES_ELEMENT
            return LayerRegistry.SUBTITLES;
        } else {
            return null;
        }
    }

}