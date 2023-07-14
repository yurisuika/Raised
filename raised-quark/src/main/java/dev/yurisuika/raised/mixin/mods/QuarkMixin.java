package dev.yurisuika.raised.mixin.mods;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.quark.content.client.module.UsageTickerModule;
import vazkii.quark.content.management.module.HotbarChangerModule;

public class QuarkMixin {

    @Pseudo
    @Mixin(HotbarChangerModule.class)
    public static class HotbarChangerModuleMixin {

        @Redirect(method = "hudPost", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private int redirectHudPost(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(UsageTickerModule.TickerElement.class)
    public static class UsageTickerModuleMixin {

        @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private int redirectRender(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}