package com.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(InGameHud.class)
public class MixinInGameHud {

    @ModifyConstant(method = "renderExperienceBar", constant = @Constant(intValue = 31))
    private int xpTextDistance(int value) {
        return 32;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 4))
    private int selectorHeight(int value) {
        return 24;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 0))
    private int hotbarDistance(int value) {
        return 23;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 22, ordinal = 2))
    private int selectorDistance(int value) {
        return 23;
    }

    @ModifyConstant(method = "renderMountJumpBar", constant = @Constant(intValue = 3))
    private int jumpBarDistance(int value) {
        return 2;
    }

    @ModifyConstant(method = "renderExperienceBar", constant = @Constant(intValue = 3))
    private int experienceBarDistance(int value) {
        return 2;
    }

    @ModifyConstant(method = "renderHeldItemTooltip", constant = @Constant(intValue = 59))
    private int heldItemTooltipDistance(int value) {
        return 60;
    }

    @ModifyConstant(method = "renderStatusBars", constant = @Constant(intValue = 39))
    private int statusBarsDistance(int value) {
        return 40;
    }

    @ModifyConstant(method = "renderMountHealth", constant = @Constant(intValue = 39))
    private int mountHealthDistance(int value) {
        return 40;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 23, ordinal = 1))
    private int emptyOffhandDistance(int value) {
        return 24;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 23, ordinal = 0))
    private int heldOffhandDistance(int value) {
        return 24;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 3, ordinal = 1))
    private int offhandItemDistance(int value) {
        return 4;
    }

    @ModifyConstant(method = "renderHotbar", constant = @Constant(intValue = 3, ordinal = 0))
    private int itemDistance(int value) {
        return 4;
    }

}
