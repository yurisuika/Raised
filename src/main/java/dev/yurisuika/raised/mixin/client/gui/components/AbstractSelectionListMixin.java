package dev.yurisuika.raised.mixin.client.gui.components;

import dev.yurisuika.raised.client.gui.components.AbstractSelectionListInterface;
import net.minecraft.client.gui.components.AbstractSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AbstractSelectionList.class)
public abstract class AbstractSelectionListMixin implements AbstractSelectionListInterface {

    @Unique
    public boolean adjusted = false;

    @ModifyConstant(method = "renderWidget", constant = @Constant(intValue = 4, ordinal = 0))
    private int adjustHeaderSpacing(int value) {
        return adjusted ? 8 : value;
    }

    @ModifyConstant(method = "getEntryAtPosition", constant = @Constant(intValue = 4, ordinal = 0))
    private int adjustEntrySpacing(int value) {
        return adjusted ? 8 : value;
    }

    @ModifyConstant(method = "ensureVisible", constant = @Constant(intValue = 4, ordinal = 0))
    private int adjustVisibleSpacing(int value) {
        return adjusted ? 8 : value;
    }

    @ModifyConstant(method = "getRowTop", constant = @Constant(intValue = 4, ordinal = 0))
    private int adjustRowTop(int value) {
        return adjusted ? 8 : value;
    }

    @ModifyConstant(method = "renderListItems", constant = @Constant(intValue = 4, ordinal = 0))
    private int adjustItemHeight(int value) {
        return adjusted ? 0 : value;
    }

    @Override
    public void setAdjusted(boolean adjusted) {
        this.adjusted = adjusted;
    }

}