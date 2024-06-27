package dev.yurisuika.raised.command.argument;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.type.Texture;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.argument.EnumArgumentType;

public class TextureArgumentType extends EnumArgumentType<Texture> {

    public TextureArgumentType() {
        super(Texture.CODEC, Texture::values);
    }

    public static EnumArgumentType<Texture> texture() {
        return new TextureArgumentType();
    }

    public static Texture getTexture(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Texture.class);
    }

}