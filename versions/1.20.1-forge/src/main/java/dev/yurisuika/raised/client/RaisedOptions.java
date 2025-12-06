package dev.yurisuika.raised.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class RaisedOptions {

    public static final KeyMapping OPTIONS = new KeyMapping(
            "key.raised.options",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_GRAVE_ACCENT,
            "key.categories.raised.raised"
    );

}