package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.commands.arguments.DirectionArgument;
import dev.yurisuika.raised.commands.arguments.LayerArgument;
import dev.yurisuika.raised.commands.arguments.TextureArgument;
import dev.yurisuika.raised.util.Validate;
import dev.yurisuika.raised.util.config.Option;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext context) {
        dispatcher.register(ClientCommandManager.literal("raised")
                .then(ClientCommandManager.literal("config")
                        .then(ClientCommandManager.literal("reload")
                                .executes(commandContext -> {
                                    Validate.reloadConfig();
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(ClientCommandManager.literal("reset")
                                .executes(commandContext -> {
                                    Validate.resetConfig();
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.config.reset"));
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
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.displacement.x.query", name, Option.getDisplacementX(name)));
                                                    return 1;
                                                })
                                                .then(ClientCommandManager.argument("x", IntegerArgumentType.integer(0))
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDisplacementX(name, IntegerArgumentType.getInteger(commandContext, "x"));
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.displacement.x.set", name, Option.getDisplacementX(name)));
                                                            return 1;
                                                        })
                                                )
                                        )
                                        .then(ClientCommandManager.literal("y")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.displacement.y.query",name, Option.getDisplacementY(name)));
                                                    return 1;
                                                })
                                                .then(ClientCommandManager.argument("y", IntegerArgumentType.integer(0))
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDisplacementY(name, IntegerArgumentType.getInteger(commandContext, "y"));
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.displacement.y.set", name, Option.getDisplacementY(name)));
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                .then(ClientCommandManager.literal("direction")
                                        .then(ClientCommandManager.literal("x")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.direction.x.query", name, Component.translatable(Option.getDirectionX(name).getKey())));
                                                    return 1;
                                                })
                                                .then(ClientCommandManager.argument("x", DirectionArgument.X.x())
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDirectionX(name, DirectionArgument.X.getX(commandContext, "x"));
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.direction.x.set", name, Component.translatable(Option.getDirectionX(name).getKey())));
                                                            return 1;
                                                        })
                                                )
                                        )
                                        .then(ClientCommandManager.literal("y")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.direction.y.query", name, Component.translatable(Option.getDirectionY(name).getKey())));
                                                    return 1;
                                                })
                                                .then(ClientCommandManager.argument("y", DirectionArgument.Y.y())
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Option.setDirectionY(name, DirectionArgument.Y.getY(commandContext, "y"));
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.direction.y.set", name, Component.translatable(Option.getDirectionY(name).getKey())));
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                .then(ClientCommandManager.literal("sync")
                                        .executes(commandContext -> {
                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.sync.query", name, Option.getSync(name)));
                                            return 1;
                                        })
                                        .then(ClientCommandManager.argument("sync", LayerArgument.layer())
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    Option.setSync(name, LayerArgument.getLayer(commandContext, "sync").toString());
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.sync.set", name, Option.getSync(name)));
                                                    return 1;
                                                })
                                        )
                                )
                        )
                )
                .then(ClientCommandManager.literal("resource")
                        .then(ClientCommandManager.literal("texture")
                                .executes(commandContext -> {
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.resources.texture.query", Component.translatable(Option.getTexture().getKey())));
                                    return 1;
                                })
                                .then(ClientCommandManager.argument("texture", TextureArgument.texture())
                                        .executes(commandContext -> {
                                            Option.setTexture(TextureArgument.getTexture(commandContext, "texture"));
                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.resources.texture.set", Component.translatable(Option.getTexture().getKey())));
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}