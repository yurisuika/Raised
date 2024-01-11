package dev.yurisuika.raised.mixin.client.gui.screen;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.yurisuika.raised.client.gui.RaisedGui.*;
import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {

    // CHAT
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;IIIZ)V"))
    private void startChatTranslate(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        start(context, 0, getSync() ? getHud() : getChat(), 0);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;IIIZ)V", shift = At.Shift.AFTER))
    private void endChatTranslate(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        end(context, 0, getSync() ? getHud() : getChat(), 0);
    }

}