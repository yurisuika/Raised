package dev.yurisuika.raised.mixin.client.gui.screen;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.type.Element;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class ChatScreenMixin {

    public abstract static class Chat {

        @Mixin(value = ChatScreen.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code chat} for {@link Element.CHAT}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;IIIZ)V"))
            private void startChatTranslate(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.CHAT);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;IIIZ)V", shift = At.Shift.AFTER))
            private void endChatTranslate(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

}