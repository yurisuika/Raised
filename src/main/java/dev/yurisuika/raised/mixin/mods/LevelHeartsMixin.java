package dev.yurisuika.raised.mixin.mods;

import com.firecontroller1847.levelhearts.gui.IngameGui;
import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class LevelHeartsMixin {

    @Mixin(value = IngameGui.class, remap = false)
    public static class IngameGuiMixin {

        @Redirect(method = "redrawAir", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private int redirectRedrawAir(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

        @Redirect(method = "redrawArmor", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private int redirectRedrawArmor(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

        @Redirect(method = "redrawHealth", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private int redirectRedrawHealth(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}