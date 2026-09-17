package dev.yurisuika.raised.option;

import dev.yurisuika.raised.client.gui.group.Group;
import dev.yurisuika.raised.client.gui.group.Groups;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;

import java.util.TreeMap;

public class Options {

    public TreeMap<String, Group> groups = new TreeMap<String, Group>() {{
        put("Default", Groups.createGroup(0, 2,
                Layers.HOTBAR.toString(), Layers.createLayer(Layer.Anchor.BOTTOM),
                Layers.ACTION_BAR.toString(), Layers.createLayer(Layer.Anchor.BOTTOM)));
    }};

    public TreeMap<String, Group> getGroups() {
        return groups;
    }

    public void setGroups(TreeMap<String, Group> groups) {
        this.groups = groups;
    }

}