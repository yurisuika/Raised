package dev.yurisuika.raised.mixin.neoforge.client.gui;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.gui.GuiLayerManager;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(value = GuiLayerManager.class, remap = false)
public abstract class GuiLayerManagerMixin {

    @Inject(method = "add(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/gui/LayeredDraw$Layer;)Lnet/neoforged/neoforge/client/gui/GuiLayerManager;", at = @At("RETURN"))
    private void registerLayer(ResourceLocation name, LayeredDraw.Layer layer, CallbackInfoReturnable<GuiLayerManager> cir) {
        addLayer(name);
    }

    @Unique
    public void addLayer(ResourceLocation name) {
        ResourceLocation curatedName = null;

        if (!name.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
            LayerRegistry.register(name, new Layer(Layer.Anchor.NONE));
        } else {
            curatedName = curateName(name);
        }

        if (curatedName != null) {
            Layers.Curated.CURATED_LAYERS.put(name, curatedName);
        }
    }

    @Unique
    public ResourceLocation curateName(ResourceLocation name) {
        Map<ResourceLocation, ResourceLocation> map = Map.ofEntries(
                Map.entry(VanillaGuiLayers.HOTBAR, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.PLAYER_HEALTH, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.ARMOR_LEVEL, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.FOOD_LEVEL, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.AIR_LEVEL, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.VEHICLE_HEALTH, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.JUMP_METER, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.EXPERIENCE_BAR, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.EXPERIENCE_LEVEL, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.SELECTED_ITEM_NAME, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.SPECTATOR_TOOLTIP, Layers.HOTBAR),
                Map.entry(VanillaGuiLayers.OVERLAY_MESSAGE, Layers.ACTION_BAR),
                Map.entry(VanillaGuiLayers.CHAT, Layers.CHAT),
                Map.entry(VanillaGuiLayers.BOSS_OVERLAY, Layers.BOSS_BAR),
                Map.entry(VanillaGuiLayers.SCOREBOARD_SIDEBAR, Layers.SCOREBOARD),
                Map.entry(VanillaGuiLayers.EFFECTS, Layers.EFFECTS),
                Map.entry(VanillaGuiLayers.TAB_LIST, Layers.PLAYER_LIST),
                Map.entry(VanillaGuiLayers.TITLE, Layers.TITLES),
                Map.entry(VanillaGuiLayers.SUBTITLE_OVERLAY, Layers.SUBTITLES)
        );

        return map.getOrDefault(name, null);
    }

}