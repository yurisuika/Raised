package dev.yurisuika.raised.mixin.mods;

import com.simibubi.create.content.equipment.armor.RemainingAirOverlay;
import com.simibubi.create.content.trains.TrainHUD;
import com.simibubi.create.content.equipment.toolbox.ToolboxHandlerClient;
import com.simibubi.create.content.schematics.client.SchematicHotbarSlotOverlay;
import com.simibubi.create.content.schematics.client.ToolSelectionScreen;
import dev.yurisuika.raised.Raised;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;

public class CreateMixin {

    @Pseudo
    @Mixin(value = RemainingAirOverlay.class, remap = false)
    public static class RemainingAirOverlayMixin {

        @ModifyVariable(method = "render", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private int modifyRender(int value) {
            return value - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(value = SchematicHotbarSlotOverlay.class, remap = false)
    public static class SchematicHotbarSlotOverlayMixin {

        @Redirect(method = "renderOn", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private int redirectRenderOn(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(value = ToolboxHandlerClient.class, remap = false)
    public static class ToolboxHandlerClientMixin {

        @ModifyVariable(method = "renderOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private static int modifyRenderOverlay(int value) {
            return value - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(value = ToolSelectionScreen.class, remap = false)
    public static class ToolSelectionScreenMixin {

        @Redirect(method = "draw", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/util/Window;getScaledHeight()I"))
        private int redirectDraw(Window instance) {
            return instance.getScaledHeight() - Raised.getHud();
        }

    }

    @Pseudo
    @Mixin(value = TrainHUD.class, remap = false)
    public static class TrainHUDMixin {

        @ModifyVariable(method = "renderOverlay", at = @At("HEAD"), ordinal = 1, argsOnly = true)
        private static int modifyRenderOverlay(int value) {
            return value - Raised.getHud();
        }

    }

}