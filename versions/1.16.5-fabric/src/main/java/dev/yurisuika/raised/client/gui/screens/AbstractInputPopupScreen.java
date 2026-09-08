package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.util.Configure;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.ArrayList;

public abstract class AbstractInputPopupScreen extends AbstractPopupScreen {

    public SelectScreen parent;
    public EditBox optionInput;
    public AbstractWidget optionConfirm;
    public int widgetWidthInput;
    public int widgetWidthConfirm;

    public AbstractInputPopupScreen(SelectScreen parent) {
        super(parent);
        this.parent = parent;
    }

    @Override
    public void setSizes() {
        super.setSizes();

        widgetWidthInput = 100;
        widgetWidthConfirm = panelWidth - PANEL_GAP - widgetWidthInput;
    }

    @Override
    public void addContent() {
        createControls();
        createOptions();
    }

    @Override
    public void createOptions() {
        options = new ArrayList<>();

        optionInput = new EditBox(font, panelX, panelY + WIDGET_AND_GAP_HEIGHT, widgetWidthInput, WIDGET_HEIGHT, new TranslatableComponent("options.raised.popup.input"));
        optionConfirm = new Button(
                panelX + widgetWidthInput + PANEL_GAP,
                panelY + WIDGET_AND_GAP_HEIGHT,
                widgetWidthConfirm,
                WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.popup.confirm"),
                button -> confirmAction(),
                (minecraft, poseStack, mouseX, mouseY) -> {
                    if (Configure.Groups.getGroups().containsKey(optionInput.getValue())) {
                        renderTooltip(poseStack, font.split(new TranslatableComponent("options.raised.popup.submit.tooltip", optionInput.getValue()), 200), mouseX, mouseY);
                    }
                });

        options.add(optionInput);
        options.add(optionConfirm);

        options.forEach(this::addButton);
    }

    public void confirmAction() {
        parent.resetLeftList();
        onClose();
    }

    @Override
    public void tick() {
        super.tick();

        boolean exists = Configure.Groups.getGroups().containsKey(optionInput.getValue());
        optionConfirm.active = !(optionInput.getValue().isEmpty() || exists);
    }

}