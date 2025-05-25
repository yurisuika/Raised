package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.commands.arguments.DirectionArgument;
import dev.yurisuika.raised.commands.arguments.LayerArgument;
import dev.yurisuika.raised.util.Validate;
import dev.yurisuika.raised.util.config.Config;
import dev.yurisuika.raised.util.config.Option;
import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.network.chat.TranslatableComponent;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher) {
        dispatcher.register(ClientCommandManager.literal("raised")
                .then(ClientCommandManager.literal("config")
                        .then(ClientCommandManager.literal("reload")
                                .executes(commandContext -> {
                                    Config.loadConfig();
                                    Validate.checkForOldConfig();
                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(ClientCommandManager.literal("reset")
                                .executes(commandContext -> {
                                    Validate.resetConfig();
                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
                .then(ClientCommandManager.literal("layer")
                        .then(ClientCommandManager.argument("name", LayerArgument.layer())
                                .then(ClientCommandManager.literal("displacement")
                                        .then(ClientCommandManager.literal("x")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.displacement.x.query", name, Option.getDisplacementX(name)));
                                                    return 1;
                                                })
                                                .then(ClientCommandManager.argument("x", IntegerArgumentType.integer(0))
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDisplacementX(name, IntegerArgumentType.getInteger(commandContext, "x"));
                                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.displacement.x.set", name, Option.getDisplacementX(name)));
                                                            return 1;
                                                        })
                                                )
                                        )
                                        .then(ClientCommandManager.literal("y")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.displacement.y.query",name, Option.getDisplacementY(name)));
                                                    return 1;
                                                })
                                                .then(ClientCommandManager.argument("y", IntegerArgumentType.integer(0))
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDisplacementY(name, IntegerArgumentType.getInteger(commandContext, "y"));
                                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.displacement.y.set", name, Option.getDisplacementY(name)));
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                .then(ClientCommandManager.literal("direction")
                                        .then(ClientCommandManager.literal("x")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.direction.x.query", name, Option.getDirectionX(name).getKey()));
                                                    return 1;
                                                })
                                                .then(ClientCommandManager.argument("x", DirectionArgument.X.x())
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDirectionX(name, DirectionArgument.X.getX(commandContext, "x"));
                                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.direction.x.set", name, Option.getDirectionX(name).getKey()));
                                                            return 1;
                                                        })
                                                )
                                        )
                                        .then(ClientCommandManager.literal("y")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.direction.y.query", name, Option.getDirectionY(name).getKey()));
                                                    return 1;
                                                })
                                                .then(ClientCommandManager.argument("y", DirectionArgument.Y.y())
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDirectionY(name, DirectionArgument.Y.getY(commandContext, "y"));
                                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.direction.y.set", name, Option.getDirectionY(name).getKey()));
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                .then(ClientCommandManager.literal("sync")
                                        .executes(commandContext -> {
                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                            commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.sync.query", name, Option.getSync(name)));
                                            return 1;
                                        })
                                        .then(ClientCommandManager.argument("sync", LayerArgument.layer())
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    Option.setSync(name, LayerArgument.getLayer(commandContext, "sync").toString());
                                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layer.sync.set", name, Option.getSync(name)));
                                                    return 1;
                                                })
                                        )
                                )
                        )
                )
        );
    }

}