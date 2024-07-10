package dev.yurisuika.raised;

import dev.yurisuika.raised.client.RaisedOptions;
import dev.yurisuika.raised.client.commands.RaisedCommand;
import dev.yurisuika.raised.client.gui.RaisedGui;
import dev.yurisuika.raised.client.gui.screens.RaisedScreen;
import dev.yurisuika.raised.util.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterClientCommandsEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod("raised")
public class Raised {

    @EventBusSubscriber(modid = "raised", value = Dist.CLIENT)
    public static class ClientNeoForgeEvents {

        @SubscribeEvent
        public static void keyInput(InputEvent.Key event) {
            while (RaisedOptions.options.consumeClick()) {
                Minecraft.getInstance().setScreen(new RaisedScreen(Component.translatable("options.raised.title")));
            }
        }

        @SubscribeEvent
        public static void registerClientCommands(RegisterClientCommandsEvent event) {
            RaisedCommand.register(event.getDispatcher(), event.getBuildContext());
        }

    }

    @EventBusSubscriber(modid = "raised", bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void clientSetup(FMLClientSetupEvent event) {
            NeoForge.EVENT_BUS.register(new RaisedGui.Hotbar());
            NeoForge.EVENT_BUS.register(new RaisedGui.Chat());
            NeoForge.EVENT_BUS.register(new RaisedGui.Bossbar());
            NeoForge.EVENT_BUS.register(new RaisedGui.Sidebar());
            NeoForge.EVENT_BUS.register(new RaisedGui.Effects());
            NeoForge.EVENT_BUS.register(new RaisedGui.Players());
            NeoForge.EVENT_BUS.register(new RaisedGui.Other());
        }

        @SubscribeEvent
        public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
            event.register(RaisedOptions.options);
        }

    }

    public Raised() {
        Config.loadConfig();
    }

}