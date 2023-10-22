package dev.yurisuika.raised.client.option;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class RaisedKeyBinding {

    public static KeyBinding options;

    public static void registerKeyBindings() {
        options = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.raised.options",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_0,
                "key.categories.raised"
        ));
    }

}