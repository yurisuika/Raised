package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.client.gui.layer.Layer;
import net.minecraft.commands.CommandSourceStack;

public class PositionArgument extends StringRepresentableArgument<Layer.Position> {

    public PositionArgument() {
        super(Layer.Position.CODEC, Layer.Position::values);
    }

    public static StringRepresentableArgument<Layer.Position> position() {
        return new PositionArgument();
    }

    public static Layer.Position getPosition(CommandContext<CommandSourceStack> context, String id) {
        return context.getArgument(id, Layer.Position.class);
    }

}