package com.yurisuika.raised;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;


public class Raised implements ClientModInitializer {

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
        FabricLoader.getInstance().getObjectShare().put("raised:distance", distance);
    }

    public static int getDistance() {
        return distance;
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Loading Raised!");

        setDistance(0);

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
