package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.client.gui.components.LayerList;
import dev.yurisuika.raised.client.gui.Scissor;
import dev.yurisuika.raised.mixin.client.OptionInvoker;
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

import java.util.*;
import java.util.stream.Collectors;

public class RaisedScreen extends Screen {

    public Screen lastScreen;
    public ArrayList<AbstractWidget> options;
    public ArrayList<AbstractWidget> controls;
    public LayerList list;
    public AbstractWidget displacementX;
    public AbstractWidget displacementY;
    public AbstractWidget directionX;
    public AbstractWidget directionY;
    public AbstractWidget sync;
    public static ResourceLocation current = Layers.HOTBAR;
    public final int SPACING = 5;
    public final int PADDING = 8;
    public final int WIDGET_WIDTH_WIDE = 150;
    public final int WIDGET_WIDTH_SQUARE = 20;
    public final int WIDGET_HEIGHT = 20;
    public final int HEADER_HEIGHT = PADDING + WIDGET_HEIGHT + PADDING;
    public final int PANEL_WIDTH = PADDING + WIDGET_WIDTH_WIDE + PADDING;

    public RaisedScreen(Screen lastScreen) {
        super(new TranslatableComponent("options.raised.title"));
        this.lastScreen = lastScreen;
    }

    @Override
    public void init() {
        addOptions();
        addControls();
        addList();

        repositionElements();
    }

