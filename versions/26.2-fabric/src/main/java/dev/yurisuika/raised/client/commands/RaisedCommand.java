package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.commands.arguments.DirectionArgument;
import dev.yurisuika.raised.commands.arguments.LayerArgument;
import dev.yurisuika.raised.commands.arguments.TextureArgument;
import dev.yurisuika.raised.util.Configure;
import dev.yurisuika.raised.util.Validate;
import net.fabricmc.fabric.api.client.command.v2.ClientCommands;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.network.chat.Component;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandBuildContext context) {
        dispatcher.register(ClientCommands.literal("raised")
                .then(ClientCommands.literal("config")
                        .then(ClientCommands.literal("reload")
                                .executes(commandContext -> {
                                    Validate.reloadConfig();
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(ClientCommands.literal("reset")
                                .executes(commandContext -> {
                                    Validate.resetConfig();
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
                .then(ClientCommands.literal("layer")
                        .then(ClientCommands.argument("name", LayerArgument.layer())
                                .then(ClientCommands.literal("displacement")
                                        .then(ClientCommands.literal("x")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.displacement.x.query", name, Configure.getDisplacementX(name)));
                                                    return 1;
                                                })
                                                .then(ClientCommands.argument("x", IntegerArgumentType.integer(0))
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Configure.setDisplacementX(name, IntegerArgumentType.getInteger(commandContext, "x"));
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.displacement.x.set", name, Configure.getDisplacementX(name)));
                                                            return 1;
                                                        })
                                                )
                                        )
                                        .then(ClientCommands.literal("y")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.displacement.y.query",name, Configure.getDisplacementY(name)));
                                                    return 1;
                                                })
                                                .then(ClientCommands.argument("y", IntegerArgumentType.integer(0))
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Configure.setDisplacementY(name, IntegerArgumentType.getInteger(commandContext, "y"));
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.displacement.y.set", name, Configure.getDisplacementY(name)));
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                .then(ClientCommands.literal("direction")
                                        .then(ClientCommands.literal("x")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.direction.x.query", name, Configure.getDirectionX(name).caption()));
                                                    return 1;
                                                })
                                                .then(ClientCommands.argument("x", DirectionArgument.X.x())
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Configure.setDirectionX(name, DirectionArgument.X.getX(commandContext, "x"));
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.direction.x.set", name, Configure.getDirectionX(name).caption()));
                                                            return 1;
                                                        })
                                                )
                                        )
                                        .then(ClientCommands.literal("y")
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.direction.y.query", name, Configure.getDirectionY(name).caption()));
                                                    return 1;
                                                })
                                                .then(ClientCommands.argument("y", DirectionArgument.Y.y())
                                                        .executes(commandContext -> {
                                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                            Configure.setDirectionY(name, DirectionArgument.Y.getY(commandContext, "y"));
                                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.direction.y.set", name, Configure.getDirectionY(name).caption()));
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                .then(ClientCommands.literal("sync")
                                        .executes(commandContext -> {
                                            String name = LayerArgument.getLayer(commandContext, "name").toString();
                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.sync.query", name, Configure.getSync(name)));
                                            return 1;
                                        })
                                        .then(ClientCommands.argument("sync", LayerArgument.layer())
                                                .executes(commandContext -> {
                                                    String name = LayerArgument.getLayer(commandContext, "name").toString();
                                                    Configure.setSync(name, LayerArgument.getLayer(commandContext, "sync").toString());
                                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layer.sync.set", name, Configure.getSync(name)));
                                                    return 1;
                                                })
                                        )
                                )
                        )
                )
                .then(ClientCommands.literal("resource")
                        .then(ClientCommands.literal("texture")
                                .executes(commandContext -> {
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.resource.texture.query", Configure.getTexture().caption()));
                                    return 1;
                                })
                                .then(ClientCommands.argument("texture", TextureArgument.texture())
                                        .executes(commandContext -> {
                                            Configure.setTexture(TextureArgument.getTexture(commandContext, "texture"));
                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.resource.texture.set", Configure.getTexture().caption()));
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}