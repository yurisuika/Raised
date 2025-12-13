package dev.yurisuika.raised;

import dev.yurisuika.raised.config.Config;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("raised")
public class Raised {

    public static final Logger LOGGER = LoggerFactory.getLogger("raised");

    @Mod.EventBusSubscriber(modid = "raised", bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ModEvents {

        @SubscribeEvent
        public static void loadConfiguration(FMLCommonSetupEvent event) {
            Config.loadConfig();
        }

    }

    public Raised() {}

}