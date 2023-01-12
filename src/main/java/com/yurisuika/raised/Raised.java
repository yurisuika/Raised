package com.yurisuika.raised;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yurisuika.raised.server.command.RaisedCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;

public class Raised implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("raised");

    private static final KeyBinding hudDown = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.raised.hud.down",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_SUBTRACT,
            "key.categories.raised"
    ));
    private static final KeyBinding hudUp = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.raised.hud.up",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_ADD,
            "key.categories.raised"
    ));
    private static final KeyBinding chatDown = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.raised.chat.down",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_DIVIDE,
            "key.categories.raised"
    ));
    private static final KeyBinding chatUp = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.raised.chat.up",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_MULTIPLY,
            "key.categories.raised"
    ));

    public static File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "raised.json");
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Config config = new Config();

    public static class Config {

        public boolean enabled = true;
        public int hud = 2;
        public int chat = 0;

    }

    public static void saveConfig() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(getConfig()));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadConfig() {
        try {
            if (file.exists()) {
                config = gson.fromJson(Files.readString(file.toPath()), Config.class);
            } else {
                config = new Config();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setConfig(config);
    }

    public static void setConfig(Config config) {
        Raised.config = config;
    }

    public static Config getConfig() {
        return config;
    }

    public static void setEnabled(boolean value) {
        config.enabled = value;
        saveConfig();
        putObjects();
    }

    public static void setHud(int value) {
        config.hud = config.enabled ? value : 0;
        saveConfig();
        putObjects();
    }

    public static void setChat(int value) {
        config.chat = config.enabled ? value : 0;
        saveConfig();
        putObjects();
    }

    public static int getHud() {
        return config.hud;
    }

    public static int getChat() {
        return config.chat;
    }

    public static void putObjects() {
        FabricLoader.getInstance().getObjectShare().put("raised:enabled", config.enabled);
        FabricLoader.getInstance().getObjectShare().put("raised:hud", config.hud);
        FabricLoader.getInstance().getObjectShare().put("raised:chat", config.chat);
    }

    @Override
    public void onInitializeClient() {
        LOGGER.info("Loading Raised!");

        loadConfig();
        putObjects();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (hudDown.wasPressed()) {
                setHud(config.hud - 1);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (hudUp.wasPressed()) {
                setHud(config.hud + 1);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (chatDown.wasPressed()) {
                setChat(config.chat - 1);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (chatUp.wasPressed()) {
                setChat(config.chat + 1);
            }
        });

        ClientCommandRegistrationCallback.EVENT.register(RaisedCommand::register);
    }

}