package dev.yurisuika.raised.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static net.minecraft.server.command.CommandManager.*;

public class RaisedCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
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
                                    setSupport(true, true);
                                    setSync(false);
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.config.reset"), false);
                                    return 1;
                                })
                        )
                )
                .then(literal("value")
                        .then(literal("hud")
                                .executes(context -> {
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.value.hud.query", config.value.hud), false);
                                    return 1;
                                })
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            setHud(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendFeedback(new TranslatableText("commands.raised.value.hud.set", config.value.hud), false);
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("chat")
                                .executes(context -> {
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.value.chat.query", config.value.chat), false);
                                    return 1;
                                })
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            setChat(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendFeedback(new TranslatableText("commands.raised.value.chat.set", config.value.chat), false);
                                            return 1;
                                        })
                                )
                        )
                )
                .then(literal("toggle")
                        .then(literal("support")
                                .executes(context -> {
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.toggle.support.query", config.toggle.support.pre, config.toggle.support.post), false);
                                    return 1;
                                })
                                .then(argument("pre", BoolArgumentType.bool())
                                        .then(argument("post", BoolArgumentType.bool())
                                                .executes(context -> {
                                                    setSupport(BoolArgumentType.getBool(context, "pre"), BoolArgumentType.getBool(context, "post"));
                                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.toggle.support.set", config.toggle.support.pre, config.toggle.support.post), false);
                                                    return 1;
                                                })
                                        )
                                )
                        )
                        .then(literal("sync")
                                .executes(context -> {
                                    context.getSource().sendFeedback(new TranslatableText("commands.raised.toggle.sync.query", config.toggle.sync), false);
                                    return 1;
                                })
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            setSync(BoolArgumentType.getBool(context, "value"));
                                            context.getSource().sendFeedback(new TranslatableText("commands.raised.toggle.sync.set", config.toggle.sync), false);
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}