package dev.yurisuika.raisedxbotania.mixin.mods;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import vazkii.botania.client.gui.HUDHandler;
import vazkii.botania.common.item.equipment.bauble.ItemFlightTiara;

public class BotaniaMixin {

    @Pseudo
    @Mixin(HUDHandler.class)
    public static class HUDHandlerMixin {

        @Redirect(method = "renderManaInvBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private static int redirectRenderManaInvBar(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(ItemFlightTiara.ClientLogic.class)
    public static class ItemFlightTiaraMixin {

        @Redirect(method = "renderHUD", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private static int redirectRenderHUD(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}