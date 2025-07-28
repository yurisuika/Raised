package dev.yurisuika.raised.mixin.client.gui.components;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.yurisuika.raised.client.gui.components.AbstractSelectionListInterface;
import net.minecraft.client.gui.components.AbstractSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(AbstractSelectionList.class)
public abstract class AbstractSelectionListMixin implements AbstractSelectionListInterface {

    @Unique
    public boolean adjusted = false;

    @ModifyExpressionValue(method = "renderWidget", at = @At(value = "CONSTANT", args = "intValue=4", ordinal = 0))
    private int adjustHeaderSpacing(int value) {
        return adjusted ? 8 : value;
    }

    @ModifyExpressionValue(method = "getEntryAtPosition", at = @At(value = "CONSTANT", args = "intValue=4", ordinal = 0))
    private int adjustEntrySpacing(int value) {
        return adjusted ? 8 : value;
    }

    @ModifyExpressionValue(method = "ensureVisible", at = @At(value = "CONSTANT", args = "intValue=4", ordinal = 0))
    private int adjustVisibleSpacing(int value) {
        return adjusted ? 8 : value;
    }

    @ModifyExpressionValue(method = "getRowTop", at = @At(value = "CONSTANT", args = "intValue=4", ordinal = 0))
    private int adjustRowTop(int value) {
        return adjusted ? 8 : value;
    }

    @ModifyExpressionValue(method = "renderListItems", at = @At(value = "CONSTANT", args = "intValue=4", ordinal = 0))
    private int adjustItemHeight(int value) {
        return adjusted ? 0 : value;
    }

    @Override
    public void setAdjusted(boolean adjusted) {
        this.adjusted = adjusted;
    }

}