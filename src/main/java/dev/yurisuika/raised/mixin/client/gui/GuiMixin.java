package dev.yurisuika.raised.mixin.client.gui;

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

public abstract class GuiMixin {

    @Mixin(value = Gui.class, priority = -999999999)
    public abstract static class Pre {

        /**
         * Resizes the hotbar selector to draw the entire texture.
         */
        @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V", ordinal = 1), index = 6)
        private int replaceHotbarSelectorHeight(int height) {
            return 24;
        }

        /**
         * Moves mod elements at the head/tail of the HUD render for {@link Layer} key "minecraft:other".
         */
        @Inject(method = "render", at = @At("HEAD"))
        private void startRenderHeadTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
            Translate.start(guiGraphics.pose(), LayerRegistry.OTHER);
        }

        @Inject(method = "render", at = @At("TAIL"))
        private void startRenderTailTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
            Translate.start(guiGraphics.pose(), LayerRegistry.OTHER);
        }

    }

    @Mixin(value = Gui.class, priority = 999999999)
    public abstract static class Post {

        /**
         * Moves mod elements at the head/tail of the HUD render for {@link Layer} key "minecraft:other".
         */
        @Inject(method = "render", at = @At("HEAD"))
        private void endRenderHeadTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
            Translate.end(guiGraphics.pose());
        }

        @Inject(method = "render", at = @At("TAIL"))
        private void endRenderTailTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
            Translate.end(guiGraphics.pose());
        }

    }

}