package dev.yurisuika.raised.registry;

import dev.yurisuika.raised.Raised;
import dev.yurisuika.raised.client.gui.Layer;
import dev.yurisuika.raised.util.Configure;
import net.minecraft.resources.Identifier;

import java.util.TreeMap;

public class LayerRegistry {

    public static final TreeMap<Identifier, Layer> LAYERS = new TreeMap<Identifier, Layer>();
    public static final Identifier HOTBAR = Identifier.withDefaultNamespace("hotbar");
    public static final Identifier CHAT = Identifier.withDefaultNamespace("chat");
    public static final Identifier BOSSBAR = Identifier.withDefaultNamespace("bossbar");
    public static final Identifier SIDEBAR = Identifier.withDefaultNamespace("sidebar");
    public static final Identifier EFFECTS = Identifier.withDefaultNamespace("effects");
    public static final Identifier PLAYERS = Identifier.withDefaultNamespace("players");
    public static final Identifier TITLES = Identifier.withDefaultNamespace("titles");
    public static final Identifier SUBTITLES = Identifier.withDefaultNamespace("subtitles");
    public static final Identifier TOASTS = Identifier.withDefaultNamespace("toasts");
    public static final Identifier OTHER = Identifier.withDefaultNamespace("other");

    public static void register(String name) {
        register(name, createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, name));
    }

    public static void register(Identifier name) {
        register(name, createLayer(0, 0, Layer.Direction.X.NONE, Layer.Direction.Y.NONE, name.toString()));
    }

    public static void register(String name, Layer layer) {
        register(Identifier.tryParse(name), layer);
    }

    public static void register(Identifier name, Layer layer) {
        LAYERS.put(name, layer);
        addLayerToConfig(name, layer);
        Raised.LOGGER.info("Registering Raised layer '{}'", name);
    }

    public static void addLayerToConfig(Identifier name, Layer layer) {
        if (!Configure.getLayers().containsKey(name.toString())) {
            Configure.addLayer(name.toString(), layer);
        }
    }

    public static void addLayersToConfig() {
        LAYERS.forEach(LayerRegistry::addLayerToConfig);
    }

    public static Layer createLayer(int displacementX, int displacementY, Layer.Direction.X directionX, Layer.Direction.Y directionY, Identifier sync) {
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