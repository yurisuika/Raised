package dev.yurisuika.raised.mixin.client.gui.components;

import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.type.Element;
import dev.yurisuika.raised.util.type.Sync;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

public abstract class ChatComponentMixin {

    public abstract static class Chat {

        @Mixin(value = ChatComponent.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code chat click} for {@link Element.CHAT}.
             */
            @ModifyVariable(method = "handleChatQueueClicked", at = @At("HEAD"), ordinal = 0, argsOnly = true)
            private double adjustChatClickX(double value) {
                return value - (Option.getX(Option.getSync(Element.CHAT) != Sync.NONE ? Element.byId(Option.getSync(Element.CHAT).getId()) : Element.CHAT) * Option.getPosition(Element.CHAT).getX());
            }

            @ModifyVariable(method = "handleChatQueueClicked", at = @At("HEAD"), ordinal = 1, argsOnly = true)
            private double adjustChatClickY(double value) {
                return value - (Option.getY(Option.getSync(Element.CHAT) != Sync.NONE ? Element.byId(Option.getSync(Element.CHAT).getId()) : Element.CHAT) * Option.getPosition(Element.CHAT).getY());
            }

            /**
             * Moves the {@code chat tooltip} for {@link Element.CHAT}.
             */
            @ModifyVariable(method = "screenToChatX", at = @At("HEAD"), ordinal = 0, argsOnly = true)
            private double adjustChatTooltipX(double value) {
                return value - (Option.getX(Option.getSync(Element.CHAT) != Sync.NONE ? Element.byId(Option.getSync(Element.CHAT).getId()) : Element.CHAT) * Option.getPosition(Element.CHAT).getX());
            }

            @ModifyVariable(method = "screenToChatY", at = @At("HEAD"), ordinal = 0, argsOnly = true)
            private double adjustChatTooltipY(double value) {
                return value - (Option.getY(Option.getSync(Element.CHAT) != Sync.NONE ? Element.byId(Option.getSync(Element.CHAT).getId()) : Element.CHAT) * Option.getPosition(Element.CHAT).getY());
            }

        }

    }

}