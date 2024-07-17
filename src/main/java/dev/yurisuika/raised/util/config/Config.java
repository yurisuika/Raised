package dev.yurisuika.raised.util.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.yurisuika.raised.config.Options;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;

public class Config {

    public static File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "raised.json");
    public static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().create();
    public static Options options = new Options();

    public static Options getOptions() {
        return options;
    }

    public static void setOptions(Options options) {
        Config.options = options;
    }

    public static void saveConfig() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(getOptions()));
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadConfig() {
        if (file.exists()) {
            try {
                setOptions(gson.fromJson(Files.readString(file.toPath()), Options.class));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            saveConfig();
        }
    }

}