package dev.yurisuika.raised.client.gui.layer;

import dev.yurisuika.raised.Raised;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;

public class Layer {

    public Anchor anchor;

    public Layer(Anchor anchor) {
        this.anchor = anchor;
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public enum Anchor implements StringRepresentable {

        NONE("none", "options.raised.anchor.none", "\uE000", 0, 0),
        LEFT("left", "options.raised.anchor.left", "\uE001", 1, 0),
        RIGHT("right", "options.raised.anchor.right", "\uE002", -1, 0),
        TOP("top", "options.raised.anchor.top", "\uE003", 0, 1),
        BOTTOM("bottom", "options.raised.anchor.bottom", "\uE004", 0, -1),
        TOP_LEFT("top_left", "options.raised.anchor.top_left", "\uE005", 1, 1),
        TOP_RIGHT("top_right", "options.raised.anchor.top_right", "\uE006", -1, 1),
        BOTTOM_LEFT("bottom_left", "options.raised.anchor.bottom_left", "\uE007", 1, -1),
        BOTTOM_RIGHT("bottom_right", "options.raised.anchor.bottom_right", "\uE008", -1, -1);

        public static final EnumCodec<Anchor> CODEC = StringRepresentable.fromEnum(Anchor::values);
        public final String name;
        public final Component caption;
        public final MutableComponent glyph;
        public final int x;
        public final int y;

        Anchor(String name, String key, String glyph, int x, int y) {
            this.name = name;
            this.caption = Component.translatable(key);
            this.glyph = Component.literal(glyph).withStyle(style -> style.withFont(new FontDescription.Resource(Identifier.fromNamespaceAndPath(Raised.MOD_ID, "anchor"))));
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