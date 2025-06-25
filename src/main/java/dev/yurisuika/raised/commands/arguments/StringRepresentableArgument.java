package dev.yurisuika.raised.commands.arguments;

import com.google.gson.JsonPrimitive;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.util.StringRepresentable;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StringRepresentableArgument<T extends Enum<T>> implements ArgumentType<T> {

    public final Codec<T> codec;
    public final Supplier<T[]> valuesSupplier;

    public StringRepresentableArgument(Codec<T> codec, Supplier<T[]> valuesSupplier) {
        this.codec = codec;
        this.valuesSupplier = valuesSupplier;
    }

    public T parse(StringReader stringReader) {
        String string = stringReader.readUnquotedString();
        return codec.parse(JsonOps.INSTANCE, new JsonPrimitive(string)).result().orElseThrow();
    }

    @Override
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
        return SharedSuggestionProvider.suggest(Arrays.stream((Enum[]) valuesSupplier.get()).map(constant -> ((StringRepresentable) constant).getSerializedName()).collect(Collectors.toList()), builder);
    }

    @Override
    public Collection<String> getExamples() {
        return Arrays.stream((Enum[]) valuesSupplier.get()).map(constant -> ((StringRepresentable) constant).getSerializedName()).limit(2L).collect(Collectors.toList());
    }

}