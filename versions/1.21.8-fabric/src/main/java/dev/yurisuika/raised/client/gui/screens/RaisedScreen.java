package dev.yurisuika.raised.client.gui.screens;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.client.gui.Resource;
import dev.yurisuika.raised.client.gui.components.SpacedSelectionList;
import dev.yurisuika.raised.mixin.minecraft.client.gui.components.AbstractWidgetInvoker;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import dev.yurisuika.raised.util.Icon;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

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
        options.add(new OptionInstance<>("options.raised.direction.x", value -> Tooltip.create(Component.translatable("options.raised.direction.x.tooltip")), (component, option) -> option.caption(), new OptionInstance.Enum<>(Arrays.asList(Layer.Direction.X.values()), Layer.Direction.X.CODEC), Layer.Direction.X.CODEC.byName(Configure.getDirectionX(current.toString()).getSerializedName()), value -> Configure.setDirectionX(current.toString(), value)).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 2, WIDGET_WIDTH_WIDE));
        options.add(new OptionInstance<>("options.raised.direction.y", value -> Tooltip.create(Component.translatable("options.raised.direction.y.tooltip")), (component, option) -> option.caption(), new OptionInstance.Enum<>(Arrays.asList(Layer.Direction.Y.values()), Layer.Direction.Y.CODEC), Layer.Direction.Y.CODEC.byName(Configure.getDirectionY(current.toString()).getSerializedName()), value -> Configure.setDirectionY(current.toString(), value)).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 3, WIDGET_WIDTH_WIDE));
        options.add(new OptionInstance<>("options.raised.sync", value -> Tooltip.create(Component.translatable("options.raised.sync.tooltip", value.toString())), (prefix, value) -> Component.literal(Parse.parsePath(value)), new OptionInstance.Enum<>(LayerRegistry.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).toList(), ResourceLocation.CODEC), ResourceLocation.tryParse(Configure.getSync(current.toString())), value -> Configure.setSync(current.toString(), value.toString())).createButton(minecraft.options, PADDING, HEADER_HEIGHT + PADDING + (WIDGET_HEIGHT + SPACING) * 4, WIDGET_WIDTH_WIDE));
        options.add(new OptionInstance<>("options.raised.texture", value -> Tooltip.create(Component.translatable("options.raised.texture." + value.getSerializedName() + ".tooltip")), (component, option) -> option.caption(), new OptionInstance.Enum<>(Arrays.asList(Resource.Texture.values()), Resource.Texture.CODEC), Resource.Texture.CODEC.byName(Configure.getTexture().getSerializedName()), Configure::setTexture).createButton(minecraft.options, PADDING, height - (PADDING + (WIDGET_HEIGHT + SPACING) + WIDGET_HEIGHT), WIDGET_WIDTH_WIDE));

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
        layers = new LayerList(minecraft, PANEL_WIDTH, height - HEADER_HEIGHT);

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
        layers.setScrollAmount(0.0F);
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

        AbstractWidgetInvoker.invokeRenderScrollingString(guiGraphics, font, Component.literal(Parse.parseNamespace(current)), width - (PADDING + WIDGET_WIDTH_WIDE - WIDGET_WIDTH_SQUARE), PADDING, width - (PADDING + WIDGET_WIDTH_SQUARE), PADDING + WIDGET_HEIGHT, -1);
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
            super(minecraft, width, height, 0, WIDGET_HEIGHT, PADDING);
            setLayers();
        }

        public void setLayers() {
            clearEntries();
            LayerRegistry.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).filter(location -> location.getNamespace().equals(RaisedScreen.current.getNamespace())).forEach(name -> addEntry(new Entry(name)));
        }

        public class Entry extends SpacedSelectionList.Entry<Entry> {

            public final ResourceLocation name;

            public Entry(ResourceLocation name) {
                this.name = name;
                setCurrent(RaisedScreen.current.equals(name));
            }

            @Override
            public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                renderScrollingString(guiGraphics, Minecraft.getInstance().font, Component.literal(Parse.parsePath(name)), left + WIDGET_WIDTH_SQUARE, top, left + (WIDGET_WIDTH_WIDE - WIDGET_WIDTH_SQUARE), top + WIDGET_HEIGHT, -1);

                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, Icon.getLayerIcon(name), left, top, 0, 0, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT);

                guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.fromNamespaceAndPath("raised", "textures/gui/direction/" + Configure.getDirectionX(name.toString()).toString().toLowerCase() + "_" + Configure.getDirectionY(name.toString()).toString().toLowerCase() + ".png"), left + (WIDGET_WIDTH_WIDE - WIDGET_WIDTH_SQUARE), top, 0, 0, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT, WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT);
            }

            @Override
            public Component getNarration() {
                return Component.translatable("narrator.select", Parse.parsePath(name));
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