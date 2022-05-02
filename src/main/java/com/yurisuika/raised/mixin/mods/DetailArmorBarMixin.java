package com.yurisuika.raised.mixin.mods;

import com.redlimerl.detailab.render.ArmorBarRenderer;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ArmorBarRenderer.class)
public class DetailArmorBarMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
    private int modifyScaledHeight(Window instance) {
        return instance.getScaledHeight() - 2;
    }

}
