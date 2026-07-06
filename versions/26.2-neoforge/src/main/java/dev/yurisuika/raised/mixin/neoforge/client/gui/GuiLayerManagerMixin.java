package dev.yurisuika.raised.mixin.neoforge.client.gui;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.MappedLayers;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.gui.GuiLayer;
import net.neoforged.neoforge.client.gui.GuiLayerManager;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BooleanSupplier;

@Mixin(value = GuiLayerManager.class, remap = false)
public abstract class GuiLayerManagerMixin {

    @Inject(method = "add(Lnet/minecraft/resources/Identifier;Lnet/neoforged/neoforge/client/gui/GuiLayer;)Lnet/neoforged/neoforge/client/gui/GuiLayerManager;", at = @At("RETURN"))
    private void registerLayer(Identifier name, GuiLayer layer, CallbackInfoReturnable<GuiLayerManager> cir) {
        addLayer(name);
    }

    @Inject(method = "add(Lnet/minecraft/resources/Identifier;Lnet/neoforged/neoforge/client/gui/GuiLayer;Ljava/util/function/BooleanSupplier;)Lnet/neoforged/neoforge/client/gui/GuiLayerManager;", at = @At("RETURN"))
    private void registerLayer(Identifier name, GuiLayer layer, BooleanSupplier shouldRender, CallbackInfoReturnable<GuiLayerManager> cir) {
        addLayer(name);
    }

    @Unique
    public void addLayer(Identifier name) {
        Identifier formattedName = name;

        if (!name.getNamespace().equals(Identifier.DEFAULT_NAMESPACE)) {
            LayerRegistry.register(name, LayerRegistry.createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, name));
        } else {
            formattedName = formatName(name);
        }

        if (formattedName != null) {
            MappedLayers.MAPPED_LAYERS.put(name, formattedName);
        }
    }

    @Unique
    public Identifier formatName(Identifier name) {
        if (name.equals(VanillaGuiLayers.HOTBAR)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.PLAYER_HEALTH)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.ARMOR_LEVEL)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.FOOD_LEVEL)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.AIR_LEVEL)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.VEHICLE_HEALTH)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.CONTEXTUAL_INFO_BAR_BACKGROUND)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.EXPERIENCE_LEVEL)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.CONTEXTUAL_INFO_BAR)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.SELECTED_ITEM_NAME)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.SPECTATOR_TOOLTIP)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.OVERLAY_MESSAGE)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(VanillaGuiLayers.CHAT)) {
            return LayerRegistry.CHAT;
        } else if (name.equals(VanillaGuiLayers.BOSS_OVERLAY)) {
            return LayerRegistry.BOSSBAR;
        } else if (name.equals(VanillaGuiLayers.SCOREBOARD_SIDEBAR)) {
            return LayerRegistry.SIDEBAR;
        } else if (name.equals(VanillaGuiLayers.EFFECTS)) {
            return LayerRegistry.EFFECTS;
        } else if (name.equals(VanillaGuiLayers.TAB_LIST)) {
            return LayerRegistry.PLAYERS;
        } else if (name.equals(VanillaGuiLayers.TITLE)) {
            return LayerRegistry.TITLES;
        } else if (name.equals(VanillaGuiLayers.SUBTITLE_OVERLAY)) {
            return LayerRegistry.SUBTITLES;
        } else {
            return null;
        }
    }

}