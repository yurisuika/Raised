package dev.yurisuika.raised.mixin.minecraft.client.gui.components.toasts;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public abstract class ToastComponentMixin {

    @Mixin(targets = "net.minecraft.client.gui.components.toasts.ToastComponent$ToastInstance", priority = -999999999)
    public abstract static class ToastInstanceMixin {

        /**
         * Moves the {@code toasts} for {@link Layer} key "minecraft:toasts".
         */
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
        private void startToastsTranslate(int x, PoseStack poseStack, CallbackInfoReturnable<Boolean> cir) {
            Translate.start(poseStack, LayerRegistry.TOASTS);
        }

        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", shift = At.Shift.AFTER))
        private void endToastsTranslate(int x, PoseStack poseStack, CallbackInfoReturnable<Boolean> cir) {
            Translate.end(poseStack, LayerRegistry.TOASTS);
        }

    }

}