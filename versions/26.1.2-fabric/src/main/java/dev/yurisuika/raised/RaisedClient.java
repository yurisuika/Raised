package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.client.gui.screens.SelectScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;

public class RaisedClient implements ClientModInitializer {

    public static void registerKeyMappings() {
        KeyMappingHelper.registerKeyMapping(RaisedOptions.OPTIONS);
    }

    public static void registerInputEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            while (RaisedOptions.OPTIONS.consumeClick()) {
                minecraft.setScreen(new SelectScreen(null));
            }
        });
    }

    public static void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register(RaisedCommand::register);
    }

    public static void registerLayers() {
        Layers.boostrap();
    }

    @Override
    public void onInitializeClient() {
        registerKeyMappings();
        registerInputEvents();
        registerCommands();
        registerLayers();
    }

}