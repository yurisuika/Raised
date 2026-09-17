package dev.yurisuika.raised.client.gui.screens.popup;

import dev.yurisuika.raised.client.gui.screens.AbstractScreen;
import dev.yurisuika.raised.client.gui.screens.SelectScreen;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.option.Settings;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;

public class SettingsScreen extends AbstractPopupScreen {

    public AbstractWidget optionHotbarSelectionFix;

    public SettingsScreen(SelectScreen parent) {
        super(parent, 164, 56);
    }

    @Override
    public void addOptions() {
        options = new ArrayList<>();

        optionHotbarSelectionFix = CycleButton.builder(Settings.HotbarSelectionFix::caption)
                .withInitialValue(Config.getOptions().getSettings().getHotbarSelectionFix())
                .withValues(Settings.HotbarSelectionFix.values())
                .withTooltip(value -> Tooltip.create(Component.translatable("options.raised.hotbar_selection_fix." + value.getSerializedName() + ".tooltip")))
                .create(panelX,
                        panelY + AbstractScreen.WIDGET_AND_GAP_HEIGHT,
                        panelWidth,
                        AbstractScreen.WIDGET_HEIGHT,
                        Component.translatable("options.raised.hotbar_selection_fix"),
                        (button, value) -> Config.update(o -> o.getSettings().setHotbarSelectionFix(value)));

        options.add(optionHotbarSelectionFix);

        options.forEach(this::addRenderableWidget);
    }

    @Override
    public Component getPopupTitle() {
        return Component.translatable("options.raised.settings");
    }

}