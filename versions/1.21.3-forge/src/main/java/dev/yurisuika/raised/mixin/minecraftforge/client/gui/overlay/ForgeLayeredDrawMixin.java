package dev.yurisuika.raised.mixin.minecraftforge.client.gui.overlay;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.MappedLayers;
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

    @Unique
    public void addLayer(ResourceLocation name, LayeredDraw.Layer layer) {
        if (name.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
            name = formatName(name);
        } else {
            LayerRegistry.register(name, LayerRegistry.createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, name));
        }

        if (name != null) {
            MappedLayers.MAPPED_LAYERS.put(layer, name);
        }
    }

    @Unique
    public ResourceLocation formatName(ResourceLocation name) {
        if (name.equals(ForgeLayeredDraw.HOTBAR)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(ForgeLayeredDraw.EXPERIENCE)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(ForgeLayeredDraw.HOTBAR_MESSAGE)) {
            return LayerRegistry.HOTBAR;
        } else if (name.equals(ForgeLayeredDraw.CHAT_OVERLAY)) {
            return LayerRegistry.CHAT;
        } else if (name.equals(ForgeLayeredDraw.BOSS_OVERLAY)) {
            return LayerRegistry.BOSSBAR;
        } else if (name.equals(ForgeLayeredDraw.SCOREBOARD)) {
            return LayerRegistry.SIDEBAR;
        } else if (name.equals(ForgeLayeredDraw.POTION_EFFECTS)) {
            return LayerRegistry.EFFECTS;
        } else if (name.equals(ForgeLayeredDraw.TAB_LIST)) {
            return LayerRegistry.PLAYERS;
        } else if (name.equals(ForgeLayeredDraw.TITLE_OVERLAY)) {
            return LayerRegistry.TITLES;
        } else if (name.equals(ForgeLayeredDraw.SUBTITLE_OVERLAY)) {
            return LayerRegistry.SUBTITLES;
        } else {
            return null;
        }
    }

}