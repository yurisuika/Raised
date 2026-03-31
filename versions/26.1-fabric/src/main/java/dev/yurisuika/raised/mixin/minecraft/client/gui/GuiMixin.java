package dev.yurisuika.raised.mixin.minecraft.client.gui;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
//? if >=26.1 {
import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
//?} else {
/*import net.minecraft.client.gui.GuiGraphics;
*///?}
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.Resource;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Gui.class, priority = -999999999)
public abstract class GuiMixin {

    /**
     * Replaces the hotbar selector with a new square asset found under the {@code raised} namespace.
     */
    @ModifyArg(method = "extractItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V", ordinal = 1), index = 1)
    private Identifier replaceHotbarSelectorIdentifier(Identifier sprite) {
        if (Configure.getTexture() == Resource.Texture.REPLACE || (Configure.getTexture() == Resource.Texture.AUTO && Pack.getPack())) {
            return Identifier.fromNamespaceAndPath(Raised.MOD_ID, "hud/hotbar_selection");
        } else {
            return sprite;
        }
    }

    @ModifyArg(method = "extractItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V", ordinal = 1), index = 5)
    private int replaceHotbarSelectorHeight(int height) {

        if (Configure.getTexture() == Resource.Texture.REPLACE || (Configure.getTexture() == Resource.Texture.AUTO && Pack.getPack())) {
            return 24;
        } else {
            return height;
        }
    }

    /**
     * Draws a vertically mirrored row taken from the top of the asset below the unmodified selector.
     */
    @WrapOperation(method = "extractItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIII)V", ordinal = 1))
    private void patchHotbarSelector(GuiGraphicsExtractor graphics, RenderPipeline pipeline, Identifier sprite, int x, int y, int width, int height, Operation<Void> operation, @Local(ordinal = 0) Player player) {
        operation.call(graphics, pipeline, sprite, x, y, width, height);
        if (Configure.getTexture() == Resource.Texture.PATCH  || (Configure.getTexture() == Resource.Texture.AUTO && !Pack.getPack())) {
            x = (graphics.guiWidth() / 2) - 92 + player.getInventory().getSelectedSlot() * 20;
            y = graphics.guiHeight();
            ((GuiGraphicsExtractorInvoker) graphics).invokeInnerBlit(RenderPipelines.GUI_TEXTURED, Identifier.withDefaultNamespace("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 1, 1 / 23.0F, 0, -1);
        }
    }

    @Inject(method = "extractHotbarAndDecorations", at = @At("HEAD"))
    private void startMainHudTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(graphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "extractHotbarAndDecorations", at = @At("TAIL"))
    private void endMainHudTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(graphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "extractOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void startOverlayMessageTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(graphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "extractOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
    private void endOverlayMessageTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(graphics.pose(), LayerRegistry.HOTBAR);
    }

    @Inject(method = "extractChat", at = @At("HEAD"))
    private void startChatTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(graphics.pose(), LayerRegistry.CHAT);
    }

    @Inject(method = "extractChat", at = @At("TAIL"))
    private void endChatTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(graphics.pose(), LayerRegistry.CHAT);
    }

    @Inject(method = "extractScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V", at = @At("HEAD"))
    private void startSidebarTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(graphics.pose(), LayerRegistry.SIDEBAR);
    }

    @Inject(method = "extractScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V", at = @At("TAIL"))
    private void endSidebarTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(graphics.pose(), LayerRegistry.SIDEBAR);
    }

    @Inject(method = "extractEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectInstance;isAmbient()Z"))
    private void startEffectsTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(graphics.pose(), LayerRegistry.EFFECTS);
    }

    @Inject(method = "extractEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/Identifier;IIIII)V", shift = At.Shift.AFTER))
    private void endEffectsTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(graphics.pose(), LayerRegistry.EFFECTS);
    }

    @Inject(method = "extractTabList", at = @At("HEAD"))
    private void startPlayersTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(graphics.pose(), LayerRegistry.PLAYERS);
    }

    @Inject(method = "extractTabList", at = @At("TAIL"))
    private void endPlayersTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(graphics.pose(), LayerRegistry.PLAYERS);
    }
    @Inject(method = "extractTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void startTitlesTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(graphics.pose(), LayerRegistry.TITLES);
    }

    @Inject(method = "extractTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
    private void endTitlesTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.end(graphics.pose(), LayerRegistry.TITLES);
    }

    @Inject(method = "extractRenderState", at = @At("HEAD"))
    private void startRenderHeadTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(graphics.pose(), LayerRegistry.OTHER);
    }

    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void startRenderTailTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(graphics.pose(), LayerRegistry.OTHER);
    }

    @Mixin(value = Gui.class, priority = 999999999)
    public abstract static class Last {

        @Inject(method = "extractRenderState", at = @At("HEAD"))
        private void endRenderHeadTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
            Translate.end(graphics.pose(), LayerRegistry.OTHER);
        }

        @Inject(method = "extractRenderState", at = @At("TAIL"))
        private void endRenderTailTranslate(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker, CallbackInfo ci) {
            Translate.end(graphics.pose(), LayerRegistry.OTHER);
        }
    }
}