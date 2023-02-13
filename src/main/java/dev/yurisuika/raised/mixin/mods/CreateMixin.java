package dev.yurisuika.raised.mixin.mods;

import com.simibubi.create.content.contraptions.components.structureMovement.interaction.controls.TrainHUD;
import com.simibubi.create.content.curiosities.armor.CopperBacktankArmorLayer;
import com.simibubi.create.content.curiosities.toolbox.ToolboxHandlerClient;
import com.simibubi.create.content.schematics.client.SchematicHotbarSlotOverlay;
import com.simibubi.create.content.schematics.client.ToolSelectionScreen;
import dev.yurisuika.raised.Raised;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

public class CreateMixin {

    @Pseudo
    @Mixin(CopperBacktankArmorLayer.class)
    public static class CopperBacktankArmorLayerMixin {

        @ModifyVariable(method = "renderRemainingAirOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private static int modifyRenderRemainingAirOverlay(int value) {
            return value - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(SchematicHotbarSlotOverlay.class)
    public static class SchematicHotbarSlotOverlayMixin {

        @ModifyVariable(method = "renderOn", at = @At("STORE"), ordinal = 2)
        private int modifyRenderOn(int value) {
            return value - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(ToolboxHandlerClient.class)
    public static class ToolboxHandlerClientMixin {

        @ModifyVariable(method = "renderOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private static int modifyRenderOverlay(int value) {
            return value - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(ToolSelectionScreen.class)
    public static class ToolSelectionScreenMixin {

        @ModifyVariable(method = "draw", at = @At("STORE"), ordinal = 1)
        private int modifyDraw(int value) {
            return value - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(TrainHUD.class)
    public static class TrainHUDMixin {

        @ModifyVariable(method = "renderOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private static int modifyRenderOverlay(int value) {
            return value - Raised.getHud();
        }

    }

}