package dev.yurisuika.raised;

import dev.yurisuika.raised.config.Config;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Raised implements ModInitializer {

    public static final String MOD_ID = "raised";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void loadConfiguration() {
        Config.loadConfig();
    }

    @Override
    public void onInitialize() {
        loadConfiguration();
    }

}