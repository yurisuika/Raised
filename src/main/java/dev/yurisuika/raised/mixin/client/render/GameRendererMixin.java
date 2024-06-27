package dev.yurisuika.raised.mixin.client.render;

import com.llamalad7.mixinextras.sugar.Local;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.type.Element;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class GameRendererMixin {

    public abstract static class Toasts {

        @Mixin(value = GameRenderer.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code toasts} for {@link Element.TOASTS}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;draw(Lnet/minecraft/client/gui/DrawContext;)V"))
            private void startToastsTranslate(float tickDelta, long startTime, boolean tick, CallbackInfo ci, @Local DrawContext context) {
                Translate.start(context.getMatrices(), Element.TOASTS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/toast/ToastManager;draw(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
            private void endToastsTranslate(float tickDelta, long startTime, boolean tick, CallbackInfo ci, @Local DrawContext context) {
                Translate.end(context.getMatrices());
            }

        }

    }

}