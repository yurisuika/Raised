package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.gui.components.LayerList;
import dev.yurisuika.raised.mixin.client.OptionInvoker;
import dev.yurisuika.raised.util.Layers;
import dev.yurisuika.raised.util.Parse;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.client.CycleOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.TooltipAccessor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;

public class RaisedScreen extends Screen {

    public Screen lastScreen;
    public ArrayList<AbstractWidget> optionsLayout;
    public LayerList layerList;
    public AbstractWidget displacementX;
    public AbstractWidget displacementY;
    public AbstractWidget directionX;
    public AbstractWidget directionY;
    public AbstractWidget sync;
    public static ResourceLocation current = Layers.HOTBAR;
    public final int SPACING = 5;
    public final int PADDING = 8;
    public final int BUTTON_WIDTH = 150;
    public final int BUTTON_HEIGHT = 20;

    public RaisedScreen(Screen lastScreen) {
        super(new TranslatableComponent("options.raised.title"));
        this.lastScreen = lastScreen;
    }

    @Override
    public void init() {
        addList();
        addOptions();

        repositionElements();
    }

    public void addList() {
        layerList = new LayerList(minecraft, BUTTON_WIDTH + (PADDING * 2), height, this);
        Option.getLayers().forEach((name, layer) -> layerList.add(name));

        addRenderableWidget(layerList);
    }

    public void addOptions() {
        optionsLayout = new ArrayList<>();

        if (Option.getLayer(current.toString()) != null) {
            displacementX = new ProgressOption("options.raised.displacement.x", 0, minecraft.getWindow().getGuiScaledWidth() / 4, 1.0F, options -> (double) Option.getDisplacementX(current.toString()), (options, value) -> Option.setDisplacementX(current.toString(), value.intValue()), (options, option) -> option.get(options) == 0 ? ((OptionInvoker) option).invokeGenericValueLabel(CommonComponents.OPTION_OFF) : ((OptionInvoker) option).invokeGenericValueLabel(new TextComponent(Option.getDisplacementX(current.toString()) + "px (" + Math.round(Math.ceil(((float) option.get(options) / ((float) option.getMaxValue())) * 100)) + "%)")), client -> font.split(new TranslatableComponent("options.raised.displacement.x.tooltip"), 200)).createButton(minecraft.options, PADDING, PADDING + BUTTON_HEIGHT + SPACING, BUTTON_WIDTH);
            displacementY = new ProgressOption("options.raised.displacement.y", 0, minecraft.getWindow().getGuiScaledHeight() / 4, 1.0F, options -> (double) Option.getDisplacementY(current.toString()), (options, value) -> Option.setDisplacementY(current.toString(), value.intValue()), (options, option) -> option.get(options) == 0 ? ((OptionInvoker) option).invokeGenericValueLabel(CommonComponents.OPTION_OFF) : ((OptionInvoker) option).invokeGenericValueLabel(new TextComponent(Option.getDisplacementY(current.toString()) + "px (" + Math.round(Math.ceil(((float) option.get(options) / ((float) option.getMaxValue())) * 100)) + "%)")), client -> font.split(new TranslatableComponent("options.raised.displacement.y.tooltip"), 200)).createButton(minecraft.options, PADDING, PADDING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING, BUTTON_WIDTH);

            directionX = CycleOption.create("options.raised.direction.x", Layer.Direction.X.values(), value -> new TranslatableComponent(value.getKey()), options -> Option.getDirectionX(current.toString()), (options, option, value) -> Option.setDirectionX(current.toString(), value)).setTooltip(client -> value -> font.split(new TranslatableComponent("options.raised.direction.x.tooltip"), 200)).createButton(minecraft.options, PADDING, PADDING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING, BUTTON_WIDTH);
            directionY = CycleOption.create("options.raised.direction.y", Layer.Direction.Y.values(), value -> new TranslatableComponent(value.getKey()), options -> Option.getDirectionY(current.toString()), (options, option, value) -> Option.setDirectionY(current.toString(), value)).setTooltip(client -> value -> font.split(new TranslatableComponent("options.raised.direction.y.tooltip"), 200)).createButton(minecraft.options, PADDING, PADDING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING, BUTTON_WIDTH);

            sync = CycleOption.create("options.raised.sync", Parse.listLoadedNames(), Parse::createLayerDisplay, options -> Option.getSync(current.toString()), (options, option, value) -> Option.setSync(current.toString(), value)).setTooltip(client -> value -> font.split(new TranslatableComponent("options.raised.sync.tooltip", value), 200)).createButton(minecraft.options, PADDING, PADDING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING, BUTTON_WIDTH);

            optionsLayout.add(displacementX);
            optionsLayout.add(displacementY);
            optionsLayout.add(directionX);
            optionsLayout.add(directionY);
            optionsLayout.add(sync);
        }

        optionsLayout.add(new Button(PADDING, height - PADDING - BUTTON_HEIGHT, BUTTON_WIDTH, BUTTON_HEIGHT, CommonComponents.GUI_DONE, button -> onClose()));

        optionsLayout.forEach(this::addRenderableWidget);
    }

    public void resetOptions() {
        optionsLayout.forEach(this::removeWidget);
        optionsLayout.clear();
        addOptions();
        repositionElements();
    }

    public void repositionElements() {
        layerList.updateSize(BUTTON_WIDTH + (PADDING * 2), height, 0, height);
        layerList.setLeftPos(width - (BUTTON_WIDTH + (PADDING * 2)));
    }

    @Override
    public void onClose() {
        super.onClose();
        if (lastScreen != null) {
            minecraft.setScreen(lastScreen);
        }
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        super.resize(minecraft, width, height);

        if (Option.getLayer(current.toString()) != null) {
            if (Option.getDisplacementX(current.toString()) > minecraft.getWindow().getGuiScaledWidth() / 4) {
                Option.setDisplacementX(current.toString(), minecraft.getWindow().getGuiScaledWidth() / 4);
            }
            if (Option.getDisplacementY(current.toString()) > minecraft.getWindow().getGuiScaledHeight() / 4) {
                Option.setDisplacementY(current.toString(), minecraft.getWindow().getGuiScaledHeight() / 4);
            }
        }

        resetOptions();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);

        drawCenteredString(poseStack, font, title, PADDING + (BUTTON_WIDTH / 2), PADDING + 6, 16777215);

        if (Option.getLayer(current.toString()) != null) {
            displacementX.active = Option.getSync(current.toString()).equals(current.toString());
            displacementY.active = Option.getSync(current.toString()).equals(current.toString());
        }

        for (AbstractWidget widget : optionsLayout) {
            if (widget.equals(displacementX) || widget.equals(displacementY) || widget.equals(directionX) || widget.equals(directionY) || widget.equals(sync)) {
                if (widget.isMouseOver(mouseX, mouseY)) {
                    renderTooltip(poseStack, ((TooltipAccessor) widget).getTooltip(), mouseX, mouseY);
                }
            }
        }
    }

    @Override
    public void renderBackground(PoseStack poseStack) {
        fillGradient(poseStack, 0, 0, width, height, -1072689136, -804253680);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        super.keyPressed(keyCode, scanCode, modifiers);
        if (RaisedOptions.OPTIONS.matches(keyCode, scanCode)) {
            onClose();
            return true;
        }
        return true;
    }

}