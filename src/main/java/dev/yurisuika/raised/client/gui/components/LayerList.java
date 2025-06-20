package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.client.gui.Scissor;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.util.Parse;
import dev.yurisuika.raised.util.config.Option;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ObjectSelectionList;
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
        super(minecraft, width, height, screen.HEADER_HEIGHT, screen.height, screen.WIDGET_HEIGHT);
        this.screen = screen;
        ((AbstractSelectionListInterface) this).setAdjusted(true);
        setRenderBackground(false);
        setRenderTopAndBottom(false);
        setRenderHeader(false, 0);
    }

    public void setList() {
        clearEntries();
        Layers.LAYERS.keySet().stream().sorted(Comparator.comparing(ResourceLocation::toString)).filter(location -> location.getNamespace().equals(RaisedScreen.current.getNamespace())).forEach(name -> addEntry(new Entry(screen, name)));
    }

    @Override
    public int getRowWidth() {
        return screen.WIDGET_WIDTH_WIDE;
    }

    @Override
    public int getMaxScroll() {
        return Math.max(0, getMaxPosition() - height);
    }

    @Override
    public int getMaxPosition() {
        return screen.PADDING + (getItemCount() * itemHeight) + screen.PADDING;
    }

    @Override
    public int getRowLeft() {
        return x0 + screen.PADDING;
    }

    @Override
    public int getScrollbarPosition() {
        return getRowRight() + screen.PADDING - 6;
    }

    @Override
    public void renderSelection(PoseStack poseStack, int top, int width, int height, int outerColor, int innerColor) {
        int i = x0 + (this.width - width) / 2;
        int j = x0 + (this.width + width) / 2;
        fill(poseStack, i, top, j, top + screen.WIDGET_HEIGHT, outerColor);
        fill(poseStack, i + 1, top + 1, j - 1, top + screen.WIDGET_HEIGHT - 1, innerColor);
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
        public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            Scissor.renderScrollingString(poseStack, Minecraft.getInstance().font, Component.literal(Parse.parsePath(name)), left + screen.WIDGET_WIDTH_SQUARE, top, left + (screen.WIDGET_WIDTH_WIDE - screen.WIDGET_WIDTH_SQUARE), top + screen.WIDGET_HEIGHT, -1);

            AtomicReference<ResourceLocation> texture = new AtomicReference<>(ResourceLocation.tryParse("raised:textures/gui/layer/default.png"));
            Minecraft.getInstance().getResourcePackRepository().openAllSelected().forEach(pack -> {
                ResourceLocation layer = ResourceLocation.tryParse("raised:textures/gui/layer/" + name.getNamespace() + "/" + name.getPath() + ".png");
                if (pack.hasResource(PackType.CLIENT_RESOURCES, layer)) {
                    texture.set(layer);
                }
            });
            RenderSystem.setShaderTexture(0, texture.get());
            blit(poseStack, left, top, 0, 0, screen.WIDGET_WIDTH_SQUARE, screen.WIDGET_HEIGHT, screen.WIDGET_WIDTH_SQUARE, screen.WIDGET_HEIGHT);

            RenderSystem.setShaderTexture(0, ResourceLocation.tryParse("raised:textures/gui/direction/" + Option.getDirectionX(name.toString()).toString().toLowerCase() + "_" + Option.getDirectionY(name.toString()).toString().toLowerCase() + ".png"));
            blit(poseStack, left + (screen.WIDGET_WIDTH_WIDE - screen.WIDGET_WIDTH_SQUARE), top, 0, 0, screen.WIDGET_WIDTH_SQUARE, screen.WIDGET_HEIGHT, screen.WIDGET_WIDTH_SQUARE, screen.WIDGET_HEIGHT);
        }

        @Override
        public Component getNarration() {
            return Component.translatable("narrator.select", Parse.createLayerDisplay(name));
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