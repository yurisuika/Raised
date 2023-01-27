package dev.yurisuika.raised;

import com.google.common.collect.ImmutableMap;
import net.minecraftforge.fml.loading.FMLLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class RaisedMixinPlugin implements IMixinConfigPlugin {

    private static final Supplier<Boolean> TRUE = () -> true;

    private static final Map<String, Supplier<Boolean>> CONDITIONS = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.BotaniaMixin$HUDHandlerMixin", () -> FMLLoader.getLoadingModList().getModFileById("botania") != null,
            "dev.yurisuika.raised.mixin.mods.BotaniaMixin$ItemFlightTiaraMixin", () -> FMLLoader.getLoadingModList().getModFileById("botania") != null,
            "dev.yurisuika.raised.mixin.mods.CreateMixin$CopperBacktankArmorLayerMixin", () -> FMLLoader.getLoadingModList().getModFileById("create") != null,
            "dev.yurisuika.raised.mixin.mods.CreateMixin$SchematicHotbarSlotOverlayMixin", () -> FMLLoader.getLoadingModList().getModFileById("create") != null,
            "dev.yurisuika.raised.mixin.mods.CreateMixin$ToolboxHandlerClientMixin", () -> FMLLoader.getLoadingModList().getModFileById("create") != null,
            "dev.yurisuika.raised.mixin.mods.CreateMixin$ToolSelectionScreenMixin", () -> FMLLoader.getLoadingModList().getModFileById("create") != null,
            "dev.yurisuika.raised.mixin.mods.CreateMixin$TrainHUDMixin", () -> FMLLoader.getLoadingModList().getModFileById("create") != null,
            "dev.yurisuika.raised.mixin.mods.DetailArmorBarMixin$ArmorBarRendererMixin", () -> FMLLoader.getLoadingModList().getModFileById("detailab") != null,
            "dev.yurisuika.raised.mixin.mods.HealthOverlayMixin$HeartRendererMixin", () -> FMLLoader.getLoadingModList().getModFileById("healthoverlay") != null,
            "dev.yurisuika.raised.mixin.mods.InventorioMixin$HotbarHUDRendererMixin", () -> FMLLoader.getLoadingModList().getModFileById("inventorio") != null
    );

    private static final Map<String, Supplier<Boolean>> CONDITIONS2 = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.InventoryProfilesNextMixin$LockSlotsHandlerMixin", () -> FMLLoader.getLoadingModList().getModFileById("inventoryprofilesnext") != null,
            "dev.yurisuika.raised.mixin.mods.LevelHeartsMixin$IngameGuiMixin", () -> FMLLoader.getLoadingModList().getModFileById("levelhearts") != null,
            "dev.yurisuika.raised.mixin.mods.QuarkMixin$HotbarChangerModuleMixin", () -> FMLLoader.getLoadingModList().getModFileById("quark") != null,
            "dev.yurisuika.raised.mixin.mods.QuarkMixin$UsageTickerModuleMixin", () -> FMLLoader.getLoadingModList().getModFileById("quark") != null
    );

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return CONDITIONS.getOrDefault(mixinClassName, TRUE).get() && CONDITIONS2.getOrDefault(mixinClassName, TRUE).get();
    }

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

}