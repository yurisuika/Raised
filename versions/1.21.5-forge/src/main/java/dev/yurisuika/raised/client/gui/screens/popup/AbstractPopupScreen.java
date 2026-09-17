package dev.yurisuika.raised.client.gui.screens.popup;

import com.mojang.blaze3d.systems.RenderSystem;
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.components.ScrollingWidget;
import dev.yurisuika.raised.client.gui.screens.AbstractScreen;
import dev.yurisuika.raised.client.gui.screens.SelectScreen;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderType;
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
    public void renderBackground(final GuiGraphics guiGraphics, final int mouseX, final int mouseY, final float a) {
        if (parent != null) {
            parent.renderBackground(guiGraphics, mouseX, mouseY, a);
            guiGraphics.flush();
            parent.render(guiGraphics, -1, -1, a);
            guiGraphics.flush();
            RenderSystem.getDevice().createCommandEncoder().clearDepthTexture(minecraft.getMainRenderTarget().getDepthTexture(), 1.0D);
            renderTransparentBackground(guiGraphics);
        } else {
            super.renderBackground(guiGraphics, mouseX, mouseY, a);
        }

        guiGraphics.blitSprite(
                RenderType::guiTextured,
                ResourceLocation.fromNamespaceAndPath(Raised.MOD_ID, "popup/background"),
                containerX,
                containerY,
                containerWidth,
                containerHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        ScrollingWidget.renderScrolling(
                guiGraphics,
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