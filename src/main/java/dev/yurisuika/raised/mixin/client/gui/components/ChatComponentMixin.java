package dev.yurisuika.raised.mixin.client.gui.components;

import dev.yurisuika.raised.util.Layers;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

public abstract class ChatComponentMixin {

    public abstract static class Chat {

        @Mixin(value = ChatComponent.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code chat click} for {@link Layer} key "minecraft:chat".
             */
            @ModifyVariable(method = "handleChatQueueClicked", at = @At("HEAD"), ordinal = 0, argsOnly = true)
            private double adjustChatClickX(double value) {
                return value - Translate.getX(Layers.CHAT.toString());
            }

            @ModifyVariable(method = "handleChatQueueClicked", at = @At("HEAD"), ordinal = 1, argsOnly = true)
            private double adjustChatClickY(double value) {
                return value - Translate.getY(Layers.CHAT.toString());
            }

            /**
             * Moves the {@code chat tooltip} for {@link Layer} key "minecraft:chat".
             */
            @ModifyVariable(method = "screenToChatX", at = @At("HEAD"), ordinal = 0, argsOnly = true)
            private double adjustChatTooltipX(double value) {
                return value - Translate.getX(Layers.CHAT.toString());
            }

            @ModifyVariable(method = "screenToChatY", at = @At("HEAD"), ordinal = 0, argsOnly = true)
            private double adjustChatTooltipY(double value) {
                return value - Translate.getY(Layers.CHAT.toString());
            }

        }

    }

}