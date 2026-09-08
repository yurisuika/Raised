package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.client.gui.group.Group;
import dev.yurisuika.raised.util.Configure;
import net.minecraft.network.chat.Component;

import java.util.TreeSet;

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
        Configure.Groups.addGroup(optionInput.getValue(), new Group(new Group.Offset(0, 0), new TreeSet<>()));
        super.confirmAction();
    }

    @Override
    public Component getPopupTitle() {
        return Component.translatable("options.raised.add");
    }

}