package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import dev.yurisuika.raised.client.gui.group.Groups;
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
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

public class RaisedCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(Commands.literal("raised")
                .then(Commands.literal("config")
                        .then(Commands.literal("reload")
                                .executes(commandContext -> {
                                    Config.load();
                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.config.reload"), false);
                                    return 1;
                                })
                        )
                        .then(Commands.literal("reset")
                                .executes(commandContext -> {
                                    Config.setOptions(new Options());
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
                                                commandContext.getSource().sendFailure(Component.translatable("commands.raised.group.add.error", name));
                                                return 0;
                                            } else {
                                                Config.update(o -> o.getGroups().putIfAbsent(name, Groups.createDefaultGroup()));
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
                                            if (!Config.getOptions().getGroups().containsKey(group)) {
                                                commandContext.getSource().sendFailure(Component.translatable("commands.raised.group.remove.error", group));
                                                return 0;
                                            } else {
                                                Config.update(o -> o.getGroups().remove(group));
                                                commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.remove", group), false);
                                                return 1;
                                            }
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
                                                        commandContext.getSource().sendFailure(Component.translatable("commands.raised.group.rename.error", name));
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
                        .then(Commands.literal("options")
                                .then(Commands.argument("group", GroupArgument.group())
                                        .then(Commands.literal("offset")
                                                .then(Commands.literal("x")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.options.offset.x.query", group, Config.getOptions().getGroups().get(group).getOffset().getX()), false);
                                                            return 1;
                                                        })
                                                        .then(Commands.argument("x", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Config.update(o -> o.getGroups().get(group).getOffset().setX(IntegerArgumentType.getInteger(commandContext, "x")));
                                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.options.offset.x.set", group, Config.getOptions().getGroups().get(group).getOffset().getX()), false);
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                                .then(Commands.literal("y")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.options.offset.y.query", group, Config.getOptions().getGroups().get(group).getOffset().getY()), false);
                                                            return 1;
                                                        })
                                                        .then(Commands.argument("y", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Config.update(o -> o.getGroups().get(group).getOffset().setY(IntegerArgumentType.getInteger(commandContext, "y")));
                                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.group.options.offset.y.set", group, Config.getOptions().getGroups().get(group).getOffset().getY()), false);
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                        )
                                        .then(Commands.literal("layer")
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    List<String> layers = new ArrayList<>(Config.getOptions().getGroups().get(group).getLayers().keySet());
                                                    if (layers.isEmpty()) {
                                                        commandContext.getSource().sendFailure(Component.translatable("commands.raised.layer.query.error", group));
                                                        return 0;
                                                    } else {
                                                        commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.query", group, layers.toString()), false);
                                                        return 1;
                                                    }
                                                })
                                                .then(Commands.literal("add")
                                                        .then(Commands.argument("layer", LayerArgument.layer())
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                    if (Config.getOptions().getGroups().get(group).getLayers().containsKey(layer)) {
                                                                        commandContext.getSource().sendFailure(Component.translatable("commands.raised.layer.add.error", group, layer));
                                                                        return 0;
                                                                    } else {
                                                                        ResourceLocation layerKey = LayerArgument.getLayer(commandContext, "layer");
                                                                        Config.update(o -> o.getGroups().get(group).getLayers().put(layer, LayerRegistry.findDefaultLayer(layerKey)));
                                                                        commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.add", layer, group), false);
                                                                        return 1;
                                                                    }
                                                                })
                                                        )
                                                )
                                                .then(Commands.literal("remove")
                                                        .then(Commands.argument("layer", LayerArgument.layer())
                                                                .suggests((commandContext, builder) -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    return SharedSuggestionProvider.suggest(Config.getOptions().getGroups().get(group).getLayers().keySet().stream().filter(entry -> LayerRegistry.hasLayer(ResourceLocation.tryParse(entry))), builder);
                                                                })
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                    if (!Config.getOptions().getGroups().get(group).getLayers().containsKey(layer)) {
                                                                        commandContext.getSource().sendFailure(Component.translatable("commands.raised.layer.remove.error", group, layer));
                                                                        return 0;
                                                                    } else {
                                                                        Config.update(o -> o.getGroups().get(group).getLayers().remove(layer));
                                                                        commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.remove", layer, group), false);
                                                                        return 1;
                                                                    }
                                                                })
                                                        )
                                                )
                                                .then(Commands.literal("options")
                                                        .then(Commands.argument("layer", LayerArgument.layer())
                                                                .suggests((commandContext, builder) -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    return SharedSuggestionProvider.suggest(Config.getOptions().getGroups().get(group).getLayers().keySet().stream().filter(entry -> LayerRegistry.hasLayer(ResourceLocation.tryParse(entry))), builder);
                                                                })
                                                                .then(Commands.literal("anchor")
                                                                        .executes(commandContext -> {
                                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                                            String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.options.anchor.query", layer, group, Config.getOptions().getGroups().get(group).getLayers().get(layer).getAnchor().caption()), false);
                                                                            return 1;
                                                                        })
                                                                        .then(Commands.argument("anchor", AnchorArgument.anchor())
                                                                                .executes(commandContext -> {
                                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                                    String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                                                    Config.update(o -> o.getGroups().get(group).getLayers().get(layer).setAnchor(AnchorArgument.getAnchor(commandContext, "anchor")));
                                                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.options.anchor.set", layer, group, Config.getOptions().getGroups().get(group).getLayers().get(layer).getAnchor().caption()), false);
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