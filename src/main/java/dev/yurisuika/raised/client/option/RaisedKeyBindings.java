package dev.yurisuika.raised.client.option;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class RaisedKeyBindings {

    public static final KeyBinding options = new KeyBinding(
            "key.raised.options",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_GRAVE_ACCENT,
            "key.raised.categories.raised"
    );

}