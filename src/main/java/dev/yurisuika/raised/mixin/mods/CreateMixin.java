package dev.yurisuika.raised.mixin.mods;

import com.simibubi.create.content.contraptions.components.structureMovement.interaction.controls.TrainHUD;
import com.simibubi.create.content.curiosities.armor.CopperBacktankArmorLayer;
import com.simibubi.create.content.curiosities.toolbox.ToolboxHandlerClient;
import com.simibubi.create.content.schematics.client.SchematicHotbarSlotOverlay;
import com.simibubi.create.content.schematics.client.ToolSelectionScreen;
import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.*;

public class CreateMixin {

    @Pseudo
    @Mixin(CopperBacktankArmorLayer.class)
    public static class CopperBacktankArmorLayerMixin {

        @Redirect(method = "renderRemainingAirOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private static int modifyRenderRemainingAirOverlay(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(SchematicHotbarSlotOverlay.class)
    public static class SchematicHotbarSlotOverlayMixin {

        @Redirect(method = "renderOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private int modifyRenderOn(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(ToolboxHandlerClient.class)
    public static class ToolboxHandlerClientMixin {

        @Redirect(method = "renderOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private static int modifyRenderOverlay(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(ToolSelectionScreen.class)
    public static class ToolSelectionScreenMixin {

        @Redirect(method = "draw", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private int redirectDraw(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(TrainHUD.class)
    public static class TrainHUDMixin {

        @Redirect(method = "renderOverlay", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private static int modifyRenderOverlay(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

}