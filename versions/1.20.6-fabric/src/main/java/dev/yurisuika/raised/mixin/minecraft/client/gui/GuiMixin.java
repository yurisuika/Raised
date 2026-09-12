package dev.yurisuika.raised.mixin.minecraft.client.gui;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.option.AdditionalSettings;
import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Gui.class, priority = -999999999)
public abstract class GuiMixin {

    /**
     * Replaces the hotbar selection with a new square asset found under the {@code raised} namespace.
     */
    @ModifyArg(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), index = 0)
    private ResourceLocation replaceHotbarSelectorIdentifier(ResourceLocation sprite) {
        if (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.REPLACE || (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.AUTO && Pack.getPack())) {
            return new ResourceLocation(Raised.MOD_ID, "hud/hotbar_selection");
        } else {
            return sprite;
        }
    }

    @ModifyArg(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), index = 4)
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
    @WrapOperation(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1))
    private void patchHotbarSelector(GuiGraphics guiGraphics, ResourceLocation sprite, int x, int y, int width, int height, Operation<Void> operation, @Local(ordinal = 0) Player player) {
        operation.call(guiGraphics, sprite, x, y, width, height);
        if (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.PATCH  || (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.AUTO && !Pack.getPack())) {
            x = (guiGraphics.guiWidth() / 2) - 92 + player.getInventory().selected * 20;
            y = guiGraphics.guiHeight();
            ((GuiGraphicsInvoker) guiGraphics).invokeInnerBlit(new ResourceLocation("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 0, 1, 1 / 23.0F, 0);
        }
    }

    /**
     * Moves the {@code hotbar}, {@code health bar}, {@code armor bar}, {@code food bar}, {@code air bar},
     * {@code mount health bar}, {@code mount jump bar}, {@code experience bar}, and {@code held item tooltip}
     * for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "renderHotbarAndDecorations", at = @At("HEAD"))
    private void startMainHudTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "renderHotbarAndDecorations", at = @At("TAIL"))
    private void endMainHudTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code experience level} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "renderExperienceLevel", at = @At("HEAD"))
    private void startExperienceLevelTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.HOTBAR);
    }

    @Inject(method = "renderExperienceLevel", at = @At("TAIL"))
    private void endExperienceLevelTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.HOTBAR);
    }

    /**
     * Moves the {@code action bar} for {@link Layer} key "minecraft:action_bar".
     */
    @Inject(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void startActionBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.ACTION_BAR);
    }

    @Inject(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
    private void endActionBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.ACTION_BAR);
    }

    /**
     * Moves the {@code chat} for {@link Layer} key "minecraft:chat".
     */
    @Inject(method = "renderChat", at = @At("HEAD"))
    private void startChatTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.CHAT);
    }

    @Inject(method = "renderChat", at = @At("TAIL"))
    private void endChatTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.CHAT);
    }

    /**
     * Moves the {@code scoreboard} for {@link Layer} key "minecraft:scoreboard".
     */
    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;F)V", at = @At("HEAD"))
    private void startScoreboardTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.SCOREBOARD);
    }

    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;F)V", at = @At("TAIL"))
    private void endScoreboardTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.SCOREBOARD);
    }

    /**
     * Moves the {@code effects} for {@link Layer} key "minecraft:effects".
     */
    @Inject(method = "renderEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getMobEffectTextures()Lnet/minecraft/client/resources/MobEffectTextureManager;"))
    private void startEffectsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.EFFECTS);
    }

    @Inject(method = "renderEffects", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V", shift = At.Shift.AFTER))
    private void endEffectsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.EFFECTS);
    }

    /**
     * Moves the {@code player list} for {@link Layer} key "minecraft:player_list".
     */
    @Inject(method = "renderTabList", at = @At("HEAD"))
    private void startPlayerListTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.PLAYER_LIST);
    }

    @Inject(method = "renderTabList", at = @At("TAIL"))
    private void endPlayerListTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.PLAYER_LIST);
    }

    /**
     * Moves the {@code titles} for {@link Layer} key "minecraft:titles".
     */
    @Inject(method = "renderTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void startTitlesTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.TITLES);
    }

    @Inject(method = "renderTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
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