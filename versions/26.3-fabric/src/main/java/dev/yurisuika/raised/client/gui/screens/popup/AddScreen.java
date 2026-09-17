package dev.yurisuika.raised.client.gui.screens.popup;

import dev.yurisuika.raised.client.gui.group.Groups;
import dev.yurisuika.raised.client.gui.screens.SelectScreen;
import dev.yurisuika.raised.config.Config;
import net.minecraft.network.chat.Component;

public class AddScreen extends AbstractInputPopupScreen {

    public AddScreen(SelectScreen parent) {
        super(parent);
    }

    @Override
    public String initialValue() {
        return "";
    }

    @Override
    public void confirmAction() {
        Config.update(o -> o.getGroups().putIfAbsent(optionInput.getValue(), Groups.createDefaultGroup()));
        super.confirmAction();
    }

    @Override
    public Component getPopupTitle() {
        return Component.translatable("options.raised.add");
    }

}