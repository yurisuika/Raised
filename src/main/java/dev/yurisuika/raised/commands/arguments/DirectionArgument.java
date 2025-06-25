package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.client.gui.Layer;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;

public abstract class DirectionArgument {

    public static class X extends StringRepresentableArgument<Layer.Direction.X> {

        public X() {
            super(Layer.Direction.X.CODEC, Layer.Direction.X::values);
        }

        public static StringRepresentableArgument<Layer.Direction.X> x() {
            return new X();
        }

        public static Layer.Direction.X getX(CommandContext<FabricClientCommandSource> context, String id) {
            return context.getArgument(id, Layer.Direction.X.class);
        }

    }

    public static class Y extends StringRepresentableArgument<Layer.Direction.Y> {

        public Y() {
            super(Layer.Direction.Y.CODEC, Layer.Direction.Y::values);
        }

        public static StringRepresentableArgument<Layer.Direction.Y> y() {
            return new Y();
        }

        public static Layer.Direction.Y getY(CommandContext<FabricClientCommandSource> context, String id) {
            return context.getArgument(id, Layer.Direction.Y.class);
        }

    }

}