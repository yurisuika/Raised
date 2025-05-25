package dev.yurisuika.raised.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.ForgeIngameGui;
import net.minecraftforge.client.gui.IIngameOverlay;

import java.util.HashMap;
import java.util.Map;

public class Layers {

    public static final ResourceLocation HOTBAR = ResourceLocation.tryParse("hotbar");
    public static final ResourceLocation CHAT = ResourceLocation.tryParse("chat");
    public static final ResourceLocation BOSSBAR = ResourceLocation.tryParse("bossbar");
    public static final ResourceLocation SIDEBAR = ResourceLocation.tryParse("sidebar");
    public static final ResourceLocation EFFECTS = ResourceLocation.tryParse("effects");
    public static final ResourceLocation PLAYERS = ResourceLocation.tryParse("players");
    public static final ResourceLocation TOASTS = ResourceLocation.tryParse("toasts");
    public static final ResourceLocation OTHER = ResourceLocation.tryParse("other");

    public static Map<ResourceLocation, IIngameOverlay> HOTBAR_LAYERS = new HashMap<>() {
        {
            put(ResourceLocation.tryParse("hotbar"), ForgeIngameGui.HOTBAR_ELEMENT);
            put(ResourceLocation.tryParse("player_health"), ForgeIngameGui.PLAYER_HEALTH_ELEMENT);
            put(ResourceLocation.tryParse("armor_level"), ForgeIngameGui.ARMOR_LEVEL_ELEMENT);
            put(ResourceLocation.tryParse("food_level"), ForgeIngameGui.FOOD_LEVEL_ELEMENT);
            put(ResourceLocation.tryParse("mount_health"), ForgeIngameGui.MOUNT_HEALTH_ELEMENT);
            put(ResourceLocation.tryParse("air_level"), ForgeIngameGui.AIR_LEVEL_ELEMENT);
            put(ResourceLocation.tryParse("jump_bar"), ForgeIngameGui.JUMP_BAR_ELEMENT);
            put(ResourceLocation.tryParse("experience_bar"), ForgeIngameGui.EXPERIENCE_BAR_ELEMENT);
            put(ResourceLocation.tryParse("item_name"), ForgeIngameGui.ITEM_NAME_ELEMENT);
            put(ResourceLocation.tryParse("record_overlay"), ForgeIngameGui.RECORD_OVERLAY_ELEMENT);
        }
    };
    public static Map<ResourceLocation, IIngameOverlay> CHAT_LAYERS = new HashMap<>() {
        {
            put(ResourceLocation.tryParse("chat_panel"), ForgeIngameGui.CHAT_PANEL_ELEMENT);
        }
    };
    public static Map<ResourceLocation, IIngameOverlay> BOSSBAR_LAYERS = new HashMap<>() {
        {
            put(ResourceLocation.tryParse("boss_health"), ForgeIngameGui.BOSS_HEALTH_ELEMENT);
        }
    };
    public static Map<ResourceLocation, IIngameOverlay> SIDEBAR_LAYERS = new HashMap<>() {
        {
            put(ResourceLocation.tryParse("scoreboard"), ForgeIngameGui.SCOREBOARD_ELEMENT);
        }
    };
    public static Map<ResourceLocation, IIngameOverlay> EFFECTS_LAYERS = new HashMap<>() {
        {
            put(ResourceLocation.tryParse("potion_icons"), ForgeIngameGui.POTION_ICONS_ELEMENT);
        }
    };
    public static Map<ResourceLocation, IIngameOverlay> PLAYERS_LAYERS = new HashMap<>() {
        {
            put(ResourceLocation.tryParse("player_list"), ForgeIngameGui.PLAYER_LIST_ELEMENT);
        }
    };
    public static Map<ResourceLocation, IIngameOverlay> TOASTS_LAYERS = new HashMap<>();
    public static Map<ResourceLocation, IIngameOverlay> OTHER_LAYERS = new HashMap<>();

}