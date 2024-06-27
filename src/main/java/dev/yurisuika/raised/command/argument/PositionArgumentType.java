package dev.yurisuika.raised.command.argument;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.type.Position;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.argument.EnumArgumentType;

public class PositionArgumentType extends EnumArgumentType<Position> {

    public PositionArgumentType() {
        super(Position.CODEC, Position::values);
    }

    public static EnumArgumentType<Position> position() {
        return new PositionArgumentType();
    }

    public static Position getPosition(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Position.class);
    }

}