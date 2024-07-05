package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.type.Texture;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class TextureArgument extends StringRepresentableArgument<Texture> {

    public TextureArgument() {
        super(Texture.CODEC, Texture::values);
    }

    public static StringRepresentableArgument<Texture> texture() {
        return new TextureArgument();
    }

    public static Texture getTexture(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Texture.class);
    }

}