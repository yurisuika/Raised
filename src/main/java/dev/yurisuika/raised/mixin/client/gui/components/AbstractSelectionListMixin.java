package dev.yurisuika.raised.mixin.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.components.AbstractSelectionListInterface;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSelectionList.class)
public abstract class AbstractSelectionListMixin implements AbstractSelectionListInterface {

    @Shadow
    protected int x0;
    @Shadow
    protected int x1;
    @Shadow
    protected int y0;
    @Shadow
    protected int y1;
    @Unique
    public boolean adjusted = false;

    @ModifyConstant(method = "render", constant = @Constant(intValue = 4, ordinal = 0))
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

    @ModifyConstant(method = "renderList", constant = @Constant(intValue = 4, ordinal = 0))
    private int adjustItemHeight(int value) {
        return adjusted ? 0 : value;
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/AbstractSelectionList;renderList(Lcom/mojang/blaze3d/vertex/PoseStack;IIF)V"))
    private void enableScissor(PoseStack poseStack, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (adjusted) {
            GuiComponent.enableScissor(x0, y0, x1, y1);
        }
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/AbstractSelectionList;renderList(Lcom/mojang/blaze3d/vertex/PoseStack;IIF)V", shift = At.Shift.AFTER))
    private void disableScissor(PoseStack poseStack, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (adjusted) {
            GuiComponent.disableScissor();
        }
    }

    @Override
    public void setAdjusted(boolean adjusted) {
        this.adjusted = adjusted;
    }

}