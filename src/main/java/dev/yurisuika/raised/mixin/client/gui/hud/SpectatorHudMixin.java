package dev.yurisuika.raised.mixin.client.gui.hud;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.gui.hud.SpectatorHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = SpectatorHud.class, priority = -1)
public class SpectatorHudMixin {

    @ModifyArg(method = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;FIILnet/minecraft/client/gui/hud/spectator/SpectatorMenuState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"), index = 2)
    private int modifySpectatorMenu(int value) {
        return value - Raised.getHud();
    }

    @ModifyArg(method = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;FIILnet/minecraft/client/gui/hud/spectator/SpectatorMenuState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V", ordinal = 1), index = 6)
    private int modifySpectatorMenuSelector(int value) {
        return value + 2;
    }

    @ModifyArg(method = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorMenu(Lnet/minecraft/client/gui/DrawContext;FIILnet/minecraft/client/gui/hud/spectator/SpectatorMenuState;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/SpectatorHud;renderSpectatorCommand(Lnet/minecraft/client/gui/DrawContext;IIFFLnet/minecraft/client/gui/hud/spectator/SpectatorMenuCommand;)V"), index = 3)
    private float modifySpectatorCommand(float value) {
        return value - Raised.getHud();
    }

    @ModifyVariable(method = "render", at = @At(value = "STORE"), ordinal = 2)
    private int modifyText(int value) {
        return value - Raised.getHud();
    }

}