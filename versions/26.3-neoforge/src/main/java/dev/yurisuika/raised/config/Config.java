package dev.yurisuika.raised.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.option.Options;
import net.neoforged.fml.loading.FMLPaths;

import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.function.Consumer;

public class Config {

    public static File file = new File(FMLPaths.CONFIGDIR.get().toFile(), Raised.MOD_ID + ".json");
    public static Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().disableHtmlEscaping().create();
    public static Options options = new Options();

    public static Options getOptions() {
        return options;
    }

    public static void setOptions(Options options) {
        Config.options = options;
    }

    public static void save() {
        try {
            FileWriter fileWriter = new FileWriter(file, StandardCharsets.UTF_8);
            fileWriter.write(gson.toJson(getOptions()));
            fileWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void load() {
        if (file.exists()) {
            try {
                setOptions(gson.fromJson(Files.readString(file.toPath()), Options.class));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            save();
        }
    }

    public static void update(Consumer<Options> updater) {
        if (options == null) {
            setOptions(new Options());
        }

        updater.accept(options);
        save();
    }

}