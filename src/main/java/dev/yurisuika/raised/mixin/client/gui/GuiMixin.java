package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = Gui.class, priority = -1)
public class GuiMixin {

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"), index = 2)
    private int modifyHotbarDistance(int value) {
        return value - Raised.getHud();
    }

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V", ordinal = 1), index = 6)
    private int modifySelectorHeight(int value) {
        return value + 2;
    }

    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSlot(IIFLnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;I)V"), index = 1)
    private int modifyItemDistance(int value) {
        return value - Raised.getHud();
    }

    @ModifyVariable(method = "renderJumpMeter", at = @At(value = "STORE"), ordinal = 3)
    private int modifyJumpBarDistance(int value) {
        return value - Raised.getHud();
    }

    @ModifyArg(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"), index = 2)
    private int modifyExperienceBarDistance(int value) {
        return value - Raised.getHud();
    }

    @ModifyArg(method = "renderExperienceBar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Font;draw(Lcom/mojang/blaze3d/vertex/PoseStack;Ljava/lang/String;FFI)I"), index = 3)
    private float modifyXpTextDistance(float value) {
        return value - (float)Raised.getHud();
    }

    @ModifyVariable(method = "renderSelectedItemName", at = @At(value = "STORE"), ordinal = 2)
    private int modifyHeldItemTooltipDistance(int value) {
        return value - Raised.getHud();
    }

    @ModifyVariable(method = "renderPlayerHealth", at = @At(value = "STORE"), ordinal = 5)
    private int modifyStatusBarsDistance(int value) {
        return value - Raised.getHud();
    }

    @ModifyVariable(method = "renderVehicleHealth", at = @At(value = "STORE"), ordinal = 2)
    private int modifyMountHealthDistance(int value) {
        return value - Raised.getHud();
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 0), index = 1)
    private double modifyActionbar(double value) {
        return value - (double)Raised.getHud();
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;translate(DDD)V", ordinal = 2), index = 1)
    private double modifyChat(double value) {
        return value - (double)Raised.getHud() - (double)Raised.getChat();
    }

}