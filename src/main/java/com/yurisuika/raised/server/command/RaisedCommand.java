package com.yurisuika.raised.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static com.yurisuika.raised.Raised.*;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("raised")
                .then(literal("config")
                        .then(literal("enable")
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            setEnabled(BoolArgumentType.getBool(context, "value"));
                                            String key = config.enabled ? "commands.raised.config.enabled" : "commands.raised.config.disabled";
                                            context.getSource().sendFeedback(Text.translatable(key));
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("reload")
                                .executes(context -> {
                                    loadConfig();
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(literal("reset")
                                .executes(context -> {
                                    setEnabled(true);
                                    setHud(2);
                                    setChat(0);
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
                .then(literal("query")
                        .then(literal("hud")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.query.hud", config.hud));
                                    return 1;
                                })
                        )
                        .then(literal("chat")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.query.chat", config.chat));
                                    return 1;
                                })
                        )
                )
                .then(literal("set")
                        .then(literal("hud")
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            setHud(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendFeedback(Text.translatable("commands.raised.set.hud", config.hud));
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("chat")
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            setChat(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendFeedback(Text.translatable("commands.raised.set.chat", config.chat));
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}