package dev.yurisuika.raised.mixin.minecraft.client.gui.components;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.SubtitleOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SubtitleOverlay.class, priority = -999999999)
public abstract class SubtitleOverlayMixin {

    /**
     * Moves the {@code subtitles} for {@link Layer} key "minecraft:subtitles".
     */
    @Inject(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lorg/joml/Matrix3x2fStack;pushMatrix()Lorg/joml/Matrix3x2fStack;"))
    private void startSubtitlesTranslate(GuiGraphicsExtractor guiGraphicsExtractor, CallbackInfo ci) {
        Translate.start(guiGraphicsExtractor.pose(), LayerRegistry.SUBTITLES);
    }

    @Inject(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lorg/joml/Matrix3x2fStack;popMatrix()Lorg/joml/Matrix3x2fStack;", shift = At.Shift.AFTER))
    private void endSubtitlesTranslate(GuiGraphicsExtractor guiGraphicsExtractor, CallbackInfo ci) {
        Translate.end(guiGraphicsExtractor.pose(), LayerRegistry.SUBTITLES);
    }

}