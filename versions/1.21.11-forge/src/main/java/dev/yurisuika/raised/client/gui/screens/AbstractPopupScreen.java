package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.Raised;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

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
    public void renderBackground(final GuiGraphics guiGraphics, final int mouseX, final int mouseY, final float a) {
        if (parent != null) {
            parent.renderBackground(guiGraphics, mouseX, mouseY, a);
            guiGraphics.nextStratum();
            parent.render(guiGraphics, -1, -1, a);
            guiGraphics.nextStratum();
            renderTransparentBackground(guiGraphics);
        } else {
            super.renderBackground(guiGraphics, mouseX, mouseY, a);
        }

        guiGraphics.blitSprite(
                RenderPipelines.GUI_TEXTURED,
                Identifier.fromNamespaceAndPath(Raised.MOD_ID, "popup/background"),
                containerX,
                containerY,
                containerWidth,
                containerHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.textRenderer().acceptScrollingWithDefaultCenter(
                getPopupTitle(),
                panelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                panelX + panelWidth,
                panelY,
                panelY + WIDGET_HEIGHT);
    }

    public abstract Component getPopupTitle();

}