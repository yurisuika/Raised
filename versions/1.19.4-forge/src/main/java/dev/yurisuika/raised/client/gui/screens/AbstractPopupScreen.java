package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.mixin.minecraft.client.gui.components.AbstractWidgetInvoker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

public abstract class AbstractPopupScreen extends AbstractRaisedScreen {

    public SelectScreen parent;
    public ArrayList<AbstractWidget> controls;
    public ArrayList<AbstractWidget> options;
    public AbstractWidget controlClose;
    public int panelX;
    public int panelY;

    public AbstractPopupScreen(SelectScreen parent) {
        super(parent, 164, 56);
        this.parent = parent;
    }

    @Override
    public void setSizes() {
        super.setSizes();

        panelX = containerX + CONTAINER_PADDING;
        panelY = containerY + CONTAINER_PADDING;
    }

    @Override
    public void addContent() {
        createControls();
        createOptions();
    }

    public void createControls() {
        controls = new ArrayList<>();

        controlClose = Button.builder(Component.translatable("options.raised.control.close"), button -> onClose())
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(panelX, panelY)
                .build();

        controls.add(controlClose);

        controls.forEach(this::addRenderableWidget);
    }

    public abstract void createOptions();

    @Override
    public void renderBackground(final PoseStack poseStack) {
        if (parent != null) {
            parent.renderBackground(poseStack);
            Minecraft.getInstance().renderBuffers().bufferSource().endBatch();
            parent.render(poseStack, -1, -1, 0.0F);
            Minecraft.getInstance().renderBuffers().bufferSource().endBatch();
            RenderSystem.clear(256, Minecraft.ON_OSX);
            fillGradient(poseStack, 0, 0, width, height, -1072689136, -804253680);
        } else {
            super.renderBackground(poseStack);
        }

        RenderSystem.setShaderTexture(0, new ResourceLocation(Raised.MOD_ID, "textures/gui/sprites/popup/background.png"));
        blit(
                poseStack,
                containerX,
                containerY,
                0,
                0,
                containerWidth,
                containerHeight,
                164,
                56);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);

        AbstractWidgetInvoker.invokeRenderScrollingString(
                poseStack,
                font,
                getPopupTitle(),
                panelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                panelY,
                panelX + panelWidth,
                panelY + WIDGET_HEIGHT,
                -1);
    }

    public abstract Component getPopupTitle();

}