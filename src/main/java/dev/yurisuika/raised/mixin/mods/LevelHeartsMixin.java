package dev.yurisuika.raised.mixin.mods;

import com.firecontroller1847.levelhearts.gui.IngameGui;
import com.mojang.blaze3d.platform.Window;
import dev.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class LevelHeartsMixin {

    @Mixin(IngameGui.class)
    public static class IngameGuiMixin {

        @Redirect(method = "redrawAir", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
        private int redirectRedrawAir(Window instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

        @Redirect(method = "redrawArmor", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
        private int redirectRedrawArmor(Window instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

        @Redirect(method = "redrawHealth", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
        private int redirectRedrawHealth(Window instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

}