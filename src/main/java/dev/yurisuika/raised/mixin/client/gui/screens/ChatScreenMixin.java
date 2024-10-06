package dev.yurisuika.raised.mixin.client.gui.screens;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.properties.Element;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.ChatScreen;
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
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;IIIZ)V"))
            private void startChatTranslate(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.CHAT);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;IIIZ)V", shift = At.Shift.AFTER))
            private void endChatTranslate(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

}