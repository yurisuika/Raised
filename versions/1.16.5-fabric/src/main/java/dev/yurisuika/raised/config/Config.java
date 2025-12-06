package dev.yurisuika.raised.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

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
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), StandardCharsets.UTF_8));
            bufferedWriter.write(gson.toJson(getOptions()));
            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadConfig() {
        if (file.exists()) {
            try {
                StringBuilder contentBuilder = new StringBuilder();
                try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
                    stream.forEach(s -> contentBuilder.append(s).append("\n"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                setOptions(gson.fromJson(contentBuilder.toString(), Options.class));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            saveConfig();
        }
    }

}