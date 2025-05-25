package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.commands.arguments.DirectionArgument;
import dev.yurisuika.raised.commands.arguments.LayerArgument;
import dev.yurisuika.raised.util.Validate;
import dev.yurisuika.raised.util.config.Config;
import dev.yurisuika.raised.util.config.Option;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.TranslatableComponent;

public class RaisedCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("raised")
                .then(Commands.literal("config")
                        .then(Commands.literal("reload")
                                .executes(commandContext -> {
                                    Config.loadConfig();
                                    Validate.checkForOldConfig();
                                    commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.config.reload"), false);
                                    return 1;
                                })
                        )
                        .then(Commands.literal("reset")
                                .executes(commandContext -> {
                                    Validate.resetConfig();
                                    commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.config.reset"), false);
                                    return 1;
                                })
                        )
                )
                .then(Commands.literal("layer")
                        .then(Commands.argument("name", LayerArgument.layer())
                                .then(Commands.literal("displacement")
                                        .then(Commands.literal("x")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.layer.displacement.x.query", name, Option.getDisplacementX(name)), false);
                                                    return 1;
                                                })
                                                .then(Commands.argument("x", IntegerArgumentType.integer(0))
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDisplacementX(name, IntegerArgumentType.getInteger(commandContext, "x"));
                                                            commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.layer.displacement.x.set", name, Option.getDisplacementX(name)), false);
                                                            return 1;
                                                        })
                                                )
                                        )
                                        .then(Commands.literal("y")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.layer.displacement.y.query",name, Option.getDisplacementY(name)), false);
                                                    return 1;
                                                })
                                                .then(Commands.argument("y", IntegerArgumentType.integer(0))
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDisplacementY(name, IntegerArgumentType.getInteger(commandContext, "y"));
                                                            commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.layer.displacement.y.set", name, Option.getDisplacementY(name)), false);
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                .then(Commands.literal("direction")
                                        .then(Commands.literal("x")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.layer.direction.x.query", name, Option.getDirectionX(name).getKey()), false);
                                                    return 1;
                                                })
                                                .then(Commands.argument("x", DirectionArgument.X.x())
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDirectionX(name, DirectionArgument.X.getX(commandContext, "x"));
                                                            commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.layer.direction.x.set", name, Option.getDirectionX(name).getKey()), false);
                                                            return 1;
                                                        })
                                                )
                                        )
                                        .then(Commands.literal("y")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.layer.direction.y.query", name, Option.getDirectionY(name).getKey()), false);
                                                    return 1;
                                                })
                                                .then(Commands.argument("y", DirectionArgument.Y.y())
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDirectionY(name, DirectionArgument.Y.getY(commandContext, "y"));
                                                            commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.layer.direction.y.set", name, Option.getDirectionY(name).getKey()), false);
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                .then(Commands.literal("sync")
                                        .executes(commandContext -> {
                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                            commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.layer.sync.query", name, Option.getSync(name)), false);
                                            return 1;
                                        })
                                        .then(Commands.argument("sync", LayerArgument.layer())
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    Option.setSync(name, LayerArgument.getLayer(commandContext, "sync").toString());
                                                    commandContext.getSource().sendSuccess(new TranslatableComponent("commands.raised.layer.sync.set", name, Option.getSync(name)), false);
                                                    return 1;
                                                })
                                        )
                                )
                        )
                )
        );
    }

}