package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.MappedLayers;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLConstructModEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = Raised.MOD_ID, dist = Dist.CLIENT)
public class RaisedClient {

    @EventBusSubscriber(modid = Raised.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
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

    @EventBusSubscriber(modid = Raised.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ModEvents {

        @SubscribeEvent
        public static void registerGuiEvents(FMLClientSetupEvent event) {
            NeoForge.EVENT_BUS.register(new MappedLayers());
        }

        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(RaisedOptions.OPTIONS);
        }

        @SubscribeEvent
        public static void registerConfigScreens(FMLConstructModEvent event) {
            ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (modContainer, screen) -> new RaisedScreen(screen));
        }

        @SubscribeEvent
        public static void registerLayers(FMLClientSetupEvent event) {
            LayerRegistry.boostrap();
        }

    }

    public RaisedClient() {}

}