package dev.yurisuika.raised.client.option;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class RaisedKeyBinding {

    public static final KeyBinding hudDown = new KeyBinding(
            "key.raised.hud.down",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_1,
            "key.categories.raised"
    );
    public static final KeyBinding hudReset = new KeyBinding(
            "key.raised.hud.reset",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_4,
            "key.categories.raised"
    );
    public static final KeyBinding hudUp = new KeyBinding(
            "key.raised.hud.up",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_7,
            "key.categories.raised"
    );
    public static final KeyBinding chatDown = new KeyBinding(
            "key.raised.chat.down",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_2,
            "key.categories.raised"
    );
    public static final KeyBinding chatReset = new KeyBinding(
            "key.raised.chat.reset",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_5,
            "key.categories.raised"
    );
    public static final KeyBinding chatUp = new KeyBinding(
            "key.raised.chat.up",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_8,
            "key.categories.raised"
    );

}