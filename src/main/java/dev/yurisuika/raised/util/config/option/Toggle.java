package dev.yurisuika.raised.util.config.option;

import dev.yurisuika.raised.util.type.Texture;

public class Toggle {

    public Texture texture;

    public Toggle(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

}