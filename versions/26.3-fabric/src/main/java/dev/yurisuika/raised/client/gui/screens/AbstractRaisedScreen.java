package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.client.RaisedOptions;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;

public abstract class AbstractRaisedScreen extends Screen {

    public Screen parent;
    public int centerX;
    public int centerY;
    public static final int CONTAINER_GAP = 6;
    public static final int CONTAINER_PADDING = 7;
    public int containerX;
    public int containerY;
    public int containerHeight;
    public int containerWidth;
    public int panelHeight;
    public int panelWidth;
    public static final int PANEL_GAP = 2;
    public static final int PANEL_PADDING = 0;
    public static final int WIDGET_HEIGHT = 20;
    public static final int WIDGET_AND_GAP_HEIGHT = WIDGET_HEIGHT + PANEL_GAP;
    public static final int WIDGET_WIDTH_SQUARE = 20;

    public AbstractRaisedScreen(Screen parent, int containerWidth, int containerHeight) {
        super(Component.translatable("options.raised.title"));
        this.parent = parent;
        this.containerWidth = containerWidth;
        this.containerHeight = containerHeight;
    }

    public void setSizes() {
        centerX = width / 2;
        centerY = height / 2;
        containerX = centerX - (containerWidth / 2);
        containerY = centerY - (containerHeight / 2);
        panelHeight = containerHeight - (2 * CONTAINER_PADDING);
        panelWidth = containerWidth - (2 * CONTAINER_PADDING);
    }

    @Override
    public void init() {
        super.init();

        setSizes();
        addContent();
        repositionElements();
    }

    public abstract void addContent();

    @Override
    public void onClose() {
        super.onClose();
        if (parent != null) {
            minecraft.gui.setScreen(parent);
        }
    }

    @Override
    public boolean keyPressed(KeyEvent keyEvent) {
        super.keyPressed(keyEvent);
        if (RaisedOptions.OPTIONS.matches(keyEvent)) {
            onClose();
            return true;
        }
        return true;
    }

    @Override
    public void repositionElements() {}

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        setSizes();
    }

}