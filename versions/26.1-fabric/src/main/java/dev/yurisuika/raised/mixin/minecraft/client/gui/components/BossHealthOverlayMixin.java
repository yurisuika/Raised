package dev.yurisuika.raised.mixin.minecraft.client.gui.components;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.BossHealthOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BossHealthOverlay.class, priority = -999999999)
public abstract class BossHealthOverlayMixin {

    /**
     * Moves the {@code bossbar} for {@link Layer} key "minecraft:bossbar".
     */
    @Inject(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void startBossBarTranslate(GuiGraphicsExtractor guiGraphicsExtractor, CallbackInfo ci) {
        Translate.start(guiGraphicsExtractor.pose(), LayerRegistry.BOSSBAR);
    }

    @Inject(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
    private void endBossBarTranslate(GuiGraphicsExtractor guiGraphicsExtractor, CallbackInfo ci) {
        Translate.end(guiGraphicsExtractor.pose(), LayerRegistry.BOSSBAR);
    }

}