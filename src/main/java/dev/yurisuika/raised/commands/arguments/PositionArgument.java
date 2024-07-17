package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.properties.Position;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class PositionArgument extends StringRepresentableArgument<Position> {

    public PositionArgument() {
        super(Position.CODEC, Position::values);
    }

    public static StringRepresentableArgument<Position> position() {
        return new PositionArgument();
    }

    public static Position getPosition(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Position.class);
    }

}