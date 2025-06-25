package dev.yurisuika.raised.client.gui.screens;

import com.mojang.serialization.Codec;
import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.Resource;
import dev.yurisuika.raised.client.gui.components.LayerList;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.*;

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
        super(Component.translatable("options.raised.title"));
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

        options.add(new OptionInstance<>("options.raised.displacement.x", OptionInstance.cachedConstantTooltip(Component.translatable("options.raised.displacement.x.tooltip")), (prefix, value) -> Options.genericValueLabel(prefix, value == 0 ? CommonComponents.OPTION_OFF : Component.literal(Configure.getDisplacementX(current.toString()) + "px (" + Math.round(Math.ceil((value.floatValue() / ((float) width / 4)) * 100)) + "%)")), new OptionInstance.IntRange(0, width / 4), Configure.getDisplacementX(current.toString()), value -> Configure.setDisplacementX(current.toString(), value)).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING, WIDGET_WIDTH_WIDE));
        options.add(new OptionInstance<>("options.raised.displacement.y", OptionInstance.cachedConstantTooltip(Component.translatable("options.raised.displacement.y.tooltip")), (prefix, value) -> Options.genericValueLabel(prefix, value == 0 ? CommonComponents.OPTION_OFF : Component.literal(Configure.getDisplacementY(current.toString()) + "px (" + Math.round(Math.ceil((value.floatValue() / ((float) height / 4)) * 100)) + "%)")), new OptionInstance.IntRange(0, height / 4), Configure.getDisplacementY(current.toString()), value -> Configure.setDisplacementY(current.toString(), value)).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING), WIDGET_WIDTH_WIDE));
        options.add(new OptionInstance<>("options.raised.direction.x", value -> Tooltip.create(Component.translatable("options.raised.direction.x.tooltip")), OptionInstance.forOptionEnum(), new OptionInstance.Enum<>(Arrays.asList(Layer.Direction.X.values()), Codec.INT.xmap(Layer.Direction.X::byId, Layer.Direction.X::getId)), Layer.Direction.X.byName(Configure.getDirectionX(current.toString()).getSerializedName()), value -> Configure.setDirectionX(current.toString(), value)).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 2, WIDGET_WIDTH_WIDE));
        options.add(new OptionInstance<>("options.raised.direction.y", value -> Tooltip.create(Component.translatable("options.raised.direction.y.tooltip")), OptionInstance.forOptionEnum(), new OptionInstance.Enum<>(Arrays.asList(Layer.Direction.Y.values()), Codec.INT.xmap(Layer.Direction.Y::byId, Layer.Direction.Y::getId)), Layer.Direction.Y.byName(Configure.getDirectionY(current.toString()).getSerializedName()), value -> Configure.setDirectionY(current.toString(), value)).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 3, WIDGET_WIDTH_WIDE));
        options.add(new OptionInstance<>("options.raised.sync", value -> Tooltip.create(Component.translatable("options.raised.sync.tooltip", value.toString())), (prefix, value) -> Component.literal(Parse.parsePath(value)), new OptionInstance.Enum<>(LayerRegistry.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).toList(), ResourceLocation.CODEC), ResourceLocation.tryParse(Configure.getSync(current.toString())), value -> Configure.setSync(current.toString(), value.toString())).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 4, WIDGET_WIDTH_WIDE));
        options.add(new OptionInstance<>("options.raised.texture", value -> Tooltip.create(Component.translatable("options.raised.texture." + value.getSerializedName() + ".tooltip")), OptionInstance.forOptionEnum(), new OptionInstance.Enum<>(Arrays.asList(Resource.Texture.values()), Codec.INT.xmap(Resource.Texture::byId, Resource.Texture::getId)), Resource.Texture.byName(Configure.getTexture().getSerializedName()), Configure::setTexture).createButton(minecraft.options, PADDING, height - (PADDING + (WIDGET_HEIGHT + SPACING) + WIDGET_HEIGHT), WIDGET_WIDTH_WIDE));

        options.forEach(this::addRenderableWidget);
    }

    public void addControls() {
        controls = new ArrayList<>();

        controls.add(Button.builder(Component.literal("<"), button -> getPrevious()).size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT).pos(width - (PADDING + WIDGET_WIDTH_WIDE), PADDING).build());
        controls.add(Button.builder(Component.literal(">"), button -> getNext()).size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT).pos(width - (PADDING + WIDGET_WIDTH_SQUARE), PADDING).build());
        controls.add(Button.builder(CommonComponents.GUI_DONE, button -> onClose()).size(WIDGET_WIDTH_WIDE, WIDGET_HEIGHT).pos(PADDING, height - (PADDING + WIDGET_HEIGHT)).build());

        controls.forEach(this::addRenderableWidget);
    }

    public void addLayers() {
        layers = new LayerList(minecraft, PANEL_WIDTH, height - HEADER_HEIGHT, this);

        layers.setLayers();

        addRenderableWidget(layers);
    }

    public void getPrevious() {
        Set<String> mods = new HashSet<>();
        LayerRegistry.LAYERS.keySet().forEach(key -> mods.add(key.getNamespace()));
        List<String> keys = mods.stream().toList();
        int index = keys.indexOf(current.getNamespace()) - 1;

        String mod;
        if (index < 0) {
            mod = keys.getLast();
        } else {
            mod = keys.get(index);
        }

        setMod(mod);
    }

    public void getNext() {
        Set<String> mods = new HashSet<>();
        LayerRegistry.LAYERS.keySet().forEach(key -> mods.add(key.getNamespace()));
        List<String> keys = mods.stream().toList();
        int index = keys.indexOf(current.getNamespace()) + 1;

        String mod;
        if (index >= keys.size()) {
            mod = keys.getFirst();
        } else {
            mod = keys.get(index);
        }

        setMod(mod);
    }

    public void setMod(String mod) {
        RaisedScreen.current = LayerRegistry.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).filter(name -> name.getNamespace().equals(mod)).toList().getFirst();

        resetOptions();
        resetLayers();
        resetControls();
    }

    public void resetOptions() {
        options.forEach(this::removeWidget);
        options.clear();
        addOptions();
    }

    public void resetControls() {
        controls.forEach(this::removeWidget);
        controls.clear();
        addControls();
    }

    public void resetLayers() {
        layers.children().clear();
        layers.setLayers();
    }

    @Override
    public void repositionElements() {
        layers.setSize(PANEL_WIDTH, height - HEADER_HEIGHT);
        layers.setPosition(width - PANEL_WIDTH, HEADER_HEIGHT);
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
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        guiGraphics.fill(0, 0, width, HEADER_HEIGHT, 1073741824);

        guiGraphics.drawCenteredString(font, title, PADDING + (WIDGET_WIDTH_WIDE / 2), PADDING + 6, -1);

        AbstractWidget.renderScrollingString(guiGraphics, font, Component.literal(Parse.parseNamespace(current)), width - (PADDING + WIDGET_WIDTH_WIDE - WIDGET_WIDTH_SQUARE), PADDING, width - (PADDING + WIDGET_WIDTH_SQUARE), PADDING + WIDGET_HEIGHT, -1);
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBlurredBackground();
        renderMenuBackground(guiGraphics);
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