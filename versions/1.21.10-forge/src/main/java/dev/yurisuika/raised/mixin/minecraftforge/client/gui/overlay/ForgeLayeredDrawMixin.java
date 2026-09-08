package dev.yurisuika.raised.mixin.minecraftforge.client.gui.overlay;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeLayer;
import net.minecraftforge.client.gui.overlay.ForgeLayeredDraw;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Iterator;
import java.util.Map;
import java.util.function.BooleanSupplier;

@Mixin(value = ForgeLayeredDraw.class, remap = false)
public abstract class ForgeLayeredDrawMixin {

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/overlay/ForgeLayer;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void startTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Iterator iterator, ForgeLayer layer) {
        if (Layers.Curated.CURATED_LAYERS.containsKey(layer)) {
            Translate.start(guiGraphics.pose(), Layers.Curated.CURATED_LAYERS.get(layer));
        }
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/overlay/ForgeLayer;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void endTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Iterator iterator, ForgeLayer layer) {
        if (Layers.Curated.CURATED_LAYERS.containsKey(layer)) {
            Translate.end(guiGraphics.pose(), Layers.Curated.CURATED_LAYERS.get(layer));
        }
    }

    @Inject(method = "add(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraftforge/client/gui/overlay/ForgeLayer;)Lnet/minecraftforge/client/gui/overlay/ForgeLayeredDraw;", at = @At("RETURN"))
    private void registerLayer(ResourceLocation name, ForgeLayer forgeLayer, CallbackInfoReturnable<ForgeLayeredDraw> cir) {
        addLayer(name, forgeLayer);
    }

    @Inject(method = "addConditionTo(Lnet/minecraft/resources/ResourceLocation;Ljava/util/function/BooleanSupplier;)Lnet/minecraftforge/client/gui/overlay/ForgeLayeredDraw;", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void registerComputedLayer(ResourceLocation targetLayer, BooleanSupplier condition, CallbackInfoReturnable<ForgeLayeredDraw> cir, ForgeLayer result) {
        addLayer(targetLayer, result);
    }

    @Unique
    public void addLayer(ResourceLocation name, ForgeLayer forgeLayer) {
        if (!name.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
            LayerRegistry.register(name, new Layer(Layer.Anchor.NONE));
        } else {
            name = curateName(name);
        }

        if (name != null) {
            Layers.Curated.CURATED_LAYERS.put(forgeLayer, name);
        }
    }

    @Unique
    public ResourceLocation curateName(ResourceLocation name) {
        Map<ResourceLocation, ResourceLocation> map = Map.ofEntries(
                Map.entry(ForgeLayeredDraw.HOTBAR_AND_DECOS, Layers.HOTBAR),
                Map.entry(ForgeLayeredDraw.HOTBAR_MESSAGE, Layers.ACTION_BAR),
                Map.entry(ForgeLayeredDraw.CHAT_OVERLAY, Layers.CHAT),
                Map.entry(ForgeLayeredDraw.BOSS_OVERLAY, Layers.BOSS_BAR),
                Map.entry(ForgeLayeredDraw.SCOREBOARD, Layers.SCOREBOARD),
                Map.entry(ForgeLayeredDraw.POTION_EFFECTS, Layers.EFFECTS),
                Map.entry(ForgeLayeredDraw.TAB_LIST, Layers.PLAYER_LIST),
                Map.entry(ForgeLayeredDraw.TITLE_OVERLAY, Layers.TITLES),
                Map.entry(ForgeLayeredDraw.SUBTITLE_OVERLAY, Layers.SUBTITLES)
        );

        return map.getOrDefault(name, null);
    }

}