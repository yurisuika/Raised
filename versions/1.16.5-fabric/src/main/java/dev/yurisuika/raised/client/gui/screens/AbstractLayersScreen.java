package dev.yurisuika.raised.client.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.gui.GuiComponentInterface;
import dev.yurisuika.raised.client.gui.components.AdjustableSelectionList;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Icon;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.client.CycleOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.TooltipAccessor;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;

import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractLayersScreen extends AbstractRaisedScreen {

    public static final Set<String> NAMESPACES = new HashSet<>();
    public static SelectScreen.GroupList.Entry currentGroup = null;
    public String selectedNamespace = "minecraft";
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

        controlPrevious = new Button(
                rightPanelX,
                rightPanelY,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.control.previous"),
                button -> getPrevious());
        controlNext = new Button(
                rightPanelX + panelWidth - WIDGET_WIDTH_SQUARE,
                rightPanelY,
                WIDGET_WIDTH_SQUARE,
                WIDGET_HEIGHT,
                new TranslatableComponent("options.raised.control.next"),
                button -> getNext());

        rightControls.add(controlPrevious);
        rightControls.add(controlNext);

        rightControls.forEach(this::addButton);
    }

    public abstract void addRightList();

    public void resetLeftControls() {
        if (leftControls != null) {
            leftControls.forEach(widget -> {
                buttons.remove(widget);
                children.remove(widget);
            });
            leftControls.clear();
            addLeftControls();
        }
    }

    public abstract void resetLeftList();

    public void resetRightControls() {
        if (rightControls != null) {
            rightControls.forEach(widget -> {
                buttons.remove(widget);
                children.remove(widget);
            });
            rightControls.clear();
            addRightControls();
        }
    }

    public abstract void resetRightList();

    public void getPrevious() {
        List<String> keys = NAMESPACES.stream().collect(Collectors.toList());
        int index = keys.indexOf(selectedNamespace) - 1;

        if (index < 0) {
            setMod(keys.get(keys.size() - 1));
        } else {
            setMod(keys.get(index));
        }
    }

    public void getNext() {
        List<String> keys = NAMESPACES.stream().collect(Collectors.toList());
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
                new TextComponent(Parse.parseNamespace(selectedNamespace)),
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

        public List<FormattedCharSequence> getHoveredTooltip(int mouseX, int mouseY) {
            E entry = getEntryAtPosition(mouseX, mouseY);

            return entry == null ? null : entry.getTooltip(mouseX, mouseY);
        }

        public abstract static class Entry<E extends Entry<E>> extends AdjustableSelectionList.Entry<E> {

            protected final ResourceLocation layerName;
            protected AbstractWidget optionAnchor;

            public Entry(ResourceLocation layerName) {
                this.layerName = layerName;

                optionAnchor = new CycleOption(
                        "options.raised.anchor",
                        (options, integer) -> {
                            List<Layer.Anchor> anchors = Arrays.stream(Layer.Anchor.values()).collect(Collectors.toList());
                            Layer.Anchor anchor = Config.getOptions().getLayers().get(layerName.toString()).getAnchor();
                            int index = anchors.indexOf(anchor);
                            Config.update(o -> o.getLayers().get(layerName.toString()).setAnchor(anchors.get(index < anchors.size() - 1 ? index + 1 : 0)));
                        },
                        (options, option) -> {
                            Layer.Anchor anchor = Config.getOptions().getLayers().get(layerName.toString()).getAnchor();
                            option.setTooltip(Minecraft.getInstance().font.split(new TranslatableComponent("options.raised.anchor.tooltip", new TranslatableComponent("options.raised.anchor." + anchor.getSerializedName())), 200));
                            return anchor.glyph();
                        })
                        .createButton(Minecraft.getInstance().options, 0, 0, WIDGET_WIDTH_SQUARE);
            }

            public ResourceLocation getLayerName() {
                return layerName;
            }

            @Override
            public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
                Minecraft.getInstance().getTextureManager().bind(Icon.getLayerIcon(layerName));
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
                        new TextComponent(entryText()),
                        left + ENTRY_PADDING + WIDGET_WIDTH_SQUARE + ENTRY_GAP,
                        top,
                        left + width - ENTRY_PADDING - WIDGET_WIDTH_SQUARE - ENTRY_GAP,
                        top + height,
                        -1);

                optionAnchor.x = left + width - ENTRY_PADDING - WIDGET_WIDTH_SQUARE;
                optionAnchor.y = top + ENTRY_PADDING;
                optionAnchor.render(poseStack, mouseX, mouseY, partialTick);
            }

            public abstract String entryText();

            @Override
            public List<? extends GuiEventListener> children() {
                return Collections.singletonList(optionAnchor);
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

            public List<FormattedCharSequence> getTooltip(int mouseX, int mouseY) {
                if (optionAnchor.isMouseOver(mouseX, mouseY)) {
                    Optional<List<FormattedCharSequence>> tooltip = ((TooltipAccessor) optionAnchor).getTooltip();
                    if (tooltip.isPresent()) {
                        return tooltip.get();
                    }
                }

                return null;
            }

        }

    }

}