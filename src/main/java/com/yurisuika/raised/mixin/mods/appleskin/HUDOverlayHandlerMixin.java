package com.yurisuika.raised.mixin.mods.appleskin;

import net.minecraftforge.client.gui.ForgeIngameGui;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import squeek.appleskin.client.HUDOverlayHandler;

@Mixin(value = HUDOverlayHandler.class, remap = false)
public class HUDOverlayHandlerMixin {

    @Redirect(method = "renderExhaustion", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;right_height:I", opcode = Opcodes.GETFIELD))
    private static int inj(ForgeIngameGui instance, ForgeIngameGui gui) {
        return gui.right_height + 2;
    }

}