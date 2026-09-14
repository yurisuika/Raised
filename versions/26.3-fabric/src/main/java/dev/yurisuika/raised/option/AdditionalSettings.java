package dev.yurisuika.raised.option;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;

public class AdditionalSettings {

    public HotbarSelectionFix hotbarSelectionFix;

    public AdditionalSettings(HotbarSelectionFix hotbarSelectionFix) {
        this.hotbarSelectionFix = hotbarSelectionFix;
    }

    public HotbarSelectionFix getHotbarSelectionFix() {
        return hotbarSelectionFix;
    }

    public void setHotbarSelectionFix(HotbarSelectionFix hotbarSelectionFix) {
        this.hotbarSelectionFix = hotbarSelectionFix;
    }

    public enum HotbarSelectionFix implements StringRepresentable {

        REPLACE("replace", "options.raised.hotbar_selection_fix.replace"),
        PATCH("patch", "options.raised.hotbar_selection_fix.patch"),
        AUTO("auto", "options.raised.hotbar_selection_fix.auto"),
        NONE("none", "options.raised.hotbar_selection_fix.none");

        public static final EnumCodec<HotbarSelectionFix> CODEC = StringRepresentable.fromEnum(HotbarSelectionFix::values);
        public final String name;
        public final Component caption;

        HotbarSelectionFix(String name, String key) {
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