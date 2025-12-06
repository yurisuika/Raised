package dev.yurisuika.raised.mixin.minecraft.client.gui;

import net.minecraft.client.gui.LayeredDraw;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(LayeredDraw.class)
public interface LayeredDrawAccessor {

    @Accessor
    List<LayeredDraw.Layer> getLayers();

}