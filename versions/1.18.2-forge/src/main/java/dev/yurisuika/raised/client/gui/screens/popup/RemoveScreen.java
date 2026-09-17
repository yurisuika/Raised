package dev.yurisuika.raised.client.gui.screens.popup;

import dev.yurisuika.raised.client.gui.screens.AbstractScreen;
import dev.yurisuika.raised.client.gui.screens.SelectScreen;
import dev.yurisuika.raised.config.Config;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.ArrayList;

public class RemoveScreen extends AbstractPopupScreen {

    public AbstractWidget optionConfirm;

    public RemoveScreen(SelectScreen parent) {
        super(parent, 114, 56);
    }

    @Override
    public void addOptions() {
        options = new ArrayList<>();

        optionConfirm = new Button(
                panelX,
                panelY + AbstractScreen.WIDGET_AND_GAP_HEIGHT,
                panelWidth,
                AbstractScreen.WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.popup.confirm"),
                button -> {
                    Config.update(o -> o.getGroups().remove(parent.getCurrentGroup().getGroupName()));
                    parent.resetLists();
                    onClose();
                });

        options.add(optionConfirm);

        options.forEach(this::addRenderableWidget);
    };

    @Override
    public Component getPopupTitle() {
        return new TranslatableComponent("options.raised.remove");
    }

}