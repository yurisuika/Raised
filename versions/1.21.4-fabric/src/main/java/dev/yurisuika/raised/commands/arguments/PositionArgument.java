package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.client.gui.layer.Layer;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class PositionArgument extends StringRepresentableArgument<Layer.Position> {

    public PositionArgument() {
        super(Layer.Position.CODEC, Layer.Position::values);
    }

    public static StringRepresentableArgument<Layer.Position> position() {
        return new PositionArgument();
    }

    public static Layer.Position getPosition(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Layer.Position.class);
    }

}