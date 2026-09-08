package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.util.Configure;
import net.minecraft.network.chat.Component;

public class RenameScreen extends AbstractInputPopupScreen {

    public RenameScreen(SelectScreen parent) {
        super(parent);
    }

    @Override
    public void confirmAction() {
        Configure.Groups.renameGroup(parent.getCurrentGroup().getGroupName(), optionInput.getValue());
        super.confirmAction();
    }

    @Override
    public Component getPopupTitle() {
        return Component.translatable("options.raised.rename");
    }

}