package dev.yurisuika.raised.client.gui.components;

import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.util.Parse;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
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
        super(minecraft, width, height, 0, screen.BUTTON_HEIGHT + screen.SPACING);
        this.screen = screen;
        ((AbstractSelectionListInterface) this).setPadding(screen.PADDING - 4);
    }

    @Override
    public int getRowWidth() {
        return screen.BUTTON_WIDTH;
    }

    public void add(ResourceLocation name) {
        addEntry(new LayerList.Entry(screen, name));
    }
    @Override
    public void renderListSeparators(GuiGraphics guiGraphics) {}

    @Override
    public void renderListBackground(GuiGraphics guiGraphics) {}

    @Override
    public int contentHeight() {
        return screen.PADDING + headerHeight + (getItemCount() * itemHeight) - screen.SPACING + screen.PADDING;
    }

    @Override
    public int getRowTop(int index) {
        return getY() + screen.PADDING - (int) scrollAmount() + index * itemHeight + headerHeight;
    }

    @Override
    public int getRowLeft() {
        return getX() + screen.PADDING;
    }

    @Override
    public int scrollBarX() {
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
            String texture;
            if (name.getNamespace().equals(ResourceLocation.DEFAULT_NAMESPACE)) {
                texture = switch (name.getPath()) {
                    case "hotbar" -> "hotbar";
                    case "chat" -> "chat";
                    case "bossbar" -> "bossbar";
                    case "sidebar" -> "sidebar";
                    case "effects" -> "effects";
                    case "players" -> "players";
                    case "subtitles" -> "subtitles";
                    case "toasts" -> "toasts";
                    default -> "other";
                };
            } else {
                texture = "other";
            }

            return LayerButton.builder(Parse.createLayerDisplay(name), button -> {
                RaisedScreen.current = name;
                screen.resetOptions();
                screen.layers.children().forEach(Entry::updateButtonStates);
            }, name.equals(RaisedScreen.current)).size(screen.BUTTON_WIDTH, screen.BUTTON_HEIGHT).texture(ResourceLocation.fromNamespaceAndPath("raised", "icon/" + texture), screen.BUTTON_HEIGHT).tooltip(Tooltip.create(Component.literal(name.toString()))).build();
        }

        @Override
        public void render(GuiGraphics guiGraphics, int index, int top, int left, int width, int height, int mouseX, int mouseY, boolean hovering, float partialTick) {
            button.setY(top);
            button.setX(left);
            button.render(guiGraphics, mouseX, mouseY, partialTick);
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