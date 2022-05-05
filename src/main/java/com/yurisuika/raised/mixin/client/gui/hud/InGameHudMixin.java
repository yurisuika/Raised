package com.yurisuika.raised.mixin.client.gui.hud;

import com.yurisuika.raised.Raised;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = InGameHud.class, priority = -1)
public class InGameHudMixin {

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"), index = 2)
    private int modifyHotbar2(int value) {
        return value - Raised.getDistance();
    }

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 1), index = 6)
    private int modifySelectorHeight(int value) {
        return value + 2;
    }

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;renderHotbarItem(IIFLnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;I)V"), index = 1)
    private int modifyItem2(int value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderMountJumpBar", at = @At(value = "STORE"), ordinal = 3)
    private int modifyJumpBar2(int value) {
        return value - Raised.getDistance();
    }

    @ModifyArg(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"), index = 2)
    private int modifyExperienceBar2(int value) {
        return value - Raised.getDistance();
    }

    @ModifyArg(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/client/util/math/MatrixStack;Ljava/lang/String;FFI)I"), index = 3)
    private float modifyXpText2(float value) {
        return value - (float)Raised.getDistance();
    }

    @ModifyVariable(method = "renderHeldItemTooltip", at = @At(value = "STORE"), ordinal = 2)
    private int modifyHeldItemTooltip2(int value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderStatusBars", at = @At(value = "STORE"), ordinal = 5)
    private int modifyStatusBars2(int value) {
        return value - Raised.getDistance();
    }

    @ModifyVariable(method = "renderMountHealth", at = @At(value = "STORE"), ordinal = 2)
    private int modifyMountHealth2(int value) {
        return value - Raised.getDistance();
    }

}
