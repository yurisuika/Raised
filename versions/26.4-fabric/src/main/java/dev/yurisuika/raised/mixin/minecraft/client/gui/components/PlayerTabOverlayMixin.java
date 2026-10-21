package dev.yurisuika.raised.mixin.minecraft.client.gui.components;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerTabOverlay.class, priority = -999999999)
public class PlayerTabOverlayMixin {

    /**
     * Moves the {@code player list} for {@link Layer} key "minecraft:player_list".
     */
    @Inject(method = "extractRenderState", at = @At("HEAD"))
    private void startPlayerListTranslate(GuiGraphicsExtractor guiGraphics, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.PLAYER_LIST);
    }

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void endPlayerListTranslate(GuiGraphicsExtractor guiGraphics, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.PLAYER_LIST);
    }

}