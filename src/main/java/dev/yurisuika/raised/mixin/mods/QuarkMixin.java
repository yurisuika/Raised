package dev.yurisuika.raised.mixin.mods;

import com.mojang.blaze3d.platform.Window;
import dev.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.quark.content.client.module.UsageTickerModule;
import vazkii.quark.content.management.module.HotbarChangerModule;

public class QuarkMixin {

    @Mixin(HotbarChangerModule.class)
    public static class HotbarChangerModuleMixin {

        @Redirect(method = "hudPost", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
        private int redirectHudPost(Window instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

    @Mixin(UsageTickerModule.TickerElement.class)
    public static class UsageTickerModuleMixin {

        @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/Window;getGuiScaledHeight()I"))
        private int redirectRender(Window instance) {
            return instance.getGuiScaledHeight() - Raised.getHud();
        }

    }

}