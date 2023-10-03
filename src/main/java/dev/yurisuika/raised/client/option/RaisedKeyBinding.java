package dev.yurisuika.raised.client.option;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class RaisedKeyBinding {

    public static KeyBinding hudDown;
    public static KeyBinding hudReset;
    public static KeyBinding hudUp;
    public static KeyBinding chatDown;
    public static KeyBinding chatReset;
    public static KeyBinding chatUp;

    public static void registerKeyBindings() {
        hudDown = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.raised.hud.down",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_1,
                "key.categories.raised"
        ));
        hudReset = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.raised.hud.reset",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_4,
                "key.categories.raised"
        ));
        hudUp = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.raised.hud.up",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_7,
                "key.categories.raised"
        ));
        chatDown = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.raised.chat.down",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_2,
                "key.categories.raised"
        ));
        chatReset = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.raised.chat.reset",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_5,
                "key.categories.raised"
        ));
        chatUp = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.raised.chat.up",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_KP_8,
                "key.categories.raised"
        ));
    }

}