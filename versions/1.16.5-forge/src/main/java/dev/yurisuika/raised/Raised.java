package dev.yurisuika.raised;

import dev.yurisuika.raised.config.Config;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Raised.MOD_ID)
public class Raised {

    public static final String MOD_ID = "raised";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void loadConfiguration() {
        Config.loadConfig();
    }

    public Raised() {
        loadConfiguration();
    }

}