package dev.yurisuika.raised.mixin.minecraft.client.gui;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.option.AdditionalSettings;
import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.Hud;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Hud.class, priority = -999999999)
public abstract class HudMixin {

    /**
     * Replaces the hotbar selection with a new square asset found under the {@code raised} namespace.
     */
    @ModifyArg(method = "extractItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V", ordinal = 1), index = 1)
    private Identifier replaceHotbarSelectorIdentifier(Identifier sprite) {
        if (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.REPLACE || (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.AUTO && Pack.getPack())) {
            return Identifier.fromNamespaceAndPath(Raised.MOD_ID, "hud/hotbar_selection");
        } else {
            return sprite;
        }
    }

    @ModifyArg(method = "extractItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V", ordinal = 1), index = 5)
    private int replaceHotbarSelectorHeight(int height) {
        if (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.REPLACE || (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.AUTO && Pack.getPack())) {
            return 24;
        } else {
            return height;
        }
    }

    /**
     * Draws a vertically mirrored row taken from the top of the asset below the unmodified selector.
     */
    @WrapOperation(method = "extractItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V", ordinal = 1))
    private void patchHotbarSelector(GuiGraphicsExtractor guiGraphics, RenderPipeline pipeline, Identifier sprite, int x, int y, int width, int height, Operation<Void> operation, @Local(ordinal = 0) Player player) {
        operation.call(guiGraphics, pipeline, sprite, x, y, width, height);
        if (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.PATCH  || (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.AUTO && !Pack.getPack())) {
            x = (guiGraphics.guiWidth() / 2) - 92 + player.getInventory().getSelectedSlot() * 20;
            y = guiGraphics.guiHeight();
            ((GuiGraphicsExtractorInvoker) guiGraphics).invokeInnerBlit(RenderPipelines.GUI_TEXTURED, Identifier.withDefaultNamespace("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 1, 1 / 23.0F, 0, -1);
        }
    }

    /**
     * Moves the {@code hotbar}, {@code health bar}, {@code armor bar}, {@code food bar}, {@code air bar},
     * {@code mount health bar}, {@code mount jump bar}, {@code experience bar}, {@code experience level}, and {@code held item tooltip}
     * for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "extractHotbarAndDecorations", at = @At("HEAD"))
    private void startMainHudTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "extractHotbarAndDecorations", at = @At("TAIL"))
    private void endMainHudTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code action bar} for {@link Layer} key "minecraft:action_bar".
     */
    @Inject(method = "extractOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void startActionBarTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.ACTION_BAR);
    }

    @Inject(method = "extractOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
    private void endActionBarTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.ACTION_BAR);
    }

    /**
     * Moves the {@code chat} for {@link Layer} key "minecraft:chat".
     */
    @Inject(method = "extractChat", at = @At("HEAD"))
    private void startChatTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.CHAT);
    }

    @Inject(method = "extractChat", at = @At("TAIL"))
    private void endChatTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.CHAT);
    }

    /**
     * Moves the {@code scoreboard} for {@link Layer} key "minecraft:scoreboard".
     */
    @Inject(method = "extractScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V", at = @At("HEAD"))
    private void startScoreboardTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.SCOREBOARD);
    }

    @Inject(method = "extractScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V", at = @At("TAIL"))
    private void endScoreboardTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.SCOREBOARD);
    }

    /**
     * Moves the {@code effects} for {@link Layer} key "minecraft:effects".
     */
    @Inject(method = "extractEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectInstance;isAmbient()Z"))
    private void startEffectsTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.EFFECTS);
    }

    @Inject(method = "extractEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V", shift = At.Shift.AFTER))
    private void endEffectsTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.EFFECTS);
    }

    /**
     * Moves the {@code player list} for {@link Layer} key "minecraft:player_list".
     */
    @Inject(method = "extractTabList", at = @At("HEAD"))
    private void startPlayerListTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.PLAYER_LIST);
    }

    @Inject(method = "extractTabList", at = @At("TAIL"))
    private void endPlayerListTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.PLAYER_LIST);
    }

    /**
     * Moves the {@code titles} for {@link Layer} key "minecraft:titles".
     */
    @Inject(method = "extractTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void startTitlesTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.TITLES);
    }

    @Inject(method = "extractTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
    private void endTitlesTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.TITLES);
    }

    /**
     * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:unknown".
     */
    @Inject(method = "extractRenderState", at = @At("HEAD"))
    private void startRenderHeadTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.UNKNOWN);
    }

    /**
     * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:unknown".
     */
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void startRenderTailTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.UNKNOWN);
    }

    @Mixin(value = Hud.class, priority = 999999999)
    public abstract static class Last {

        /**
         * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:unknown".
         */
        @Inject(method = "extractRenderState", at = @At("HEAD"))
        private void endRenderHeadTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
            Translate.end(guiGraphics.pose(), Layers.UNKNOWN);
        }

        /**
         * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:unknown".
         */
        @Inject(method = "extractRenderState", at = @At("TAIL"))
        private void endRenderTailTranslate(GuiGraphicsExtractor guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
            Translate.end(guiGraphics.pose(), Layers.UNKNOWN);
        }

    }

}