package dev.yurisuika.raised.client;

import com.mojang.blaze3d.platform.InputConstants;
import dev.yurisuika.raised.Raised;
import net.minecraft.client.KeyMapping;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class RaisedOptions {

    public static final KeyMapping OPTIONS = new KeyMapping(
            "key.raised.options",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_GRAVE_ACCENT,
            KeyMapping.Category.register(Identifier.fromNamespaceAndPath(Raised.MOD_ID, "raised"))
    );

}