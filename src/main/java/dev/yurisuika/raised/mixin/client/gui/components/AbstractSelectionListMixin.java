package dev.yurisuika.raised.mixin.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.components.AbstractSelectionListInterface;
import dev.yurisuika.raised.client.gui.Scissor;
import net.minecraft.client.gui.components.AbstractSelectionList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
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

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/AbstractSelectionList;renderList(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIF)V"))
    private void enableScissor(PoseStack poseStack, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (adjusted) {
            Scissor.enableScissor(x0, y0, x1, y1);
        }
    }

    @Inject(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/AbstractSelectionList;renderList(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIF)V", shift = At.Shift.AFTER))
    private void disableScissor(PoseStack poseStack, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
        if (adjusted) {
            Scissor.disableScissor();
        }
    }

    @ModifyArg(method = "renderList", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;vertex(DDD)Lcom/mojang/blaze3d/vertex/VertexConsumer;", ordinal = 0), index = 1)
    private double adjustSelectionOuterBottomLeft(double value) {
        return value - (adjusted ? 2 : 0);
    }

    @ModifyArg(method = "renderList", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;vertex(DDD)Lcom/mojang/blaze3d/vertex/VertexConsumer;", ordinal = 1), index = 1)
    private double adjustSelectionOuterBottomRight(double value) {
        return value - (adjusted ? 2 : 0);
    }

    @ModifyArg(method = "renderList", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;vertex(DDD)Lcom/mojang/blaze3d/vertex/VertexConsumer;", ordinal = 2), index = 1)
    private double adjustSelectionOuterTopLeft(double value) {
        return value + (adjusted ? 2 : 0);
    }

    @ModifyArg(method = "renderList", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;vertex(DDD)Lcom/mojang/blaze3d/vertex/VertexConsumer;", ordinal = 3), index = 1)
    private double adjustSelectionOuterTopRight(double value) {
        return value + (adjusted ? 2 : 0);
    }

    @ModifyArg(method = "renderList", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;vertex(DDD)Lcom/mojang/blaze3d/vertex/VertexConsumer;", ordinal = 4), index = 1)
    private double adjustSelectionInnerBottomLeft(double value) {
        return value - (adjusted ? 2 : 0);
    }

    @ModifyArg(method = "renderList", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;vertex(DDD)Lcom/mojang/blaze3d/vertex/VertexConsumer;", ordinal = 5), index = 1)
    private double adjustSelectionInnerBottomRight(double value) {
        return value - (adjusted ? 2 : 0);
    }

    @ModifyArg(method = "renderList", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;vertex(DDD)Lcom/mojang/blaze3d/vertex/VertexConsumer;", ordinal = 6), index = 1)
    private double adjustSelectionInnerTopLeft(double value) {
        return value + (adjusted ? 2 : 0);
    }

    @ModifyArg(method = "renderList", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;vertex(DDD)Lcom/mojang/blaze3d/vertex/VertexConsumer;", ordinal = 7), index = 1)
    private double adjustSelectionInnerTopRight(double value) {
        return value + (adjusted ? 2 : 0);
    }

    @Override
    public void setAdjusted(boolean adjusted) {
        this.adjusted = adjusted;
    }

}