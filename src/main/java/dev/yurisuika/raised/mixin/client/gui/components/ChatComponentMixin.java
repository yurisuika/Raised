package dev.yurisuika.raised.mixin.client.gui.components;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChatComponent.class)
public class ChatComponentMixin {

    @ModifyVariable(method = "handleChatQueueClicked", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private double modifyMouseClick(double value) {
        return value + (double)Raised.getHud() + (double)Raised.getChat();
    }

    @ModifyVariable(method = "getClickedComponentStyleAt", at = @At(value = "HEAD"), ordinal = 1, argsOnly = true)
    private double modifyChatTooltip(double value) {
        return value + (double)Raised.getHud() + (double)Raised.getChat();
    }

}