package dev.yurisuika.raised.util;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

import java.util.List;

public class Layers {

    public static final ResourceLocation HOTBAR = ResourceLocation.tryParse("hotbar");
    public static final ResourceLocation CHAT = ResourceLocation.tryParse("chat");
    public static final ResourceLocation BOSSBAR = ResourceLocation.tryParse("bossbar");
    public static final ResourceLocation SIDEBAR = ResourceLocation.tryParse("sidebar");
    public static final ResourceLocation EFFECTS = ResourceLocation.tryParse("effects");
    public static final ResourceLocation PLAYERS = ResourceLocation.tryParse("players");
    public static final ResourceLocation TOASTS = ResourceLocation.tryParse("toasts");
    public static final ResourceLocation OTHER = ResourceLocation.tryParse("other");

    public static List<ResourceLocation> HOTBAR_LAYERS = Lists.newArrayList(
            VanillaGuiLayers.HOTBAR,
            VanillaGuiLayers.PLAYER_HEALTH,
            VanillaGuiLayers.ARMOR_LEVEL,
            VanillaGuiLayers.FOOD_LEVEL,
            VanillaGuiLayers.AIR_LEVEL,
            VanillaGuiLayers.VEHICLE_HEALTH,
            VanillaGuiLayers.JUMP_METER,
            VanillaGuiLayers.EXPERIENCE_BAR,
            VanillaGuiLayers.EXPERIENCE_LEVEL,
            VanillaGuiLayers.SELECTED_ITEM_NAME,
            VanillaGuiLayers.SPECTATOR_TOOLTIP,
            VanillaGuiLayers.OVERLAY_MESSAGE
    );
    public static List<ResourceLocation> CHAT_LAYERS = Lists.newArrayList(
            VanillaGuiLayers.CHAT
    );
    public static List<ResourceLocation> BOSSBAR_LAYERS = Lists.newArrayList(
            VanillaGuiLayers.BOSS_OVERLAY
    );
    public static List<ResourceLocation> SIDEBAR_LAYERS = Lists.newArrayList(
            VanillaGuiLayers.SCOREBOARD_SIDEBAR
    );
    public static List<ResourceLocation> EFFECTS_LAYERS = Lists.newArrayList(
            VanillaGuiLayers.EFFECTS
    );
    public static List<ResourceLocation> PLAYERS_LAYERS = Lists.newArrayList(
            VanillaGuiLayers.TAB_LIST
    );
    public static List<ResourceLocation> TOASTS_LAYERS = Lists.newArrayList();
    public static List<ResourceLocation> OTHER_LAYERS = Lists.newArrayList();

}