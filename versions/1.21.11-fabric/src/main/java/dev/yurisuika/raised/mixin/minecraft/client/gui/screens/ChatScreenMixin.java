package dev.yurisuika.raised.mixin.minecraft.client.gui.screens;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.input.MouseButtonEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ChatScreen.class, priority = -999999999)
public abstract class ChatScreenMixin {

    /**
     * Moves the {@code chat} for {@link Layer} key "minecraft:chat".
     */
    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/gui/Font;IIIZZ)V"))
    private void startChatTranslate(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), LayerRegistry.CHAT);
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/gui/Font;IIIZZ)V", shift = At.Shift.AFTER))
    private void endChatTranslate(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), LayerRegistry.CHAT);
    }

    /**
     * Moves the {@code chat click} for {@link Layer} key "minecraft:chat".
     */
    @ModifyVariable(method = "mouseClicked", at = @At("HEAD"), ordinal = 0, argsOnly = true)
    private MouseButtonEvent adjustChatMouseClick(MouseButtonEvent event) {
        return new MouseButtonEvent(event.x() - Translate.getX(LayerRegistry.CHAT), event.y() - Translate.getY(LayerRegistry.CHAT), event.buttonInfo());
    }

}