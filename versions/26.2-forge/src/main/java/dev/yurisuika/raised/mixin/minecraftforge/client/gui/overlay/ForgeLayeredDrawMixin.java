package dev.yurisuika.raised.mixin.minecraftforge.client.gui.overlay;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.resources.Identifier;
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

    @Inject(method = "extract", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/overlay/ForgeLayer;extract(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void startTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Iterator iterator, ForgeLayer layer) {
        if (Layers.Curated.CURATED_LAYERS.containsKey(layer)) {
            Translate.start(guiGraphics.pose(), Layers.Curated.CURATED_LAYERS.get(layer));
        }
    }

    @Inject(method = "extract", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/overlay/ForgeLayer;extract(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void endTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Iterator iterator, ForgeLayer layer) {
        if (Layers.Curated.CURATED_LAYERS.containsKey(layer)) {
            Translate.end(guiGraphics.pose(), Layers.Curated.CURATED_LAYERS.get(layer));
        }
    }

    @Inject(method = "add(Lnet/minecraft/resources/Identifier;Lnet/minecraftforge/client/gui/overlay/ForgeLayer;)Lnet/minecraftforge/client/gui/overlay/ForgeLayeredDraw;", at = @At("RETURN"))
    private void registerLayer(Identifier name, ForgeLayer forgeLayer, CallbackInfoReturnable<ForgeLayeredDraw> cir) {
        addLayer(name, forgeLayer);
    }

    @Inject(method = "addConditionTo(Lnet/minecraft/resources/Identifier;Ljava/util/function/BooleanSupplier;)Lnet/minecraftforge/client/gui/overlay/ForgeLayeredDraw;", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void registerComputedLayer(Identifier targetLayer, BooleanSupplier condition, CallbackInfoReturnable<ForgeLayeredDraw> cir, ForgeLayer result) {
        addLayer(targetLayer, result);
    }

    @Unique
    public void addLayer(Identifier name, ForgeLayer forgeLayer) {
        if (!name.getNamespace().equals(Identifier.DEFAULT_NAMESPACE)) {
            LayerRegistry.register(name, new Layer(Layer.Anchor.NONE));
        } else {
            name = curateName(name);
        }

        if (name != null) {
            Layers.Curated.CURATED_LAYERS.put(forgeLayer, name);
        }
    }

    @Unique
    public Identifier curateName(Identifier name) {
        Map<Identifier, Identifier> map = Map.ofEntries(
                Map.entry(ForgeLayeredDraw.SPECTATOR_HOTBAR, Layers.HOTBAR),
                Map.entry(ForgeLayeredDraw.ITEM_HOTBAR, Layers.HOTBAR),
                Map.entry(ForgeLayeredDraw.HEALTH_BAR, Layers.HOTBAR),
                Map.entry(ForgeLayeredDraw.VEHICLE_HEALTH, Layers.HOTBAR),
                Map.entry(ForgeLayeredDraw.BACKGROUND, Layers.HOTBAR),
                Map.entry(ForgeLayeredDraw.EXPERIENCE_LEVEL, Layers.HOTBAR),
                Map.entry(ForgeLayeredDraw.CONTEXTUAL_INFO, Layers.HOTBAR),
                Map.entry(ForgeLayeredDraw.SELECTED_ITEM_NAME, Layers.HOTBAR),
                Map.entry(ForgeLayeredDraw.SPECTATOR_ACTION, Layers.HOTBAR),
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