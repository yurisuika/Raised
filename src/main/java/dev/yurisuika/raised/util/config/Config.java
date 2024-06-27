package dev.yurisuika.raised.util.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.yurisuika.raised.config.RaisedConfig;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;

public class Config {

    public static File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "raised.json");
    public static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().create();
    public static RaisedConfig config = new RaisedConfig();

    public static void saveConfig() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(config));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadConfig() {
        try {
            if (file.exists()) {
                config = gson.fromJson(Files.readString(file.toPath()), RaisedConfig.class);
            } else {
                saveConfig();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}