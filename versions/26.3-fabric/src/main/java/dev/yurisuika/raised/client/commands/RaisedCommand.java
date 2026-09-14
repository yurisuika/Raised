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
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.IdentifierArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext context) {
        dispatcher.register(ClientCommands.literal("raised")
                .then(ClientCommands.literal("config")
                        .then(ClientCommands.literal("reload")
                                .executes(commandContext -> {
                                    Config.load();
                                    LayerRegistry.addDefaultLayersToConfig();
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(ClientCommands.literal("reset")
                                .executes(commandContext -> {
                                    Config.setOptions(new Options());
                                    LayerRegistry.addDefaultLayersToConfig();
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
                                            if (Config.getOptions().getGroups().containsKey(name)) {
                                                commandContext.getSource().sendError(Component.translatable("commands.raised.group.add.error", name));
                                                return 0;
                                            } else {
                                                Config.update(o -> o.getGroups().putIfAbsent(name, new Group(new Group.Offset(0, 0), new TreeSet<>())));
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
                                            if (!Config.getOptions().getGroups().containsKey(group)) {
                                                commandContext.getSource().sendError(Component.translatable("commands.raised.group.remove.error", group));
                                                return 0;
                                            } else {
                                                Config.update(o -> o.getGroups().remove(group));
                                                commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.remove", group));
                                                return 1;
                                            }
                                        })
                                )
                        )
                        .then(ClientCommands.literal("rename")
                                .then(ClientCommands.argument("group", GroupArgument.group())
                                        .then(ClientCommands.argument("name", StringArgumentType.string())
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    String name = StringArgumentType.getString(commandContext, "name");
                                                    if (Config.getOptions().getGroups().containsKey(name)) {
                                                        commandContext.getSource().sendError(Component.translatable("commands.raised.group.rename.error", name));
                                                        return 0;
                                                    } else {
                                                        Config.update(o -> o.getGroups().put(name, o.getGroups().remove(group)));
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
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.offset.x.query", group, Config.getOptions().getGroups().get(group).getOffset().getX()));
                                                            return 1;
                                                        })
                                                        .then(ClientCommands.argument("x", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Config.update(o -> o.getGroups().get(group).getOffset().setX(IntegerArgumentType.getInteger(commandContext, "x")));
                                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.offset.x.set", group, Config.getOptions().getGroups().get(group).getOffset().getX()));
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                                .then(ClientCommands.literal("y")
                                                        .executes(commandContext -> {
                                                            String group = GroupArgument.getGroup(commandContext, "group");
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.offset.y.query",group, Config.getOptions().getGroups().get(group).getOffset().getY()));
                                                            return 1;
                                                        })
                                                        .then(ClientCommands.argument("y", IntegerArgumentType.integer(0))
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    Config.update(o -> o.getGroups().get(group).getOffset().setY(IntegerArgumentType.getInteger(commandContext, "y")));
                                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.offset.y.set", group, Config.getOptions().getGroups().get(group).getOffset().getY()));
                                                                    return 1;
                                                                })
                                                        )
                                                )
                                        )
                                        .then(ClientCommands.literal("layers")
                                                .executes(commandContext -> {
                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                    List<String> layers = new ArrayList<>(Config.getOptions().getGroups().get(group).getLayers());
                                                    if (layers.isEmpty()) {
                                                        commandContext.getSource().sendError(Component.translatable("commands.raised.group.settings.layers.query.error", group));
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
                                                                    if (Config.getOptions().getGroups().get(group).getLayers().contains(layer)) {
                                                                        commandContext.getSource().sendError(Component.translatable("commands.raised.group.settings.layers.add.error", group, layer));
                                                                        return 0;
                                                                    } else {
                                                                        Config.update(o -> o.getGroups().get(group).getLayers().add(layer));
                                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.layers.add", layer, group));
                                                                        return 1;
                                                                    }
                                                                })
                                                        )
                                                )
                                                .then(ClientCommands.literal("remove")
                                                        .then(ClientCommands.argument("layer", IdentifierArgument.id())
                                                                .suggests((commandContext, builder) -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    return SharedSuggestionProvider.suggest(Config.getOptions().getGroups().get(group).getLayers(), builder);
                                                                })
                                                                .executes(commandContext -> {
                                                                    String group = GroupArgument.getGroup(commandContext, "group");
                                                                    String layer = commandContext.getArgument("layer", Identifier.class).toString();
                                                                    if (!Config.getOptions().getGroups().get(group).getLayers().contains(layer)) {
                                                                        commandContext.getSource().sendError(Component.translatable("commands.raised.group.settings.layers.remove.error", group, layer));
                                                                        return 0;
                                                                    } else {
                                                                        Config.update(o -> o.getGroups().get(group).getLayers().remove(layer));
                                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.group.settings.layers.remove", layer, group));
                                                                        return 1;
                                                                    }
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
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.settings.anchor.query", layer, Config.getOptions().getLayers().get(layer).getAnchor().caption()));
                                                    return 1;
                                                })
                                                .then(ClientCommands.argument("anchor", AnchorArgument.anchor())
                                                        .executes(commandContext -> {
                                                            String layer = LayerArgument.getLayer(commandContext, "layer").toString();
                                                            Config.update(o -> o.getLayers().get(layer).setAnchor(AnchorArgument.getAnchor(commandContext, "anchor")));
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.settings.anchor.set", layer, Config.getOptions().getLayers().get(layer).getAnchor().caption()));
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
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.additional_settings.hotbar_selection_fix.query", Config.getOptions().getAdditionalSettings().getHotbarSelectionFix().caption()));
                                    return 1;
                                })
                                .then(ClientCommands.argument("texture", HotbarSelectionFixArgument.hotbarSelectionFix())
                                        .executes(commandContext -> {
                                            Config.update(o -> o.getAdditionalSettings().setHotbarSelectionFix(HotbarSelectionFixArgument.getHotbarSelectionFix(commandContext, "hotbarSelectionFix")));
                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.additional_settings.hotbar_selection_fix.set", Config.getOptions().getAdditionalSettings().getHotbarSelectionFix().caption()));
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}