package dev.yurisuika.raised.client.gui.group;

import dev.yurisuika.raised.client.gui.layer.Layer;

import java.util.TreeMap;

public class Groups {

    public Groups() {}

    public static Group createGroup(int x, int y, Object... keyValues) {
        TreeMap<String, Layer> map = new TreeMap<>();

        for (int i = 0; i < keyValues.length; i += 2) {
            Object key = keyValues[i];
            Object value = keyValues[i + 1];

            map.put((String) key, (Layer) value);
        }

        return new Group(new Group.Offset(x, y), map);
    }

    public static Group createDefaultGroup() {
        return new Group(new Group.Offset(0, 0), new TreeMap<>());
    }

}