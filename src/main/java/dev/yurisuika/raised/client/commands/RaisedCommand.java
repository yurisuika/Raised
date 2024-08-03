package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.commands.arguments.PositionArgument;
import dev.yurisuika.raised.commands.arguments.SyncArgument;
import dev.yurisuika.raised.config.Options;
import dev.yurisuika.raised.util.config.Config;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.properties.Element;
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
                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(ClientCommandManager.literal("reset")
                                .executes(commandContext -> {
                                    Option.setLayers(new Options().getLayers());
                                    commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
        );

        for (Element element : Element.values()) {
            dispatcher.register(ClientCommandManager.literal("raised")
                    .then(ClientCommandManager.literal("layers")
                            .then(ClientCommandManager.literal(element.getSerializedName())
                                    .then(ClientCommandManager.literal("x")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layers.element.x.query", new TranslatableComponent(element.getKey()), Option.getX(element)));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("x", IntegerArgumentType.integer(0))
                                                    .executes(commandContext -> {
                                                        Option.setX(element, IntegerArgumentType.getInteger(commandContext, "x"));
                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layers.element.x.set", new TranslatableComponent(element.getKey()), Option.getX(element)));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("y")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layers.element.y.query", new TranslatableComponent(element.getKey()), Option.getY(element)));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("y", IntegerArgumentType.integer(0))
                                                    .executes(commandContext -> {
                                                        Option.setY(element, IntegerArgumentType.getInteger(commandContext, "y"));
                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layers.element.y.set", new TranslatableComponent(element.getKey()), Option.getY(element)));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("position")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layers.element.position.query", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getPosition(element).getKey())));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("position", PositionArgument.position())
                                                    .executes(commandContext -> {
                                                        Option.setPosition(element, PositionArgument.getPosition(commandContext, "position"));
                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layers.element.position.set", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getPosition(element).getKey())));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("sync")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layers.element.sync.query", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getSync(element).getKey())));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("sync", SyncArgument.sync())
                                                    .executes(commandContext -> {
                                                        Option.setSync(element, SyncArgument.getSync(commandContext, "sync"));
                                                        commandContext.getSource().sendFeedback(new TranslatableComponent("commands.raised.layers.element.sync.set", new TranslatableComponent(element.getKey()), new TranslatableComponent(Option.getSync(element).getKey())));
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