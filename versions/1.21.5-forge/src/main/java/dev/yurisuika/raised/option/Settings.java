package dev.yurisuika.raised.option;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

public class Settings {

    public SelectionIndicator selectionIndicator;

    public Settings(SelectionIndicator selectionIndicator) {
        this.selectionIndicator = selectionIndicator;
    }

    public SelectionIndicator getSelectionIndicator() {
        return selectionIndicator;
    }

    public void setSelectionIndicator(SelectionIndicator selectionIndicator) {
        this.selectionIndicator = selectionIndicator;
    }

    public enum SelectionIndicator implements StringRepresentable {

        REPLACE("replace", "options.raised.selection_indicator.replace"),
        PATCH("patch", "options.raised.selection_indicator.patch"),
        AUTO("auto", "options.raised.selection_indicator.auto"),
        NONE("none", "options.raised.selection_indicator.none");

        public static final EnumCodec<SelectionIndicator> CODEC = StringRepresentable.fromEnum(SelectionIndicator::values);
        public final String name;
        public final Component caption;

        SelectionIndicator(String name, String key) {
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