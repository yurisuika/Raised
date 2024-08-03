package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.commands.arguments.PositionArgument;
import dev.yurisuika.raised.commands.arguments.SyncArgument;
import dev.yurisuika.raised.commands.arguments.TextureArgument;
import dev.yurisuika.raised.config.Options;
import dev.yurisuika.raised.util.config.Config;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.properties.Element;
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
                                    Config.loadConfig();
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(ClientCommandManager.literal("reset")
                                .executes(commandContext -> {
                                    Option.setLayers(new Options().getLayers());
                                    Option.setResources(new Options().getResources());
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
                .then(ClientCommandManager.literal("resources")
                        .then(ClientCommandManager.literal("texture")
                                .executes(commandContext -> {
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.resources.texture.query", Component.translatable(Config.getOptions().getResources().getTexture().getKey())));
                                    return 1;
                                })
                                .then(ClientCommandManager.argument("texture", TextureArgument.texture())
                                        .executes(commandContext -> {
                                            Option.setTexture(TextureArgument.getTexture(commandContext, "texture"));
                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.resources.texture.set", Component.translatable(Config.getOptions().getResources().getTexture().getKey())));
                                            return 1;
                                        })
                                )
                        )
                )
        );

        for (Element element : Element.values()) {
            dispatcher.register(ClientCommandManager.literal("raised")
                    .then(ClientCommandManager.literal("layers")
                            .then(ClientCommandManager.literal(element.getSerializedName())
                                    .then(ClientCommandManager.literal("x")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layers.element.x.query", Component.translatable(element.getKey()), Option.getX(element)));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("x", IntegerArgumentType.integer(0))
                                                    .executes(commandContext -> {
                                                        Option.setX(element, IntegerArgumentType.getInteger(commandContext, "x"));
                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layers.element.x.set", Component.translatable(element.getKey()), Option.getX(element)));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("y")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layers.element.y.query", Component.translatable(element.getKey()), Option.getY(element)));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("y", IntegerArgumentType.integer(0))
                                                    .executes(commandContext -> {
                                                        Option.setY(element, IntegerArgumentType.getInteger(commandContext, "y"));
                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layers.element.y.set", Component.translatable(element.getKey()), Option.getY(element)));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("position")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layers.element.position.query", Component.translatable(element.getKey()), Component.translatable(Option.getPosition(element).getKey())));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("position", PositionArgument.position())
                                                    .executes(commandContext -> {
                                                        Option.setPosition(element, PositionArgument.getPosition(commandContext, "position"));
                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layers.element.position.set", Component.translatable(element.getKey()), Component.translatable(Option.getPosition(element).getKey())));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("sync")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layers.element.sync.query", Component.translatable(element.getKey()), Component.translatable(Option.getSync(element).getKey())));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("sync", SyncArgument.sync())
                                                    .executes(commandContext -> {
                                                        Option.setSync(element, SyncArgument.getSync(commandContext, "sync"));
                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.layers.element.sync.set", Component.translatable(element.getKey()), Component.translatable(Option.getSync(element).getKey())));
                                                        return 1;
                                                    })
                                            )
                                    )
                            )
                    )
            );
        }
    }

}