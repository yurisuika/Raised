package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.gui.GuiComponentInterface;
import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.components.SpacedSelectionList;
import dev.yurisuika.raised.mixin.minecraft.client.gui.components.SliderButtonAccessor;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import dev.yurisuika.raised.util.Icon;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.client.CycleOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ProgressOption;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionButton;
import net.minecraft.client.gui.components.SliderButton;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

import java.util.*;
import java.util.stream.Collectors;

public class RaisedScreen extends Screen {

    public Screen parent;
    public ArrayList<AbstractWidget> options;
    public ArrayList<AbstractWidget> controls;
    public LayerList layers;
    public static ResourceLocation current = LayerRegistry.HOTBAR;
    public final int SPACING = 5;
    public final int PADDING = 8;
    public final int WIDGET_WIDTH_WIDE = 150;
    public final int WIDGET_WIDTH_SQUARE = 20;
    public final int WIDGET_HEIGHT = 20;
    public final int HEADER_HEIGHT = PADDING + WIDGET_HEIGHT + PADDING;
    public final int PANEL_WIDTH = PADDING + WIDGET_WIDTH_WIDE + PADDING;

    public RaisedScreen(Screen parent) {
        super(new TranslatableComponent("options.raised.title"));
        this.parent = parent;
    }

    @Override
    public void init() {
        addOptions();
        addControls();
        addLayers();

        repositionElements();
    }

