package dev.yurisuika.raised.client.option;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;

public class RaisedConfig {

    public static File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "raised.json");
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static Config config = new Config();

    public static class Config {

        public Value value = new Value(
                2,
                0
        );
        public Toggle toggle = new Toggle(
                new Toggle.Support(
                        true,
                        true,
                        true
                ),
                false
        );

        public static class Value {

            public int hud;
            public int chat;

            public Value(int hud, int chat) {
                this.hud = hud;
                this.chat = chat;
            }

        }

        public static class Toggle {

            public Support support;
            public boolean sync;

            public Toggle(Support support, boolean sync) {
                this.support = support;
                this.sync = sync;
            }

            public static class Support {

                public boolean pre;
                public boolean post;
                public boolean share;

                public Support(boolean pre, boolean post, boolean share) {
                    this.pre = pre;
                    this.post = post;
                    this.share = share;
                }

            }

        }

    }

    public static void saveConfig() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(getConfig()));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadConfig() {
        try {
            if (file.exists()) {
                config = gson.fromJson(Files.readString(file.toPath()), Config.class);
            } else {
                config = new Config();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setConfig(config);
    }

    public static void setConfig(Config config) {
        RaisedConfig.config = config;
    }

    public static Config getConfig() {
        return config;
    }

    public static void setHud(int value) {
        config.value.hud = value;
        saveConfig();
        putObjects();
    }

    public static void setChat(int value) {
        config.value.chat = value;
        saveConfig();
        putObjects();
    }

    public static void setSupport(boolean pre, boolean post, boolean share) {
        config.toggle.support.pre = pre;
        config.toggle.support.post = post;
        config.toggle.support.share = share;
        saveConfig();
        putObjects();
    }

    public static void setSync(boolean value) {
        config.toggle.sync = value;
        saveConfig();
    }

    public static int getHud() {
        return config.value.hud;
    }

    public static int getChat() {
        if (getSync()) {
            return config.value.hud;
        } else {
            return config.value.chat;
        }
    }

    public static Config.Toggle.Support getSupport() {
        return config.toggle.support;
    }

    public static boolean getSync() {
        return config.toggle.sync;
    }

    public static void putObjects() {
        FabricLoader.getInstance().getObjectShare().put("raised:hud", getSupport().share ? getHud() : 0);
        FabricLoader.getInstance().getObjectShare().put("raised:chat", getSupport().share ? getChat() : 0);
    }

}