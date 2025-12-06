package dev.yurisuika.raised;

import dev.yurisuika.raised.config.Config;
import net.fabricmc.api.ModInitializer;

public class Raised implements ModInitializer {

    public static void loadConfiguration() {
        Config.loadConfig();
    }

    @Override
    public void onInitialize() {
        loadConfiguration();
    }

}