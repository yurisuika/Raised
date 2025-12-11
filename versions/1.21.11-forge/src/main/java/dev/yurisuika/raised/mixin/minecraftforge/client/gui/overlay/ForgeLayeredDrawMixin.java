package dev.yurisuika.raised.mixin.minecraftforge.client.gui.overlay;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.MappedLayers;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphics;
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

@Mixin(value = ForgeLayeredDraw.class, remap = false)
public abstract class ForgeLayeredDrawMixin {

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/overlay/ForgeLayer;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void startTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Iterator iterator, ForgeLayer layer) {
        if (MappedLayers.MAPPED_LAYERS.containsKey(layer)) {
            Translate.start(guiGraphics.pose(), MappedLayers.MAPPED_LAYERS.get(layer));
        }
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/overlay/ForgeLayer;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", shift = At.Shift.AFTER), locals = LocalCapture.CAPTURE_FAILHARD)
    private void endTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Iterator iterator, ForgeLayer layer) {
        if (MappedLayers.MAPPED_LAYERS.containsKey(layer)) {
            Translate.end(guiGraphics.pose(), MappedLayers.MAPPED_LAYERS.get(layer));
        }
    }

    @Inject(method = "add(Lnet/minecraft/resources/Identifier;Lnet/minecraftforge/client/gui/overlay/ForgeLayer;)Lnet/minecraftforge/client/gui/overlay/ForgeLayeredDraw;", at = @At("RETURN"))
    private void registerLayer(Identifier name, ForgeLayer forgeLayer, CallbackInfoReturnable<ForgeLayeredDraw> cir) {
        addLayer(name, forgeLayer);
    }

    @Unique
    public void addLayer(Identifier name, ForgeLayer forgeLayer) {
        if (name.getNamespace().equals(Identifier.DEFAULT_NAMESPACE)) {
            name = formatName(name);
        } else {
            LayerRegistry.register(name, LayerRegistry.createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, name));
        }

        if (name != null) {
            MappedLayers.MAPPED_LAYERS.put(forgeLayer, name);
        }
    }

    @Unique
    public Identifier formatName(Identifier name) {
        if (name.equals(ForgeLayeredDraw.HOTBAR_AND_DECOS)) {
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