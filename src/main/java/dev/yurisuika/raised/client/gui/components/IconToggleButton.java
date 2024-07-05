package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class IconToggleButton extends Button {

    public final ResourceLocation texture;
    public final int textureWidth;
    public final int textureHeight;
    public boolean toggled;
    public static final WidgetSprites TEXTURES = new WidgetSprites(ResourceLocation.tryParse("widget/button"), ResourceLocation.tryParse("widget/button_disabled"), ResourceLocation.tryParse("widget/button_highlighted"));

    public IconToggleButton(int x, int y, int width, int height, Component message, int textureWidth, int textureHeight, ResourceLocation texture, OnPress onPress, CreateNarration createNarration, boolean toggled) {
        super(x, y, width, height, message, onPress, createNarration == null ? DEFAULT_NARRATION : createNarration);
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.texture = texture;
        this.toggled = toggled;
    }

    public static Builder builder(Component message, OnPress onPress, boolean toggled) {
        return new Builder(message, onPress, toggled);
    }

    public boolean isToggled() {
        return toggled;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        if (texture != null) {
            RenderSystem.disableDepthTest();
            guiGraphics.blitSprite(TEXTURES.get(!isToggled(), isHoveredOrFocused()), getX(), getY(), getWidth(), getHeight());
            guiGraphics.blitSprite(texture, getX(), getY(), getWidth(), getHeight());
            RenderSystem.enableDepthTest();
        }
    }

    @Override
    public void renderString(GuiGraphics guiGraphics, Font font, int color) {}

    public static class Builder {

        public final Component message;
        public final OnPress onPress;
        public Tooltip tooltip;
        public int x;
        public int y;
        public int width = 150;
        public int height = 20;
        public ResourceLocation texture;
        public int textureWidth;
        public int textureHeight;
        public CreateNarration createNarration;
        public boolean toggled;

        public Builder(Component message, OnPress onPress, boolean toggled) {
            this.message = message;
            this.onPress = onPress;
            this.toggled = toggled;
        }

        public Builder pos(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public Builder bounds(int x, int y, int width, int height) {
            return pos(x, y).size(width, height);
        }

        public Builder tooltip(Tooltip tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public Builder texture(ResourceLocation texture, int width, int height) {
            this.texture = texture;
            this.textureWidth = width;
            this.textureHeight = height;
            return this;
        }

        public Builder createNarration(CreateNarration createNarration) {
            this.createNarration = createNarration;
            return this;
        }

        public IconToggleButton build() {
            if (texture == null) {
                throw new IllegalStateException("Sprite not set");
            } else {
                IconToggleButton iconToggleButtonWidget = new IconToggleButton(x, y, width, height, message, textureWidth, textureHeight, texture, onPress, createNarration, toggled);
                iconToggleButtonWidget.setTooltip(tooltip);
                return iconToggleButtonWidget;
            }
        }

    }

}