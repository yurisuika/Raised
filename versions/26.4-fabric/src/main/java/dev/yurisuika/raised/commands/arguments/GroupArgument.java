package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.yurisuika.raised.config.Config;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.network.chat.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GroupArgument implements ArgumentType<String> {

    public GroupArgument() {}

    public static GroupArgument group() {
        return new GroupArgument();
    }

    public static String getGroup(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, String.class);
    }

    @Override
    public String parse(StringReader reader) throws CommandSyntaxException {
        String groupName = reader.readString();
        if (Config.getOptions().getGroups().containsKey(groupName)) {
            return groupName;
        } else {
            throw new DynamicCommandExceptionType(object -> Component.translatable("commands.raised.group.unknown", object)).createWithContext(reader, groupName);
        }
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandContext, SuggestionsBuilder suggestionsBuilder) {
        Config.getOptions().getGroups().keySet().forEach(group -> suggestionsBuilder.suggest(StringArgumentType.escapeIfRequired(group)));
        return suggestionsBuilder.buildFuture();
    }

    @Override
    public Collection<String> getExamples() {
        return List.of("whatever", "you", "want");
    }

}