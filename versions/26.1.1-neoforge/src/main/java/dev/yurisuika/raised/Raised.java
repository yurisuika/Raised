package dev.yurisuika.raised;

import dev.yurisuika.raised.config.Config;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(Raised.MOD_ID)
public class Raised {

    public static final String MOD_ID = "raised";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static void loadConfiguration() {
        LOGGER.info("Loading Raised config...");
        Config.loadConfig();
    }

    public Raised() {
        loadConfiguration();
    }

}