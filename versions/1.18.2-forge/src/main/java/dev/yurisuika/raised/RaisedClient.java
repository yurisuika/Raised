package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.client.gui.screens.SelectScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class RaisedClient {

    @Mod.EventBusSubscriber(modid = Raised.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class GameEvents {

        @SubscribeEvent
        public static void registerInputEvents(InputEvent.KeyInputEvent event) {
            while (RaisedOptions.OPTIONS.consumeClick()) {
                Minecraft.getInstance().setScreen(new SelectScreen(null));
            }
        }

        @SubscribeEvent
        public static void registerCommands(RegisterCommandsEvent event) {
            RaisedCommand.register(event.getDispatcher());
        }

    }

    @Mod.EventBusSubscriber(modid = Raised.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModEvents {

        @SubscribeEvent
        public static void registerGuiEvents(FMLClientSetupEvent event) {
            MinecraftForge.EVENT_BUS.register(new Layers.Curated());
        }

        @SubscribeEvent
        public static void registerKeyMappings(FMLClientSetupEvent event) {
            ClientRegistry.registerKeyBinding(RaisedOptions.OPTIONS);
        }

        @SubscribeEvent
        public static void registerConfigScreens(FMLClientSetupEvent event) {
            ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class, () -> new ConfigGuiHandler.ConfigGuiFactory((client, screen) -> new SelectScreen(screen)));
        }

        @SubscribeEvent
        public static void registerLayers(FMLClientSetupEvent event) {
            Layers.boostrap();
        }

    }

    public RaisedClient() {}

}