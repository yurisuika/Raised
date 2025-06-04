package dev.yurisuika.raised.mixin.client.gui.components.toasts;

import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.client.gui.GuiGraphics;
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
            private void startToastsTranslate(int screenWidth, GuiGraphics guiGraphics, CallbackInfoReturnable<Boolean> cir) {
                Translate.start(guiGraphics.pose(), Layers.TOASTS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", shift = At.Shift.AFTER))
            private void endToastsTranslate(int x,GuiGraphics guiGraphics, CallbackInfoReturnable<Boolean> cir) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

}