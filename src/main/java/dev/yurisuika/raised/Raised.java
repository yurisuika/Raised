package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.event.ClientStartedEvent;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Validate;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.registry.CommandRegistry;

public class Raised {

    public static class Client implements ClientModInitializer {

        public static void registerKeyMappings() {
            KeyBindingHelper.registerKeyBinding(RaisedOptions.OPTIONS);
        }

        public static void registerInputEvents() {
            ClientTickCallback.EVENT.register(minecraft -> {
                while (RaisedOptions.OPTIONS.consumeClick()) {
                    minecraft.setScreen(new RaisedScreen(null));
                }
            });
        }

        public static void registerCommands() {
            CommandRegistry.INSTANCE.register(false, RaisedCommand::register);
        }

        public static void registerLayers() {
            LayerRegistry.boostrap();
        }

        public static void validateLayers() {
            ClientStartedEvent.CLIENT_STARTED.register(minecraft -> Validate.validateConfig());
        }

        public void onInitializeClient() {
            Config.loadConfig();

            registerKeyMappings();
            registerInputEvents();
            registerCommands();
            registerLayers();
            validateLayers();
        }

    }

}