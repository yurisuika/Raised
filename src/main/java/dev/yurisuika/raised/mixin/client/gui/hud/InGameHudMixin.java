package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static dev.yurisuika.raised.client.gui.RaisedGui.*;
import static dev.yurisuika.raised.client.option.RaisedConfig.*;

public abstract class InGameHudMixin {

    @Mixin(value = InGameHud.class, priority = -999999999)
    public abstract static class Pre {

        // HEAD
        @Inject(method = "render", at = @At("HEAD"))
        private void startHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                start(context, 0, getHud(), 0);
            }
        }

        // MAIN HUD
        @Inject(method = "renderMainHud", at = @At(value = "HEAD"))
        private void startMainHudTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "renderMainHud", at = @At(value = "TAIL"))
        private void endMainHudTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context, 0, getHud(), 0);
        }

        // EXPERIENCE LEVEL
        @Inject(method = "renderExperienceLevel", at = @At(value = "HEAD"))
        private void startExperienceLevelTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "renderExperienceLevel", at = @At(value = "TAIL"))
        private void endExperienceLevelTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context, 0, getHud(), 0);
        }

        // OVERLAY MESSAGE
        @Inject(method = "renderOverlayMessage", at = @At(value = "HEAD"))
        private void startOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "renderOverlayMessage", at = @At(value = "TAIL"))
        private void endOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context, 0, getHud(), 0);
        }

        // TITLE AND SUBTITLES
        @Inject(method = "renderOverlayMessage", at = @At(value = "HEAD"))
        private void startTitleAndSubtitlesTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context, 0, getHud(), 0);
        }

        @Inject(method = "renderOverlayMessage", at = @At(value = "TAIL"))
        private void endTitleAndSubtitlesTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        // CHAT
        @Inject(method = "renderChat", at = @At(value = "HEAD"))
        private void startChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getSync() ? getHud() : getChat(), 0);
        }

        @Inject(method = "renderChat", at = @At(value = "TAIL"))
        private void endChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context, 0, getSync() ? getHud() : getChat(), 0);
        }

        // TAIL
        @Inject(method = "render", at = @At("TAIL"))
        private void startTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                start(context, 0, getHud(), 0);
            }
        }

        // HOTBAR SELECTOR
        @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
        private void resizeHotbarSelector(Args args) {
            args.set(0, new Identifier("raised:hud/hotbar_selection"));
            args.set(4, 24);
        }

    }

    @Mixin(value = InGameHud.class, priority = 999999999)
    public abstract static class Post {

        // HEAD
        @Inject(method = "render", at = @At("HEAD"))
        private void endHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                end(context, 0, getHud(), 0);
            }
        }

        // TAIL
        @Inject(method = "render", at = @At("TAIL"))
        private void endTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                end(context, 0, getHud(), 0);
            }
        }

    }

}