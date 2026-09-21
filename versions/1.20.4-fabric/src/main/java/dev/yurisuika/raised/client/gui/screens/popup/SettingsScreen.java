package dev.yurisuika.raised.client.gui.screens.popup;

import dev.yurisuika.raised.client.gui.screens.SelectScreen;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.option.Settings;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;

public class SettingsScreen extends AbstractPopupScreen {

    public AbstractWidget optionSelectionIndicator;

    public SettingsScreen(SelectScreen parent) {
        super(parent, 164, 56);
    }

    @Override
    public void addOptions() {
        options = new ArrayList<>();

        optionSelectionIndicator = CycleButton.builder(Settings.SelectionIndicator::caption)
                .withInitialValue(Config.getOptions().getSettings().getSelectionIndicator())
                .withValues(Settings.SelectionIndicator.values())
                .withTooltip(value -> Tooltip.create(Component.translatable("options.raised.selection_indicator." + value.getSerializedName() + ".tooltip")))
                .create(panelX,
                        panelY + WIDGET_AND_GAP_HEIGHT,
                        panelWidth,
                        WIDGET_HEIGHT,
                        Component.translatable("options.raised.selection_indicator"),
                        (button, value) -> Config.update(o -> o.getSettings().setSelectionIndicator(value)));

        options.add(optionSelectionIndicator);

        options.forEach(this::addRenderableWidget);
    }

    @Override
    public Component getPopupTitle() {
        return Component.translatable("options.raised.settings");
    }

}