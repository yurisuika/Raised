package dev.yurisuika.raised.client.option;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class RaisedKeyBinding {

    public static final KeyBinding options = new KeyBinding(
            "key.raised.options",
            KeyConflictContext.IN_GAME,
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_0,
            "key.categories.raised"
    );

}