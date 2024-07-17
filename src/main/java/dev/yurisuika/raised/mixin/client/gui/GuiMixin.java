package dev.yurisuika.raised.mixin.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.properties.Element;
import net.minecraft.client.gui.Gui;
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
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lcom/mojang/blaze3d/vertex/PoseStack;F)V"))
            private void startSpectatorMenuTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lcom/mojang/blaze3d/vertex/PoseStack;F)V", shift = At.Shift.AFTER))
            private void endSpectatorMenuTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code hotbar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startHotbarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endHotbarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code health bar}, {@code armor bar}, {@code food bar}, and {@code air bar}
             * for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startStatusBarsTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endStatusBarsTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code mount health bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startMountHealthTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endMountHealthTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code mount jump bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startMountJumpBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endMountJumpBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code experience bar} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lcom/mojang/blaze3d/vertex/PoseStack;I)V"))
            private void startExperienceBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lcom/mojang/blaze3d/vertex/PoseStack;I)V", shift = At.Shift.AFTER))
            private void endExperienceBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code held item tooltip} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startHeldItemTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endHeldItemTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code spectator tooltip} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startSpectatorTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endSpectatorTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code overlay message} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;pushMatrix()V", ordinal = 0))
            private void startOverlayMessageTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;popMatrix()V", ordinal = 0, shift = At.Shift.AFTER))
            private void endOverlayMessageTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Resizes the hotbar selector to draw the entire texture.
             */
            @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V", ordinal = 1))
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
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lcom/mojang/blaze3d/vertex/PoseStack;I)V"))
            private void startChatTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.CHAT);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lcom/mojang/blaze3d/vertex/PoseStack;I)V", shift = At.Shift.AFTER))
            private void endChatTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

        }

    }

    public abstract static class Boss {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code bossbar} for {@link Element.BOSSBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;render(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startBossBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.BOSSBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;render(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endBossBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

        }

    }

    public abstract static class Sidebar {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code sidebar} for {@link Element.SIDEBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/scores/Objective;)V"))
            private void startScoreboardTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.SIDEBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
            private void endScoreboardTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

        }

    }

    public abstract static class Effects {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code status effects} for {@link Element.EFFECTS}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startStatusEffectTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.EFFECTS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endStatusEffectTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

        }

    }

    public abstract static class Players {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code player list} for {@link Element.PLAYERS}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lcom/mojang/blaze3d/vertex/PoseStack;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V"))
            private void startPlayerListTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.PLAYERS);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lcom/mojang/blaze3d/vertex/PoseStack;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
            private void endPlayerListTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
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
            private void startRenderHeadTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.OTHER);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void startRenderTailTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.OTHER);
            }

        }

        @Mixin(value = Gui.class, priority = 999999999)
        public abstract static class Post {

            /**
             * Moves mod elements at the head/tail of the HUD render if {@link Element.OTHER}.
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void endRenderHeadTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void endRenderTailTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

        }

    }

}