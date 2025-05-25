package dev.yurisuika.raised.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

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

    public static Map<ResourceLocation, RenderGameOverlayEvent.ElementType> HOTBAR_LAYERS = new HashMap<ResourceLocation, RenderGameOverlayEvent.ElementType>() {
        {
            put(ResourceLocation.tryParse("hotbar"), RenderGameOverlayEvent.ElementType.HOTBAR);
            put(ResourceLocation.tryParse("player_health"), RenderGameOverlayEvent.ElementType.HEALTH);
            put(ResourceLocation.tryParse("armor_level"), RenderGameOverlayEvent.ElementType.ARMOR);
            put(ResourceLocation.tryParse("food_level"), RenderGameOverlayEvent.ElementType.FOOD);
            put(ResourceLocation.tryParse("mount_health"), RenderGameOverlayEvent.ElementType.HEALTHMOUNT);
            put(ResourceLocation.tryParse("air_level"), RenderGameOverlayEvent.ElementType.AIR);
            put(ResourceLocation.tryParse("jump_bar"), RenderGameOverlayEvent.ElementType.JUMPBAR);
            put(ResourceLocation.tryParse("experience_bar"), RenderGameOverlayEvent.ElementType.EXPERIENCE);
        }
    };
    public static Map<ResourceLocation, RenderGameOverlayEvent.ElementType> CHAT_LAYERS = new HashMap<ResourceLocation, RenderGameOverlayEvent.ElementType>() {
        {
            put(ResourceLocation.tryParse("chat_panel"), RenderGameOverlayEvent.ElementType.CHAT);
        }
    };
    public static Map<ResourceLocation, RenderGameOverlayEvent.ElementType> BOSSBAR_LAYERS = new HashMap<ResourceLocation, RenderGameOverlayEvent.ElementType>() {
        {
            put(ResourceLocation.tryParse("boss_health"), RenderGameOverlayEvent.ElementType.BOSSHEALTH);
            put(ResourceLocation.tryParse("boss_info"), RenderGameOverlayEvent.ElementType.BOSSINFO);
        }
    };
    public static Map<ResourceLocation, RenderGameOverlayEvent.ElementType> SIDEBAR_LAYERS = new HashMap<>();
    public static Map<ResourceLocation, RenderGameOverlayEvent.ElementType> EFFECTS_LAYERS = new HashMap<ResourceLocation, RenderGameOverlayEvent.ElementType>() {
        {
            put(ResourceLocation.tryParse("potion_icons"), RenderGameOverlayEvent.ElementType.POTION_ICONS);
        }
    };
    public static Map<ResourceLocation, RenderGameOverlayEvent.ElementType> PLAYERS_LAYERS = new HashMap<ResourceLocation, RenderGameOverlayEvent.ElementType>() {
        {
            put(ResourceLocation.tryParse("player_list"), RenderGameOverlayEvent.ElementType.PLAYER_LIST);
        }
    };
    public static Map<ResourceLocation, RenderGameOverlayEvent.ElementType> TOASTS_LAYERS = new HashMap<>();
    public static Map<ResourceLocation, RenderGameOverlayEvent.ElementType> OTHER_LAYERS = new HashMap<ResourceLocation, RenderGameOverlayEvent.ElementType>() {
        {
            put(ResourceLocation.tryParse("all"), RenderGameOverlayEvent.ElementType.ALL);
        }
    };

}