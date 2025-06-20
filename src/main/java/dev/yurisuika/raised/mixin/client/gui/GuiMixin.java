package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;
import dev.yurisuika.raised.util.config.options.Resource;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

public abstract class GuiMixin {

    public abstract static class Hotbar {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Replaces the hotbar selector with a new square asset found under the {@code raised} namespace.
             */
            @ModifyArg(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), index = 1)
            private ResourceLocation replaceHotbarSelectorIdentifier(ResourceLocation sprite) {
                if (Option.getTexture() == Resource.Texture.REPLACE || (Option.getTexture() == Resource.Texture.AUTO && Pack.getPack())) {
                    return ResourceLocation.fromNamespaceAndPath("raised", "hud/hotbar_selection");
                } else {
                    return sprite;
                }
            }

            @ModifyArg(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), index = 5)
            private int replaceHotbarSelectorHeight(int height) {
                if (Option.getTexture() == Resource.Texture.REPLACE || (Option.getTexture() == Resource.Texture.AUTO && Pack.getPack())) {
                    return 24;
                } else {
                    return height;
                }
            }

            /**
             * Draws a vertically mirrored row taken from the top of the asset below the unmodified selector.
             */
            @Inject(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), locals = LocalCapture.CAPTURE_FAILHARD)
            private void patchHotbarSelector(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Player player) {
                if (Option.getTexture() == Resource.Texture.PATCH  || (Option.getTexture() == Resource.Texture.AUTO && !Pack.getPack())) {
                    int x = (guiGraphics.guiWidth() / 2) - 92 + player.getInventory().getSelectedSlot() * 20;
                    int y = guiGraphics.guiHeight();
                    ((GuiGraphicsInvoker) guiGraphics).invokeInnerBlit(RenderPipelines.GUI_TEXTURED, ResourceLocation.tryParse("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 1, 1 / 23.0F, 0, -1);
                }
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
            private void startRenderHeadTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.OTHER);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void startRenderTailTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.OTHER);
            }

        }

        @Mixin(value = Gui.class, priority = 999999999)
        public abstract static class Post {

            /**
             * Moves mod elements at the head/tail of the HUD render for {@link Layer} key "minecraft:other".
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void endRenderHeadTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void endRenderTailTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

}