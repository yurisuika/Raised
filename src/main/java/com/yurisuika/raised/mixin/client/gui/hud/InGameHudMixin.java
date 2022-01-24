package com.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = InGameHud.class, priority = 9001)
public class InGameHudMixin {

    @ModifyConstant(method = "renderExperienceBar", constant = @Constant(intValue = 31))
    private int xpTextDistance(int value) {
        return value + 1;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 4))
    private int selectorHeight(int value) {
        return value + 2;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 0))
    private int hotbarDistance(int value) {
        return value + 1;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 2))
    private int selectorDistance(int value) {
        return value + 1;
    }

    @ModifyConstant(method = "renderMountJumpBar", constant = @Constant(intValue = 3))
    private int jumpBarDistance(int value) {
        return value - 1;
    }

    @ModifyConstant(method = "renderExperienceBar", constant = @Constant(intValue = 3))
    private int experienceBarDistance(int value) {
        return value - 1;
    }

    @ModifyConstant(method = "renderHeldItemTooltip", constant = @Constant(intValue = 59))
    private int heldItemTooltipDistance(int value) {
        return value + 1;
    }

    @ModifyConstant(method = "renderStatusBars", constant = @Constant(intValue = 39))
    private int statusBarsDistance(int value) {
        return value + 1;
    }

    @ModifyConstant(method = "renderMountHealth", constant = @Constant(intValue = 39))
    private int mountHealthDistance(int value) {
        return value + 1;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 23, ordinal = 1))
    private int emptyOffhandDistance(int value) {
        return value + 1;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 23, ordinal = 0))
    private int heldOffhandDistance(int value) {
        return value + 1;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 3, ordinal = 1))
    private int offhandItemDistance(int value) {
        return value + 1;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 3, ordinal = 0))
    private int itemDistance(int value) {
        return value + 1;
    }

}
