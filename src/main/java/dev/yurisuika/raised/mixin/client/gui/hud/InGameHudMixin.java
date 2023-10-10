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

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

public abstract class InGameHudMixin {

    @Mixin(value = InGameHud.class, priority = -999999999)
    public abstract static class Pre {

        // HEAD
        @Inject(method = "render", at = @At("HEAD"))
        private void startHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                context.getMatrices().translate(0, -getHud(), 0);
            }
        }

        // SPECTATOR MENU
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void startSpectatorMenuTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endSpectatorMenuTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // HOTBAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/gui/DrawContext;)V"))
        private void startHotbarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endHotbarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // STATUS BARS
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void startStatusBarsTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endStatusBarsTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // MOUNT HEALTH BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void startMountHealthTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endMountHealthTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // MOUNT JUMP BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/gui/DrawContext;I)V"))
        private void startMountJumpBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/gui/DrawContext;I)V", shift = At.Shift.AFTER))
        private void endMountJumpBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // EXPERIENCE BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V"))
        private void startExperienceBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V", shift = At.Shift.AFTER))
        private void endExperienceBarTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // HELD ITEM TOOLTIP
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void startHeldItemTooltipTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endHeldItemTooltipTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // SPECTATOR TOOLTIP
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void startSpectatorHudTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void endSpectatorHudTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // OVERLAY MESSAGE
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 0))
        private void startOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V", ordinal = 0, shift = At.Shift.AFTER))
        private void endOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // CHAT
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;III)V"))
        private void startChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getChat(), +300);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;III)V", shift = At.Shift.AFTER))
        private void endChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getChat(), -300);
        }

        // TAIL
        @Inject(method = "render", at = @At("TAIL"))
        private void startTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                context.getMatrices().translate(0, -getHud(), 0);
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
                context.getMatrices().translate(0, +getHud(), 0);
            }
        }

        // TAIL
        @Inject(method = "render", at = @At("TAIL"))
        private void endTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                context.getMatrices().translate(0, +getHud(), 0);
            }
        }

    }

}