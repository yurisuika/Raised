package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.config.Config;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

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

        optionCancel = Button.builder(Component.translatable("options.raised.popup.cancel"), button -> onClose())
                .size(widgetWidthHalf, WIDGET_HEIGHT)
                .pos(panelX, panelY + WIDGET_AND_GAP_HEIGHT)
                .build();
        optionConfirm = Button.builder(Component.translatable("options.raised.popup.confirm"), button -> {
                    Config.update(o -> o.getGroups().remove(parent.getCurrentGroup().getGroupName()));
                    parent.resetLeftList();
                    onClose();
                })
                .size(widgetWidthHalf, WIDGET_HEIGHT)
                .pos(panelX + widgetWidthHalf + PANEL_GAP, panelY + WIDGET_AND_GAP_HEIGHT)
                .build();

        options.add(optionCancel);
        options.add(optionConfirm);

        options.forEach(this::addRenderableWidget);
    };

    @Override
    public Component getPopupTitle() {
        return Component.translatable("options.raised.remove");
    }

}