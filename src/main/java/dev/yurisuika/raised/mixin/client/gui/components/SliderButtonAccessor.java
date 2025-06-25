package dev.yurisuika.raised.mixin.client.gui.components;

import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.SliderButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SliderButton.class)
public interface SliderButtonAccessor {

    @Accessor
    ProgressOption getOption();

}