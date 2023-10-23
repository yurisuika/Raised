package dev.yurisuika.raised.client.gui.screen;

import dev.yurisuika.raised.mixin.client.option.OptionInvoker;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ScreenTexts;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.CyclingOption;
import net.minecraft.client.option.DoubleOption;
import net.minecraft.client.util.OrderableTooltip;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;

import java.util.ArrayList;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;

public class RaisedScreen extends Screen {

    ArrayList<ClickableWidget> grid = new ArrayList<>();

    public RaisedScreen(Text title) {
        super(title);
    }

    @Override
    public void init() {
        grid.add(new DoubleOption("options.raised.hud", 0, client.getWindow().getScaledHeight() / 2, 1.0F, gameOptions -> (double)getHud(), (gameOptions, value) -> setHud(value.intValue()), (gameOptions, option) -> option.get(gameOptions) == 0 ? ((OptionInvoker)option).invokeGetGenericLabel(ScreenTexts.OFF) : ((OptionInvoker)option).invokeGetGenericLabel(new LiteralText(String.valueOf((int)option.get(gameOptions)))), client -> client.textRenderer.wrapLines(new TranslatableText("options.raised.hud.tooltip"), 200)).createButton(client.options, 16, 32, 200));
        grid.add(new DoubleOption("options.raised.chat", 0, client.getWindow().getScaledHeight() / 2, 1.0F, gameOptions -> (double)getChat(), (gameOptions, value) -> setChat(value.intValue()), (gameOptions, option) -> option.get(gameOptions) == 0 ? ((OptionInvoker)option).invokeGetGenericLabel(ScreenTexts.OFF) : ((OptionInvoker)option).invokeGetGenericLabel(new LiteralText(String.valueOf((int)option.get(gameOptions)))), client -> client.textRenderer.wrapLines(new TranslatableText("options.raised.chat.tooltip"), 200)).createButton(client.options, 16, 56, 200));
        grid.add(CyclingOption.create("options.raised.support", new TranslatableText("options.raised.support.tooltip"), gameOptions -> getSupport(), (gameOptions, option, value) -> setSupport(value)).createButton(client.options, 16, 80, 98));
        grid.add(CyclingOption.create("options.raised.sync", new TranslatableText("options.raised.sync.tooltip"), gameOptions -> getSync(), (gameOptions, option, value) -> setSync(value)).createButton(client.options, 118, 80, 98));
        for (ClickableWidget option : grid) {
            addDrawableChild(option);
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        drawTextWithShadow(matrices, this.textRenderer, new TranslatableText("options.raised.title"), 16, 16, -1);
        for (ClickableWidget option : grid) {
            if (option != null && option.isMouseOver(mouseX, mouseY)) {
                renderOrderedTooltip(matrices, ((OrderableTooltip)option).getOrderedTooltip(), mouseX, mouseY);
            }
        }
    }

    @Override
    public void renderBackground(MatrixStack matrices) {
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

}