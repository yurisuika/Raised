package dev.yurisuika.raised.mixin.client.gui.components;

import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SubtitleOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class SubtitleOverlayMixin {

    public abstract static class Subtitles {

        @Mixin(value = SubtitleOverlay.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code subtitles} for {@link Layer} key "minecraft:subtitles".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lorg/joml/Matrix3x2fStack;pushMatrix()Lorg/joml/Matrix3x2fStack;"))
            private void startSubtitlesTranslate(GuiGraphics guiGraphics, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.SUBTITLES);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lorg/joml/Matrix3x2fStack;popMatrix()Lorg/joml/Matrix3x2fStack;", shift = At.Shift.AFTER))
            private void endSubtitlesTranslate(GuiGraphics guiGraphics, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

}