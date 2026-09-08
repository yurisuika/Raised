package dev.yurisuika.raised.util;

import dev.yurisuika.raised.client.gui.group.Group;
import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.option.AdditionalSettings;
import net.minecraft.resources.Identifier;

import java.util.TreeMap;
import java.util.TreeSet;

public class Configure {

    public static class Groups {

        public static TreeMap<String, Group> getGroups() {
            return Config.getOptions().getGroups();
        }

        public static void setGroups(TreeMap<String, Group> groups) {
            Config.getOptions().setGroups(groups);
            Config.saveConfig();
        }

        public static Group getGroup(String groupName) {
            return getGroups().get(groupName);
        }

        public static void setGroup(String groupName, Group group) {
            setOffsetX(groupName, group.getOffset().getX());
            setOffsetY(groupName, group.getOffset().getY());
            setLayers(groupName, group.getLayers());
            Config.saveConfig();
        }

        public static void addGroup(String groupName, Group group) {
            getGroups().putIfAbsent(groupName, group);
            Config.saveConfig();
        }

        public static void removeGroup(String groupName) {
            getGroups().remove(groupName);
            Config.saveConfig();
        }

        public static void renameGroup(String groupName, String groupNameNew) {
            TreeMap<String, Group> groups = getGroups();
            if (groups.containsKey(groupName) && !groups.containsKey(groupNameNew)) {
                Group value = groups.remove(groupName);
                groups.put(groupNameNew, value);
            }
            Config.saveConfig();
        }

        public static Group.Offset getOffset(String groupName) {
            return getGroup(groupName).getOffset();
        }

        public static void setOffset(String groupName, int x, int y) {
            getGroup(groupName).setOffset(new Group.Offset(x, y));
            Config.saveConfig();
        }

        public static int getOffsetX(String groupName) {
            return getGroup(groupName).getOffset().getX();
        }

        public static void setOffsetX(String groupName, int x) {
            getGroup(groupName).getOffset().setX(x);
            Config.saveConfig();
        }

        public static int getOffsetY(String groupName) {
            return getGroup(groupName).getOffset().getY();
        }

        public static void setOffsetY(String groupName, int y) {
            getGroup(groupName).getOffset().setY(y);
            Config.saveConfig();
        }

        public static TreeSet<String> getLayers(String groupName) {
            return getGroup(groupName).getLayers();
        }

        public static void setLayers(String groupName, TreeSet<String> layers) {
            getGroup(groupName).setLayers(layers);
            Config.saveConfig();
        }

        public static void addLayer(String groupName, Identifier layerName) {
            addLayer(groupName, layerName.toString());
        }

        public static void addLayer(String groupName, String layerName) {
            getLayers(groupName).add(layerName);
            Config.saveConfig();
        }

        public static void removeLayer(String groupName, Identifier layerName) {
            removeLayer(groupName, layerName.toString());
        }

        public static void removeLayer(String groupName, String layerName) {
            getLayers(groupName).remove(layerName);
            Config.saveConfig();
        }

    }

    public static class Layers {

        public static TreeMap<String, Layer> getLayers() {
            return Config.getOptions().getLayers();
        }

        public static void setLayers(TreeMap<String, Layer> layers) {
            Config.getOptions().setLayers(layers);
            Config.saveConfig();
        }

        public static Layer getLayer(String layerName) {
            return getLayers().get(layerName);
        }

        public static void setLayer(String layerName, Layer layer) {
            setAnchor(layerName, layer.getAnchor());
            Config.saveConfig();
        }

        public static void addLayer(String layerName, Layer layer) {
            getLayers().putIfAbsent(layerName, layer);
            Config.saveConfig();
        }

        public static void removeLayer(String layerName) {
            getLayers().remove(layerName);
            Config.saveConfig();
        }

        public static Layer.Anchor getAnchor(String layerName) {
            return getLayer(layerName).getAnchor();
        }

        public static void setAnchor(String layerName, Layer.Anchor anchor) {
            getLayer(layerName).setAnchor(anchor);
            Config.saveConfig();
        }

    }

    public static AdditionalSettings getAdditionalSettings() {
        return Config.getOptions().getAdditionalSettings();
    }

    public static void setAdditionalSettings(AdditionalSettings additionalSettings) {
        Config.getOptions().setAdditionalSettings(additionalSettings);
        Config.saveConfig();
    }

    public static AdditionalSettings.HotbarSelectionFix getHotbarSelectionFix() {
        return getAdditionalSettings().getHotbarSelectionFix();
    }

    public static void setHotbarSelectionFix(AdditionalSettings.HotbarSelectionFix hotbarSelectionFix) {
        getAdditionalSettings().setHotbarSelectionFix(hotbarSelectionFix);
        Config.saveConfig();
    }

}