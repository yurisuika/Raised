package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.properties.Element;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public abstract class GuiMixin {

    public abstract static class Hotbar {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code spectator menu} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startSpectatorMenuTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endSpectatorMenuTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code hotbar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startHotbarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endHotbarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code health bar}, {@code armor bar}, {@code food bar}, and {@code air bar}
             * for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startStatusBarsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endStatusBarsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code mount health bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startMountHealthTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endMountHealthTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code mount jump bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderJumpMeter(Lnet/minecraft/world/entity/PlayerRideableJumping;Lnet/minecraft/client/gui/GuiGraphics;I)V"))
            private void startMountJumpBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderJumpMeter(Lnet/minecraft/world/entity/PlayerRideableJumping;Lnet/minecraft/client/gui/GuiGraphics;I)V", shift = At.Shift.AFTER))
            private void endMountJumpBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code experience bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V"))
            private void startExperienceBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V", shift = At.Shift.AFTER))
            private void endExperienceBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code held item tooltip} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startHeldItemTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endHeldItemTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code spectator tooltip} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startSpectatorTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endSpectatorTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code overlay message} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", ordinal = 0))
            private void startOverlayMessageTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", ordinal = 0, shift = At.Shift.AFTER))
            private void endOverlayMessageTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Resizes the hotbar selector to draw the entire texture.
             */
            @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V", ordinal = 1))
            private void replaceHotbarSelectorHeight(Args args) {
                args.set(6, 24);
            }

        }

    }

    public abstract static class Chat {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code chat} for {@link Element.CHAT}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;III)V"))
            private void startChatTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.CHAT);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;III)V", shift = At.Shift.AFTER))
            private void endChatTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

    public abstract static class Boss {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code bossbar} for {@link Element.BOSSBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startBossBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.BOSSBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endBossBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
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
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/scores/Objective;)V"))
            private void startScoreboardTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.SIDEBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
            private void endScoreboardTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
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
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startStatusEffectTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.EFFECTS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endStatusEffectTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
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
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V"))
            private void startPlayerListTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.PLAYERS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
            private void endPlayerListTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
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
            private void startRenderHeadTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.OTHER);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void startRenderTailTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.OTHER);
            }

        }

        @Mixin(value = Gui.class, priority = 999999999)
        public abstract static class Post {

            /**
             * Moves mod elements at the head/tail of the HUD render if {@link Element.OTHER}.
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void endRenderHeadTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void endRenderTailTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

}