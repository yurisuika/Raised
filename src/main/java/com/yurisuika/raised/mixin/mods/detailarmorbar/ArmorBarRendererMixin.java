package com.yurisuika.raised.mixin.mods.detailarmorbar;

import com.redlimerl.detailab.render.ArmorBarRenderer;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ArmorBarRenderer.class)
public class ArmorBarRendererMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
    private int modifyAddonsScaledHeight(Window instance) {
        return instance.getScaledHeight() - 2;
    }

}
