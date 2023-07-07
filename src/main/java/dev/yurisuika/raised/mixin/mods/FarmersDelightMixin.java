package dev.yurisuika.raised.mixin.mods;

import com.nhoryzon.mc.farmersdelight.client.gui.ComfortHealthOverlay;
import com.nhoryzon.mc.farmersdelight.client.gui.NourishmentHungerOverlay;
import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class FarmersDelightMixin {

    @Pseudo
    @Mixin(ComfortHealthOverlay.class)
    public static class ComfortHealthOverlayMixin {

        @Redirect(method = "renderComfortOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private int redirectRender(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(NourishmentHungerOverlay.class)
    public static class NourishmentHungerOverlayMixin {

        @Redirect(method = "renderNourishmentOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private int redirectRender(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}