package dev.yurisuika.raised.mixin.client.gui;

import dev.yurisuika.raised.util.Layers;
import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.Translate;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;
import dev.yurisuika.raised.util.config.options.Resource;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public abstract class GuiMixin {

    public abstract static class Hotbar {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code spectator menu} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startSpectatorMenuTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.HOTBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderHotbar(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endSpectatorMenuTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code hotbar} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startHotbarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.HOTBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderHotbar(FLnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endHotbarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code health bar}, {@code armor bar}, {@code food bar}, and {@code air bar}
             * for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startStatusBarsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.HOTBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderPlayerHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endStatusBarsTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code mount health bar} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startMountHealthTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.HOTBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderVehicleHealth(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endMountHealthTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code mount jump bar} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderJumpMeter(Lnet/minecraft/world/entity/PlayerRideableJumping;Lnet/minecraft/client/gui/GuiGraphics;I)V"))
            private void startMountJumpBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.HOTBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderJumpMeter(Lnet/minecraft/world/entity/PlayerRideableJumping;Lnet/minecraft/client/gui/GuiGraphics;I)V", shift = At.Shift.AFTER))
            private void endMountJumpBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code experience bar} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V"))
            private void startExperienceBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.HOTBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderExperienceBar(Lnet/minecraft/client/gui/GuiGraphics;I)V", shift = At.Shift.AFTER))
            private void endExperienceBarTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code held item tooltip} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startHeldItemTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.HOTBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderSelectedItemName(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endHeldItemTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code spectator tooltip} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startSpectatorTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.HOTBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/spectator/SpectatorGui;renderTooltip(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endSpectatorTooltipTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Moves the {@code overlay message} for {@link Layer} key "minecraft:hotbar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;pushPose()V", ordinal = 0))
            private void startOverlayMessageTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.HOTBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V", ordinal = 0, shift = At.Shift.AFTER))
            private void endOverlayMessageTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            /**
             * Replaces the hotbar selector with a new square asset found under the {@code raised} namespace.
             */
            @ModifyArgs(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1))
            private void replaceHotbarSelector(Args args) {
                if (Option.getTexture() == Resource.Texture.REPLACE || (Option.getTexture() == Resource.Texture.AUTO && Pack.getPack())) {
                    args.set(0, ResourceLocation.tryParse("raised:hud/hotbar_selection"));
                    args.set(4, 24);
                }
            }

            /**
             * Draws a vertically mirrored row taken from the top of the asset below the unmodified selector.
             */
            @Inject(method = "renderHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"), locals = LocalCapture.CAPTURE_FAILHARD)
            private void patchHotbarSelector(float partialTick, GuiGraphics guiGraphics, CallbackInfo ci, Player player) {
                if (Option.getTexture() == Resource.Texture.PATCH  || (Option.getTexture() == Resource.Texture.AUTO && !Pack.getPack())) {
                    int x = (guiGraphics.guiWidth() / 2) - 92 + player.getInventory().selected * 20;
                    int y = guiGraphics.guiHeight();
                    ((GuiGraphicsInvoker) guiGraphics).invokeInnerBlit(ResourceLocation.tryParse("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 0, 1, 1 / 23.0F, 0);
                }
            }

        }

    }

    public abstract static class Chat {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code chat} for {@link Layer} key "minecraft:chat".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;III)V"))
            private void startChatTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.HOTBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/ChatComponent;render(Lnet/minecraft/client/gui/GuiGraphics;III)V", shift = At.Shift.AFTER))
            private void endChatTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

    public abstract static class Sidebar {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code sidebar} for {@link Layer} key "minecraft:sidebar".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/scores/Objective;)V"))
            private void startScoreboardTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.SIDEBAR.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;displayScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
            private void endScoreboardTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

    public abstract static class Effects {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code status effects} for {@link Layer} key "minecraft:effects".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lnet/minecraft/client/gui/GuiGraphics;)V"))
            private void startStatusEffectTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.EFFECTS.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;renderEffects(Lnet/minecraft/client/gui/GuiGraphics;)V", shift = At.Shift.AFTER))
            private void endStatusEffectTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

    public abstract static class Players {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves the {@code player list} for {@link Layer} key "minecraft:players".
             */
            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V"))
            private void startPlayerListTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.PLAYERS.toString());
            }

            @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;render(Lnet/minecraft/client/gui/GuiGraphics;ILnet/minecraft/world/scores/Scoreboard;Lnet/minecraft/world/scores/Objective;)V", shift = At.Shift.AFTER))
            private void endPlayerListTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

    public abstract static class Other {

        @Mixin(value = Gui.class, priority = -999999999)
        public abstract static class Pre {

            /**
             * Moves mod elements at the head/tail of the HUD render for {@link Layer} key "minecraft:other".
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void startRenderHeadTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.OTHER.toString());
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void startRenderTailTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.start(guiGraphics.pose(), Layers.OTHER.toString());
            }

        }

        @Mixin(value = Gui.class, priority = 999999999)
        public abstract static class Post {

            /**
             * Moves mod elements at the head/tail of the HUD render for {@link Layer} key "minecraft:other".
             */
            @Inject(method = "render", at = @At("HEAD"))
            private void endRenderHeadTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

            @Inject(method = "render", at = @At("TAIL"))
            private void endRenderTailTranslate(GuiGraphics guiGraphics, float partialTick, CallbackInfo ci) {
                Translate.end(guiGraphics.pose());
            }

        }

    }

}