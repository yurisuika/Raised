package com.yurisuika.raised.mixin.mods;

import com.mojang.blaze3d.platform.Window;
import com.redlimerl.detailab.render.ArmorBarRenderer;
import com.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class DetailArmorBarMixin {

    @Mixin(ArmorBarRenderer.class)
    public static class ArmorBarRendererMixin {

        @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
        private int modifyRender(Window instance) {
            return instance.getGuiScaledHeight() - Raised.getDistance();
        }

    }

}
