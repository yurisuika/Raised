package dev.yurisuika.raised.client.gui;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

public class Resource {

    public Texture texture;

    public Resource(Texture texture) {
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public enum Texture implements StringRepresentable {

        REPLACE("replace", "options.raised.texture.replace"),
        PATCH("patch", "options.raised.texture.patch"),
        AUTO("auto", "options.raised.texture.auto"),
        NONE("none", "options.raised.texture.none");

        public static final EnumCodec<Texture> CODEC = StringRepresentable.fromEnum(Texture::values);
        public final String name;
        public final Component caption;

        Texture(String name, String key) {
            this.name = name;
            this.caption = Component.translatable(key);
        }

        public Component caption() {
            return caption;
        }

        public String getSerializedName() {
            return name;
        }

    }

}