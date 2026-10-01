package dev.yurisuika.raised.mixin.minecraft.client.gui.components;

import net.minecraft.client.gui.components.CommandSuggestions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CommandSuggestions.class)
public interface CommandSuggestionsAccessor {

    @Accessor("anchorToBottom")
    boolean getAnchorToBottom();

}