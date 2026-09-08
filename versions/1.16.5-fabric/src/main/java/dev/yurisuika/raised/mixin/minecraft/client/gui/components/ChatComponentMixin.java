package dev.yurisuika.raised.mixin.minecraft.client.gui.components;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = ChatComponent.class, priority = -999999999)
public abstract class ChatComponentMixin {

    /**
     * Moves the {@code chat click} for {@link Layer} key "minecraft:chat".
     */
    @ModifyVariable(method = "handleChatQueueClicked", at = @At("HEAD"), ordinal = 0)
    private double adjustChatClickX(double value) {
        return value - Translate.getX(Layers.CHAT);
    }

    @ModifyVariable(method = "handleChatQueueClicked", at = @At("HEAD"), ordinal = 1)
    private double adjustChatClickY(double value) {
        return value - Translate.getY(Layers.CHAT);
    }

    /**
     * Moves the {@code chat tooltip} for {@link Layer} key "minecraft:chat".
     */
    @ModifyVariable(method = "getClickedComponentStyleAt", at = @At("HEAD"), ordinal = 0)
    private double adjustChatTooltipX(double value) {
        return value - Translate.getX(Layers.CHAT);
    }

    @ModifyVariable(method = "getClickedComponentStyleAt", at = @At("HEAD"), ordinal = 1)
    private double adjustChatTooltipY(double value) {
        return value - Translate.getY(Layers.CHAT);
    }

}