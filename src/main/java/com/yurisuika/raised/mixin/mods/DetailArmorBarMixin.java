package com.yurisuika.raised.mixin.mods;

import com.mojang.blaze3d.platform.Window;
import com.redlimerl.detailab.render.ArmorBarRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ArmorBarRenderer.class)
public class DetailArmorBarMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
    private int modifyAddonsScaledHeight(Window instance) {
        return instance.getGuiScaledHeight() - 2;
    }

}
