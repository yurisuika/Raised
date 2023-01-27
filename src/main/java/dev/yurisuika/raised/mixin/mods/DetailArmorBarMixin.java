package dev.yurisuika.raised.mixin.mods;

import com.redlimerl.detailab.render.ArmorBarRenderer;
import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class DetailArmorBarMixin {

    @Mixin(ArmorBarRenderer.class)
    public static class ArmorBarRendererMixin {

        @Redirect(method = "render", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private int redirectRender(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}