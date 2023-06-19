package dev.yurisuika.raised.mixin.client.gui.hud;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.gui.hud.SpectatorHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = SpectatorHud.class, priority = -1)
public class SpectatorHudMixin {

    @ModifyArg(method = "renderSpectatorMenu(Lnet/minecraft/client/util/math/MatrixStack;FIILnet/minecraft/client/gui/hud/spectator/SpectatorMenuState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V"), index = 2)
    private int modifySpectatorMenu(int value) {
        return value - Raised.getHud();
    }

    @ModifyArg(method = "renderSpectatorMenu(Lnet/minecraft/client/util/math/MatrixStack;FIILnet/minecraft/client/gui/hud/spectator/SpectatorMenuState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 1), index = 6)
    private int modifySpectatorMenuSelector(int value) {
        return value + 2;
    }

    @ModifyArg(method = "renderSpectatorMenu(Lnet/minecraft/client/util/math/MatrixStack;FIILnet/minecraft/client/gui/hud/spectator/SpectatorMenuState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorCommand(Lnet/minecraft/client/util/math/MatrixStack;IIFFLnet/minecraft/client/gui/hud/spectator/SpectatorMenuCommand;)V"), index = 3)
    private float modifySpectatorCommand(float value) {
        return value - Raised.getHud();
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/font/TextRenderer;drawWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/text/Text;FFI)I"), index = 3)
    private float modifyText(float value) {
        return value - Raised.getHud();
    }

}