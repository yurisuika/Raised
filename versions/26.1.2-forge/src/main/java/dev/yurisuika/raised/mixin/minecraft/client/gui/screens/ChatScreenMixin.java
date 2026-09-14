package dev.yurisuika.raised.mixin.minecraft.client.gui.screens;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.client.input.MouseButtonEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = ChatScreen.class, priority = -999999999, remap = false)
public abstract class ChatScreenMixin {

    @Shadow
    protected EditBox input;

    /**
     * Moves the {@code chat} for {@link Layer} key "minecraft:chat".
     */
    @Inject(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/gui/Font;IIILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;Z)V"))
    private void startChatTranslate(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.CHAT);
    }

    @Inject(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;extractRenderState(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/gui/Font;IIILnet/minecraft/client/gui/components/ChatComponent$DisplayMode;Z)V", shift = At.Shift.AFTER))
    private void endChatTranslate(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        Translate.end(guiGraphics.pose(), Layers.CHAT);
    }

    /**
     * Moves and resizes the {@code chat input} for {@link Layer} key "minecraft:chat_input".
     */
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void adjustChatInput(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        input.setWidth((((ChatScreen) (Object) this).width - 8) - (2 * Translate.getX(Layers.CHAT_INPUT)));
        input.setPosition(4 + Translate.getX(Layers.CHAT_INPUT), (((ChatScreen) (Object) this).height - 12) + Translate.getY(Layers.CHAT_INPUT));
    }

    /**
     * Moves the {@code chat input background} for {@link Layer} key "minecraft:chat_input".
     */
    @ModifyArg(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V", ordinal = 0), index = 0)
    private int adjustChatInputBackgroundMinX(int minX) {
        return minX + Translate.getX(Layers.CHAT_INPUT);
    }

    @ModifyArg(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V", ordinal = 0), index = 1)
    private int adjustChatInputBackgroundMinY(int minY) {
        return minY + Translate.getY(Layers.CHAT_INPUT);
    }

    @ModifyArg(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V", ordinal = 0), index = 2)
    private int adjustChatInputBackgroundMaxX(int maxX) {
        return maxX - Translate.getX(Layers.CHAT_INPUT);
    }

    @ModifyArg(method = "extractRenderState", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;fill(IIIII)V", ordinal = 0), index = 3)
    private int adjustChatInputBackgroundMaxY(int maxY) {
        return maxY + Translate.getY(Layers.CHAT_INPUT);
    }

    /**
     * Moves the {@code chat click} for {@link Layer} key "minecraft:chat".
     */
    @Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/MouseButtonEvent;button()I"))
    private int adjustChatMouseClickButton(MouseButtonEvent instance) {
        return new MouseButtonEvent(instance.x() - Translate.getX(Layers.CHAT_INPUT), instance.y() - Translate.getY(Layers.CHAT_INPUT), instance.buttonInfo()).button();
    }

    @Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/MouseButtonEvent;x()D"))
    private double adjustChatMouseClickX(MouseButtonEvent instance) {
        return instance.x() - Translate.getX(Layers.CHAT);
    }

    @Redirect(method = "mouseClicked", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/input/MouseButtonEvent;y()D"))
    private double adjustChatMouseClickY(MouseButtonEvent instance) {
        return instance.y() - Translate.getY(Layers.CHAT);
    }

}