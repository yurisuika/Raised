package com.yurisuika.raised;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.platform.InputConstants;
import com.yurisuika.raised.server.commands.RaisedCommand;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;

@Mod("raised")
public class Raised {

    private static final Logger LOGGER = LoggerFactory.getLogger("raised");

    public static final KeyMapping hudDown = new KeyMapping(
            "key.raised.hud.down",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_SUBTRACT,
            "key.categories.raised"
    );
    public static final KeyMapping hudUp = new KeyMapping(
            "key.raised.hud.up",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_ADD,
            "key.categories.raised"
    );
    public static final KeyMapping chatDown = new KeyMapping(
            "key.raised.chat.down",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_DIVIDE,
            "key.categories.raised"
    );
    public static final KeyMapping chatUp = new KeyMapping(
            "key.raised.chat.up",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_KP_MULTIPLY,
            "key.categories.raised"
    );

    public static File file = new File(FMLPaths.CONFIGDIR.get().toFile(), "raised.json");
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
    }

    public static void setHud(int value) {
        config.hud = config.enabled ? value : 0;
        saveConfig();
    }

    public static void setChat(int value) {
        config.chat = config.enabled ? value : 0;
        saveConfig();
    }

    public static int getHud() {
        return config.hud;
    }

    public static int getChat() {
        return config.chat;
    }

    @Mod.EventBusSubscriber(modid = "raised", value = Dist.CLIENT)
    public static class ClientForgeEvents {
        
        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            if (hudDown.consumeClick()) {
                Raised.setHud(config.hud - 1);
            }
            if (hudUp.consumeClick()) {
                Raised.setHud(config.hud + 1);
            }
            if (chatDown.consumeClick()) {
                Raised.setChat(config.chat - 1);
            }
            if (chatUp.consumeClick()) {
                Raised.setChat(config.chat + 1);
            }
        }

        @SubscribeEvent
        public static void onCommandsRegister(RegisterClientCommandsEvent event) {
            RaisedCommand.register(event.getDispatcher());
        }

    }

    public Raised() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(final FMLClientSetupEvent event) {
        LOGGER.info("Loading Raised!");

        loadConfig();

        ClientRegistry.registerKeyBinding(hudDown);
        ClientRegistry.registerKeyBinding(hudUp);
        ClientRegistry.registerKeyBinding(chatDown);
        ClientRegistry.registerKeyBinding(chatUp);
    }

}