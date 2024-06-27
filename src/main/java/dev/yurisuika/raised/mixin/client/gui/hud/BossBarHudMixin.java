package dev.yurisuika.raised.mixin.client.gui.hud;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.type.Element;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.BossBarHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class BossBarHudMixin {

    public abstract static class Boss {

        @Mixin(value = BossBarHud.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code bossbar} for {@link Element.BOSSBAR}.
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;push(Ljava/lang/String;)V"))
            private void startBossBarTranslate(DrawContext context, CallbackInfo ci) {
                Translate.start(context.getMatrices(), Element.BOSSBAR);
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiler/Profiler;pop()V", shift = At.Shift.AFTER))
            private void endBossBarTranslate(DrawContext context, CallbackInfo ci) {
                Translate.end(context.getMatrices());
            }

        }

    }

}