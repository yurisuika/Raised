package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.properties.Element;
import net.minecraft.client.gui.Gui;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

public abstract class GuiMixin {

    public abstract static class Hotbar {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code hotbar item} for {@link Element.HOTBAR}.
             */
            @ModifyVariable(method = "renderSlot", at = @At("HEAD"), index = 1, argsOnly = true)
            private int moveHotbarItemX(int value) {
                return value + Translate.getX(Element.HOTBAR);
            }

            @ModifyVariable(method = "renderSlot", at = @At("HEAD"), index = 2, argsOnly = true)
            private int moveHotbarItemY(int value) {
                return value + Translate.getY(Element.HOTBAR);
            }

            /**
             * Resizes the hotbar selector to draw the entire texture.
             */
            @ModifyArg(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V", ordinal = 1), index = 6)
            private int replaceHotbarSelectorHeight(int height) {
                return 24;
            }

        }

    }

}