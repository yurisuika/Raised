package dev.yurisuika.raised.client.gui;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public interface Graphics {

    static void blitNineSliced(GuiGraphics guiGraphics, ResourceLocation texture, int textureWidth, int textureHeight, int textureBorder, int x, int y, int width, int height) {
        int middleWidth = width - textureBorder * 2;
        int middleHeight = height - textureBorder * 2;

        guiGraphics.blit(
                texture,
                x, y,
                textureBorder, textureBorder,
                0, 0,
                textureBorder, textureBorder,
                textureWidth, textureHeight);
        guiGraphics.blit(
                texture,
                x + textureBorder, y,
                middleWidth, textureBorder,
                textureBorder, 0,
                textureWidth - textureBorder * 2, textureBorder,
                textureWidth, textureHeight);
        guiGraphics.blit(
                texture,
                x + width - textureBorder, y,
                textureBorder, textureBorder,
                textureWidth - textureBorder, 0,
                textureBorder, textureBorder,
                textureWidth, textureHeight);
        guiGraphics.blit(
                texture,
                x, y + textureBorder,
                textureBorder, middleHeight,
                0, textureBorder,
                textureBorder, textureHeight - textureBorder * 2,
                textureWidth, textureHeight);
        guiGraphics.blit(
                texture,
                x + textureBorder, y + textureBorder,
                middleWidth, middleHeight,
                textureBorder, textureBorder,
                textureWidth - textureBorder * 2,
                textureHeight - textureBorder * 2,
                textureWidth, textureHeight);
        guiGraphics.blit(
                texture,
                x + width - textureBorder, y + textureBorder,
                textureBorder, middleHeight,
                textureWidth - textureBorder,
                textureBorder,
                textureBorder,
                textureHeight - textureBorder * 2,
                textureWidth, textureHeight);
        guiGraphics.blit(
                texture,
                x, y + height - textureBorder,
                textureBorder, textureBorder,
                0,
                textureHeight - textureBorder,
                textureBorder,
                textureBorder,
                textureWidth, textureHeight);
        guiGraphics.blit(
                texture,
                x + textureBorder, y + height - textureBorder,
                middleWidth, textureBorder,
                textureBorder,
                textureHeight - textureBorder,
                textureWidth - textureBorder * 2,
                textureBorder,
                textureWidth, textureHeight);
        guiGraphics.blit(
                texture,
                x + width - textureBorder,
                y + height - textureBorder,
                textureBorder,
                textureBorder,
                textureWidth - textureBorder,
                textureHeight - textureBorder,
                textureBorder,
                textureBorder,
                textureWidth, textureHeight);
    }

}