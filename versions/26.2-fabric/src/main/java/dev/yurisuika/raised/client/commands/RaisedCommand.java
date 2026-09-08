package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.yurisuika.raised.client.gui.group.Group;
import dev.yurisuika.raised.commands.arguments.AnchorArgument;
import dev.yurisuika.raised.commands.arguments.GroupArgument;
import dev.yurisuika.raised.commands.arguments.HotbarSelectionFixArgument;
import dev.yurisuika.raised.commands.arguments.LayerArgument;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.option.Options;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext context) {
        dispatcher.register(ClientCommands.literal("raised")
                .then(ClientCommands.literal("config")
                        .then(ClientCommands.literal("reload")
                                .executes(commandContext -> {
                                    Config.loadConfig();
                                    LayerRegistry.addLayersToConfig();
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(ClientCommands.literal("reset")
                                .executes(commandContext -> {
                                    Configure.Groups.setGroups(new Options().getGroups());
                                    Configure.Layers.setLayers(new Options().getLayers());
                                    Configure.setAdditionalSettings(new Options().getAdditionalSettings());
                                    LayerRegistry.addLayersToConfig();
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
                .then(ClientCommands.literal("group")
                        .then(ClientCommands.literal("add")
                                .then(ClientCommands.argument("name", StringArgumentType.string())
                                        .executes(commandContext -> {
                                            String name = StringArgumentType.getString(commandContext, "name");
                                            if (Configure.Groups.getGroups().containsKey(name)) {
                                                commandContext.getSource().sendError(Component.translatable("commands.raised.group.error", name));
                                                return 0;
                                            } else {
                                                Configure.Groups.addGroup(name, new Group(new Group.Offset(0, 0), new TreeSet<>()));
                                                commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.add", name));
                                                return 1;
                                            }
                                        })
                                )
                        )
                        .then(ClientCommands.literal("remove")
                                .then(ClientCommands.argument("group", GroupArgument.group())
                                        .executes(commandContext -> {
                                            String group = GroupArgument.getGroup(commandContext, "group");
                                            Configure.Groups.removeGroup(group);
                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.remove", group));
                                            return 1;
                                        })
                                )
                        )
                        .then(ClientCommands.literal("rename")
                                .then(ClientCommands.argument("group", GroupArgument.group())
                                        .then(ClientCommands.argument("name", StringArgumentType.string())
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    String name = StringArgumentType.getString(commandContext, "name");
                                                    if (Configure.Groups.getGroups().containsKey(name)) {
                                                        commandContext.getSource().sendError(Component.translatable("commands.raised.group.error", name));
                                                        return 0;
                                                    } else {
                                                        Configure.Groups.renameGroup(group, name);
                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.rename", group, name));
                                                        return 1;
                                                    }
                                                })
                                        )
                                )
                        )
                        .then(ClientCommands.literal("settings")
                                .then(ClientCommands.argument("group", GroupArgument.group())
                                        .then(ClientCommands.literal("offset")
                                                .then(ClientCommands.literal("x")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.offset.x.query", group, Configure.Groups.getOffsetX(group)));
                                                            return 1;
                                                        })
                                                        .then(ClientCommands.argument("x", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Configure.Groups.setOffsetX(group, IntegerArgumentType.getInteger(commandContext, "x"));
                                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.offset.x.set", group, Configure.Groups.getOffsetX(group)));
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                                .then(ClientCommands.literal("y")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.offset.y.query",group, Configure.Groups.getOffsetY(group)));
                                                            return 1;
                                                        })
                                                        .then(ClientCommands.argument("y", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Configure.Groups.setOffsetY(group, IntegerArgumentType.getInteger(commandContext, "y"));
                                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.offset.y.set", group, Configure.Groups.getOffsetY(group)));
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                        )
                                        .then(ClientCommands.literal("layers")
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    List<String> layers = new ArrayList<>(Configure.Groups.getLayers(group));
                                                    if (layers.isEmpty()) {
                                                        commandContext.getSource().sendError(Component.translatable("commands.raised.group.empty", group));
                                                        return 0;
                                                    } else {
                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.layers.query", group, layers.toString()));
                                                        return 1;
                                                    }
                                                })
                                                .then(ClientCommands.literal("add")
                                                        .then(ClientCommands.argument("layer", LayerArgument.layer())
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                    Configure.Groups.addLayer(group, layer);
                                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.layers.add", layer, group));
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                                .then(ClientCommands.literal("remove")
                                                        .then(ClientCommands.argument("layer", LayerArgument.layer())
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                    Configure.Groups.removeLayer(group, layer);
                                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.layers.remove", layer, group));
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                        )
                                )
                        )
                )
                .then(ClientCommands.literal("layer")
                        .then(ClientCommands.literal("settings")
                                .then(ClientCommands.argument("layer", LayerArgument.layer())
                                        .then(ClientCommands.literal("anchor")
                                                .executes(commandContext -> {
                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.settings.anchor.query", layer, Configure.Layers.getAnchor(layer).caption()));
                                                    return 1;
                                                })
                                                .then(ClientCommands.argument("anchor", AnchorArgument.anchor())
                                                        .executes(commandContext -> {
                                                            String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                            Configure.Layers.setAnchor(layer, AnchorArgument.getAnchor(commandContext, "anchor"));
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.settings.anchor.set", layer, Configure.Layers.getAnchor(layer).caption()));
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )
                )
                .then(ClientCommands.literal("additionalSettings")
                        .then(ClientCommands.literal("hotbarSelectionFix")
                                .executes(commandContext -> {
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.additional_settings.hotbar_selection_fix.query", Configure.getHotbarSelectionFix().caption()));
                                    return 1;
                                })
                                .then(ClientCommands.argument("texture", HotbarSelectionFixArgument.hotbarSelectionFix())
                                        .executes(commandContext -> {
                                            Configure.setHotbarSelectionFix(HotbarSelectionFixArgument.getHotbarSelectionFix(commandContext, "hotbarSelectionFix"));
                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.additional_settings.hotbar_selection_fix.set", Configure.getHotbarSelectionFix().caption()));
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}