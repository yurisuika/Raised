package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.gui.GuiComponentInterface;
import dev.yurisuika.raised.client.gui.components.AdjustableSelectionList;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Icon;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class AbstractLayersScreen extends AbstractRaisedScreen {

    public static final Set<String> NAMESPACES = new HashSet<>();
    public static SelectScreen.GroupList.Entry currentGroup = null;
    public String selectedNamespace = ResourceLocation.DEFAULT_NAMESPACE;
    public ArrayList<AbstractWidget> leftControls;
    public ArrayList<AbstractWidget> rightControls;
    public AbstractWidget controlPrevious;
    public AbstractWidget controlNext;
    public int leftRowCount;
    public int rightRowCount;
    public int leftPanelX;
    public int leftPanelY;
    public int rightPanelX;
    public int rightPanelY;
    public int leftListHeight;
    public int rightListHeight;
    public int listWidth;
    public int leftListY;
    public int rightListY;
    public static final int LIST_PADDING_X = 0;
    public static final int LIST_PADDING_Y = 0;
    public static final int LIST_BORDER = 1;
    public static final int ENTRY_GAP = 2;
    public static final int ENTRY_PADDING = 2;
    public static final int ENTRY_HEIGHT = 24;

    public AbstractLayersScreen(Screen parent, int leftRowCount, int rightRowCount) {
        super(parent, 320, 240);
        this.leftRowCount = leftRowCount;
        this.rightRowCount = rightRowCount;
    }

    public void setCurrentGroup(SelectScreen.GroupList.Entry entry) {
        currentGroup = entry;
    }

    public SelectScreen.GroupList.Entry getCurrentGroup() {
        return currentGroup;
    }

    @Override
    public void setSizes() {
        super.setSizes();

        panelWidth = (containerWidth - (CONTAINER_GAP + (2 * CONTAINER_PADDING))) / 2;
        leftPanelX = containerX + CONTAINER_PADDING;
        leftPanelY = containerY + CONTAINER_PADDING;
        rightPanelX = leftPanelX + panelWidth + CONTAINER_GAP;
        rightPanelY = containerY + CONTAINER_PADDING;
        leftListHeight = panelHeight - (2 * LIST_BORDER) - (leftRowCount * WIDGET_AND_GAP_HEIGHT);
        rightListHeight = panelHeight - (2 * LIST_BORDER) - (rightRowCount * WIDGET_AND_GAP_HEIGHT);
        listWidth = panelWidth - (2 * LIST_BORDER) - (2 * PANEL_PADDING);
        leftListY = leftPanelY + (leftRowCount * WIDGET_AND_GAP_HEIGHT) + LIST_BORDER;
        rightListY = rightPanelY + (rightRowCount * WIDGET_AND_GAP_HEIGHT) + LIST_BORDER;
    }

    @Override
    public void init() {
        LayerRegistry.LAYERS.forEach(layerName -> NAMESPACES.add(layerName.getNamespace()));

        super.init();
    }

    @Override
    public void addContent() {
        addLeftList();
        addLeftControls();

        addRightList();
        addRightControls();
    }

    public abstract void addLeftControls();

    public abstract void addLeftList();

    public void addRightControls() {
        rightControls = new ArrayList<>();

        controlPrevious = Button.builder(Component.translatable("options.raised.control.previous"), button -> getPrevious())
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(rightPanelX, rightPanelY)
                .build();
        controlNext = Button.builder(Component.translatable("options.raised.control.next"), button -> getNext())
                .size(WIDGET_WIDTH_SQUARE, WIDGET_HEIGHT)
                .pos(rightPanelX + panelWidth - WIDGET_WIDTH_SQUARE, rightPanelY)
                .build();

        rightControls.add(controlPrevious);
        rightControls.add(controlNext);

        rightControls.forEach(this::addRenderableWidget);
    }

    public abstract void addRightList();

    public void resetLeftControls() {
        if (leftControls != null) {
            leftControls.forEach(this::removeWidget);
            leftControls.clear();
            addLeftControls();
        }
    }

    public abstract void resetLeftList();

    public void resetRightControls() {
        if (rightControls != null) {
            rightControls.forEach(this::removeWidget);
            rightControls.clear();
            addRightControls();
        }
    }

    public abstract void resetRightList();

    public void getPrevious() {
        List<String> keys = NAMESPACES.stream().toList();
        int index = keys.indexOf(selectedNamespace) - 1;

        if (index < 0) {
            setMod(keys.get(keys.size() - 1));
        } else {
            setMod(keys.get(index));
        }
    }

    public void getNext() {
        List<String> keys = NAMESPACES.stream().toList();
        int index = keys.indexOf(selectedNamespace) + 1;

        if (index >= keys.size()) {
            setMod(keys.get(0));
        } else {
            setMod(keys.get(index));
        }
    }

    public void setMod(String mod) {
        selectedNamespace = mod;

        resetRightList();
    }

    @Override
    public void resize(Minecraft minecraft, int width, int height) {
        super.resize(minecraft, width, height);

        resetLeftControls();
        resetRightControls();
    }

    @Override
    public void tick() {
        super.tick();

        controlPrevious.active = NAMESPACES.size() > 1;
        controlNext.active = NAMESPACES.size() > 1;
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        renderBackground(poseStack);
        super.render(poseStack, mouseX, mouseY, partialTick);

        GuiComponentInterface.renderScrollingString(
                poseStack,
                font,
                Component.literal(Parse.parseNamespace(selectedNamespace)),
                rightPanelX + WIDGET_WIDTH_SQUARE + PANEL_GAP,
                rightPanelY,
                rightPanelX + panelWidth - WIDGET_WIDTH_SQUARE - PANEL_GAP,
                rightPanelY + WIDGET_HEIGHT,
                -1);
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

    public abstract static class AbstractLayerList<E extends AbstractLayerList.Entry<E>> extends AdjustableSelectionList<E> {

        protected AbstractLayersScreen parent;

        public AbstractLayerList(Minecraft minecraft, AbstractLayersScreen parent, int width, int height, int y0, int y1) {
            super(minecraft, width, height, y0, y1, ENTRY_HEIGHT, LIST_PADDING_X, LIST_PADDING_Y);
            this.parent = parent;
            setEntries();
        }

        public abstract static class Entry<E extends Entry<E>> extends AdjustableSelectionList.Entry<E> {

            protected final ResourceLocation layerName;
            protected AbstractWidget optionAnchor;

            public Entry(ResourceLocation layerName) {
                this.layerName = layerName;

                optionAnchor = CycleButton.builder(Layer.Anchor::glyph)
                        .withInitialValue(Config.getOptions().getLayers().get(layerName.toString()).getAnchor())
                        .withValues(Layer.Anchor.values())
                        .displayOnlyValue()
                        .withTooltip(value -> Tooltip.create(Component.translatable("options.raised.anchor.tooltip", Component.translatable("options.raised.anchor." + value.getSerializedName()))))
                        .create(0,
                                0,
                                WIDGET_WIDTH_SQUARE,
                                WIDGET_HEIGHT,
                                Component.translatable("options.raised.anchor"),
                                (button, value) -> Config.update(o -> o.getLayers().get(layerName.toString()).setAnchor(value)));
            }

            public ResourceLocation getLayerName() {
                return layerName;
            }

            @Override
            public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                RenderSystem.setShaderTexture(0, Icon.getLayerIcon(layerName));
                blit(
                        poseStack,
                        left + ENTRY_PADDING,
                        top + ENTRY_PADDING,
                        0,
                        0,
                        WIDGET_WIDTH_SQUARE,
                        WIDGET_HEIGHT,
                        20,
                        20);

                GuiComponentInterface.renderScrollingString(
                        poseStack,
                        Minecraft.getInstance().font,
                        Component.literal(entryText()),
                        left + ENTRY_PADDING + WIDGET_WIDTH_SQUARE + ENTRY_GAP,
                        top,
                        left + width - ENTRY_PADDING - WIDGET_WIDTH_SQUARE - ENTRY_GAP,
                        top + height,
                        -1);

                optionAnchor.setPosition(left + width - ENTRY_PADDING - WIDGET_WIDTH_SQUARE, top + ENTRY_PADDING);
                optionAnchor.render(poseStack, mouseX, mouseY, partialTick);
            }

            public abstract String entryText();

            @Override
            public List<? extends GuiEventListener> children() {
                return List.of(optionAnchor);
            }

            @Override
            public List<? extends NarratableEntry> narratables() {
                NarratableEntry narration = new NarratableEntry() {

                    @Override
                    public NarrationPriority narrationPriority() {
                        return NarrationPriority.NONE;
                    }

                    @Override
                    public void updateNarration(NarrationElementOutput output) {
                        output.add(NarratedElementType.TITLE, Component.literal(entryText()));
                    }

                };

                return List.of(optionAnchor, narration);
            }

            public abstract boolean handleClick(final double mouseX, final double mouseY, final int button);

            @Override
            public boolean mouseClicked(final double mouseX, final double mouseY, final int button) {
                Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
                setSelected(true);

                if (handleClick(mouseX, mouseY, button)) {
                    return true;
                }

                if (optionAnchor.mouseClicked(mouseX, mouseY, button)) {
                    setFocused(optionAnchor);
                    return true;
                }

                return true;
            }

            @Override
            public boolean mouseReleased(final double mouseX, final double mouseY, final int button) {
                return optionAnchor.mouseReleased(mouseX, mouseY, button);
            }

            public abstract void setSelected(boolean selected);

        }

    }

}