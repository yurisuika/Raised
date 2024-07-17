package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.properties.Element;
import net.minecraft.commands.CommandSourceStack;

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