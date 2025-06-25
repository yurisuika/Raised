package dev.yurisuika.raised.client.gui.components;

import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ObjectSelectionList;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.sounds.SoundEvents;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;

public class LayerList extends ObjectSelectionList<LayerList.Entry> {

    public RaisedScreen screen;

    public LayerList(Minecraft minecraft, int width, int height, RaisedScreen screen) {
        super(minecraft, width, height, 0, screen.WIDGET_HEIGHT);
        this.screen = screen;
        ((AbstractSelectionListInterface) this).setAdjusted(true);
    }

    public void setLayers() {
        clearEntries();
        LayerRegistry.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).filter(location -> location.getNamespace().equals(RaisedScreen.current.getNamespace())).forEach(name -> addEntry(new Entry(screen, name)));
    }

    @Override
    public int getRowWidth() {
        return screen.WIDGET_WIDTH_WIDE;
    }

    @Override
    public void renderListSeparators(GuiGraphics guiGraphics) {}

    @Override
    public void renderListBackground(GuiGraphics guiGraphics) {}

    @Override
    public int contentHeight() {
        return screen.PADDING + (getItemCount() * itemHeight) + screen.PADDING;
    }

    @Override
    public int getRowLeft() {
        return getX() + screen.PADDING;
    }

    @Override
    public int scrollBarX() {
        return getRowRight() + screen.PADDING - 6;
    }

    @Override
    public void renderSelection(GuiGraphics guiGraphics, int top, int width, int height, int outerColor, int innerColor) {
        int i = getX() + (this.width - width) / 2;
        int j = getX() + (this.width + width) / 2;
        guiGraphics.fill(i, top, j, top + screen.WIDGET_HEIGHT, outerColor);
        guiGraphics.fill(i + 1, top + 1, j - 1, top + screen.WIDGET_HEIGHT - 1, innerColor);
    }

    public class Entry extends ObjectSelectionList.Entry<Entry> {

        public final RaisedScreen screen;
        public final ResourceLocation name;

        public Entry(RaisedScreen screen, ResourceLocation name) {
            this.screen = screen;
            this.name = name;
            setCurrent(RaisedScreen.current.equals(name));
        }

        @Override
        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            renderScrollingString(guiGraphics, Minecraft.getInstance().font, Component.literal(Parse.parsePath(name)), left + screen.WIDGET_WIDTH_SQUARE, top, left + (screen.WIDGET_WIDTH_WIDE - screen.WIDGET_WIDTH_SQUARE), top + screen.WIDGET_HEIGHT, -1);

            AtomicReference<ResourceLocation> texture = new AtomicReference<>(ResourceLocation.fromNamespaceAndPath("raised", "textures/gui/layer/default.png"));
            Minecraft.getInstance().getResourcePackRepository().openAllSelected().forEach(pack -> pack.listResources(PackType.CLIENT_RESOURCES, "raised", "textures/gui/layer/" + name.getNamespace() + "/" + name.getPath() + ".png", (location, supplier) -> texture.set(location)));
            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, texture.get(), left, top, 0, 0, screen.WIDGET_WIDTH_SQUARE, screen.WIDGET_HEIGHT, screen.WIDGET_WIDTH_SQUARE, screen.WIDGET_HEIGHT);

            guiGraphics.blit(RenderPipelines.GUI_TEXTURED, ResourceLocation.fromNamespaceAndPath("raised", "textures/gui/direction/" + Configure.getDirectionX(name.toString()).toString().toLowerCase() + "_" + Configure.getDirectionY(name.toString()).toString().toLowerCase() + ".png"), left + (screen.WIDGET_WIDTH_WIDE - screen.WIDGET_WIDTH_SQUARE), top, 0, 0, screen.WIDGET_WIDTH_SQUARE, screen.WIDGET_HEIGHT, screen.WIDGET_WIDTH_SQUARE, screen.WIDGET_HEIGHT);
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
                screen.resetOptions();
                LayerList.this.setSelected(this);
            }
        }

    }

}