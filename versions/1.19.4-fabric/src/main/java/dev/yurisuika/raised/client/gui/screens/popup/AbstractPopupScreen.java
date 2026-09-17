package dev.yurisuika.raised.client.gui.screens.popup;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.Graphics;
import dev.yurisuika.raised.client.gui.components.ScrollingWidget;
import dev.yurisuika.raised.client.gui.screens.AbstractScreen;
import dev.yurisuika.raised.client.gui.screens.SelectScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

public abstract class AbstractPopupScreen extends AbstractScreen {

    public SelectScreen parent;
    public ArrayList<AbstractWidget> controls;
    public ArrayList<AbstractWidget> options;
    public AbstractWidget controlClose;
    public int panelX;
    public int panelY;

    public AbstractPopupScreen(SelectScreen parent, int containerWidth, int containerHeight) {
        super(parent, containerWidth, containerHeight);
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
        addControls();
        addOptions();
    }

    public void addControls() {
        controls = new ArrayList<>();

        controlClose = Button.builder(Component.translatable("options.raised.control.close"), button -> onClose())
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(panelX, panelY)
                .build();

        controls.add(controlClose);

        controls.forEach(this::addRenderableWidget);
    }

    public void resetControls() {
        if (controls != null) {
            controls.forEach(this::removeWidget);
            controls.clear();
            addControls();
        }
    }

    public void resetOptions() {
        if (options != null) {
            options.forEach(this::removeWidget);
            options.clear();
            addOptions();
        }
    }

    public abstract void addOptions();

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
        Graphics.blitNineSliced(
                poseStack,
                164,
                56,
                7,
                containerX,
                containerY,
                containerWidth,
                containerHeight);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        super.render(poseStack, mouseX, mouseY, partialTick);

        ScrollingWidget.renderScrolling(
                poseStack,
                font,
                getPopupTitle(),
                panelX + (panelWidth / 2),
                panelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                panelY,
                panelX + panelWidth,
                panelY + WIDGET_HEIGHT,
                -1);
    }

    public abstract Component getPopupTitle();

}