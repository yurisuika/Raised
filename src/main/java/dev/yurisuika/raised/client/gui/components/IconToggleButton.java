package dev.yurisuika.raised.client.gui.components;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class IconToggleButton extends Button {

    public final ResourceLocation texture;
    public final int textureWidth;
    public final int textureHeight;
    public boolean toggled;
    public static final ResourceLocation TEXTURE = ResourceLocation.tryParse("textures/gui/widgets.png");

    public IconToggleButton(int x, int y, int width, int height, Component message, int textureWidth, int textureHeight, ResourceLocation texture, OnPress onPress, boolean toggled) {
        super(x, y, width, height, message, onPress, (button, poseStack, mouseX, mouseY) -> {});
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.texture = texture;
        this.toggled = toggled;
    }

    public boolean isToggled() {
        return toggled;
    }

    public void renderButton(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        if (texture != null) {
            RenderSystem.disableDepthTest();
            int v;
            if (!isToggled()) {
                if (isHovered() || isFocused()) {
                    v = 86;
                } else {
                    v = 66;
                }
            }  else {
                v = 46;
            }
            RenderSystem.setShaderTexture(0, TEXTURE);
            blit(poseStack, x, y, 0, v, width / 2, height);
            blit(poseStack, x + (width / 2), y, 200 - (width / 2), v, width / 2, height);
            RenderSystem.setShaderTexture(0, texture);
            blit(poseStack, x, y, 0, 0, getWidth(), getHeight(), getWidth(), getHeight());
            RenderSystem.enableDepthTest();
        }
    }

}