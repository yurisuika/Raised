package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.config.Config;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.ArrayList;

public class RemoveScreen extends AbstractPopupScreen {

    public AbstractWidget optionCancel;
    public AbstractWidget optionConfirm;
    public int widgetWidthHalf;

    public RemoveScreen(SelectScreen parent) {
        super(parent);
    }

    @Override
    public void setSizes() {
        super.setSizes();

        widgetWidthHalf = (panelWidth - PANEL_GAP) / 2;
    }

    @Override
    public void createOptions() {
        options = new ArrayList<>();

        optionCancel = new Button(
                panelX,
                panelY + WIDGET_AND_GAP_HEIGHT,
                widgetWidthHalf,
                WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.popup.cancel"),
                button -> onClose());
        optionConfirm = new Button(
                panelX + widgetWidthHalf + PANEL_GAP,
                panelY + WIDGET_AND_GAP_HEIGHT,
                widgetWidthHalf,
                WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.popup.confirm"),
                button -> {
                    Config.update(o -> o.getGroups().remove(parent.getCurrentGroup().getGroupName()));
                    parent.resetLeftList();
                    onClose();
                });

        options.add(optionCancel);
        options.add(optionConfirm);

        options.forEach(this::addRenderableWidget);
    };

    @Override
    public Component getPopupTitle() {
        return new TranslatableComponent("options.raised.remove");
    }

}