package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

public abstract class InGameHudMixin {

    @Mixin(value = InGameHud.class, priority = -999999999)
    public abstract static class Pre {

        // HEAD
        @Inject(method = "render", at = @At("HEAD"))
        private void headStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                context.getMatrices().translate(0, -getHud(), 0);
            }
        }

        // SPECTATOR MENU
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void spectatorMenuStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void spectatorMenuEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // HOTBAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/gui/DrawContext;)V"))
        private void hotbarStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbar(FLnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void hotbarEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // STATUS BARS
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void statusBarsStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderStatusBars(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void statusBarsEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // MOUNT HEALTH BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void mountHealthStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountHealth(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void mountHealthEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // MOUNT JUMP BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/gui/DrawContext;I)V"))
        private void mountJumpBarStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderMountJumpBar(Lnet/minecraft/entity/JumpingMount;Lnet/minecraft/client/gui/DrawContext;I)V", shift = At.Shift.AFTER))
        private void mountJumpBarEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // EXPERIENCE BAR
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V"))
        private void experienceBarStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderExperienceBar(Lnet/minecraft/client/gui/DrawContext;I)V", shift = At.Shift.AFTER))
        private void experienceBarEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // HELD ITEM TOOLTIP
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void heldItemTooltipStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHeldItemTooltip(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void heldItemTooltipEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // SPECTATOR TOOLTIP
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V"))
        private void spectatorHudStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;render(Lnet/minecraft/client/gui/DrawContext;)V", shift = At.Shift.AFTER))
        private void spectatorHudEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // OVERLAY MESSAGE
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;push()V", ordinal = 0))
        private void overlayMessageStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getHud(), 0);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V", ordinal = 0, shift = At.Shift.AFTER))
        private void overlayMessageEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getHud(), 0);
        }

        // CHAT
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;III)V"))
        private void chatStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, -getChat(), +300);
        }
        @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/ChatHud;render(Lnet/minecraft/client/gui/DrawContext;III)V", shift = At.Shift.AFTER))
        private void chatEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            context.getMatrices().translate(0, +getChat(), -300);
        }

        // TAIL
        @Inject(method = "render", at = @At("TAIL"))
        private void tailStart(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                context.getMatrices().translate(0, -getHud(), 0);
            }
        }

        // HOTBAR SELECTOR
        @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V"), locals = LocalCapture.CAPTURE_FAILHARD)
        private void drawHotbarSelector(float tickDelta, DrawContext context, CallbackInfo ci, PlayerEntity playerEntity) {
            context.drawTexture(new Identifier("textures/gui/sprites/hud/hotbar_selection.png"), (context.getScaledWindowWidth() / 2) - 92 + playerEntity.getInventory().selectedSlot * 20, context.getScaledWindowHeight(), 0, 0, 24, 1);
        }

    }

    @Mixin(value = InGameHud.class, priority = 999999999)
    public abstract static class Post {

        // HEAD
        @Inject(method = "render", at = @At("HEAD"))
        private void headEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                context.getMatrices().translate(0, +getHud(), 0);
            }
        }

        // TAIL
        @Inject(method = "render", at = @At("TAIL"))
        private void tailEnd(DrawContext context, float tickDelta, CallbackInfo ci) {
            if (getSupport()) {
                context.getMatrices().translate(0, +getHud(), 0);
            }
        }

    }

}