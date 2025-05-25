package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.Codec;
import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.gui.components.LayerList;
import dev.yurisuika.raised.util.Layers;
import dev.yurisuika.raised.util.Parse;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.options.Layer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CenteredStringWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;

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
        super(Component.translatable("options.raised.title"));
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

        optionsLayout.add(new CenteredStringWidget(PADDING, PADDING, BUTTON_WIDTH, BUTTON_HEIGHT, title, font));

        if (Option.getLayer(current.toString()) != null) {
            displacementX = new OptionInstance<>("options.raised.displacement.x", OptionInstance.cachedConstantTooltip(Component.translatable("options.raised.displacement.x.tooltip")), (prefix, value) -> value == 0 ? Options.genericValueLabel(prefix, CommonComponents.OPTION_OFF) : Options.genericValueLabel(prefix, Component.literal(Option.getDisplacementX(current.toString()) + "px (" + Math.round(Math.ceil((value.floatValue() / ((float) minecraft.getWindow().getGuiScaledWidth() / 4)) * 100)) + "%)")), new OptionInstance.IntRange(0, minecraft.getWindow().getGuiScaledWidth() / 4), Option.getDisplacementX(current.toString()), value -> Option.setDisplacementX(current.toString(), value)).createButton(minecraft.options, PADDING, PADDING + BUTTON_HEIGHT + SPACING, BUTTON_WIDTH);
            displacementY = new OptionInstance<>("options.raised.displacement.y", OptionInstance.cachedConstantTooltip(Component.translatable("options.raised.displacement.y.tooltip")), (prefix, value) -> value == 0 ? Options.genericValueLabel(prefix, CommonComponents.OPTION_OFF) : Options.genericValueLabel(prefix, Component.literal(Option.getDisplacementY(current.toString()) + "px (" + Math.round(Math.ceil((value.floatValue() / ((float) minecraft.getWindow().getGuiScaledHeight() / 4)) * 100)) + "%)")), new OptionInstance.IntRange(0, minecraft.getWindow().getGuiScaledHeight() / 4), Option.getDisplacementY(current.toString()), value -> Option.setDisplacementY(current.toString(), value)).createButton(minecraft.options, PADDING, PADDING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING, BUTTON_WIDTH);

            directionX = new OptionInstance<>("options.raised.direction.x", value -> Tooltip.create(Component.translatable("options.raised.direction.x.tooltip")), OptionInstance.forOptionEnum(), new OptionInstance.Enum<>(Arrays.asList(Layer.Direction.X.values()), Codec.INT.xmap(Layer.Direction.X::byId, Layer.Direction.X::getId)), Layer.Direction.X.byName(Option.getDirectionX(current.toString()).getSerializedName()), value -> Option.setDirectionX(current.toString(), value)).createButton(minecraft.options, PADDING, PADDING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING, BUTTON_WIDTH);
            directionY = new OptionInstance<>("options.raised.direction.y", value -> Tooltip.create(Component.translatable("options.raised.direction.y.tooltip")), OptionInstance.forOptionEnum(), new OptionInstance.Enum<>(Arrays.asList(Layer.Direction.Y.values()), Codec.INT.xmap(Layer.Direction.Y::byId, Layer.Direction.Y::getId)), Layer.Direction.Y.byName(Option.getDirectionY(current.toString()).getSerializedName()), value -> Option.setDirectionY(current.toString(), value)).createButton(minecraft.options, PADDING, PADDING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING, BUTTON_WIDTH);

            sync = new OptionInstance<>("options.raised.sync", value -> Tooltip.create(Component.translatable("options.raised.sync.tooltip", value)), (prefix, value) -> Parse.createLayerDisplay(value), new OptionInstance.Enum<>(Parse.listLoadedNames(), Codec.STRING), Option.getSync(current.toString()), value -> Option.setSync(current.toString(), value)).createButton(minecraft.options, PADDING, PADDING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING + BUTTON_HEIGHT + SPACING, BUTTON_WIDTH);

            optionsLayout.add(displacementX);
            optionsLayout.add(displacementY);
            optionsLayout.add(directionX);
            optionsLayout.add(directionY);
            optionsLayout.add(sync);
        }

        optionsLayout.add(Button.builder(CommonComponents.GUI_DONE, button -> onClose()).width(BUTTON_WIDTH).pos(PADDING, height - PADDING - BUTTON_HEIGHT).build());

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

        if (Option.getLayer(current.toString()) != null) {
            displacementX.active = Option.getSync(current.toString()).equals(current.toString());
            displacementY.active = Option.getSync(current.toString()).equals(current.toString());
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