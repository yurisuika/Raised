package dev.yurisuika.raised.mixin.client.gui.components;

import dev.yurisuika.raised.client.gui.components.AbstractSelectionListInterface;
import net.minecraft.client.gui.components.AbstractSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(AbstractSelectionList.class)
public abstract class AbstractSelectionListMixin implements AbstractSelectionListInterface {

    @Unique
    public int padding = 4;

    @ModifyVariable(method = "renderWidget", at = @At("STORE"), ordinal = 3)
    private int adjustHeaderSpacing(int value) {
        return value + (padding - 4);
    }

    @ModifyArg(method = "getEntryAtPosition", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;floor(D)I"), index = 0)
    public double adjustEntrySpacing(double value) {
        return value - (padding - 4);
    }

    @ModifyVariable(method = "ensureVisible", at = @At("STORE"), ordinal = 1)
    public int adjustVisibleSpacing(int value) {
        return value - (padding - 4);
    }

    @ModifyVariable(method = "renderListItems", at = @At("STORE"), ordinal = 4)
    public int adjustItemHeight(int value) {
        return value - (padding - 4);
    }

    @Override
    public void setPadding(int padding) {
        this.padding = padding;
    }

}