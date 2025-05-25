package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

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

    public void add(String name) {
        addEntry(new Entry(screen, name));
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
        public final String name;
        public final LayerButton button;

        public Entry(RaisedScreen screen, String name) {
            this.screen = screen;
            this.name = name;
            this.button = createLayerButton(name);
        }

        public void updateButtonStates() {
            button.toggled = RaisedScreen.current.toString().equals(name);
            button.active = !button.toggled;
        }

        public LayerButton createLayerButton(String name) {
            String texture;
            ResourceLocation id = ResourceLocation.tryParse(name);
            if (id.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
                texture = switch (id.getPath()) {
                    case "hotbar" -> "hotbar";
                    case "chat" -> "chat";
                    case "bossbar" -> "bossbar";
                    case "sidebar" -> "sidebar";
                    case "effects" -> "effects";
                    case "players" -> "players";
                    case "toasts" -> "toasts";
                    default -> "other";
                };
            } else {
                texture = "other";
            }

            return LayerButton.builder(Parse.createLayerDisplay(name), button -> {
                RaisedScreen.current = id;
                screen.resetOptions();
                screen.layerList.children().forEach(Entry::updateButtonStates);
            }, name.equals(RaisedScreen.current.toString())).size(screen.BUTTON_WIDTH, screen.BUTTON_HEIGHT).texture(ResourceLocation.tryParse("raised:textures/gui/icon/" + texture + ".png"), screen.BUTTON_HEIGHT).tooltip(Tooltip.create(Component.literal(name))).build();
        }

        @Override
        public void render(PoseStack poseStack, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            button.setY(top);
            button.setX(left);
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