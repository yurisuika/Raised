package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class LayerButton extends Button {

    public final Component message;
    public final ResourceLocation texture;
    public final int textureSize;
    public boolean toggled;
    public static final ResourceLocation TEXTURE = ResourceLocation.tryParse("textures/gui/widgets.png");

    public LayerButton(int x, int y, int width, int height, Component message, int textureSize, ResourceLocation texture, OnPress onPress, CreateNarration createNarration, boolean toggled) {
        super(x, y, width, height, message, onPress, createNarration == null ? DEFAULT_NARRATION : createNarration);
        this.message = message;
        this.textureSize = textureSize;
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
    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (texture != null) {
            RenderSystem.disableDepthTest();
            int v;
            if (!isToggled()) {
                if (isHoveredOrFocused()) {
                    v = 86;
                } else {
                    v = 66;
                }
            }  else {
                v = 46;
            }
            RenderSystem.setShaderTexture(0, TEXTURE);
            blit(poseStack, getX(), getY(), 0, v, width / 2, height);
            blit(poseStack, getX() + (width / 2), getY(), 200 - (width / 2), v, width / 2, height);
            RenderSystem.setShaderTexture(0, texture);
            blit(poseStack, getX(), getY(), 0, 0, textureSize, textureSize, textureSize, textureSize);
            ((AbstractWidgetInterface) this).renderScrollingString(poseStack, Minecraft.getInstance().font, message, getX() + textureSize, getY(), getX() + getWidth() - 2, getY() + getHeight(), -1);
            RenderSystem.enableDepthTest();
        }
    }

    public static class Builder {

        public final Component message;
        public final OnPress onPress;
        public Tooltip tooltip;
        public int x;
        public int y;
        public int width = 150;
        public int height = 20;
        public ResourceLocation texture;
        public int textureSize;
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

        public Builder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }
        public Builder tooltip(Tooltip tooltip) {
            this.tooltip = tooltip;
            return this;
        }

        public Builder texture(ResourceLocation texture, int textureSize) {
            this.texture = texture;
            this.textureSize = textureSize;
            return this;
        }

        public Builder createNarration(CreateNarration createNarration) {
            this.createNarration = createNarration;
            return this;
        }

        public LayerButton build() {
            LayerButton layerButtonWidget = new LayerButton(x, y, width, height, message, textureSize, texture, onPress, createNarration, toggled);
            layerButtonWidget.setTooltip(tooltip);
            return layerButtonWidget;
        }

    }

}