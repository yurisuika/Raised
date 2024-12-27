package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.util.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class Raised {

    @Mod("raised")
    public static class Client {

        @Mod.EventBusSubscriber(modid = "raised", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
        public static class GameEvents {

            @SubscribeEvent
            public static void registerInputEvents(InputEvent.Key event) {
                while (RaisedOptions.options.consumeClick()) {
                    Minecraft.getInstance().setScreen(new RaisedScreen(Component.translatable("options.raised.title")));
                }
            }

            @SubscribeEvent
            public static void registerCommands(RegisterClientCommandsEvent event) {
                RaisedCommand.register(event.getDispatcher(), event.getBuildContext());
            }

        }

        @Mod.EventBusSubscriber(modid = "raised", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
        public static class ModEvents {

            @SubscribeEvent
            public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
                event.register(RaisedOptions.options);
            }

        }

        public Client() {
            Config.loadConfig();
        }

    }

    public Raised() {}

}