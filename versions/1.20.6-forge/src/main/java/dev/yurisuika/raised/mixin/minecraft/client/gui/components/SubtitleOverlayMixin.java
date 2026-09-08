package dev.yurisuika.raised.mixin.minecraft.client.gui.components;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.SubtitleOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = SubtitleOverlay.class, priority = -999999999)
public abstract class SubtitleOverlayMixin {

    /**
     * Moves the {@code subtitles} for {@link Layer} key "minecraft:subtitles".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V"))
    private void startSubtitlesTranslate(GuiGraphics guiGraphics, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.start(guiGraphics.pose(), Layers.SUBTITLES);
        }
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", shift = At.Shift.AFTER))
    private void endSubtitlesTranslate(GuiGraphics guiGraphics, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.end(guiGraphics.pose(), Layers.SUBTITLES);
        }
    }

}