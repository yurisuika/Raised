package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.config.options.Resource;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class TextureArgument extends StringRepresentableArgument<Resource.Texture> {

    public TextureArgument() {
        super(Resource.Texture.CODEC, Resource.Texture::values);
    }

    public static StringRepresentableArgument<Resource.Texture> texture() {
        return new TextureArgument();
    }

    public static Resource.Texture getTexture(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Resource.Texture.class);
    }

}