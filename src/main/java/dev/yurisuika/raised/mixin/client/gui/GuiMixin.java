package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.Config;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.properties.Element;
import dev.yurisuika.raised.util.resources.Texture;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public abstract class GuiMixin {

    public abstract static class Hotbar {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code hotbar}, {@code health bar}, {@code armor bar}, {@code food bar}, {@code air bar},
             * {@code mount health bar}, {@code mount jump bar}, {@code experience bar}, and {@code held item tooltip}
             * if {@link Element.HOTBAR}.
             */
            @Inject(method = "renderHotbarAndDecorations", at = @At("HEAD"))
            private void startMainHudTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "renderHotbarAndDecorations", at = @At("TAIL"))
            private void endMainHudTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code experience level} for {@link Element.HOTBAR}.
             */
            @Inject(method = "renderExperienceLevel", at = @At("HEAD"))
            private void startExperienceLevelTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "renderExperienceLevel", at = @At("TAIL"))
            private void endExperienceLevelTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code overlay message} for {@link Element.HOTBAR}.
             */
            @Inject(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
            private void startOverlayMessageTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
            private void endOverlayMessageTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Replaces the hotbar selector with a new square asset found under the {@code raised} namespace.
             */
            @ModifyArgs(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1))
            private void replaceHotbarSelector(Args args) {
                if (Config.getOptions().getResources().getTexture() == Texture.REPLACE || (Option.getTexture() == Texture.AUTO && Pack.getPack())) {
                    args.set(0, ResourceLocation.tryParse("raised:hud/hotbar_selection"));
                    args.set(4, 24);
                }
            }

            /**
             * Draws a vertically mirrored row taken from the top of the asset below the unmodified selector.
             */
            @Inject(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"), locals = LocalCapture.CAPTURE_FAILHARD)
            private void patchHotbarSelector(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Player player) {
                if (Option.getTexture() == Texture.PATCH  || (Option.getTexture() == Texture.AUTO && !Pack.getPack())) {
                    int x = (guiGraphics.guiWidth() / 2) - 92 + player.getInventory().selected * 20;
                    int y = guiGraphics.guiHeight();
                    ((GuiGraphicsInvoker)guiGraphics).invokeInnerBlit(RenderType::guiTextured, ResourceLocation.tryParse("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 0, 1, 1 / 23.0F, 0);
                }
            }

        }

    }

    public abstract static class Chat {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code chat} for {@link Element.CHAT}.
             */
            @Inject(method = "renderChat", at = @At("HEAD"))
            private void startChatTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.CHAT);
            }

            @Inject(method = "renderChat", at = @At("TAIL"))
            private void endChatTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

    public abstract static class Sidebar {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code sidebar} for {@link Element.SIDEBAR}.
             */
            @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", at = @At("HEAD"))
            private void startScoreboardTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.SIDEBAR);
            }

            @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", at = @At("TAIL"))
            private void endScoreboardTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

    public abstract static class Effects {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code status effects} for {@link Element.EFFECTS}.
             */
            @Inject(method = "renderEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getMobEffectTextures()Lnet/minecraft/client/resources/MobEffectTextureManager;"))
            private void startStatusEffectTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.EFFECTS);
            }

            @Inject(method = "renderEffects", at = @At("TAIL"))
            private void endStatusEffectTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

    public abstract static class Players {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code player list} for {@link Element.PLAYERS}.
             */
            @Inject(method = "renderTabList", at = @At("HEAD"))
            private void startPlayerListTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.PLAYERS);
            }

            @Inject(method = "renderTabList", at = @At("TAIL"))
            private void endPlayerListTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

    public abstract static class Other {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves mod elements at the head/tail of the HUD render if {@link Element.OTHER}.
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void startRenderHeadTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.OTHER);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void startRenderTailTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.OTHER);
            }

        }

        @Mixin(value = Gui.class, priority = 999999999)
        public abstract static class Post {

            /**
             * Moves mod elements at the head/tail of the HUD render if {@link Element.OTHER}.
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void endRenderHeadTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void endRenderTailTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

}