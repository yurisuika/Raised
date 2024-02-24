package dev.yurisuika.raised.client.gui.screen;

import dev.yurisuika.raised.client.option.RaisedConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static dev.yurisuika.raised.client.option.RaisedKeyBinding.*;

@Environment(EnvType.CLIENT)
public abstract class RaisedScreen extends Screen {

    public GridWidget gridWidget;
    public GridWidget.Adder adder;
    public CheckboxWidget checkbox;
    public ClickableWidget support;
    public ClickableWidget sync;
    public ClickableWidget share;

    public RaisedScreen(Text title) {
        super(title);
    }

    public void setScreenType() {
        if (client.currentScreen instanceof SliderScreen) {
            client.setScreen(new TextScreen(Text.translatable("options.raised.title")));
        } else if (client.currentScreen instanceof TextScreen) {
            client.setScreen(new SliderScreen(Text.translatable("options.raised.title")));
        }
    }

    @Override
    public void init() {
        gridWidget = new GridWidget();
        gridWidget.getMainPositioner().alignHorizontalCenter().margin(2, 0, 2, 4);
        adder = gridWidget.createAdder(2);

        checkbox = CheckboxWidget.builder(Text.translatable("options.raised.checkbox").withColor(-1).formatted(Formatting.WHITE), textRenderer).option(SimpleOption.ofBoolean("options.raised.checkbox", SimpleOption.emptyTooltip(), client.currentScreen instanceof TextScreen, value -> setScreenType())).tooltip(Tooltip.of(Text.translatable("options.raised.checkbox.tooltip"))).build();
        checkbox.setWidth(200);
        support = SimpleOption.ofBoolean("options.raised.support", SimpleOption.constantTooltip(Text.translatable("options.raised.support.tooltip")), getSupport(), RaisedConfig::setSupport).createWidget(client.options, 0, 0, 98);
        sync = SimpleOption.ofBoolean("options.raised.sync", SimpleOption.constantTooltip(Text.translatable("options.raised.sync.tooltip")), getSync(), RaisedConfig::setSync).createWidget(client.options, 0, 0, 98);
        share = SimpleOption.ofBoolean("options.raised.share", SimpleOption.constantTooltip(Text.translatable("options.raised.share.tooltip")), getShare(), RaisedConfig::setShare).createWidget(client.options, 0, 0, 200);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
        if (options.matchesKey(keyCode, scanCode)) {
            close();
            return true;
        }
        return true;
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    public static class SliderScreen extends RaisedScreen {

        public ClickableWidget hud;
        public ClickableWidget chat;

        public SliderScreen(Text title) {
            super(title);
        }

        @Override
        public void init() {
            super.init();

            hud = new SimpleOption<>("options.raised.hud", SimpleOption.constantTooltip(Text.translatable("options.raised.hud.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(String.valueOf(value))), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledHeight() / 4), getHud(), RaisedConfig::setHud).createWidget(client.options, 0, 0, 200);
            chat = new SimpleOption<>("options.raised.chat", SimpleOption.constantTooltip(Text.translatable("options.raised.chat.tooltip")), (prefix, value) -> value == 0 ? GameOptions.getGenericValueText(prefix, ScreenTexts.OFF) : GameOptions.getGenericValueText(prefix, Text.literal(String.valueOf(value))), new SimpleOption.ValidatingIntSliderCallbacks(0, client.getWindow().getScaledHeight() / 4), getChat(), RaisedConfig::setChat).createWidget(client.options, 0, 0, 200);

            adder.add(checkbox, 2);
            adder.add(hud, 2);
            adder.add(chat, 2);
            adder.add(support);
            adder.add(sync);
            adder.add(share, 2);

            gridWidget.refreshPositions();
            SimplePositioningWidget.setPos(gridWidget, 0, 32, width, height, 0.5F, 0.0F);
            gridWidget.forEachChild(this::addDrawableChild);
        }

        @Override
        public void render(DrawContext context, int mouseX, int mouseY, float delta) {
            int max = client.getWindow().getScaledHeight() / 4;
            boolean changed = false;

            if (getHud() > max) {
                setHud(max);
                changed = true;
            }
            if (getChat() > max) {
                setChat(max);
                changed = true;
            }
            if (changed) {
                client.setScreen(new SliderScreen(Text.translatable("options.raised.title")));
            }

            super.render(context, mouseX, mouseY, delta);
        }

        @Override
        public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
            context.fillGradient((width / 2) - (((gridWidget.getWidth() - 4) / 2) + 8), 24, (width / 2) + (((gridWidget.getWidth() - 4) / 2) + 8), gridWidget.getHeight() + 24 + 8 + 4, -1072689136, -804253680);
        }

    }

    public static class TextScreen extends RaisedScreen {

        public TextFieldWidget hud;
        public TextFieldWidget chat;

        public TextScreen(Text title) {
            super(title);
        }

        @Override
        public void init() {
            super.init();

            hud = new TextFieldWidget(textRenderer, 50, 20, Text.translatable("options.raised.hud"));
            hud.setTooltip(Tooltip.of(Text.translatable("options.raised.hud.tooltip")));
            hud.setPlaceholder(Text.of(String.valueOf(0)));
            hud.setText(String.valueOf(getHud()));
            hud.setMaxLength(7);
            hud.setChangedListener(value -> {
                if (value.matches("[0-9]+") || value.isEmpty()) {
                    setHud(Integer.parseInt(value.isEmpty() ? "0" : value));
                }
            });
            chat = new TextFieldWidget(textRenderer, 50, 20, Text.translatable("options.raised.chat"));
            chat.setTooltip(Tooltip.of(Text.translatable("options.raised.chat.tooltip")));
            chat.setPlaceholder(Text.of(String.valueOf(0)));
            chat.setText(String.valueOf(getChat()));
            chat.setMaxLength(7);
            chat.setChangedListener(value -> {
                if (value.matches("[0-9]+") || value.isEmpty()) {
                    setChat(Integer.parseInt(value.isEmpty() ? "0" : value));
                }
            });

            adder.add(checkbox, 2);
            adder.add(hud, 2);
            adder.add(chat, 2);
            adder.add(support);
            adder.add(sync);
            adder.add(share, 2);

            gridWidget.refreshPositions();
            SimplePositioningWidget.setPos(gridWidget, 0, 32, width, height, 0.5F, 0.0F);
            gridWidget.forEachChild(this::addDrawableChild);
        }

        @Override
        public void renderBackground(DrawContext context, int mouseX, int mouseY, float delta) {
            context.fillGradient((width / 2) - (((gridWidget.getWidth() - 4) / 2) + 8), 24, (width / 2) + (((gridWidget.getWidth() - 4) / 2) + 8), gridWidget.getHeight() + 24 + 8 + 4, -1072689136, -804253680);
        }

    }

}