package dev.yurisuika.raised.mixin.resource;

import dev.yurisuika.raised.util.Pack;
import net.minecraft.resource.ResourcePackManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ResourcePackManager.class)
public abstract class ResourcePackManagerMixin {

    /**
     * Checks if resource pack support is present.
     */
    @Inject(method = "scanPacks", at = @At("TAIL"))
    private void checkPacks(CallbackInfo ci) {
        Pack.checkForResource();
    }

}