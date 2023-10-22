package dev.yurisuika.raised.mixin.client.gui.hud;

import net.minecraft.client.gui.hud.ChatHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

@Mixin(value = ChatHud.class, priority = 999999999)
public abstract class ChatHudMixin {

    // CHAT CLICK
    @ModifyVariable(method = "mouseClicked", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private double moveChatClick(double value) {
        return value + (getSync() ? getHud() : getChat());
    }

    // CHAT TOOLTIP
    @ModifyVariable(method = "toChatLineY", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    private double moveChatTooltip(double value) {
        return value + (getSync() ? getHud() : getChat());
    }

}