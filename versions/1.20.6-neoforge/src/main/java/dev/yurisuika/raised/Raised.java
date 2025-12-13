package dev.yurisuika.raised;

import dev.yurisuika.raised.config.Config;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("raised")
public class Raised {

    public static final Logger LOGGER = LoggerFactory.getLogger("raised");

    @EventBusSubscriber(modid = "raised", bus = EventBusSubscriber.Bus.GAME)
    public static class ModEvents {

        @SubscribeEvent
        public static void loadConfiguration(FMLCommonSetupEvent event) {
            Config.loadConfig();
        }

    }

    public Raised() {}

}