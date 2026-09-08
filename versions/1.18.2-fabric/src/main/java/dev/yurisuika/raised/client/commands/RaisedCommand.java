package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.yurisuika.raised.client.gui.group.Group;
import dev.yurisuika.raised.commands.arguments.AnchorArgument;
import dev.yurisuika.raised.commands.arguments.GroupArgument;
import dev.yurisuika.raised.commands.arguments.LayerArgument;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.option.Options;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.TranslatableComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("raised")
                .then(ClientCommandManager.literal("config")
                        .then(ClientCommandManager.literal("reload")
                                .executes(commandContext -> {
                                    Config.loadConfig();
                                    LayerRegistry.addLayersToConfig();
                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(ClientCommandManager.literal("reset")
                                .executes(commandContext -> {
                                    Configure.Groups.setGroups(new Options().getGroups());
                                    Configure.Layers.setLayers(new Options().getLayers());
                                    LayerRegistry.addLayersToConfig();
                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
                .then(ClientCommandManager.literal("group")
                        .then(ClientCommandManager.literal("add")
                                .then(ClientCommandManager.argument("name", StringArgumentType.string())
                                        .executes(commandContext -> {
                                            String name = StringArgumentType.getString(commandContext, "name");
                                            if (Configure.Groups.getGroups().containsKey(name)) {
                                                commandContext.getSource().sendError(new TranslatableComponent("commands.raised.group.error", name));
                                                return 0;
                                            } else {
                                                Configure.Groups.addGroup(name, new Group(new Group.Offset(0, 0), new TreeSet<>()));
                                                commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.add", name));
                                                return 1;
                                            }
                                        })
                                )
                        )
                        .then(ClientCommandManager.literal("remove")
                                .then(ClientCommandManager.argument("group", GroupArgument.group())
                                        .executes(commandContext -> {
                                            String group = GroupArgument.getGroup(commandContext, "group");
                                            Configure.Groups.removeGroup(group);
                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.remove", group));
                                            return 1;
                                        })
                                )
                        )
                        .then(ClientCommandManager.literal("rename")
                                .then(ClientCommandManager.argument("group", GroupArgument.group())
                                        .then(ClientCommandManager.argument("name", StringArgumentType.string())
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    String name = StringArgumentType.getString(commandContext, "name");
                                                    if (Configure.Groups.getGroups().containsKey(name)) {
                                                        commandContext.getSource().sendError(new TranslatableComponent("commands.raised.group.error", name));
                                                        return 0;
                                                    } else {
                                                        Configure.Groups.renameGroup(group, name);
                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.rename", group, name));
                                                        return 1;
                                                    }
                                                })
                                        )
                                )
                        )
                        .then(ClientCommandManager.literal("settings")
                                .then(ClientCommandManager.argument("group", GroupArgument.group())
                                        .then(ClientCommandManager.literal("offset")
                                                .then(ClientCommandManager.literal("x")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.settings.offset.x.query", group, Configure.Groups.getOffsetX(group)));
                                                            return 1;
                                                        })
                                                        .then(ClientCommandManager.argument("x", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Configure.Groups.setOffsetX(group, IntegerArgumentType.getInteger(commandContext, "x"));
                                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.settings.offset.x.set", group, Configure.Groups.getOffsetX(group)));
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                                .then(ClientCommandManager.literal("y")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.settings.offset.y.query",group, Configure.Groups.getOffsetY(group)));
                                                            return 1;
                                                        })
                                                        .then(ClientCommandManager.argument("y", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Configure.Groups.setOffsetY(group, IntegerArgumentType.getInteger(commandContext, "y"));
                                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.settings.offset.y.set", group, Configure.Groups.getOffsetY(group)));
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                        )
                                        .then(ClientCommandManager.literal("layers")
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    List<String> layers = new ArrayList<>(Configure.Groups.getLayers(group));
                                                    if (layers.isEmpty()) {
                                                        commandContext.getSource().sendError(new TranslatableComponent("commands.raised.group.empty", group));
                                                        return 0;
                                                    } else {
                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.settings.layers.query", group, layers.toString()));
                                                        return 1;
                                                    }
                                                })
                                                .then(ClientCommandManager.literal("add")
                                                        .then(ClientCommandManager.argument("layer", LayerArgument.layer())
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                    if (!Configure.Groups.getLayers(group).contains(layer)) {
                                                                        commandContext.getSource().sendError(new TranslatableComponent("commands.raised.group.settings.layers.add.error", group, layer));
                                                                        return 0;
                                                                    } else {
                                                                        Configure.Groups.addLayer(group, layer);
                                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.settings.layers.add", layer, group));
                                                                        return 1;
                                                                    }
                                                                })
                                                        )
                                                )
                                                .then(ClientCommandManager.literal("remove")
                                                        .then(ClientCommandManager.argument("layer", StringArgumentType.string())
                                                                .suggests((commandContext, builder) -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    return SharedSuggestionProvider.suggest(Configure.Groups.getLayers(group), builder);
                                                                })
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = StringArgumentType.getString(commandContext, "layer");
                                                                    if (!Configure.Groups.getLayers(group).contains(layer)) {
                                                                        commandContext.getSource().sendError(new TranslatableComponent("commands.raised.group.settings.layers.remove.error", group, layer));
                                                                        return 0;
                                                                    } else {
                                                                        Configure.Groups.removeLayer(group, layer);
                                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.settings.layers.remove", layer, group));
                                                                        return 1;
                                                                    }
                                                                })
                                                        )
                                                )
                                        )
                                )
                        )
                )
                .then(ClientCommandManager.literal("layer")
                        .then(ClientCommandManager.literal("settings")
                                .then(ClientCommandManager.argument("layer", LayerArgument.layer())
                                        .then(ClientCommandManager.literal("anchor")
                                                .executes(commandContext -> {
                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.settings.anchor.query", layer, Configure.Layers.getAnchor(layer).caption()));
                                                    return 1;
                                                })
                                                .then(ClientCommandManager.argument("anchor", AnchorArgument.anchor())
                                                        .executes(commandContext -> {
                                                            String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                            Configure.Layers.setAnchor(layer, AnchorArgument.getAnchor(commandContext, "anchor"));
                                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.settings.anchor.set", layer, Configure.Layers.getAnchor(layer).caption()));
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                        )
                )
        );
    }

}