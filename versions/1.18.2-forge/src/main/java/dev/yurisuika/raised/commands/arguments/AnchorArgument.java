package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.client.gui.layer.Layer;
import net.minecraft.commands.CommandSourceStack;

public class AnchorArgument extends StringRepresentableArgument<Layer.Anchor> {

    public AnchorArgument() {
        super(Layer.Anchor.CODEC, Layer.Anchor::values);
    }

    public static StringRepresentableArgument<Layer.Anchor> anchor() {
        return new AnchorArgument();
    }

    public static Layer.Anchor getAnchor(CommandContext<CommandSourceStack> context, String id) {
        return context.getArgument(id, Layer.Anchor.class);
    }

}