package dev.yurisuika.raised.mixin.client.gui.hud;

import dev.yurisuika.raised.mixin.client.gui.DrawContextInvoker;
import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.type.Element;
import dev.yurisuika.raised.util.type.Texture;
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

public abstract class InGameHudMixin {

    public abstract static class Hotbar {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code hotbar}, {@code health bar}, {@code armor bar}, {@code food bar}, {@code air bar},
             * {@code mount health bar}, {@code mount jump bar}, {@code experience bar}, and {@code held item tooltip}
             * for {@link Element.HOTBAR}.
             */
            @Inject(method = "renderMainHud", at = @At("HEAD"))
            private void startMainHudTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "renderMainHud", at = @At("TAIL"))
            private void endMainHudTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Moves the {@code experience level} for {@link Element.HOTBAR}.
             */
            @Inject(method = "renderExperienceLevel", at = @At("HEAD"))
            private void startExperienceLevelTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "renderExperienceLevel", at = @At("TAIL"))
            private void endExperienceLevelTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Moves the {@code overlay message} for {@link Element.HOTBAR}.
             */
            @Inject(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V"))
            private void startOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.HOTBAR);
            }

            @Inject(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", shift = At.Shift.AFTER))
            private void endOverlayMessageTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            /**
             * Replaces the hotbar selector with a new square asset found under the {@code raised} namespace.
             */
            @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lnet/minecraft/util/Identifier;IIII)V", ordinal = 1))
            private void replaceHotbarSelector(Args args) {
                if (Option.getTexture() == Texture.REPLACE || (Option.getTexture() == Texture.AUTO && Pack.getPack())) {
                    args.set(0, new Identifier("raised:hud/hotbar_selection"));
                    args.set(4, 24);
                }
            }

            /**
             * Draws a vertically mirrored row taken from the top of the asset below the unmodified selector.
             */
            @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/math/MatrixStack;pop()V"), locals = LocalCapture.CAPTURE_FAILHARD)
            private void patchHotbarSelector(DrawContext context, float tickDelta, CallbackInfo ci, PlayerEntity playerEntity) {
                if (Option.getTexture() == Texture.PATCH  || (Option.getTexture() == Texture.AUTO && !Pack.getPack())) {
                    int x = (context.getScaledWindowWidth() / 2) - 92 + playerEntity.getInventory().selectedSlot * 20;
                    int y = context.getScaledWindowHeight();
                    ((DrawContextInvoker)context).invokeDrawTexturedQuad(new Identifier("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 0, 1, 1 / 23.0F, 0);
                }
            }

        }

    }

    public abstract static class Chat {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code chat} for {@link Element.CHAT}
             */
            @Inject(method = "renderChat", at = @At("HEAD"))
            private void startChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.CHAT);
            }

            @Inject(method = "renderChat", at = @At("TAIL"))
            private void endChatTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

    public abstract static class Sidebar {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code sidebar} for {@link Element.SIDEBAR}.
             */
            @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;F)V", at = @At("HEAD"))
            private void startScoreboardTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.SIDEBAR);
            }

            @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/DrawContext;F)V", at = @At("TAIL"))
            private void endScoreboardTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

    public abstract static class Effects {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code status effects} for {@link Element.EFFECTS}
             */
            @Inject(method = "renderStatusEffectOverlay", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;enableBlend()V"))
            private void startStatusEffectTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.EFFECTS);
            }

            @Inject(method = "renderStatusEffectOverlay", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;disableBlend()V", shift = At.Shift.AFTER))
            private void endStatusEffectTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

    public abstract static class Players {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code player list} for {@link Element.PLAYERS}.
             */
            @Inject(method = "renderPlayerList", at = @At("HEAD"))
            private void startPlayerListTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.PLAYERS);
            }

            @Inject(method = "renderPlayerList", at = @At("TAIL"))
            private void endPlayerListTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

    public abstract static class Other {

        @Mixin(value = InGameHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves mod elements at the head/tail of the HUD render for {@link Element.OTHER}.
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void startRenderHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.OTHER);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void startRenderTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.OTHER);
            }

        }

        @Mixin(value = InGameHud.class, priority = 999999999)
        public abstract static class Post {

            /**
             * Moves mod elements at the head/tail of the HUD render for {@link Element.OTHER}.
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void endRenderHeadTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void endRenderTailTranslate(DrawContext context, float tickDelta, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

}