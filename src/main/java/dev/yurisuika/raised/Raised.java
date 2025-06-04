package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.Layers;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.util.Validate;
import dev.yurisuika.raised.util.config.Config;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class Raised {

    public static class Client implements ClientModInitializer {

        public static void registerKeyMappings() {
            KeyBindingHelper.registerKeyBinding(RaisedOptions.OPTIONS);
        }

        public static void registerInputEvents() {
            ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
                while (RaisedOptions.OPTIONS.consumeClick()) {
                    minecraft.setScreen(new RaisedScreen(null));
                }
            });
        }

        public static void registerCommands() {
            RaisedCommand.register(ClientCommandManager.DISPATCHER);
        }

        public static void registerLayers() {
            Validate.checkForOldConfig();
            Layers.boostrap();
        }

        public void onInitializeClient() {
            Config.loadConfig();

            registerKeyMappings();
            registerInputEvents();
            registerCommands();
            registerLayers();
        }

    }

}