package dev.yurisuika.raised.util;

import com.google.common.collect.Lists;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.gui.overlay.VanillaGuiOverlay;

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
            VanillaGuiOverlay.HOTBAR.id(),
            VanillaGuiOverlay.PLAYER_HEALTH.id(),
            VanillaGuiOverlay.ARMOR_LEVEL.id(),
            VanillaGuiOverlay.FOOD_LEVEL.id(),
            VanillaGuiOverlay.AIR_LEVEL.id(),
            VanillaGuiOverlay.MOUNT_HEALTH.id(),
            VanillaGuiOverlay.JUMP_BAR.id(),
            VanillaGuiOverlay.EXPERIENCE_BAR.id(),
            VanillaGuiOverlay.ITEM_NAME.id(),
            VanillaGuiOverlay.RECORD_OVERLAY.id()
    );
    public static List<ResourceLocation> CHAT_LAYERS = Lists.newArrayList(
            VanillaGuiOverlay.CHAT_PANEL.id()
    );
    public static List<ResourceLocation> BOSSBAR_LAYERS = Lists.newArrayList(
            VanillaGuiOverlay.BOSS_EVENT_PROGRESS.id()
    );
    public static List<ResourceLocation> SIDEBAR_LAYERS = Lists.newArrayList(
            VanillaGuiOverlay.SCOREBOARD.id()
    );
    public static List<ResourceLocation> EFFECTS_LAYERS = Lists.newArrayList(
            VanillaGuiOverlay.POTION_ICONS.id()
    );
    public static List<ResourceLocation> PLAYERS_LAYERS = Lists.newArrayList(
            VanillaGuiOverlay.PLAYER_LIST.id()
    );
    public static List<ResourceLocation> TOASTS_LAYERS = Lists.newArrayList();
    public static List<ResourceLocation> OTHER_LAYERS = Lists.newArrayList();

}