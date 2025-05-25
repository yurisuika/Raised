package dev.yurisuika.raised.mixin.client.gui.components.toasts;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.util.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.options.Layer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public abstract class ToastComponentMixin {

    public abstract static class Toasts {

        @Mixin(targets = "net.minecraft.client.gui.components.toasts.ToastComponent$ToastInstance", priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code toasts} for {@link Layer} key "minecraft:toasts".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
            private void startToastsTranslate(int x, PoseStack poseStack, CallbackInfoReturnable<Boolean> cir) {
                Translate.start(RenderSystem.getModelViewStack(), Layers.TOASTS.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", shift = At.Shift.AFTER))
            private void endToastsTranslate(int x, PoseStack poseStack, CallbackInfoReturnable<Boolean> cir) {
                Translate.end(RenderSystem.getModelViewStack());
            }

        }

    }

}