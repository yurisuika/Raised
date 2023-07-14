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

public final class RaisedQuarkMixinPlugin implements IMixinConfigPlugin {

    private static final Map<String, Supplier<Boolean>> QUARK = ImmutableMap.of(
            "dev.yurisuika.raised.mixin.mods.QuarkMixin$HotbarChangerModuleMixin", () -> FMLLoader.getLoadingModList().getModFileById("quark") != null,
            "dev.yurisuika.raised.mixin.mods.QuarkMixin$UsageTickerModuleMixin", () -> FMLLoader.getLoadingModList().getModFileById("quark") != null
    );

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return QUARK.getOrDefault(mixinClassName, () -> true).get();
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