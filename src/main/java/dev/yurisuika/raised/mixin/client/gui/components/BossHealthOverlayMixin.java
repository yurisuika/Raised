package dev.yurisuika.raised.mixin.client.gui.components;

import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.BossHealthOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class BossHealthOverlayMixin {

    public abstract static class Bossbar {

        @Mixin(value = BossHealthOverlay.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code bossbar} for {@link Layer} key "minecraft:bossbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;guiWidth()I"))
            private void startBossBarTranslate(GuiGraphics guiGraphics, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.BOSSBAR);
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void endBossBarTranslate(GuiGraphics guiGraphics, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

}