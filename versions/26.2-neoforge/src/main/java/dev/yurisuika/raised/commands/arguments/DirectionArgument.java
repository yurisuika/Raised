package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.client.gui.Layer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public abstract class DirectionArgument {

    public static class X extends StringRepresentableArgument<Layer.Direction.X> {

        public X() {
            super(Layer.Direction.X.CODEC, Layer.Direction.X::values);
        }

        public static StringRepresentableArgument<Layer.Direction.X> x() {
            return new DirectionArgument.X();
        }

        public static Layer.Direction.X getX(CommandContext<CommandSourceStack> context, String id) {
            return context.getArgument(id, Layer.Direction.X.class);
        }

    }

    public static class Y extends StringRepresentableArgument<Layer.Direction.Y> {

        public Y() {
            super(Layer.Direction.Y.CODEC, Layer.Direction.Y::values);
        }

        public static StringRepresentableArgument<Layer.Direction.Y> y() {
            return new DirectionArgument.Y();
        }

        public static Layer.Direction.Y getY(CommandContext<CommandSourceStack> context, String id) {
            return context.getArgument(id, Layer.Direction.Y.class);
        }

    }

}