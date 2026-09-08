package dev.yurisuika.raised.option;

import dev.yurisuika.raised.client.gui.group.Group;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;

import java.util.TreeMap;
import java.util.TreeSet;

public class Options {

    public TreeMap<String, Group> groups = new TreeMap<String, Group>() {{
        put(
            "Default",
            new Group(
                new Group.Offset(0, 2),
                new TreeSet<String>() {{
                    add(Layers.HOTBAR.toString());
                    add(Layers.ACTION_BAR.toString());
                }}
            )
        );
    }};
    public TreeMap<String, Layer> layers = new TreeMap<String, Layer>() {};

    public TreeMap<String, Group> getGroups() {
        return groups;
    }

    public void setGroups(TreeMap<String, Group> groups) {
        this.groups = groups;
    }

    public TreeMap<String, Layer> getLayers() {
        return layers;
    }

    public void setLayers(TreeMap<String, Layer> layers) {
        this.layers = layers;
    }

}