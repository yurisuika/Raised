package dev.yurisuika.raised.mixin.client.gui.components.toasts;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.properties.Element;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class ToastManagerMixin {

    public abstract static class Toasts {

        @Mixin(targets = "net.minecraft.client.gui.components.toasts.ToastManager$ToastInstance", priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code toasts} if {@link Element.TOASTS} is enabled.
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void startToastsTranslate(GuiGraphics guiGraphics, int i, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.TOASTS);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void endToastsTranslate(GuiGraphics guiGraphics, int i, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

}