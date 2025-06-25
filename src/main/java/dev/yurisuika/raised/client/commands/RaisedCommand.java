package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.commands.arguments.DirectionArgument;
import dev.yurisuika.raised.commands.arguments.LayerArgument;
import dev.yurisuika.raised.commands.arguments.TextureArgument;
import dev.yurisuika.raised.util.Configure;
import dev.yurisuika.raised.util.Validate;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class RaisedCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext context) {
        dispatcher.register(Commands.literal("raised")
                .then(Commands.literal("config")
                        .then(Commands.literal("reload")
                                .executes(commandContext -> {
                                    Validate.reloadConfig();
                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.config.reload"), false);
                                    return 1;
                                })
                        )
                        .then(Commands.literal("reset")
                                .executes(commandContext -> {
                                    Validate.resetConfig();
                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.config.reset"), false);
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
                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.displacement.x.query", name, Configure.getDisplacementX(name)), false);
                                                    return 1;
                                                })
                                                .then(Commands.argument("x", IntegerArgumentType.integer(0))
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Configure.setDisplacementX(name, IntegerArgumentType.getInteger(commandContext, "x"));
                                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.displacement.x.set", name, Configure.getDisplacementX(name)), false);
                                                            return 1;
                                                        })
                                                )
                                        )
                                        .then(Commands.literal("y")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.displacement.y.query",name, Configure.getDisplacementY(name)), false);
                                                    return 1;
                                                })
                                                .then(Commands.argument("y", IntegerArgumentType.integer(0))
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Configure.setDisplacementY(name, IntegerArgumentType.getInteger(commandContext, "y"));
                                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.displacement.y.set", name, Configure.getDisplacementY(name)), false);
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                .then(Commands.literal("direction")
                                        .then(Commands.literal("x")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.direction.x.query", name, Component.translatable(Configure.getDirectionX(name).getKey())), false);
                                                    return 1;
                                                })
                                                .then(Commands.argument("x", DirectionArgument.X.x())
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Configure.setDirectionX(name, DirectionArgument.X.getX(commandContext, "x"));
                                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.direction.x.set", name, Component.translatable(Configure.getDirectionX(name).getKey())), false);
                                                            return 1;
                                                        })
                                                )
                                        )
                                        .then(Commands.literal("y")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.direction.y.query", name, Component.translatable(Configure.getDirectionY(name).getKey())), false);
                                                    return 1;
                                                })
                                                .then(Commands.argument("y", DirectionArgument.Y.y())
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Configure.setDirectionY(name, DirectionArgument.Y.getY(commandContext, "y"));
                                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.direction.y.set", name, Component.translatable(Configure.getDirectionY(name).getKey())), false);
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                .then(Commands.literal("sync")
                                        .executes(commandContext -> {
                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.sync.query", name, Configure.getSync(name)), false);
                                            return 1;
                                        })
                                        .then(Commands.argument("sync", LayerArgument.layer())
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    Configure.setSync(name, LayerArgument.getLayer(commandContext, "sync").toString());
                                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.layer.sync.set", name, Configure.getSync(name)), false);
                                                    return 1;
                                                })
                                        )
                                )
                        )
                )
                .then(Commands.literal("resource")
                        .then(Commands.literal("texture")
                                .executes(commandContext -> {
                                    commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.resources.texture.query", Component.translatable(Configure.getTexture().getKey())), false);
                                    return 1;
                                })
                                .then(Commands.argument("texture", TextureArgument.texture())
                                        .executes(commandContext -> {
                                            Configure.setTexture(TextureArgument.getTexture(commandContext, "texture"));
                                            commandContext.getSource().sendSuccess(() -> Component.translatable("commands.raised.resources.texture.set", Component.translatable(Configure.getTexture().getKey())), false);
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}