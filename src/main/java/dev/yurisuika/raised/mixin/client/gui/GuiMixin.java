package dev.yurisuika.raised.mixin.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.properties.Element;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class GuiMixin {

    public abstract static class Hotbar {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code hotbar item} for {@link Element.HOTBAR}.
             */
            @Inject(method = "renderSlot", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/systems/RenderSystem;getModelViewStack()Lcom/mojang/blaze3d/vertex/PoseStack;"))
            private void startHotbarItemTranslate(int x, int y, float partialTick, Player player, ItemStack stack, int i, CallbackInfo ci) {
                Translate.start(RenderSystem.getModelViewStack(), Element.HOTBAR);
            }

            @Inject(method = "renderSlot", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;renderGuiItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;II)V", shift = At.Shift.AFTER))
            private void endHotbarItemTranslate(int x, int y, float partialTick, Player player, ItemStack stack, int i, CallbackInfo ci) {
                Translate.end(RenderSystem.getModelViewStack());
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