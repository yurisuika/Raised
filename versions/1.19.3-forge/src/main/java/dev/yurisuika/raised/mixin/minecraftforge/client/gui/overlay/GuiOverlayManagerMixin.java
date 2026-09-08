package dev.yurisuika.raised.mixin.minecraftforge.client.gui.overlay;

import dev.yurisuika.raised.client.gui.layer.Layers;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.GuiOverlayManager;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Mixin(value = GuiOverlayManager.class, remap = false)
public abstract class GuiOverlayManagerMixin {

    @Inject(method = "preRegisterVanillaOverlays", at = @At(value = "INVOKE", target = "Ljava/util/HashMap;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"), locals = LocalCapture.CAPTURE_FAILHARD)
    private static void mapVanillaOverlays(HashMap<ResourceLocation, IGuiOverlay> overlays, ArrayList<ResourceLocation> orderedOverlays, CallbackInfo ci, VanillaGuiOverlay[] var2, int var3, int var4, VanillaGuiOverlay entry) {
        ResourceLocation curatedName = curateName(entry.id());

        if (curatedName != null) {
            Layers.Curated.CURATED_LAYERS.put(entry.id(), curatedName);
        }
    }

    @Unique
    private static ResourceLocation curateName(ResourceLocation name) {
        Map<ResourceLocation, ResourceLocation> map = Map.ofEntries(
                Map.entry(VanillaGuiOverlay.HOTBAR.id(), Layers.HOTBAR),
                Map.entry(VanillaGuiOverlay.PLAYER_HEALTH.id(), Layers.HOTBAR),
                Map.entry(VanillaGuiOverlay.ARMOR_LEVEL.id(), Layers.HOTBAR),
                Map.entry(VanillaGuiOverlay.FOOD_LEVEL.id(), Layers.HOTBAR),
                Map.entry(VanillaGuiOverlay.AIR_LEVEL.id(), Layers.HOTBAR),
                Map.entry(VanillaGuiOverlay.MOUNT_HEALTH.id(), Layers.HOTBAR),
                Map.entry(VanillaGuiOverlay.JUMP_BAR.id(), Layers.HOTBAR),
                Map.entry(VanillaGuiOverlay.EXPERIENCE_BAR.id(), Layers.HOTBAR),
                Map.entry(VanillaGuiOverlay.ITEM_NAME.id(), Layers.HOTBAR),
                Map.entry(VanillaGuiOverlay.RECORD_OVERLAY.id(), Layers.ACTION_BAR),
                Map.entry(VanillaGuiOverlay.CHAT_PANEL.id(), Layers.CHAT),
                Map.entry(VanillaGuiOverlay.BOSS_EVENT_PROGRESS.id(), Layers.BOSS_BAR),
                Map.entry(VanillaGuiOverlay.SCOREBOARD.id(), Layers.SCOREBOARD),
                Map.entry(VanillaGuiOverlay.POTION_ICONS.id(), Layers.EFFECTS),
                Map.entry(VanillaGuiOverlay.PLAYER_LIST.id(), Layers.PLAYER_LIST),
                Map.entry(VanillaGuiOverlay.TITLE_TEXT.id(), Layers.TITLES),
                Map.entry(VanillaGuiOverlay.SUBTITLES.id(), Layers.SUBTITLES)
        );

        return map.getOrDefault(name, null);
    }

}