package dev.yurisuika.raised.mixin.minecraft.client.gui.screens;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ChatScreen.class, priority = -999999999)
public abstract class ChatScreenMixin {

    @Shadow
    protected EditBox input;

    /**
     * Moves and resizes the {@code chat input} for {@link Layer} key "minecraft:chat_input".
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void adjustChatInput(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        input.setWidth((((ChatScreen) (Object) this).width - 8) - (2 * Translate.getX(Layers.CHAT_INPUT)));
        input.setPosition(4 + Translate.getX(Layers.CHAT_INPUT), (((ChatScreen) (Object) this).height - 12) + Translate.getY(Layers.CHAT_INPUT));
    }

    /**
     * Moves the {@code chat input background} for {@link Layer} key "minecraft:chat_input".
     */
    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 0), index = 0)
    private int adjustChatInputBackgroundMinX(int minX) {
        return minX + Translate.getX(Layers.CHAT_INPUT);
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 0), index = 1)
    private int adjustChatInputBackgroundMinY(int minY) {
        return minY + Translate.getY(Layers.CHAT_INPUT);
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 0), index = 2)
    private int adjustChatInputBackgroundMaxX(int maxX) {
        return maxX - Translate.getX(Layers.CHAT_INPUT);
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V", ordinal = 0), index = 3)
    private int adjustChatInputBackgroundMaxY(int maxY) {
        return maxY + Translate.getY(Layers.CHAT_INPUT);
    }

}