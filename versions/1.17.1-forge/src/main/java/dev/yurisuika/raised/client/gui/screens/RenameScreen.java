package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.config.Config;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class RenameScreen extends AbstractInputPopupScreen {

    public RenameScreen(SelectScreen parent) {
        super(parent);
    }

    @Override
    public String initialValue() {
        return parent.getCurrentGroup().getGroupName();
    }

    @Override
    public void confirmAction() {
        Config.update(o -> o.getGroups().put(optionInput.getValue(), o.getGroups().remove(parent.getCurrentGroup().getGroupName())));
        super.confirmAction();
    }

    @Override
    public Component getPopupTitle() {
        return new TranslatableComponent("options.raised.rename");
    }

}