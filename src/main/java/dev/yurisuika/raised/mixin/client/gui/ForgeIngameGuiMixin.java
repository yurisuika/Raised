package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.Raised;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = ForgeIngameGui.class, priority = -1)
public class ForgeIngameGuiMixin {

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;right_height:I", opcode = Opcodes.PUTFIELD))
    private void redirectRight(ForgeIngameGui instance, int value) {
        instance.right_height = value + Raised.getHud();
    }

    @Redirect(method = "render", at = @At(value = "FIELD", target = "Lnet/minecraftforge/client/gui/ForgeIngameGui;left_height:I", opcode = Opcodes.PUTFIELD))
    private void redirectLeft(ForgeIngameGui instance, int value) {
        instance.left_height = value + Raised.getHud();
    }

    @ModifyVariable(method = "renderChat", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true, remap = false)
    private int modifyChat(int value) {
        return value - Raised.getChat();
    }

    @ModifyVariable(method = "renderRecordOverlay", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true, remap = false)
    private int modifyActionbar(int value) {
        return value - Raised.getHud();
    }

}