package dev.yurisuika.raised.mixin.minecraft.client.gui;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Gui.class, priority = -999999999)
public abstract class GuiMixin {

    /**
     * Resizes the hotbar selector to draw the entire texture.
     */
    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V", ordinal = 1), index = 6)
    private int replaceHotbarSelectorHeight(int height) {
        return 24;
    }

    /**
     * Moves the {@code spectator menu} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startSpectatorMenuTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endSpectatorMenuTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code hotbar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startHotbarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endHotbarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code health bar}, {@code armor bar}, {@code food bar}, and {@code air bar}
     * for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startStatusBarsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endStatusBarsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code mount health bar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startMountHealthTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endMountHealthTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code mount jump bar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderJumpMeter(Lnet/minecraft/world/entity/PlayerRideableJumping;Lnet/minecraft/client/gui/GuiGraphics;I)V"))
    private void startMountJumpBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderJumpMeter(Lnet/minecraft/world/entity/PlayerRideableJumping;Lnet/minecraft/client/gui/GuiGraphics;I)V", shift = At.Shift.AFTER))
    private void endMountJumpBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code experience bar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V"))
    private void startExperienceBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V", shift = At.Shift.AFTER))
    private void endExperienceBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code held item tooltip} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startHeldItemTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endHeldItemTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code spectator tooltip} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startSpectatorTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endSpectatorTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code overlay message} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", ordinal = 0))
    private void startOverlayMessageTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", ordinal = 0, shift = At.Shift.AFTER))
    private void endOverlayMessageTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code chat} for {@link Layer} key "minecraft:chat".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;III)V"))
    private void startChatTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.CHAT);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;III)V", shift = At.Shift.AFTER))
    private void endChatTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.CHAT);
    }

    /**
     * Moves the {@code bossbar} for {@link Layer} key "minecraft:bossbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startBossBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.BOSSBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endBossBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.BOSSBAR);
    }

    /**
     * Moves the {@code sidebar} for {@link Layer} key "minecraft:sidebar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/scores/Objective;)V"))
    private void startSidebarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.SIDEBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
    private void endSidebarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.SIDEBAR);
    }

    /**
     * Moves the {@code effects} for {@link Layer} key "minecraft:effects".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startEffectsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.EFFECTS);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endEffectsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.EFFECTS);
    }

    /**
     * Moves the {@code players} for {@link Layer} key "minecraft:players".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V"))
    private void startPlayersTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.PLAYERS);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
    private void endPlayersTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.PLAYERS);
    }

    /**
     * Moves the {@code titles} for {@link Layer} key "minecraft:titles".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V", ordinal = 3))
    private void startTitlesTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.TITLES);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", ordinal = 3, shift = At.Shift.AFTER))
    private void endTitlesTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.TITLES);
    }

    /**
     * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:other".
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void startRenderHeadTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.OTHER);
    }

    /**
     * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:other".
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void startRenderTailTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.OTHER);
    }

    @Mixin(value = Gui.class, priority = 999999999)
    public abstract static class Last {

        /**
         * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:other".
         */
        @Inject(method = "render", at = @At("HEAD"))
        private void endRenderHeadTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
            Translate.end(guiGraphics.pose(), LayerRegistry.OTHER);
        }

        /**
         * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:other".
         */
        @Inject(method = "render", at = @At("TAIL"))
        private void endRenderTailTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
            Translate.end(guiGraphics.pose(), LayerRegistry.OTHER);
        }

    }

}