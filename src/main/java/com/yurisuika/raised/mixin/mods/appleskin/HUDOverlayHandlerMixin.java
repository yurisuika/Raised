package com.yurisuika.raised.mixin.mods.appleskin;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import squeek.appleskin.client.HUDOverlayHandler;

@Mixin(HUDOverlayHandler.class)
public class HUDOverlayHandlerMixin {

    @Shadow
    private int foodIconsOffset;
    @Shadow public int FOOD_BAR_HEIGHT;

    @Redirect(method = "onPreRender", at = @At(value = "FIELD", target = "squeek/appleskin/client/HUDOverlayHandler.foodIconsOffset:I", opcode = Opcodes.PUTFIELD))
    private void modifyFoodIconsOffset(HUDOverlayHandler instance, int value) {
        foodIconsOffset = FOOD_BAR_HEIGHT + 2;
    }

}