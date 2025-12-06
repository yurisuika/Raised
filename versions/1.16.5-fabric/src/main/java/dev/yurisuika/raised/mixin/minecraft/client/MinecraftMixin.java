package dev.yurisuika.raised.mixin.minecraft.client;

import dev.yurisuika.raised.client.event.ClientStartedEvent;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {

    @Inject(method = "run", at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;gameThread:Ljava/lang/Thread;", shift = At.Shift.AFTER))
    private void onStart(CallbackInfo ci) {
        ClientStartedEvent.CLIENT_STARTED.invoker().onClientStarted((Minecraft) (Object) this);
    }

}