package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.yurisuika.raised.client.gui.group.Groups;
import dev.yurisuika.raised.commands.arguments.GroupArgument;
import dev.yurisuika.raised.commands.arguments.LayerArgument;
import dev.yurisuika.raised.commands.arguments.PositionArgument;
import dev.yurisuika.raised.config.Config;
import dev.yurisuika.raised.option.Options;
import dev.yurisuika.raised.registry.LayerRegistry;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("raised")
                .then(ClientCommandManager.literal("config")
                        .then(ClientCommandManager.literal("reload")
                                .executes(commandContext -> {
                                    Config.load();
                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(ClientCommandManager.literal("reset")
                                .executes(commandContext -> {
                                    Config.setOptions(new Options());
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
                                            if (Config.getOptions().getGroups().containsKey(name)) {
                                                commandContext.getSource().sendError(new TranslatableComponent("commands.raised.group.add.error", name));
                                                return 0;
                                            } else {
                                                Config.update(o -> o.getGroups().putIfAbsent(name, Groups.createDefaultGroup()));
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
                                            if (!Config.getOptions().getGroups().containsKey(group)) {
                                                commandContext.getSource().sendError(new TranslatableComponent("commands.raised.group.remove.error", group));
                                                return 0;
                                            } else {
                                                Config.update(o -> o.getGroups().remove(group));
                                                commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.remove", group));
                                                return 1;
                                            }
                                        })
                                )
                        )
                        .then(ClientCommandManager.literal("rename")
                                .then(ClientCommandManager.argument("group", GroupArgument.group())
                                        .then(ClientCommandManager.argument("name", StringArgumentType.string())
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    String name = StringArgumentType.getString(commandContext, "name");
                                                    if (Config.getOptions().getGroups().containsKey(name)) {
                                                        commandContext.getSource().sendError(new TranslatableComponent("commands.raised.group.rename.error", name));
                                                        return 0;
                                                    } else {
                                                        Config.update(o -> o.getGroups().put(name, o.getGroups().remove(group)));
                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.rename", group, name));
                                                        return 1;
                                                    }
                                                })
                                        )
                                )
                        )
                        .then(ClientCommandManager.literal("options")
                                .then(ClientCommandManager.argument("group", GroupArgument.group())
                                        .then(ClientCommandManager.literal("offset")
                                                .then(ClientCommandManager.literal("x")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.options.offset.x.query", group, Config.getOptions().getGroups().get(group).getOffset().getX()));
                                                            return 1;
                                                        })
                                                        .then(ClientCommandManager.argument("x", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Config.update(o -> o.getGroups().get(group).getOffset().setX(IntegerArgumentType.getInteger(commandContext, "x")));
                                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.options.offset.x.set", group, Config.getOptions().getGroups().get(group).getOffset().getX()));
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                                .then(ClientCommandManager.literal("y")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.options.offset.y.query", group, Config.getOptions().getGroups().get(group).getOffset().getY()));
                                                            return 1;
                                                        })
                                                        .then(ClientCommandManager.argument("y", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Config.update(o -> o.getGroups().get(group).getOffset().setY(IntegerArgumentType.getInteger(commandContext, "y")));
                                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.group.options.offset.y.set", group, Config.getOptions().getGroups().get(group).getOffset().getY()));
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                        )
                                        .then(ClientCommandManager.literal("layer")
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    List<String> layers = new ArrayList<>(Config.getOptions().getGroups().get(group).getLayers().keySet());
                                                    if (layers.isEmpty()) {
                                                        commandContext.getSource().sendError(new TranslatableComponent("commands.raised.layer.query.error", group));
                                                        return 0;
                                                    } else {
                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.query", group, layers.toString()));
                                                        return 1;
                                                    }
                                                })
                                                .then(ClientCommandManager.literal("add")
                                                        .then(ClientCommandManager.argument("layer", LayerArgument.layer())
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                    if (Config.getOptions().getGroups().get(group).getLayers().containsKey(layer)) {
                                                                        commandContext.getSource().sendError(new TranslatableComponent("commands.raised.layer.add.error", group, layer));
                                                                        return 0;
                                                                    } else {
                                                                        ResourceLocation layerKey = LayerArgument.getLayer(commandContext, "layer");
                                                                        Config.update(o -> o.getGroups().get(group).getLayers().put(layer, LayerRegistry.findDefaultLayer(layerKey)));
                                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.add", layer, group));
                                                                        return 1;
                                                                    }
                                                                })
                                                        )
                                                )
                                                .then(ClientCommandManager.literal("remove")
                                                        .then(ClientCommandManager.argument("layer", LayerArgument.layer())
                                                                .suggests((commandContext, builder) -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    return SharedSuggestionProvider.suggest(Config.getOptions().getGroups().get(group).getLayers().keySet().stream().filter(entry -> LayerRegistry.hasLayer(ResourceLocation.tryParse(entry))), builder);
                                                                })
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                    if (!Config.getOptions().getGroups().get(group).getLayers().containsKey(layer)) {
                                                                        commandContext.getSource().sendError(new TranslatableComponent("commands.raised.layer.remove.error", group, layer));
                                                                        return 0;
                                                                    } else {
                                                                        Config.update(o -> o.getGroups().get(group).getLayers().remove(layer));
                                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.remove", layer, group));
                                                                        return 1;
                                                                    }
                                                                })
                                                        )
                                                )
                                                .then(ClientCommandManager.literal("options")
                                                        .then(ClientCommandManager.argument("layer", LayerArgument.layer())
                                                                .suggests((commandContext, builder) -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    return SharedSuggestionProvider.suggest(Config.getOptions().getGroups().get(group).getLayers().keySet().stream().filter(entry -> LayerRegistry.hasLayer(ResourceLocation.tryParse(entry))), builder);
                                                                })
                                                                .then(ClientCommandManager.literal("position")
                                                                        .executes(commandContext -> {
                                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                                            String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.options.position.query", layer, group, Config.getOptions().getGroups().get(group).getLayers().get(layer).getPosition().caption()));
                                                                            return 1;
                                                                        })
                                                                        .then(ClientCommandManager.argument("position", PositionArgument.position())
                                                                                .executes(commandContext -> {
                                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                                    Config.update(o -> o.getGroups().get(group).getLayers().get(layer).setPosition(PositionArgument.getPosition(commandContext, "position")));
                                                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.options.position.set", layer, group, Config.getOptions().getGroups().get(group).getLayers().get(layer).getPosition().caption()));
                                                                                    return 1;
                                                                                })
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

}