package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.client.gui.screens.SelectScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.registry.CommandRegistry;

public class RaisedClient implements ClientModInitializer {

    public static void registerKeyMappings() {
        KeyBindingHelper.registerKeyBinding(RaisedOptions.OPTIONS);
    }

    public static void registerInputEvents() {
        ClientTickCallback.EVENT.register(minecraft -> {
            while (RaisedOptions.OPTIONS.consumeClick()) {
                minecraft.setScreen(new SelectScreen(null));
            }
        });
    }

    public static void registerCommands() {
        CommandRegistry.INSTANCE.register(false, RaisedCommand::register);
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