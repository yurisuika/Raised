package dev.yurisuika.raised;

import dev.yurisuika.raised.client.command.RaisedCommand;
import dev.yurisuika.raised.client.gui.screen.RaisedScreen;
import dev.yurisuika.raised.client.option.RaisedKeyBindings;
import dev.yurisuika.raised.util.config.Config;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.text.Text;

public class Raised implements ClientModInitializer {

    @Environment(EnvType.CLIENT)
    public static void registerClientTickEvents() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (RaisedKeyBindings.options.wasPressed()) {
                client.setScreen(new RaisedScreen(Text.translatable("options.raised.title")));
            }
        });
    }

    @Environment(EnvType.CLIENT)
    public static void registerCommands() {
        ClientCommandRegistrationCallback.EVENT.register(RaisedCommand::register);
    }

    @Environment(EnvType.CLIENT)
    public static void registerKeyBindings() {
        KeyBindingHelper.registerKeyBinding(RaisedKeyBindings.options);
    }

    @Override
    public void onInitializeClient() {
        Config.loadConfig();

        registerClientTickEvents();
        registerCommands();
        registerKeyBindings();
    }

}