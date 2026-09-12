package dev.yurisuika.raised.mixin.minecraft.client.gui;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.option.AdditionalSettings;
import dev.yurisuika.raised.util.Pack;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(value = Gui.class, priority = -999999999)
public abstract class GuiMixin {

    /**
     * Replaces the hotbar selection with a new square asset found under the {@code raised} namespace.
     */
    @ModifyArg(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), index = 1)
    private ResourceLocation replaceHotbarSelectorIdentifier(ResourceLocation sprite) {
        if (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.REPLACE || (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.AUTO && Pack.getPack())) {
            return ResourceLocation.fromNamespaceAndPath(Raised.MOD_ID, "hud/hotbar_selection");
        } else {
            return sprite;
        }
    }

    @ModifyArg(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphics;blitSprite(Ljava/util/function/Function;Lnet/minecraft/resources/ResourceLocation;IIII)V", ordinal = 1), index = 5)
    private int replaceHotbarSelectorHeight(int height) {
        if (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.REPLACE || (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.AUTO && Pack.getPack())) {
            return 24;
        } else {
            return height;
        }
    }

    /**
     * Draws a vertically mirrored row taken from the top of the asset below the unmodified selector.
     */
    @Inject(method = "renderItemHotbar", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/PoseStack;popPose()V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void patchHotbarSelector(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci, Player player) {
        if (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.PATCH  || (Config.getOptions().getAdditionalSettings().getHotbarSelectionFix() == AdditionalSettings.HotbarSelectionFix.AUTO && !Pack.getPack())) {
            int x = (guiGraphics.guiWidth() / 2) - 92 + player.getInventory().selected * 20;
            int y = guiGraphics.guiHeight();
            ((GuiGraphicsInvoker) guiGraphics).invokeInnerBlit(RenderType::guiTextured, ResourceLocation.withDefaultNamespace("textures/gui/sprites/hud/hotbar_selection.png"), x, x + 24, y, y + 1, 0, 1, 1 / 23.0F, 0, -1);
        }
    }

    /**
     * Moves the {@code hotbar}, {@code health bar}, {@code armor bar}, {@code food bar}, {@code air bar},
     * {@code mount health bar}, {@code mount jump bar}, {@code experience bar}, and {@code held item tooltip}
     * for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "renderHotbarAndDecorations", at = @At("HEAD"))
    private void startMainHudTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.start(guiGraphics.pose(), Layers.HOTBAR);
        }
    }

    @Inject(method = "renderHotbarAndDecorations", at = @At("TAIL"))
    private void endMainHudTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.end(guiGraphics.pose(), Layers.HOTBAR);
        }
    }

    /**
     * Moves the {@code experience level} for {@link Layer} key "minecraft:hotbar".
     */
    @Inject(method = "renderExperienceLevel", at = @At("HEAD"))
    private void startExperienceLevelTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.start(guiGraphics.pose(), Layers.HOTBAR);
        }
    }

    @Inject(method = "renderExperienceLevel", at = @At("TAIL"))
    private void endExperienceLevelTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.end(guiGraphics.pose(), Layers.HOTBAR);
        }
    }

    /**
     * Moves the {@code action bar} for {@link Layer} key "minecraft:action_bar".
     */
    @Inject(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void startActionBarTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.start(guiGraphics.pose(), Layers.ACTION_BAR);
        }
    }

    @Inject(method = "renderOverlayMessage", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
    private void endActionBarTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.end(guiGraphics.pose(), Layers.ACTION_BAR);
        }
    }

    /**
     * Moves the {@code chat} for {@link Layer} key "minecraft:chat".
     */
    @Inject(method = "renderChat", at = @At("HEAD"))
    private void startChatTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.start(guiGraphics.pose(), Layers.CHAT);
        }
    }

    @Inject(method = "renderChat", at = @At("TAIL"))
    private void endChatTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.end(guiGraphics.pose(), Layers.CHAT);
        }
    }

    /**
     * Moves the {@code scoreboard} for {@link Layer} key "minecraft:scoreboard".
     */
    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", at = @At("HEAD"))
    private void startScoreboardTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.start(guiGraphics.pose(), Layers.SCOREBOARD);
        }
    }

    @Inject(method = "renderScoreboardSidebar(Lnet/minecraft/client/gui/GuiGraphics;Lnet/minecraft/client/DeltaTracker;)V", at = @At("TAIL"))
    private void endScoreboardTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.end(guiGraphics.pose(), Layers.SCOREBOARD);
        }
    }

    /**
     * Moves the {@code effects} for {@link Layer} key "minecraft:effects".
     */
    @Inject(method = "renderEffects", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;getMobEffectTextures()Lnet/minecraft/client/resources/MobEffectTextureManager;"))
    private void startEffectsTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.start(guiGraphics.pose(), Layers.EFFECTS);
        }
    }

    @Inject(method = "renderEffects", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V", shift = At.Shift.AFTER))
    private void endEffectsTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.end(guiGraphics.pose(), Layers.EFFECTS);
        }
    }

    /**
     * Moves the {@code player list} for {@link Layer} key "minecraft:player_list".
     */
    @Inject(method = "renderTabList", at = @At("HEAD"))
    private void startPlayerListTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.start(guiGraphics.pose(), Layers.PLAYER_LIST);
        }
    }

    @Inject(method = "renderTabList", at = @At("TAIL"))
    private void endPlayerListTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.end(guiGraphics.pose(), Layers.PLAYER_LIST);
        }
    }

    /**
     * Moves the {@code titles} for {@link Layer} key "minecraft:titles".
     */
    @Inject(method = "renderTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;push(Ljava/lang/String;)V"))
    private void startTitlesTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.start(guiGraphics.pose(), Layers.TITLES);
        }
    }

    @Inject(method = "renderTitle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/profiling/ProfilerFiller;pop()V", shift = At.Shift.AFTER))
    private void endTitlesTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        try {
            Class.forName("net.minecraftforge.client.gui.overlay.ForgeLayeredDraw");
        } catch (ClassNotFoundException e) {
            Translate.end(guiGraphics.pose(), Layers.TITLES);
        }
    }

    /**
     * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:unknown".
     */
    @Inject(method = "render", at = @At("HEAD"))
    private void startRenderHeadTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.UNKNOWN);
    }

    /**
     * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:unknown".
     */
    @Inject(method = "render", at = @At("TAIL"))
    private void startRenderTailTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
        Translate.start(guiGraphics.pose(), Layers.UNKNOWN);
    }

    @Mixin(value = Gui.class, priority = 999999999)
    public abstract static class Last {

        /**
         * Moves layers injected at the head of the main render method for {@link Layer} key "minecraft:unknown".
         */
        @Inject(method = "render", at = @At("HEAD"))
        private void endRenderHeadTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
            Translate.end(guiGraphics.pose(), Layers.UNKNOWN);
        }

        /**
         * Moves layers injected at the tail of the main render method for {@link Layer} key "minecraft:unknown".
         */
        @Inject(method = "render", at = @At("TAIL"))
        private void endRenderTailTranslate(GuiGraphics guiGraphics, DeltaTracker deltaTracker, CallbackInfo ci) {
            Translate.end(guiGraphics.pose(), Layers.UNKNOWN);
        }

    }

}