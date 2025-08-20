package dev.yurisuika.raised.registry;

import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.util.Configure;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.LoggerFactory;

import java.util.TreeMap;

public class LayerRegistry {

    public static final TreeMap<ResourceLocation, Layer> LAYERS = new TreeMap<ResourceLocation, Layer>();
    public static final ResourceLocation HOTBAR = ResourceLocation.withDefaultNamespace("hotbar");
    public static final ResourceLocation CHAT = ResourceLocation.withDefaultNamespace("chat");
    public static final ResourceLocation BOSSBAR = ResourceLocation.withDefaultNamespace("bossbar");
    public static final ResourceLocation SIDEBAR = ResourceLocation.withDefaultNamespace("sidebar");
    public static final ResourceLocation EFFECTS = ResourceLocation.withDefaultNamespace("effects");
    public static final ResourceLocation PLAYERS = ResourceLocation.withDefaultNamespace("players");
    public static final ResourceLocation TITLES = ResourceLocation.withDefaultNamespace("titles");
    public static final ResourceLocation SUBTITLES = ResourceLocation.withDefaultNamespace("subtitles");
    public static final ResourceLocation TOASTS = ResourceLocation.withDefaultNamespace("toasts");
    public static final ResourceLocation OTHER = ResourceLocation.withDefaultNamespace("other");

    public static void register(String name) {
        register(name, createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, name));
    }

    public static void register(ResourceLocation name) {
        register(name, createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, name.toString()));
    }

    public static void register(String name, Layer layer) {
        register(ResourceLocation.tryParse(name), layer);
    }

    public static void register(ResourceLocation name, Layer layer) {
        LAYERS.put(name, layer);
        addLayerToConfig(name, layer);
        LoggerFactory.getLogger("raised").info("Registering Raised layer '{}'", name);
    }

    public static void addLayerToConfig(ResourceLocation name, Layer layer) {
        if (!Configure.getLayers().containsKey(name.toString())) {
            Configure.addLayer(name.toString(), layer);
        }
    }

    public static void addLayersToConfig() {
        LAYERS.forEach(LayerRegistry::addLayerToConfig);
    }

    public static Layer createLayer(int displacementX, int displacementY, Layer.Direction.X directionX, Layer.Direction.Y directionY, ResourceLocation sync) {
        return createLayer(displacementX, displacementY, directionX, directionY, sync.toString());
    }

    public static Layer createLayer(int displacementX, int displacementY, Layer.Direction.X directionX, Layer.Direction.Y directionY, String sync) {
        return new Layer(new Layer.Displacement(displacementX, displacementY), new Layer.Direction(directionX, directionY), sync);
    }

    public static void boostrap() {
        register(HOTBAR, createLayer(0, 2, Layer.Direction.X.NONE, Layer.Direction.Y.UP, HOTBAR));
        register(CHAT, createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.UP, CHAT));
        register(BOSSBAR, createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.DOWN, BOSSBAR));
        register(SIDEBAR, createLayer(0, 0, Layer.Direction.X.LEFT, Layer.Direction.Y.NONE, SIDEBAR));
        register(EFFECTS, createLayer(0, 0, Layer.Direction.X.LEFT, Layer.Direction.Y.DOWN, EFFECTS));
        register(PLAYERS, createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.DOWN, PLAYERS));
        register(TITLES, createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, TITLES));
        register(SUBTITLES, createLayer(0, 0, Layer.Direction.X.LEFT, Layer.Direction.Y.UP, SUBTITLES));
        register(TOASTS, createLayer(0, 0, Layer.Direction.X.LEFT, Layer.Direction.Y.DOWN, TOASTS));
        register(OTHER, createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, OTHER));
    }

}