package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.type.Element;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class ElementArgument extends StringRepresentableArgument<Element> {

    public ElementArgument() {
        super(Element.CODEC, Element::values);
    }

    public static StringRepresentableArgument<Element> element() {
        return new ElementArgument();
    }

    public static Element getElement(CommandContext<CommandSourceStack> context, String id) {
        return context.getArgument(id, Element.class);
    }

}