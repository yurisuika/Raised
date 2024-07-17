package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.properties.Element;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class ForgeIngameGuiMixin {

    public abstract static class Hotbar {

        @Mixin(value = ForgeIngameGui.class, priority = -999999999)
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
             * Moves the {@code held item tooltip} for {@link Element.HOTBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderHeldItemTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startHeldItemTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderHeldItemTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
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
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderRecordOverlay(IIFLcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startOverlayMessageTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderRecordOverlay(IIFLcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endOverlayMessageTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

        }

    }

    public abstract static class Sidebar {

        @Mixin(value = ForgeIngameGui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code sidebar} for {@link Element.SIDEBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderScoreboardSidebar(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/scoreboard/ScoreboardObjective;)V"))
            private void startScoreboardTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Element.SIDEBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderScoreboardSidebar(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/scoreboard/ScoreboardObjective;)V", shift = At.Shift.AFTER))
            private void endScoreboardTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

        }

    }

}