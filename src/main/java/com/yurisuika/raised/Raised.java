package com.yurisuika.raised;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;


public class Raised implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("Raised");

    private static final KeyBinding down = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "raised.down",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_MINUS,
            "raised.title"
    ));

    private static final KeyBinding up = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "raised.up",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_EQUAL,
            "raised.title"
    ));

    public static int distance = 2;

    public static void setDistance(int change) {
        distance += change;
    }

    public static int getDistance() {
        return distance;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Loading Raised!");

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (down.wasPressed()) {
                setDistance(-1);
            }
        });

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (up.wasPressed()) {
                setDistance(1);
            }
        });
    }

}
