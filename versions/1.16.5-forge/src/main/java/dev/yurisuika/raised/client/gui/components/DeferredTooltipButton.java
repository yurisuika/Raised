package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class DeferredTooltipButton extends Button {

    private final OnTooltip deferredTooltip;

    public DeferredTooltipButton(int x, int y, int width, int height, Component message, OnPress onPress) {
        this(x, y, width, height, message, onPress, NO_TOOLTIP);
    }

    public DeferredTooltipButton(int x, int y, int width, int height, Component message, OnPress onPress, OnTooltip tooltip) {
        super(x, y, width, height, message, onPress);
        this.deferredTooltip = tooltip;
    }

    @Override
    public void renderToolTip(PoseStack poseStack, int mouseX, int mouseY) {}

    public void renderDeferredTooltip(PoseStack poseStack, int mouseX, int mouseY) {
        if (deferredTooltip != null) {
            deferredTooltip.onTooltip(this, poseStack, mouseX, mouseY);
        }
    }

}