package dev.yurisuika.raised.command.argument;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.type.Element;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.argument.EnumArgumentType;

public class ElementArgumentType extends EnumArgumentType<Element> {

    public ElementArgumentType() {
        super(Element.CODEC, Element::values);
    }

    public static EnumArgumentType<Element> element() {
        return new ElementArgumentType();
    }

    public static Element getElement(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Element.class);
    }

}