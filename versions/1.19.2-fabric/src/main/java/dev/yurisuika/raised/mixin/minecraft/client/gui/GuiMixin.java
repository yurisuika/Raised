package dev.yurisuika.raised.mixin.minecraft.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V", ordinal = 1), index = 6)
    private int replaceHotbarSelectorHeight(int height) {
        return 24;
    }

    /**
     * Moves the {@code spectator menu} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startSpectatorMenuTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endSpectatorMenuTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code hotbar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startHotbarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endHotbarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code health bar}, {@code armor bar}, {@code food bar}, and {@code air bar}
     * for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startStatusBarsTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endStatusBarsTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code mount health bar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startMountHealthTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endMountHealthTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code mount jump bar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderJumpMeter(Lcom/mojang/blaze3d/vertex/PoseStack;I)V"))
    private void startMountJumpBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderJumpMeter(Lcom/mojang/blaze3d/vertex/PoseStack;I)V", shift = At.Shift.AFTER))
    private void endMountJumpBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code experience bar} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lcom/mojang/blaze3d/vertex/PoseStack;I)V"))
    private void startExperienceBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lcom/mojang/blaze3d/vertex/PoseStack;I)V", shift = At.Shift.AFTER))
    private void endExperienceBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code held item tooltip} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startHeldItemTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endHeldItemTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code spectator tooltip} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startSpectatorTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endSpectatorTooltipTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code overlay message} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", ordinal = 0))
    private void startOverlayMessageTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.HOTBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", ordinal = 0, shift = At.Shift.AFTER))
    private void endOverlayMessageTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code hotbar item} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "renderSlot", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;getModelViewStack()Lcom/mojang/blaze3d/vertex/PoseStack;"))
    private void startHotbarItemTranslate(int x, int y, float partialTick, Player player, ItemStack stack, int i, CallbackInfo ci) {
        Translate.start(RenderSystem.getModelViewStack(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderGuiItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V", shift = At.Shift.AFTER))
    private void endHotbarItemTranslate(int x, int y, float partialTick, Player player, ItemStack stack, int i, CallbackInfo ci) {
        Translate.end(RenderSystem.getModelViewStack(), LayerRegistry.HOTBAR);
    }

    /**
     * Moves the {@code chat} for {@link Layer} key "minecraft:chat".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lcom/mojang/blaze3d/vertex/PoseStack;I)V"))
    private void startChatTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.CHAT);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lcom/mojang/blaze3d/vertex/PoseStack;I)V", shift = At.Shift.AFTER))
    private void endChatTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.CHAT);
    }

    /**
     * Moves the {@code bossbar} for {@link Layer} key "minecraft:bossbar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;render(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startBossBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.BOSSBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/BossHealthOverlay;render(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endBossBarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.BOSSBAR);
    }

    /**
     * Moves the {@code sidebar} for {@link Layer} key "minecraft:sidebar".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/scores/Objective;)V"))
    private void startSidebarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.SIDEBAR);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
    private void endSidebarTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.SIDEBAR);
    }

    /**
     * Moves the {@code effects} for {@link Layer} key "minecraft:effects".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lcom/mojang/blaze3d/vertex/PoseStack;)V"))
    private void startEffectsTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.EFFECTS);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lcom/mojang/blaze3d/vertex/PoseStack;)V", shift = At.Shift.AFTER))
    private void endEffectsTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.EFFECTS);
    }

    /**
     * Moves the {@code players} for {@link Layer} key "minecraft:players".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lcom/mojang/blaze3d/vertex/PoseStack;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V"))
    private void startPlayersTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.PLAYERS);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lcom/mojang/blaze3d/vertex/PoseStack;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
    private void endPlayersTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.PLAYERS);
    }

    /**
     * Moves the {@code titles} for {@link Layer} key "minecraft:titles".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V", ordinal = 3))
    private void startTitlesTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.TITLES);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", ordinal = 3, shift = At.Shift.AFTER))
    private void endTitlesTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.end(poseStack, LayerRegistry.TITLES);
    }

    /**
     * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:other".
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void startRenderHeadTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.OTHER);
    }

    /**
     * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:other".
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void startRenderTailTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
        Translate.start(poseStack, LayerRegistry.OTHER);
    }

    @Mixin(value = Gui.class, priority = 999999999)
    public abstract static class Last {

        /**
         * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:other".
         */
        @Inject(method = "render", at = @At("HEAD"))
        private void endRenderHeadTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
            Translate.end(poseStack, LayerRegistry.OTHER);
        }

        /**
         * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:other".
         */
        @Inject(method = "render", at = @At("TAIL"))
        private void endRenderTailTranslate(PoseStack poseStack, float partialTick, CallbackInfo ci) {
            Translate.end(poseStack, LayerRegistry.OTHER);
        }

    }

}