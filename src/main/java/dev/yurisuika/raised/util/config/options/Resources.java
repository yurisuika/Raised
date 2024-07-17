package dev.yurisuika.raised.util.config.options;

import dev.yurisuika.raised.util.resources.Texture;

public class Resources {

    public Texture texture;

    public Resources(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

}