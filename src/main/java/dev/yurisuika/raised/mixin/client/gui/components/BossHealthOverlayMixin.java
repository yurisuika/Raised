package dev.yurisuika.raised.mixin.client.gui.components;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.type.Element;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.BossHealthOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class BossHealthOverlayMixin {

    public abstract static class Boss {

        @Mixin(value = BossHealthOverlay.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code bossbar} for {@link Element.BOSSBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
            private void startBossBarTranslate(GuiGraphics guiGraphics, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Element.BOSSBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
            private void endBossBarTranslate(GuiGraphics guiGraphics, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

}