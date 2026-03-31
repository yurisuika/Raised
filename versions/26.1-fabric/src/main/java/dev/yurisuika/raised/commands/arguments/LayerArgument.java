package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.yurisuika.raised.registry.LayerRegistry;
import dev.yurisuika.raised.util.Configure;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class LayerArgument implements ArgumentType<Identifier> {

    public LayerArgument() {}

    public static LayerArgument layer() {
        return new LayerArgument();
    }

    public static Identifier getLayer(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Identifier.class);
    }

    @Override
    public Identifier parse(StringReader reader) throws CommandSyntaxException {
        Identifier name = Identifier.read(reader);
        if (Configure.getLayers().containsKey(name.toString())) {
            return name;
        } else {
            throw new DynamicCommandExceptionType(object -> Component.translatable("commands.raised.layer.unknown", object)).createWithContext(reader, name);
        }
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandContext, SuggestionsBuilder suggestionsBuilder) {
        return SharedSuggestionProvider.suggestResource(LayerRegistry.LAYERS.keySet().stream().sorted(Comparator.comparing(Identifier::toString)), suggestionsBuilder);
    }

    @Override
    public Collection<String> getExamples() {
        return List.of("minecraft:hotbar", "appleskin:saturation_level", "farmersdelight:nourishment");
    }

}