    public void addOptions() {
        options = new ArrayList<>();

        displacementX = new ProgressOption("options.raised.displacement.x", 0.0D, (double) minecraft.getWindow().getGuiScaledWidth() / 4, 1.0F, options -> (double) Option.getDisplacementX(current.toString()), (options, value) -> Option.setDisplacementX(current.toString(), value.intValue()), (options, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.displacement.x.tooltip"), 200));
            return option.get(options) == 0 ? ((OptionInvoker) option).invokeGenericValueLabel(CommonComponents.OPTION_OFF) : ((OptionInvoker) option).invokeGenericValueLabel(new TextComponent(Option.getDisplacementX(current.toString()) + "px (" + Math.round(Math.ceil(((float) option.get(options) / ((float) option.getMaxValue())) * 100)) + "%)"));
        }).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING, WIDGET_WIDTH_WIDE);
        displacementY = new ProgressOption("options.raised.displacement.y", 0.0D, (double) minecraft.getWindow().getGuiScaledHeight() / 4, 1.0F, options -> (double) Option.getDisplacementY(current.toString()), (options, value) -> Option.setDisplacementY(current.toString(), value.intValue()), (options, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.displacement.y.tooltip"), 200));
            return option.get(options) == 0 ? ((OptionInvoker) option).invokeGenericValueLabel(CommonComponents.OPTION_OFF) : ((OptionInvoker) option).invokeGenericValueLabel(new TextComponent(Option.getDisplacementY(current.toString()) + "px (" + Math.round(Math.ceil(((float) option.get(options) / ((float) option.getMaxValue())) * 100)) + "%)"));
        }).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING), WIDGET_WIDTH_WIDE);
        directionX = new CycleOption("options.raised.direction.x", (options, integer) -> Option.setDirectionX(current.toString(), Layer.Direction.X.byId(Option.getDirectionX(current.toString()).getId() < Layer.Direction.X.values().length - 1 ? Option.getDirectionX(current.toString()).getId() + 1 : 0)), (options, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.direction.x.tooltip"), 200));
            return ((OptionInvoker) option).invokeGenericValueLabel(new TranslatableComponent(Option.getDirectionX(current.toString()).getKey()));
        }).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 2, WIDGET_WIDTH_WIDE);
        directionY = new CycleOption("options.raised.direction.y", (options, integer) -> Option.setDirectionY(current.toString(), Layer.Direction.Y.byId(Option.getDirectionY(current.toString()).getId() < Layer.Direction.Y.values().length - 1 ? Option.getDirectionX(current.toString()).getId() + 1 : 0)), (options, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.direction.y.tooltip"), 200));
            return ((OptionInvoker) option).invokeGenericValueLabel(new TranslatableComponent(Option.getDirectionY(current.toString()).getKey()));
        }).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 3, WIDGET_WIDTH_WIDE);
        sync = new CycleOption("options.raised.sync", (options, integer) -> {
            List<ResourceLocation> names = Layers.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).collect(Collectors.toList());
            int index = names.indexOf(ResourceLocation.tryParse(Option.getSync(current.toString())));
            Option.setSync(current.toString(), names.get(index < names.size() - 1 ? index + 1 : 0).toString());
        }, (options, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.sync.tooltip", Option.getSync(current.toString())), 200));
            return ((OptionInvoker) option).invokeGenericValueLabel(Parse.createLayerDisplay(ResourceLocation.tryParse(Option.getSync(current.toString()))));
        }).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 4, WIDGET_WIDTH_WIDE);

        options.add(displacementX);
        options.add(displacementY);
        options.add(directionX);
        options.add(directionY);
        options.add(sync);

        options.forEach(this::addButton);
    }

    public void addControls() {
        controls = new ArrayList<>();

        controls.add(new Button(width - (PADDING + WIDGET_WIDTH_WIDE), PADDING, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT, new TextComponent("<"), button -> getPrevious()));
        controls.add(new Button(width - (PADDING + WIDGET_WIDTH_SQUARE), PADDING, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT, new TextComponent(">"), button -> getNext()));
        controls.add(new Button(PADDING, height - (PADDING + WIDGET_HEIGHT), WIDGET_WIDTH_WIDE, WIDGET_HEIGHT, CommonComponents.GUI_DONE, button -> onClose()));

        controls.forEach(this::addButton);
    }

    public void addList() {
        list = new LayerList(minecraft, PANEL_WIDTH, height - HEADER_HEIGHT, this);
        list.setList();

        addWidget(list);
    }

    public void getPrevious() {
        Set<String> mods = new HashSet<>();
        Layers.LAYERS.keySet().forEach(key -> mods.add(key.getNamespace()));
        List<String> keys = mods.stream().collect(Collectors.toList());
        int index = keys.indexOf(current.getNamespace()) - 1;

        String mod;
        if (index < 0) {
            mod = keys.get(keys.size() - 1);
        } else {
            mod = keys.get(index);
        }

        setMod(mod);
    }

    public void getNext() {
        Set<String> mods = new HashSet<>();
        Layers.LAYERS.keySet().forEach(key -> mods.add(key.getNamespace()));
        List<String> keys = mods.stream().collect(Collectors.toList());
        int index = keys.indexOf(current.getNamespace()) + 1;

        String mod;
        if (index >= keys.size()) {
            mod = keys.get(0);
        } else {
            mod = keys.get(index);
        }

        setMod(mod);
    }

    public void setMod(String mod) {
        RaisedScreen.current = Layers.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).filter(name -> name.getNamespace().equals(mod)).collect(Collectors.toList()).get(0);

        resetOptions();
        resetList();
        resetControls();
    }

    public void resetOptions() {
        options.forEach(widget -> {
            buttons.remove(widget);
            children.remove(widget);
        });
        options.clear();
        addOptions();
    }

    public void resetControls() {
        controls.forEach(widget -> {
            buttons.remove(widget);
            children.remove(widget);
        });
        controls.clear();
        addControls();
    }

    public void resetList() {
        list.children().clear();
        list.setList();
    }

    public void repositionElements() {
        list.updateSize(PANEL_WIDTH, height - HEADER_HEIGHT, HEADER_HEIGHT, height);
        list.setLeftPos(width - PANEL_WIDTH);
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
            if (Option.getDisplacementX(current.toString()) > width / 4) {
                Option.setDisplacementX(current.toString(), width / 4);
            }
            if (Option.getDisplacementY(current.toString()) > height / 4) {
                Option.setDisplacementY(current.toString(), height / 4);
            }
        }

        resetOptions();
        resetControls();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(poseStack);
        list.render(poseStack, mouseX, mouseY, partialTick);
        super.render(poseStack, mouseX, mouseY, partialTick);

        fill(poseStack, 0, 0, width, (PADDING + WIDGET_HEIGHT + PADDING), 1073741824);

        drawCenteredString(poseStack, font, title, PADDING + (WIDGET_WIDTH_WIDE / 2), PADDING + 6, -1);

        Scissor.renderScrollingString(poseStack, font, new TextComponent(Parse.parseNamespace(current)), width - (PADDING + WIDGET_WIDTH_WIDE - WIDGET_WIDTH_SQUARE), PADDING, width - (PADDING + WIDGET_WIDTH_SQUARE), PADDING + WIDGET_HEIGHT, -1);

        if (Option.getLayer(current.toString()) != null) {
            displacementX.active = Option.getSync(current.toString()).equals(current.toString());
            displacementY.active = Option.getSync(current.toString()).equals(current.toString());
        }

        for (AbstractWidget widget : options) {
            if (widget.equals(displacementX) || widget.equals(displacementY) || widget.equals(directionX) || widget.equals(directionY) || widget.equals(sync)) {
                if (widget.isMouseOver(mouseX, mouseY)) {
                    renderTooltip(poseStack, ((TooltipAccessor) widget).getTooltip().orElse(null), mouseX, mouseY);
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