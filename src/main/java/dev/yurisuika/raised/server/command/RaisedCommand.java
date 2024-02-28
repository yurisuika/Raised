package dev.yurisuika.raised.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static dev.yurisuika.raised.client.option.RaisedConfig.*;
import static net.minecraft.server.command.CommandManager.*;

public class RaisedCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("raised")
                .then(literal("config")
                        .then(literal("reload")
                                .executes(context -> {
                                    loadConfig();
                                    context.getSource().sendMessage(Text.translatable("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(literal("reset")
                                .executes(context -> {
                                    setHud(2);
                                    setChat(0);
                                    setSupport(true);
                                    setSync(false);
                                    setTexture(false);
                                    context.getSource().sendMessage(Text.translatable("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
                .then(literal("value")
                        .then(literal("hud")
                                .executes(context -> {
                                    context.getSource().sendMessage(Text.translatable("commands.raised.value.hud.query", config.value.hud));
                                    return 1;
                                })
                                .then(argument("value", IntegerArgumentType.integer(0))
                                        .executes(context -> {
                                            setHud(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendMessage(Text.translatable("commands.raised.value.hud.set", config.value.hud));
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("chat")
                                .executes(context -> {
                                    context.getSource().sendMessage(Text.translatable("commands.raised.value.chat.query", config.value.chat));
                                    return 1;
                                })
                                .then(argument("value", IntegerArgumentType.integer(0))
                                        .executes(context -> {
                                            setChat(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendMessage(Text.translatable("commands.raised.value.chat.set", config.value.chat));
                                            return 1;
                                        })
                                )
                        )
                )
                .then(literal("toggle")
                        .then(literal("support")
                                .executes(context -> {
                                    context.getSource().sendMessage(Text.translatable("commands.raised.toggle.support.query", config.toggle.support));
                                    return 1;
                                })
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            setSupport(BoolArgumentType.getBool(context, "value"));
                                            context.getSource().sendMessage(Text.translatable("commands.raised.toggle.support.set", config.toggle.support));
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("sync")
                                .executes(context -> {
                                    context.getSource().sendMessage(Text.translatable("commands.raised.toggle.sync.query", config.toggle.sync));
                                    return 1;
                                })
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            setSync(BoolArgumentType.getBool(context, "value"));
                                            context.getSource().sendMessage(Text.translatable("commands.raised.toggle.sync.set", config.toggle.sync));
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("texture")
                                .executes(context -> {
                                    context.getSource().sendMessage(Text.translatable("commands.raised.toggle.texture.query", config.toggle.texture));
                                    return 1;
                                })
                                .then(argument("value", BoolArgumentType.bool())
                                        .executes(context -> {
                                            setTexture(BoolArgumentType.getBool(context, "value"));
                                            context.getSource().sendMessage(Text.translatable("commands.raised.toggle.texture.set", config.toggle.texture));
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}