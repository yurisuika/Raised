package dev.yurisuika.raised.mixin.minecraftforge.client.gui;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.IIngameOverlay;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.fml.ModLoadingContext;
import org.apache.commons.lang3.StringUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(value = OverlayRegistry.class, remap = false)
public abstract class OverlayRegistryMixin {

    @Inject(method = "registerOverlay", at = @At("RETURN"))
    private static void addLayer(int sort, IIngameOverlay other, String displayName, IIngameOverlay overlay, CallbackInfoReturnable<IIngameOverlay> cir) {
        addLayer(displayName, overlay);
    }

    @Unique
    private static void addLayer(String displayName, IIngameOverlay overlay) {
        String namespace = ModLoadingContext.get().getActiveNamespace();
        namespace = namespace.equals(Raised.MOD_ID) ? ResourceLocation.DEFAULT_NAMESPACE : namespace;
        String path = sanitizeToPath(displayName);
        ResourceLocation name = new ResourceLocation(namespace, path);

        if (!name.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
            LayerRegistry.register(name, new Layer(Layer.Anchor.NONE));
        } else {
            name = curateName(name);
        }

        if (name != null) {
            Layers.Curated.CURATED_LAYERS.put(overlay, name);
        }
    }

    @Unique
    private static String sanitizeToPath(String s) {
        s = StringUtils.removePattern(s, "[^a-zA-Z0-9 ]");
        s = StringUtils.replace(s, " ", "_");
        s = s.toLowerCase();
        return s;
    }

    @Unique
    private static ResourceLocation curateName(ResourceLocation name) {
        Map<ResourceLocation, ResourceLocation> map = Map.ofEntries(
                Map.entry(new ResourceLocation("hotbar"), Layers.HOTBAR),               // ForgeIngameGui.HOTBAR
                Map.entry(new ResourceLocation("player_health"), Layers.HOTBAR),        // ForgeIngameGui.PLAYER_HEALTH
                Map.entry(new ResourceLocation("armor_level"), Layers.HOTBAR),          // ForgeIngameGui.ARMOR_LEVEL_ELEMENT
                Map.entry(new ResourceLocation("food_level"), Layers.HOTBAR),           // ForgeIngameGui.FOOD_LEVEL_ELEMENT
                Map.entry(new ResourceLocation("air_level"), Layers.HOTBAR),            // ForgeIngameGui.AIR_LEVEL_ELEMENT
                Map.entry(new ResourceLocation("mount_health"), Layers.HOTBAR),         // ForgeIngameGui.MOUNT_HEALTH_ELEMENT
                Map.entry(new ResourceLocation("jump_bar"), Layers.HOTBAR),             // ForgeIngameGui.JUMP_BAR_ELEMENT
                Map.entry(new ResourceLocation("experience_bar"), Layers.HOTBAR),       // ForgeIngameGui.EXPERIENCE_BAR_ELEMENT
                Map.entry(new ResourceLocation("item_name"), Layers.HOTBAR),            // ForgeIngameGui.ITEM_NAME_ELEMENT
                Map.entry(new ResourceLocation("record"), Layers.ACTION_BAR),           // ForgeIngameGui.RECORD_OVERLAY_ELEMENT
                Map.entry(new ResourceLocation("chat_history"), Layers.CHAT),           // ForgeIngameGui.CHAT_PANEL_ELEMENT
                Map.entry(new ResourceLocation("boss_health"), Layers.BOSS_BAR),        // ForgeIngameGui.BOSS_HEALTH_ELEMENT
                Map.entry(new ResourceLocation("scoreboard"), Layers.SCOREBOARD),       // ForgeIngameGui.SCOREBOARD_ELEMENT
                Map.entry(new ResourceLocation("potion_icons"), Layers.EFFECTS),        // ForgeIngameGui.POTION_ICONS_ELEMENT
                Map.entry(new ResourceLocation("player_list"), Layers.PLAYER_LIST),     // ForgeIngameGui.PLAYER_LIST_ELEMENT
                Map.entry(new ResourceLocation("title_text"), Layers.TITLES),           // ForgeIngameGui.TITLE_TEXT_ELEMENT
                Map.entry(new ResourceLocation("subtitles"), Layers.SUBTITLES)          // ForgeIngameGui.SUBTITLES_ELEMENT
        );

        return map.getOrDefault(name, null);
    }

}