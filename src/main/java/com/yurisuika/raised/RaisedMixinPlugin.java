package com.yurisuika.raised;

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
            "com.yurisuika.raised.mixin.mods.DetailArmorBarMixin", () -> FMLLoader.getLoadingModList().getModFileById("detailab") != null,
            "com.yurisuika.raised.mixin.mods.HealthOverlayMixin", () -> FMLLoader.getLoadingModList().getModFileById("healthoverlay") != null,
            "com.yurisuika.raised.mixin.mods.InventorioMixin", () -> FMLLoader.getLoadingModList().getModFileById("inventorio") != null,
            "com.yurisuika.raised.mixin.mods.LevelHeartsMixin", () -> FMLLoader.getLoadingModList().getModFileById("levelhearts") != null
    );

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return CONDITIONS.getOrDefault(mixinClassName, TRUE).get();
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