    public void addOptions() {
        options = new ArrayList<>();

        options.add(new ProgressOption("options.raised.displacement.x", 0.0D, (double) width / 4, 1.0F, options -> (double) Configure.getDisplacementX(current.toString()), (options, value) -> Configure.setDisplacementX(current.toString(), value.intValue()), (options, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.displacement.x.tooltip"), 200));
            return new TranslatableComponent("options.raised.displacement.x").append(": ").append(option.get(options) == 0 ? CommonComponents.OPTION_OFF : new TextComponent(Configure.getDisplacementX(current.toString()) + "px (" + Math.round(Math.ceil(((float) option.get(options) / ((float) option.getMaxValue())) * 100)) + "%)"));
        }).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING, WIDGET_WIDTH_WIDE));
        options.add(new ProgressOption("options.raised.displacement.y", 0.0D, (double) height / 4, 1.0F, options -> (double) Configure.getDisplacementY(current.toString()), (options, value) -> Configure.setDisplacementY(current.toString(), value.intValue()), (options, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.displacement.y.tooltip"), 200));
            return new TranslatableComponent("options.raised.displacement.y").append(": ").append(option.get(options) == 0 ? CommonComponents.OPTION_OFF : new TextComponent(Configure.getDisplacementY(current.toString()) + "px (" + Math.round(Math.ceil(((float) option.get(options) / ((float) option.getMaxValue())) * 100)) + "%)"));
        }).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING), WIDGET_WIDTH_WIDE));
        options.add(new CycleOption("options.raised.direction.x", (options, integer) -> Configure.setDirectionX(current.toString(), Layer.Direction.X.byId(Configure.getDirectionX(current.toString()).getId() + integer)), (options, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.direction.x.tooltip"), 200));
            return new TranslatableComponent("options.raised.direction.x").append(": ").append(Configure.getDirectionX(current.toString()).caption());
        }).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 2, WIDGET_WIDTH_WIDE));
        options.add(new CycleOption("options.raised.direction.y", (options, integer) -> Configure.setDirectionY(current.toString(), Layer.Direction.Y.byId(Configure.getDirectionY(current.toString()).getId() + integer)), (options, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.direction.y.tooltip"), 200));
            return new TranslatableComponent("options.raised.direction.y").append(": ").append(Configure.getDirectionY(current.toString()).caption());
        }).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 3, WIDGET_WIDTH_WIDE));
        options.add(new CycleOption("options.raised.sync", (options, integer) -> {
            List<ResourceLocation> names = LayerRegistry.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).collect(Collectors.toList());
            int index = names.indexOf(ResourceLocation.tryParse(Configure.getSync(current.toString())));
            Configure.setSync(current.toString(), names.get(index < names.size() - 1 ? index + 1 : 0).toString());
        }, (options, option) -> {
            option.setTooltip(font.split(new TranslatableComponent("options.raised.sync.tooltip", Configure.getSync(current.toString())), 200));
            return new TranslatableComponent("options.raised.sync").append(": ").append(Parse.parsePath(ResourceLocation.tryParse(Configure.getSync(current.toString()))));
        }).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 4, WIDGET_WIDTH_WIDE));

        options.forEach(this::addButton);
    }

    public void addControls() {
        controls = new ArrayList<>();

        controls.add(new Button(width - (PADDING + WIDGET_WIDTH_WIDE), PADDING, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT, new TextComponent("<"), button -> getPrevious()));
        controls.add(new Button(width - (PADDING + WIDGET_WIDTH_SQUARE), PADDING, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT, new TextComponent(">"), button -> getNext()));
        controls.add(new Button(PADDING, height - (PADDING + WIDGET_HEIGHT), WIDGET_WIDTH_WIDE, WIDGET_HEIGHT, CommonComponents.GUI_DONE, button -> onClose()));

        controls.forEach(this::addButton);
    }

    public void addLayers() {
        layers = new LayerList(minecraft, PANEL_WIDTH, height - HEADER_HEIGHT);

        addWidget(layers);
    }

    public void getPrevious() {
        Set<String> mods = new HashSet<>();
        LayerRegistry.LAYERS.keySet().forEach(key -> mods.add(key.getNamespace()));
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
        LayerRegistry.LAYERS.keySet().forEach(key -> mods.add(key.getNamespace()));
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
        RaisedScreen.current = LayerRegistry.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).filter(name -> name.getNamespace().equals(mod)).collect(Collectors.toList()).get(0);

        resetOptions();
        resetLayers();
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

    public void resetLayers() {
        layers.setScrollAmount(0.0F);
        layers.setLayers();
    }

    public void repositionElements() {
        layers.updateSize(PANEL_WIDTH, height - HEADER_HEIGHT, HEADER_HEIGHT, height);
        layers.setLeftPos(width - PANEL_WIDTH);
    }

    @Override
    public void onClose() {
        super.onClose();
        if (parent != null) {
            minecraft.setScreen(parent);
        }
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        super.resize(minecraft, width, height);

        if (Configure.getLayer(current.toString()) != null) {
            if (Configure.getDisplacementX(current.toString()) > width / 4) {
                Configure.setDisplacementX(current.toString(), width / 4);
            }
            if (Configure.getDisplacementY(current.toString()) > height / 4) {
                Configure.setDisplacementY(current.toString(), height / 4);
            }
        }

        resetOptions();
        resetControls();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(poseStack);
        layers.render(poseStack, mouseX, mouseY, partialTick);
        super.render(poseStack, mouseX, mouseY, partialTick);

        fill(poseStack, 0, 0, width, HEADER_HEIGHT, 1073741824);

        font.drawShadow(poseStack, title.getString(), (float) ((PADDING + (WIDGET_WIDTH_WIDE / 2)) - font.width(title.getString()) / 2), (float) PADDING + 6, -1);

        ((GuiComponentInterface) this).renderScrollingString(poseStack, font, new TextComponent(Parse.parseNamespace(current)), width - (PADDING + WIDGET_WIDTH_WIDE - WIDGET_WIDTH_SQUARE), PADDING, width - (PADDING + WIDGET_WIDTH_SQUARE), PADDING + WIDGET_HEIGHT, -1);

        options.forEach(widget -> {
            if (widget.isMouseOver(mouseX, mouseY)) {
                if (widget instanceof OptionButton) {
                    renderTooltip(poseStack, ((OptionButton) widget).getOption().getTooltip().get(), mouseX, mouseY);
                } else if (widget instanceof SliderButton) {
                    renderTooltip(poseStack, ((SliderButtonAccessor) widget).getOption().getTooltip().get(), mouseX, mouseY);
                }
            }
        });
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

    public class LayerList extends SpacedSelectionList<LayerList.Entry> {

        public LayerList(Minecraft minecraft, int width, int height) {
            super(minecraft, width, height, HEADER_HEIGHT, RaisedScreen.this.height, WIDGET_HEIGHT, PADDING);
            setLayers();
        }

        public void setLayers() {
            clearEntries();
            LayerRegistry.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).filter(location -> location.getNamespace().equals(RaisedScreen.current.getNamespace())).forEach(name -> addEntry(new Entry(name)));
        }

        @Override
        public boolean isFocused() {
            return RaisedScreen.this.getFocused() == this;
        }

        public class Entry extends SpacedSelectionList.Entry<Entry> {

            public final ResourceLocation name;

            public Entry(ResourceLocation name) {
                this.name = name;
                setCurrent(RaisedScreen.current.equals(name));
            }

            @Override
            public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                ((GuiComponentInterface) RaisedScreen.this).renderScrollingString(poseStack, Minecraft.getInstance().font, new TextComponent(Parse.parsePath(name)), left + WIDGET_WIDTH_SQUARE, top, left + (WIDGET_WIDTH_WIDE - WIDGET_WIDTH_SQUARE), top + WIDGET_HEIGHT, -1);

                Minecraft.getInstance().getTextureManager().bind(Icon.getLayerIcon(name));
                blit(poseStack, left, top, 0, 0, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT);

                Minecraft.getInstance().getTextureManager().bind(new ResourceLocation("raised", "textures/gui/direction/" + Configure.getDirectionX(name.toString()).toString().toLowerCase() + "_" + Configure.getDirectionY(name.toString()).toString().toLowerCase() + ".png"));
                blit(poseStack, left + (WIDGET_WIDTH_WIDE - WIDGET_WIDTH_SQUARE), top, 0, 0, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT);
            }

            @Override
            public boolean mouseClicked(double mouseX, double mouseY, int button) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                setCurrent(true);
                return true;
            }

            public void setCurrent(boolean current) {
                if (current) {
                    RaisedScreen.current = name;
                    resetOptions();
                    LayerList.this.setSelected(this);
                }
            }

        }

    }

}