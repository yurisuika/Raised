package dev.yurisuika.raised.client.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.commands.arguments.PositionArgument;
import dev.yurisuika.raised.commands.arguments.SyncArgument;
import dev.yurisuika.raised.commands.arguments.TextureArgument;
import dev.yurisuika.raised.util.config.Config;
import dev.yurisuika.raised.util.config.Option;
import dev.yurisuika.raised.util.config.option.Elements;
import dev.yurisuika.raised.util.config.option.Properties;
import dev.yurisuika.raised.util.type.Element;
import dev.yurisuika.raised.util.type.Position;
import dev.yurisuika.raised.util.type.Sync;
import dev.yurisuika.raised.util.type.Texture;
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
                                    Option.setElements(new Elements(
                                            new Properties.Hotbar(
                                                    0,
                                                    2,
                                                    Position.BOTTOM,
                                                    Sync.NONE
                                            ),
                                            new Properties.Chat(
                                                    0,
                                                    0,
                                                    Position.BOTTOM,
                                                    Sync.NONE
                                            ),
                                            new Properties.Bossbar(
                                                    0,
                                                    0,
                                                    Position.TOP,
                                                    Sync.NONE
                                            ),
                                            new Properties.Sidebar(
                                                    0,
                                                    0,
                                                    Position.RIGHT,
                                                    Sync.NONE
                                            ),
                                            new Properties.Effects(
                                                    0,
                                                    0,
                                                    Position.TOP_RIGHT,
                                                    Sync.NONE
                                            ),
                                            new Properties.Players(
                                                    0,
                                                    0,
                                                    Position.TOP,
                                                    Sync.NONE
                                            ),
                                            new Properties.Toasts(
                                                    0,
                                                    0,
                                                    Position.TOP_RIGHT,
                                                    Sync.NONE
                                            ),
                                            new Properties.Other(
                                                    0,
                                                    0,
                                                    Position.BOTTOM,
                                                    Sync.NONE
                                            )
                                    ));
                                    Option.setTexture(
                                            Texture.AUTO
                                    );
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
                .then(ClientCommandManager.literal("toggle")
                        .then(ClientCommandManager.literal("texture")
                                .executes(commandContext -> {
                                    commandContext.getSource().sendFeedback(Component.translatable("commands.raised.toggle.texture.query", Component.translatable(Option.getTexture().getSerializedName())));
                                    return 1;
                                })
                                .then(ClientCommandManager.argument("texture", TextureArgument.texture())
                                        .executes(commandContext -> {
                                            Option.setTexture(TextureArgument.getTexture(commandContext, "texture"));
                                            commandContext.getSource().sendFeedback(Component.translatable("commands.raised.toggle.texture.set", Component.translatable(Option.getTexture().getSerializedName())));
                                            return 1;
                                        })
                                )
                        )
                )
        );

        for (Element element : Element.values()) {
            dispatcher.register(ClientCommandManager.literal("raised")
                    .then(ClientCommandManager.literal("elements")
                            .then(ClientCommandManager.literal(element.getSerializedName())
                                    .then(ClientCommandManager.literal("x")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(Component.translatable("commands.raised.elements.element.x.query", Component.translatable(element.getKey()), Option.getY(element)));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("x", IntegerArgumentType.integer(0))
                                                    .executes(commandContext -> {
                                                        Option.setX(element, IntegerArgumentType.getInteger(commandContext, "x"));
                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.elements.element.x.set", Component.translatable(element.getKey()), Option.getX(element)));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("y")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(Component.translatable("commands.raised.elements.element.y.query", Component.translatable(element.getKey()), Option.getY(element)));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("y", IntegerArgumentType.integer(0))
                                                    .executes(commandContext -> {
                                                        Option.setY(element, IntegerArgumentType.getInteger(commandContext, "y"));
                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.elements.element.y.set", Component.translatable(element.getKey()), Option.getY(element)));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("position")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(Component.translatable("commands.raised.elements.element.position.query", Component.translatable(element.getKey()), Component.translatable(Option.getPosition(element).getKey())));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("position", PositionArgument.position())
                                                    .executes(commandContext -> {
                                                        Option.setPosition(element, PositionArgument.getPosition(commandContext, "position"));
                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.elements.element.position.set", Component.translatable(element.getKey()), Component.translatable(Option.getPosition(element).getKey())));
                                                        return 1;
                                                    })
                                            )
                                    )
                                    .then(ClientCommandManager.literal("sync")
                                            .executes(commandContext -> {
                                                commandContext.getSource().sendFeedback(Component.translatable("commands.raised.elements.element.sync.query", Component.translatable(element.getKey()), Component.translatable(Option.getSync(element).getKey())));
                                                return 1;
                                            })
                                            .then(ClientCommandManager.argument("sync", SyncArgument.sync())
                                                    .executes(commandContext -> {
                                                        Option.setSync(element, SyncArgument.getSync(commandContext, "sync"));
                                                        commandContext.getSource().sendFeedback(Component.translatable("commands.raised.elements.element.sync.set", Component.translatable(element.getKey()), Component.translatable(Option.getSync(element).getKey())));
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