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
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class RaisedCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(Commands.literal("raised")
                .then(Commands.literal("config")
                        .then(Commands.literal("reload")
                                .executes(commandContext -> {
                                    Config.load();
                                    LayerRegistry.addDefaultLayersToConfig();
                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.config.reload"), false);
                                    return 1;
                                })
                        )
                        .then(Commands.literal("reset")
                                .executes(commandContext -> {
                                    Config.setOptions(new Options());
                                    LayerRegistry.addDefaultLayersToConfig();
                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.config.reset"), false);
                                    return 1;
                                })
                        )
                )
                .then(Commands.literal("group")
                        .then(Commands.literal("add")
                                .then(Commands.argument("name", StringArgumentType.string())
                                        .executes(commandContext -> {
                                            String name = StringArgumentType.getString(commandContext, "name");
                                            if (Config.getOptions().getGroups().containsKey(name)) {
                                                commandContext.getSource().sendFailure(Component.translatable("commands.raised.group.error", name));
                                                return 0;
                                            } else {
                                                Config.update(o -> o.getGroups().putIfAbsent(name, new Group(new Group.Offset(0, 0), new TreeSet<>())));
                                                commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.add", name), false);
                                                return 1;
                                            }
                                        })
                                )
                        )
                        .then(Commands.literal("remove")
                                .then(Commands.argument("group", GroupArgument.group())
                                        .executes(commandContext -> {
                                            String group = GroupArgument.getGroup(commandContext, "group");
                                            Config.update(o -> o.getGroups().remove(group));
                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.remove", group), false);
                                            return 1;
                                        })
                                )
                        )
                        .then(Commands.literal("rename")
                                .then(Commands.argument("group", GroupArgument.group())
                                        .then(Commands.argument("name", StringArgumentType.string())
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    String name = StringArgumentType.getString(commandContext, "name");
                                                    if (Config.getOptions().getGroups().containsKey(name)) {
                                                        commandContext.getSource().sendFailure(Component.translatable("commands.raised.group.error", name));
                                                        return 0;
                                                    } else {
                                                        Config.update(o -> o.getGroups().put(name, o.getGroups().remove(group)));
                                                        commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.rename", group, name), false);
                                                        return 1;
                                                    }
                                                })
                                        )
                                )
                        )
                        .then(Commands.literal("settings")
                                .then(Commands.argument("group", GroupArgument.group())
                                        .then(Commands.literal("offset")
                                                .then(Commands.literal("x")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.settings.offset.x.query", group, Config.getOptions().getGroups().get(group).getOffset().getX()), false);
                                                            return 1;
                                                        })
                                                        .then(Commands.argument("x", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Config.update(o -> o.getGroups().get(group).getOffset().setX(IntegerArgumentType.getInteger(commandContext, "x")));
                                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.settings.offset.x.set", group, Config.getOptions().getGroups().get(group).getOffset().getX()), false);
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                                .then(Commands.literal("y")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.settings.offset.y.query",group, Config.getOptions().getGroups().get(group).getOffset().getY()), false);
                                                            return 1;
                                                        })
                                                        .then(Commands.argument("y", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Config.update(o -> o.getGroups().get(group).getOffset().setY(IntegerArgumentType.getInteger(commandContext, "y")));
                                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.settings.offset.y.set", group, Config.getOptions().getGroups().get(group).getOffset().getY()), false);
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                        )
                                        .then(Commands.literal("layers")
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    List<String> layers = new ArrayList<>(Config.getOptions().getGroups().get(group).getLayers());
                                                    if (layers.isEmpty()) {
                                                        commandContext.getSource().sendFailure(Component.translatable("commands.raised.group.settings.layers.query.error", group));
                                                        return 0;
                                                    } else {
                                                        commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.settings.layers.query", group, layers.toString()), false);
                                                        return 1;
                                                    }
                                                })
                                                .then(Commands.literal("add")
                                                        .then(Commands.argument("layer", LayerArgument.layer())
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                    if (!Config.getOptions().getGroups().get(group).getLayers().contains(layer)) {
                                                                        commandContext.getSource().sendFailure(Component.translatable("commands.raised.group.settings.layers.add.error", group, layer));
                                                                        return 0;
                                                                    } else {
                                                                        Config.update(o -> o.getGroups().get(group).getLayers().add(layer));
                                                                        commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.settings.layers.add", layer, group), false);
                                                                        return 1;
                                                                    }
                                                                })
                                                        )
                                                )
                                                .then(Commands.literal("remove")
                                                        .then(Commands.argument("layer", StringArgumentType.string())
                                                                .suggests((commandContext, builder) -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    return SharedSuggestionProvider.suggest(Config.getOptions().getGroups().get(group).getLayers(), builder);
                                                                })
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = StringArgumentType.getString(commandContext, "layer");
                                                                    if (!Config.getOptions().getGroups().get(group).getLayers().contains(layer)) {
                                                                        commandContext.getSource().sendFailure(Component.translatable("commands.raised.group.settings.layers.remove.error", group, layer));
                                                                        return 0;
                                                                    } else {
                                                                        Config.update(o -> o.getGroups().get(group).getLayers().remove(layer));
                                                                        commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.settings.layers.remove", layer, group), false);
                                                                        return 1;
                                                                    }
                                                                })
                                                        )
                                                )
                                        )
                                )
                        )
                )
                .then(Commands.literal("layer")
                        .then(Commands.literal("settings")
                                .then(Commands.argument("layer", LayerArgument.layer())
                                        .then(Commands.literal("anchor")
                                                .executes(commandContext -> {
                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.settings.anchor.query", layer, Config.getOptions().getLayers().get(layer).getAnchor().caption()), false);
                                                    return 1;
                                                })
                                                .then(Commands.argument("anchor", AnchorArgument.anchor())
                                                        .executes(commandContext -> {
                                                            String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                            Config.update(o -> o.getLayers().get(layer).setAnchor(AnchorArgument.getAnchor(commandContext, "anchor")));
                                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.settings.anchor.set", layer, Config.getOptions().getLayers().get(layer).getAnchor().caption()), false);
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