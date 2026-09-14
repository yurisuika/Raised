package dev.yurisuika.raised.mixin.minecraft.client.gui.components;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.client.gui.screens.Screen;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = CommandSuggestions.class, priority = -999999999, remap = false)
public abstract class CommandSuggestionsMixin {

    /**
     * Moves the {@code chat suggestions} for {@link Layer} key "minecraft:chat_input".
     */
    @Redirect(method = "showSuggestions", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/Screen;height:I", opcode = Opcodes.GETFIELD))
    private int adjustSuggestionsY(Screen instance) {
        return instance.height + Translate.getY(Layers.CHAT_INPUT);
    }

}