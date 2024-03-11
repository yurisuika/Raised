package dev.yurisuika.raised.mixin.client.gui.hud;

import dev.yurisuika.raised.mixin.client.gui.DrawContextInvoker;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import static dev.yurisuika.raised.client.gui.RaisedGui.*;
import static dev.yurisuika.raised.client.option.RaisedConfig.*;

public abstract class InGameHudMixin {

    @Mixin(value = InGameHud.class, priority = -999999999)
    public abstract static class Pre {

        // RENDER (HEAD)
        @Inject(method = "render", at = @At("HEAD"))
        private void startRenderHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                start(context, 0, getHud(), 0);
            }
        }

        // RENDER (TAIL)
        @Inject(method = "render", at = @At("TAIL"))
        private void startRenderTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
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
            end(context);
        }

        // EXPERIENCE LEVEL
        @Inject(method = "renderExperienceLevel", at = @At(value = "HEAD"))
        private void startExperienceLevelTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "renderExperienceLevel", at = @At(value = "TAIL"))
        private void endExperienceLevelTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // OVERLAY MESSAGE
        @Inject(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V"))
        private void startOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getHud(), 0);
        }

        @Inject(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", shift = At.Shift.AFTER))
        private void endOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // CHAT
        @Inject(method = "renderChat", at = @At(value = "HEAD"))
        private void startChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            start(context, 0, getSync() ? getHud() : getChat(), 0);
        }

        @Inject(method = "renderChat", at = @At(value = "TAIL"))
        private void endChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            end(context);
        }

        // HOTBAR SELECTOR
        @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
        private void resizeHotbarSelector(Args args) {
            if (config.toggle.texture) {
                args.set(0, new Identifier("raised:hud/hotbar_selection"));
                args.set(4, 24);
            }
        }

        @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V"), locals = LocalCapture.CAPTURE_FAILHARD)
        private void drawHotbarSelector(DrawContext context, float tickDelta, CallbackInfo ci, PlayerEntity playerEntity) {
            if (!config.toggle.texture) {
                int x = (context.getScaledWindowWidth() / 2) - 92 + playerEntity.getInventory().selectedSlot * 20;
                int y = context.getScaledWindowHeight();
                ((DrawContextInvoker)context).invokeDrawTexturedQuad(new Identifier("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 0, 1, 1 / 23.0F, 0);
            }
        }

    }

    @Mixin(value = InGameHud.class, priority = 999999999)
    public abstract static class Post {

        // RENDER (HEAD)
        @Inject(method = "render", at = @At("HEAD"))
        private void endRenderHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                end(context);
            }
        }

        // RENDER (TAIL)
        @Inject(method = "render", at = @At("TAIL"))
        private void endRenderTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                end(context);
            }
        }

    }

}