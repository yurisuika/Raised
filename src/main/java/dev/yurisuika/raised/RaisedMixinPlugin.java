package dev.yurisuika.raised;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.loader.api.FabricLoader;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

public final class RaisedMixinPlugin implements IMixinConfigPlugin {

    private static final Map<String, Supplier<Boolean>> APPLESKIN = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.AppleskinMixin$HUDOverlayHandlerMixin", () -> FabricLoader.getInstance().isModLoaded("appleskin")
    );

    private static final Map<String, Supplier<Boolean>> BOTANIA = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.BotaniaMixin$HUDHandlerMixin", () -> FabricLoader.getInstance().isModLoaded("botania"),
            "dev.yurisuika.raised.mixin.mods.BotaniaMixin$FlugelTiaraItemMixin", () -> FabricLoader.getInstance().isModLoaded("botania")
    );

    private static final Map<String, Supplier<Boolean>> CREATE = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.CreateMixin$RemainingAirOverlayMixin", () -> FabricLoader.getInstance().isModLoaded("create"),
            "dev.yurisuika.raised.mixin.mods.CreateMixin$SchematicHotbarSlotOverlayMixin", () -> FabricLoader.getInstance().isModLoaded("create"),
            "dev.yurisuika.raised.mixin.mods.CreateMixin$ToolboxHandlerClientMixin", () -> FabricLoader.getInstance().isModLoaded("create"),
            "dev.yurisuika.raised.mixin.mods.CreateMixin$ToolSelectionScreenMixin", () -> FabricLoader.getInstance().isModLoaded("create"),
            "dev.yurisuika.raised.mixin.mods.CreateMixin$TrainHUDMixin", () -> FabricLoader.getInstance().isModLoaded("create")
    );

    private static final Map<String, Supplier<Boolean>> DETAILARMORBAR = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.DetailArmorBarMixin$ArmorBarRendererMixin", () -> FabricLoader.getInstance().isModLoaded("detailab")
    );

    private static final Map<String, Supplier<Boolean>> FARMERSDELIGHT = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.FarmersDelightMixin$ComfortHealthOverlayMixin", () -> FabricLoader.getInstance().isModLoaded("farmersdelight"),
            "dev.yurisuika.raised.mixin.mods.FarmersDelightMixin$NourishmentHungerOverlayMixin", () -> FabricLoader.getInstance().isModLoaded("farmersdelight")
    );

    private static final Map<String, Supplier<Boolean>> INVENTORIO = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.InventorioMixin$HotbarHUDRendererMixin", () -> FabricLoader.getInstance().isModLoaded("inventorio")
    );

    private static final Map<String, Supplier<Boolean>> INVENTORYPROFILESNEXT = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.InventoryProfilesNextMixin$LockSlotsHandlerMixin", () -> FabricLoader.getInstance().isModLoaded("inventoryprofilesnext")
    );

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return APPLESKIN.getOrDefault(mixinClassName, () -> true).get() && BOTANIA.getOrDefault(mixinClassName, () -> true).get() && CREATE.getOrDefault(mixinClassName, () -> true).get() && DETAILARMORBAR.getOrDefault(mixinClassName, () -> true).get() && FARMERSDELIGHT.getOrDefault(mixinClassName, () -> true).get() && INVENTORIO.getOrDefault(mixinClassName, () -> true).get() && INVENTORYPROFILESNEXT.getOrDefault(mixinClassName, () -> true).get();
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