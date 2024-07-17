package dev.yurisuika.raised.mixin.server.packs.repository.resource;

import dev.yurisuika.raised.util.Pack;
import net.minecraft.server.packs.repository.PackRepository;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PackRepository.class)
public abstract class PackRepositoryMixin {

    /**
     * Checks if resource pack support is present.
     */
    @Inject(method = "reload", at = @At("TAIL"))
    private void checkPacks(CallbackInfo ci) {
        Pack.checkResources();
    }

}