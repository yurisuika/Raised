package dev.yurisuika.raised.mixin.mods;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class ForgeMixin {

    public abstract static class Hotbar {

        @Mixin(value = ForgeIngameGui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code spectator menu} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lcom/mojang/blaze3d/vertex/PoseStack;F)V"))
            private void startSpectatorMenuTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Layers.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lcom/mojang/blaze3d/vertex/PoseStack;F)V", shift = At.Shift.AFTER))
            private void endSpectatorMenuTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code held item tooltip} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderSelectedItemName(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startHeldItemTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Layers.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderSelectedItemName(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endHeldItemTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code spectator tooltip} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startSpectatorTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Layers.HOTBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
            private void endSpectatorTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

            /**
             * Moves the {@code overlay message} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderRecordOverlay(IIFLcom/mojang/blaze3d/vertex/PoseStack;)V"))
            private void startOverlayMessageTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Layers.HOTBAR);
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
             * Moves the {@code sidebar} for {@link Layer} key "minecraft:sidebar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;displayScoreboardSidebar(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/scores/Objective;)V"))
            private void startScoreboardTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Layers.SIDEBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;displayScoreboardSidebar(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
            private void endScoreboardTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.end();
            }

        }

    }

    public abstract static class Other {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves mod elements at the head/tail of the HUD render for {@link Layer} key "minecraft:other".
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void startRenderHeadTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Layers.OTHER);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void startRenderTailTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
                Translate.start(Layers.OTHER);
            }

        }

        @Mixin(value = Gui.class, priority = 999999999)
        public abstract static class Post {

            /**
             * Moves mod elements at the head/tail of the HUD render for {@link Layer} key "minecraft:other".
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