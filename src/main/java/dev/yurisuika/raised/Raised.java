package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Validate;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

public class Raised {

    @Mod("raised")
    public static class Client {

        @Mod.EventBusSubscriber(modid = "raised", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
        public static class GameEvents {

            @SubscribeEvent
            public static void registerInputEvents(InputEvent.Key event) {
                while (RaisedOptions.OPTIONS.consumeClick()) {
                    Minecraft.getInstance().setScreen(new RaisedScreen(null));
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
                event.register(RaisedOptions.OPTIONS);
            }

            @SubscribeEvent
            public static void registerConfigScreens(FMLConstructModEvent event) {
                ModLoadingContext.get().registerExtensionPoint(ConfigScreenHandler.ConfigScreenFactory.class, () -> new ConfigScreenHandler.ConfigScreenFactory((client, parent) -> new RaisedScreen(parent)));
            }

            @SubscribeEvent
            public static void registerLayers(FMLClientSetupEvent event) {
                LayerRegistry.boostrap();
            }

            @SubscribeEvent(priority = 127)
            public static void validateConfig(FMLClientSetupEvent event) {
                Validate.validateConfig();
            }

        }

        public Client() {
            Config.loadConfig();
        }

    }

    public Raised() {}

}