package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.GuiEvents;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Validate;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class Raised {

    @Mod("raised")
    public static class Client {

        @Mod.EventBusSubscriber(modid = "raised", bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
        public static class GameEvents {

            @SubscribeEvent
            public static void registerInputEvents(InputEvent.KeyInputEvent event) {
                while (RaisedOptions.OPTIONS.consumeClick()) {
                    Minecraft.getInstance().setScreen(new RaisedScreen(null));
                }
            }

            @SubscribeEvent
            public static void registerCommands(RegisterCommandsEvent event) {
                RaisedCommand.register(event.getDispatcher());
            }

        }

        @Mod.EventBusSubscriber(modid = "raised", bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
        public static class ModEvents {

            @SubscribeEvent
            public static void registerGuiEvents(FMLClientSetupEvent event) {
                MinecraftForge.EVENT_BUS.register(new GuiEvents());
            }

            @SubscribeEvent
            public static void registerKeyMappings(FMLClientSetupEvent event) {
                ClientRegistry.registerKeyBinding(RaisedOptions.OPTIONS);
            }

            @SubscribeEvent
            public static void registerConfigScreens(FMLClientSetupEvent event) {
                ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (client, parent) -> new RaisedScreen(parent));
            }

            @SubscribeEvent
            public static void registerLayers(FMLClientSetupEvent event) {
                LayerRegistry.boostrap();
            }

            @SubscribeEvent(priority = EventPriority.LOWEST)
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