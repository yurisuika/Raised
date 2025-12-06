package dev.yurisuika.raised.mixin.minecraft.client.gui;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.Resource;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = Gui.class, priority = -999999999)
public abstract class GuiMixin {

    /**
     * Replaces the hotbar selector with a new square asset found under the {@code raised} namespace.
     */
    @ModifyArg(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), index = 0)
    private ResourceLocation replaceHotbarSelectorIdentifier(ResourceLocation sprite) {
        if (Configure.getTexture() == Resource.Texture.REPLACE || (Configure.getTexture() == Resource.Texture.AUTO && Pack.getPack())) {
            return ResourceLocation.fromNamespaceAndPath("raised", "hud/hotbar_selection");
        } else {
            return sprite;
        }
    }

    @ModifyArg(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), index = 4)
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
    @Inject(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void patchHotbarSelector(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Player player) {
        if (Configure.getTexture() == Resource.Texture.PATCH  || (Configure.getTexture() == Resource.Texture.AUTO && !Pack.getPack())) {
            int x = (guiGraphics.guiWidth() / 2) - 92 + player.getInventory().selected * 20;
            int y = guiGraphics.guiHeight();
            ((GuiGraphicsInvoker) guiGraphics).invokeInnerBlit(ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 0, 1, 1 / 23.0F, 0);
        }
    }

    /**
     * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:other".
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void startRenderHeadTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.OTHER);
    }

    /**
     * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:other".
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void startRenderTailTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.OTHER);
    }

    @Mixin(value = Gui.class, priority = 999999999)
    public abstract static class Last {

        /**
         * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:other".
         */
        @Inject(method = "render", at = @At("HEAD"))
        private void endRenderHeadTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
            Translate.end(guiGraphics.pose(), LayerRegistry.OTHER);
        }

        /**
         * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:other".
         */
        @Inject(method = "render", at = @At("TAIL"))
        private void endRenderTailTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
            Translate.end(guiGraphics.pose(), LayerRegistry.OTHER);
        }

    }

}