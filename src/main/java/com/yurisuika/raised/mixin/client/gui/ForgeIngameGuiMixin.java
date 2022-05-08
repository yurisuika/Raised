package com.yurisuika.raised.mixin.client.gui;

import com.yurisuika.raised.Raised;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.gui.ForgeIngameGui;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
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

//    @ModifyVariable(method = "renderChat", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
//    private int modifyChat(int value) {
//        return value - Raised.getDistance();
//    }
//
//    @ModifyVariable(method = "renderRecordOverlay", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
//    private int modifyActionbar(int value) {
//        return value - Raised.getDistance();
//    }

//    @Redirect(method = "renderChat", at = @At(value = "INVOKE", target = "net/minecraftforge/client/event/RenderGameOverlayEvent$Chat.getPosY()I"))
//    private int redirectChat(RenderGameOverlayEvent.Chat instance, int value) {
//        return value - Raised.getDistance();
//    }

//    @Redirect(method = "renderRecordOverlay", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate.p_85839()I"))
//    private int redirectActionbar(RenderGameOverlayEvent.Chat instance, int value) {
//        return value - Raised.getDistance();
//    }

}
