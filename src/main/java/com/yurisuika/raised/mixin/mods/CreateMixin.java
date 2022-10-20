package com.yurisuika.raised.mixin.mods;

import com.simibubi.create.content.contraptions.components.structureMovement.interaction.controls.TrainHUD;
import com.simibubi.create.content.curiosities.toolbox.ToolboxHandlerClient;
import com.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

public class CreateMixin {

    @Mixin(ToolboxHandlerClient.class)
    public static class TrainHUDMixin {

        @Redirect(method = "renderOverlay", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private static int modifyScaledHeight(Window instance) {
            return instance.getScaledHeight() - Raised.getDistance();
        }

    }

    @Mixin(TrainHUD.class)
    public static class ToolboxHandlerClientMixin {

        @Redirect(method = "renderOverlay", at = @At(value = "INVOKE", target = "net/minecraft/client/util/Window.getScaledHeight()I"))
        private static int modifyScaledHeight(Window instance) {
            return instance.getScaledHeight() - Raised.getDistance();
        }

    }

}