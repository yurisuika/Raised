package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Mixin(value = InGameHud.class, priority = -999999999)
public abstract class InGameHudMixin {

    // HOTBAR SELECTOR
    @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/hud/InGameHud;drawTexture(Lnet/minecraft/client/util/math/MatrixStack;IIIIII)V", ordinal = 1), index = 6)
    private int resizeHotbarSelector(int value) {
        return 24;
    }

    // HOTBAR ITEM
    @ModifyVariable(method = "renderHotbarItem", at = @At("HEAD"), index = 2, argsOnly = true)
    private int moveHotbarItem(int value) {
        return value - getHud();
    }

}