package com.yurisuika.raised.mixin.client.gui;

import com.yurisuika.raised.Raised;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ForgeIngameGui.class)
public class ForgeIngameGuiMixin {

    @Shadow
    public int left_height = 41;
    @Shadow
    public int right_height = 41;

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;right_height:I", opcode = Opcodes.PUTFIELD))
    private void redirectRight(ForgeIngameGui instance, int value) {
        instance.right_height = value + Raised.getDistance();
    }

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;left_height:I", opcode = Opcodes.PUTFIELD))
    private void redirectLeft(ForgeIngameGui instance, int value) {
        instance.left_height = value + Raised.getDistance();
    }

}
