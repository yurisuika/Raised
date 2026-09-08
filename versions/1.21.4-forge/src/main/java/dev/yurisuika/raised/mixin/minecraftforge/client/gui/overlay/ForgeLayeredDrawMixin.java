package dev.yurisuika.raised.mixin.minecraftforge.client.gui.overlay;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.mixin.minecraft.client.gui.LayeredDrawAccessor;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeLayeredDraw;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Map;
import java.util.function.BooleanSupplier;

@Mixin(value = ForgeLayeredDraw.class, remap = false)
public abstract class ForgeLayeredDrawMixin {

    @Inject(method = "add(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/gui/LayeredDraw;Ljava/util/function/BooleanSupplier;)Lnet/minecraftforge/client/gui/overlay/ForgeLayeredDraw;", at = @At("RETURN"))
    private void registerLayeredDrawLayers(ResourceLocation name, LayeredDraw layeredDraw, BooleanSupplier supplier, CallbackInfoReturnable<ForgeLayeredDraw> cir) {
        for (LayeredDraw.Layer layer : ((LayeredDrawAccessor) layeredDraw).getLayers()) {
            addLayer(name, layer);
        }
    }

    @Inject(method = "add(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/gui/LayeredDraw$Layer;)Lnet/minecraftforge/client/gui/overlay/ForgeLayeredDraw;", at = @At("RETURN"))
    private void registerLayer(ResourceLocation name, LayeredDraw.Layer layer, CallbackInfoReturnable<ForgeLayeredDraw> cir) {
        addLayer(name, layer);
    }

    @Inject(method = "addConditionTo(Lnet/minecraft/resources/ResourceLocation;Ljava/util/function/BooleanSupplier;)Lnet/minecraftforge/client/gui/overlay/ForgeLayeredDraw;", at = @At("RETURN"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void registerComputedLayer(ResourceLocation targetLayer, BooleanSupplier condition, CallbackInfoReturnable<ForgeLayeredDraw> cir, LayeredDraw.Layer result) {
        addLayer(targetLayer, result);
    }

    @Unique
    public void addLayer(ResourceLocation name, LayeredDraw.Layer layer) {
        if (!name.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
            LayerRegistry.register(name, new Layer(Layer.Anchor.NONE));
        } else {
            name = curateName(name);
        }

        if (name != null) {
            Layers.Curated.CURATED_LAYERS.put(layer, name);
        }
    }

    @Unique
    public ResourceLocation curateName(ResourceLocation name) {
        Map<ResourceLocation, ResourceLocation> map = Map.ofEntries(
                Map.entry(ForgeLayeredDraw.HOTBAR, Layers.HOTBAR),
                Map.entry(ForgeLayeredDraw.EXPERIENCE, Layers.HOTBAR),
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