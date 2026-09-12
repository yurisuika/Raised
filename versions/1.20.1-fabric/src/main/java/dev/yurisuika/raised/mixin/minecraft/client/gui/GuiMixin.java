package dev.yurisuika.raised.mixin.minecraft.client.gui;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
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
     * Resizes the hotbar selection to draw the entire texture.
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
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endSpectatorMenuTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code hotbar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startHotbarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endHotbarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code health bar}, {@code armor bar}, {@code food bar}, and {@code air bar}
     * for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startStatusBarsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endStatusBarsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code mount health bar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startMountHealthTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endMountHealthTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code mount jump bar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderJumpMeter(Lnet/minecraft/world/entity/PlayerRideableJumping;Lnet/minecraft/client/gui/GuiGraphics;I)V"))
    private void startMountJumpBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderJumpMeter(Lnet/minecraft/world/entity/PlayerRideableJumping;Lnet/minecraft/client/gui/GuiGraphics;I)V", shift = At.Shift.AFTER))
    private void endMountJumpBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code experience bar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V"))
    private void startExperienceBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V", shift = At.Shift.AFTER))
    private void endExperienceBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code held item tooltip} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startHeldItemTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endHeldItemTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code spectator tooltip} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startSpectatorTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endSpectatorTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code action bar} for {@link Layer} key "minecraft:action_bar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", ordinal = 0))
    private void startActionBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.ACTION_BAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", ordinal = 0, shift = At.Shift.AFTER))
    private void endActionBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.ACTION_BAR);
    }

    /**
     * Moves the {@code chat} for {@link Layer} key "minecraft:chat".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;III)V"))
    private void startChatTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.CHAT);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;III)V", shift = At.Shift.AFTER))
    private void endChatTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.CHAT);
    }

    /**
     * Moves the {@code boss bar} for {@link Layer} key "minecraft:boss_bar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startBossBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.BOSS_BAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endBossBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.BOSS_BAR);
    }

    /**
     * Moves the {@code scoreboard} for {@link Layer} key "minecraft:scoreboard".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/scores/Objective;)V"))
    private void startScoreboardTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.SCOREBOARD);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
    private void endScoreboardTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.SCOREBOARD);
    }

    /**
     * Moves the {@code effects} for {@link Layer} key "minecraft:effects".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lnet/minecraft/client/gui/GuiGraphics;)V"))
    private void startEffectsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.EFFECTS);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
    private void endEffectsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.EFFECTS);
    }

    /**
     * Moves the {@code player list} for {@link Layer} key "minecraft:player_list".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V"))
    private void startPlayerListTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.PLAYER_LIST);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
    private void endPlayerListTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.PLAYER_LIST);
    }

    /**
     * Moves the {@code titles} for {@link Layer} key "minecraft:titles".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V", ordinal = 3))
    private void startTitlesTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.TITLES);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", ordinal = 3, shift = At.Shift.AFTER))
    private void endTitlesTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.TITLES);
    }

    /**
     * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:unknown".
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void startRenderHeadTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.UNKNOWN);
    }

    /**
     * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:unknown".
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void startRenderTailTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.UNKNOWN);
    }

    @Mixin(value = Gui.class, priority = 999999999)
    public abstract static class Last {

        /**
         * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:unknown".
         */
        @Inject(method = "render", at = @At("HEAD"))
        private void endRenderHeadTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
            Translate.end(guiGraphics.pose(), Layers.UNKNOWN);
        }

        /**
         * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:unknown".
         */
        @Inject(method = "render", at = @At("TAIL"))
        private void endRenderTailTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
            Translate.end(guiGraphics.pose(), Layers.UNKNOWN);
        }

    }

}