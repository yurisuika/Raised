package com.yurisuika.raised.mixin.mods;

import com.simibubi.create.content.contraptions.components.structureMovement.interaction.controls.TrainHUD;
import com.simibubi.create.content.curiosities.toolbox.ToolboxHandlerClient;
import com.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

public class CreateMixin {

    @Mixin(value = ToolboxHandlerClient.class, remap = false)
    public static class TrainHUDMixin {

        @ModifyVariable(method = "renderOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private static int modifyScaledHeight(int value) {
            return value - Raised.getDistance();
        }

    }

    @Mixin(value = TrainHUD.class, remap = false)
    public static class ToolboxHandlerClientMixin {

        @ModifyVariable(method = "renderOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private static int modifyScaledHeight(int value) {
            return value - Raised.getDistance();
        }

    }

}