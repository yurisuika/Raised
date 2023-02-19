package dev.yurisuika.raised.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;

import static dev.yurisuika.raised.Raised.*;
import static net.minecraft.server.command.CommandManager.*;

public class RaisedCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, RegistrationEnvironment environment) {
        dispatcher.register(literal("raised")
                .then(literal("config")
                        .then(literal("reload")
                                .executes(context -> {
                                    loadConfig();
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.config.reload"), false);
                                    return 1;
                                })
                        )
                        .then(literal("reset")
                                .executes(context -> {
                                    setHud(2);
                                    setChat(0);
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.config.reset"), false);
                                    return 1;
                                })
                        )
                )
                .then(literal("query")
                        .then(literal("hud")
                                .executes(context -> {
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.query.hud", config.hud), false);
                                    return 1;
                                })
                        )
                        .then(literal("chat")
                                .executes(context -> {
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.query.chat", config.chat), false);
                                    return 1;
                                })
                        )
                )
                .then(literal("set")
                        .then(literal("hud")
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            setHud(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendFeedback(new TranslatableText("commands.raised.set.hud", config.hud), false);
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("chat")
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            setChat(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendFeedback(new TranslatableText("commands.raised.set.chat", config.chat), false);
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}