package dev.yurisuika.raised.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.Font;
import net.minecraft.network.chat.Component;

public interface GuiComponentInterface {

    void renderScrollingString(PoseStack poseStack, Font font, Component text, int minX, int minY, int maxX, int maxY, int color);

    void enableScissor(int minX, int minY, int maxX, int maxY);

    void disableScissor();

}