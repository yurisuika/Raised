package dev.yurisuika.raised.server.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.yurisuika.raised.Raised;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class RaisedCommand {

    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess registryAccess) {
        dispatcher.register(literal("raised")
                .then(literal("config")
                        .then(literal("reload")
                                .executes(context -> {
                                    Raised.loadConfig();
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.config.reload"));
                                    return 1;
                                })
                        )
                        .then(literal("reset")
                                .executes(context -> {
                                    Raised.setHud(2);
                                    Raised.setChat(0);
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.config.reset"));
                                    return 1;
                                })
                        )
                )
                .then(literal("query")
                        .then(literal("hud")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.query.hud", Raised.config.hud));
                                    return 1;
                                })
                        )
                        .then(literal("chat")
                                .executes(context -> {
                                    context.getSource().sendFeedback(Text.translatable("commands.raised.query.chat", Raised.config.chat));
                                    return 1;
                                })
                        )
                )
                .then(literal("set")
                        .then(literal("hud")
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            Raised.setHud(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendFeedback(Text.translatable("commands.raised.set.hud", Raised.config.hud));
                                            return 1;
                                        })
                                )
                        )
                        .then(literal("chat")
                                .then(argument("value", IntegerArgumentType.integer())
                                        .executes(context -> {
                                            Raised.setChat(IntegerArgumentType.getInteger(context, "value"));
                                            context.getSource().sendFeedback(Text.translatable("commands.raised.set.chat", Raised.config.chat));
                                            return 1;
                                        })
                                )
                        )
                )
        );
    }

}