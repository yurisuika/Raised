package com.yurisuika.raised;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
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

    public static final KeyMapping down = new KeyMapping("raised.down", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_SUBTRACT, "raised.title");
    public static final KeyMapping up = new KeyMapping("raised.up", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_ADD, "raised.title");
    public static final KeyMapping offsetDown = new KeyMapping("raised.offset.down", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_DIVIDE, "raised.title");
    public static final KeyMapping offsetUp = new KeyMapping("raised.offset.up", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_MULTIPLY, "raised.title");
    public static final KeyMapping reset = new KeyMapping("raised.reset", KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_KP_ENTER, "raised.title");

    public static File file = new File(FMLPaths.CONFIGDIR.get().toFile(), "raised.json");
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static RaisedConfig config = new RaisedConfig();

    public static void saveConfig() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(getConfig()));
            fileWriter.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void loadConfig() {
        try {
            if (file.exists()) {
                config = gson.fromJson(Files.readString(file.toPath()), RaisedConfig.class);
            } else {
                config = new RaisedConfig();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setConfig(config);
    }

    public static void setConfig(RaisedConfig config) {
        Raised.config = config;
    }

    public static RaisedConfig getConfig() {
        return config;
    }

    public static void setDistance(int change) {
        config.distance += change;
        saveConfig();
    }

    public static void setOffset(int change) {
        config.offset += change;
        saveConfig();
    }

    public static void setReset() {
        config.distance = 2;
        config.offset = 0;
        saveConfig();
    }

    public static int getDistance() {
        return config.distance;
    }

    public static int getOffset() {
        return config.offset;
    }

    @Mod.EventBusSubscriber(modid = "raised", value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.KeyInputEvent event) {
            if (down.consumeClick()) {
                Raised.setDistance(-1);
            }
            if (up.consumeClick()) {
                Raised.setDistance(1);
            }
            if (offsetDown.consumeClick()) {
                Raised.setOffset(-1);
            }
            if (offsetUp.consumeClick()) {
                Raised.setOffset(1);
            }
            if (reset.consumeClick()) {
                Raised.setReset();
            }
        }
    }

    public Raised()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(final FMLClientSetupEvent event) {
        LOGGER.info("Loading Raised!");

        loadConfig();

        ClientRegistry.registerKeyBinding(down);
        ClientRegistry.registerKeyBinding(up);
        ClientRegistry.registerKeyBinding(offsetDown);
        ClientRegistry.registerKeyBinding(offsetUp);
        ClientRegistry.registerKeyBinding(reset);
    }

}