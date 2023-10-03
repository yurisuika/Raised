package dev.yurisuika.raised;

import dev.yurisuika.raised.server.command.RaisedCommand;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static dev.yurisuika.raised.client.option.RaisedKeyBinding.*;

public class Raised implements ClientModInitializer {

    @Environment(EnvType.CLIENT)
    public static void registerClientTickEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (hudDown.wasPressed()) {
                setHud(config.value.hud - 1);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (hudReset.wasPressed()) {
                setHud(2);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (hudUp.wasPressed()) {
                setHud(config.value.hud + 1);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (chatDown.wasPressed()) {
                setChat(config.value.chat - 1);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (chatReset.wasPressed()) {
                setChat(0);
            }
        });
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (chatUp.wasPressed()) {
                setChat(config.value.chat + 1);
            }
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register(RaisedCommand::register);
    }

    @Override
    public void onInitializeClient() {
        if (!file.exists()) {
            saveConfig();
        }
        loadConfig();
        putObjects();

        registerClientTickEvents();
        registerCommands();
        registerKeyBindings();
    }

}