package dev.yurisuika.raised;

import dev.yurisuika.raised.config.Config;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Raised implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("raised");

    public static void loadConfiguration() {
        Config.loadConfig();
    }

    @Override
    public void onInitialize() {
        loadConfiguration();
    }

}