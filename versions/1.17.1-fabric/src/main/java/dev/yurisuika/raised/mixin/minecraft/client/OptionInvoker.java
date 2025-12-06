package dev.yurisuika.raised.mixin.minecraft.client;

import net.minecraft.client.Option;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Option.class)
public interface OptionInvoker {

    @Invoker("genericValueLabel")
    Component invokeGenericValueLabel(Component valueMessage);

}