package dev.yurisuika.raised.client.gui.layer;

import com.mojang.serialization.Codec;
import dev.yurisuika.raised.Raised;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;

public class Layer {

    public Position position;

    public Layer(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public enum Position implements StringRepresentable {

        NONE("none", "options.raised.position.none", "\uE000", 0, 0),
        LEFT("left", "options.raised.position.left", "\uE001", 1, 0),
        RIGHT("right", "options.raised.position.right", "\uE002", -1, 0),
        TOP("top", "options.raised.position.top", "\uE003", 0, 1),
        BOTTOM("bottom", "options.raised.position.bottom", "\uE004", 0, -1),
        TOP_LEFT("top_left", "options.raised.position.top_left", "\uE005", 1, 1),
        TOP_RIGHT("top_right", "options.raised.position.top_right", "\uE006", -1, 1),
        BOTTOM_LEFT("bottom_left", "options.raised.position.bottom_left", "\uE007", 1, -1),
        BOTTOM_RIGHT("bottom_right", "options.raised.position.bottom_right", "\uE008", -1, -1);

        public static final Codec<Position> CODEC = StringRepresentable.fromEnum(Position::values, Position::valueOf);
        public final String name;
        public final Component caption;
        public final MutableComponent glyph;
        public final int x;
        public final int y;

        Position(String name, String key, String glyph, int x, int y) {
            this.name = name;
            this.caption = new TranslatableComponent(key);
            this.glyph = new TextComponent(glyph).withStyle(style -> style.withFont(new ResourceLocation(Raised.MOD_ID, "position")));
            this.x = x;
            this.y = y;
        }

        public Component caption() {
            return caption;
        }

        public MutableComponent glyph() {
            return glyph;
        }

        public String getSerializedName() {
            return name;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

    }

}