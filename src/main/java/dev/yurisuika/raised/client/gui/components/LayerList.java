package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class LayerList extends ContainerObjectSelectionList<LayerList.Entry> {

    public RaisedScreen screen;

    public LayerList(Minecraft minecraft, int width, int height, RaisedScreen screen) {
        super(minecraft, width, height, 0, height, screen.BUTTON_HEIGHT + screen.SPACING);
        this.screen = screen;
        ((AbstractSelectionListInterface) this).setPadding(screen.PADDING);
        setRenderBackground(false);
        setRenderTopAndBottom(false);
        setRenderHeader(false, 0);
    }

    @Override
    public int getRowWidth() {
        return screen.BUTTON_WIDTH;
    }

    public void add(ResourceLocation name) {
        addEntry(new LayerList.Entry(screen, name));
    }
    @Override
    public int getMaxScroll() {
        return Math.max(0, getMaxPosition() - height);
    }

    @Override
    public int getMaxPosition() {
        return screen.PADDING + headerHeight + (getItemCount() * itemHeight) - screen.SPACING + screen.PADDING;
    }

    @Override
    public void renderDecorations(PoseStack poseStack, int mouseX, int mouseY) {
        for (int i = 0; i < children().size(); i++) {
            if (getEntry(i).button.isHoveredOrFocused()) {
                getEntry(i).button.renderToolTip(poseStack, mouseX, mouseY);
            }
        }
    }

    @Override
    public int getRowTop(int index) {
        return y0 + screen.PADDING - (int) getScrollAmount() + index * itemHeight + headerHeight;
    }

    @Override
    public int getRowLeft() {
        return x0 + screen.PADDING;
    }

    @Override
    public int getScrollbarPosition() {
        return getRowRight() + screen.PADDING - 6;
    }

    public static class Entry extends ContainerObjectSelectionList.Entry<Entry> {

        public final RaisedScreen screen;
        public final ResourceLocation name;
        public final LayerButton button;

        public Entry(RaisedScreen screen, ResourceLocation name) {
            this.screen = screen;
            this.name = name;
            this.button = createLayerButton(name);
        }

        public void updateButtonStates() {
            button.toggled = RaisedScreen.current.equals(name);
            button.active = !button.toggled;
        }

        public LayerButton createLayerButton(ResourceLocation name) {
            AtomicReference<ResourceLocation> texture = new AtomicReference<>(ResourceLocation.tryParse("raised:textures/layer/default.png"));
            Minecraft.getInstance().getResourcePackRepository().openAllSelected().forEach(pack -> {
                ResourceLocation layer = ResourceLocation.tryParse("raised:textures/layer/" + name.getNamespace() + "/" + name.getPath() + ".png");
                if (pack.hasResource(PackType.CLIENT_RESOURCES, layer)) {
                    texture.set(layer);
                }
            });

            return LayerButton.builder(Parse.createLayerDisplay(name), button -> {
                RaisedScreen.current = name;
                screen.resetOptions();
                screen.layers.children().forEach(Entry::updateButtonStates);
            }, name.equals(RaisedScreen.current)).size(screen.BUTTON_WIDTH, screen.BUTTON_HEIGHT).texture(texture.get(), screen.BUTTON_HEIGHT).tooltip((button, poseStack, mouseX, mouseY) -> screen.renderTooltip(poseStack, Component.literal(name.toString()), mouseX, mouseY)).build();
        }

        @Override
        public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            button.y = top;
            button.x = left;
            button.render(poseStack, mouseX, mouseY, partialTick);
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return List.of(button);
        }

        @Override
        public List<? extends NarratableEntry> narratables() {
            return List.of(button);
        }

    }

}