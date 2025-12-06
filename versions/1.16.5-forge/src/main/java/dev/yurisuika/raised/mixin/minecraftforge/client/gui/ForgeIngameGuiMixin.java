package dev.yurisuika.raised.mixin.minecraftforge.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ForgeIngameGui.class, priority = -999999999)
public abstract class ForgeIngameGuiMixin {

    /**
     * Moves the {@code spectator menu} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lcom/mojang/blaze3d/vertex/PoseStack;F)V"))
    private void startSpectatorMenuTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lcom/mojang/blaze3d/vertex/PoseStack;F)V", shift = At.Shift.AFTER))
    private void endSpectatorMenuTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code held item tooltip} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderSelectedItemName(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startHeldItemTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderSelectedItemName(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endHeldItemTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code spectator tooltip} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startSpectatorTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endSpectatorTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code overlay message} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderRecordOverlay(IIFLcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startOverlayMessageTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderRecordOverlay(IIFLcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endOverlayMessageTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code sidebar} for {@link Layer} key "minecraft:sidebar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;displayScoreboardSidebar(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/scores/Objective;)V"))
    private void startSidebarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(LayerRegistry.SIDEBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;displayScoreboardSidebar(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
    private void endSidebarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(LayerRegistry.SIDEBAR);
    }

    /**
     * Moves the {@code titles} for {@link Layer} key "minecraft:titles".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderTitle(IIFLcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startTitlesTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(LayerRegistry.TITLES);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;renderTitle(IIFLcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endTitlesTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(LayerRegistry.TITLES);
    }

    /**
     * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:other".
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void startRenderHeadTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(LayerRegistry.OTHER);
    }

    /**
     * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:other".
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void startRenderTailTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(LayerRegistry.OTHER);
    }

    @Mixin(value = ForgeIngameGui.class, priority = 999999999)
    public abstract static class Last {

        /**
         * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:other".
         */
        @Inject(method = "render", at = @At("HEAD"))
        private void endRenderHeadTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
            Translate.end(LayerRegistry.OTHER);
        }

        /**
         * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:other".
         */
        @Inject(method = "render", at = @At("TAIL"))
        private void endRenderTailTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
            Translate.end(LayerRegistry.OTHER);
        }

    }

}