package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.resources.Texture;
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

public abstract class GuiMixin {

    public abstract static class Hotbar {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Replaces the hotbar selector with a new square asset found under the {@code raised} namespace.
             */
            @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), index = 0)
            private ResourceLocation replaceHotbarSelectorIdentifier(ResourceLocation texture) {
                if (Option.getTexture() == Texture.REPLACE || (Option.getTexture() == Texture.AUTO && Pack.getPack())) {
                    return ResourceLocation.tryParse("raised:hud/hotbar_selection");
                } else {
                    return texture;
                }
            }

            @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), index = 4)
            private int replaceHotbarSelectorHeight(int height) {
                if (Option.getTexture() == Texture.REPLACE || (Option.getTexture() == Texture.AUTO && Pack.getPack())) {
                    return 24;
                } else {
                    return height;
                }
            }

            /**
             * Draws a vertically mirrored row taken from the top of the asset below the unmodified selector.
             */
            @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"), locals = LocalCapture.CAPTURE_FAILHARD)
            private void patchHotbarSelector(float partialTick, GuiGraphics guiGraphics, CallbackInfo ci, Player player) {
                if (Option.getTexture() == Texture.PATCH  || (Option.getTexture() == Texture.AUTO && !Pack.getPack())) {
                    int x = (guiGraphics.guiWidth() / 2) - 92 + player.getInventory().selected * 20;
                    int y = guiGraphics.guiHeight();
                    ((GuiGraphicsInvoker)guiGraphics).invokeInnerBlit(ResourceLocation.tryParse("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 0, 1, 1 / 23.0F, 0);
                }
            }

        }

    }

}