package dev.yurisuika.raised.mixin.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.options.Layer;
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
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
            private void startSubtitlesTranslate(PoseStack poseStack, CallbackInfo ci) {
                Translate.start(poseStack, Layers.SUBTITLES);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", shift = At.Shift.AFTER))
            private void endSubtitlesTranslate(PoseStack poseStack, CallbackInfo ci) {
                Translate.end(poseStack);
            }

        }

    }

}