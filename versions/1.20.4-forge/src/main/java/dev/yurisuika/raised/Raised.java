package dev.yurisuika.raised;

import dev.yurisuika.raised.config.Config;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod("raised")
public class Raised {

    public static final Logger LOGGER = LoggerFactory.getLogger("raised");

    @Mod.EventBusSubscriber(modid = "raised", bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEvents {

        @SubscribeEvent
        public static void loadConfiguration(FMLCommonSetupEvent event) {
            Config.loadConfig();
        }

    }

    public Raised() {}

}