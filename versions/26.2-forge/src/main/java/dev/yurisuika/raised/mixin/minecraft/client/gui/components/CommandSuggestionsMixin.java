package dev.yurisuika.raised.mixin.minecraft.client.gui.components;

import dev.yurisuika.raised.client.gui.layer.Layer;
import dev.yurisuika.raised.client.gui.layer.Layers;
import dev.yurisuika.raised.util.Translate;
import net.minecraft.client.gui.components.CommandSuggestions;
import net.minecraft.client.gui.screens.Screen;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = CommandSuggestions.class, priority = -999999999, remap = false)
public abstract class CommandSuggestionsMixin {

    @Shadow
    private int commandUsagePosition;

    /**
     * Moves the {@code chat suggestions} for {@link Layer} key "minecraft:chat_input".
     */
    @Redirect(method = "showSuggestions", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/Screen;height:I", opcode = Opcodes.GETFIELD))
    private int adjustSuggestionsY(Screen instance) {
        return instance.height + Translate.getY(Layers.CHAT_INPUT);
    }

    /**
     * Moves the {@code chat suggestions usage} for {@link Layer} key "minecraft:chat_input".
     */
    @Redirect(method = "extractUsage", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/screens/Screen;height:I", opcode = Opcodes.GETFIELD))
    private int adjustUsageInfoY(Screen instance) {
        return instance.height + Translate.getY(Layers.CHAT_INPUT);
    }

    @ModifyArg(method = "updateUsageInfo", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/Mth;clamp(III)I"), index = 1)
    private int adjustUsageInfoX(int value) {
        return value + Translate.getX(Layers.CHAT_INPUT);
    }

    @Redirect(method = "updateUsageInfo", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/components/CommandSuggestions;commandUsagePosition:I", ordinal = 1, opcode = Opcodes.PUTFIELD))
    private void adjustUsageInfoPosition(CommandSuggestions instance, int value) {
        commandUsagePosition = value + Translate.getX(Layers.CHAT_INPUT);
    }

    @Redirect(method = "updateCommandInfo", at = @At(value = "FIELD", target = "Lnet/minecraft/client/gui/components/CommandSuggestions;commandUsagePosition:I", opcode = Opcodes.PUTFIELD))
    private void adjustCommandUsagePosition(CommandSuggestions instance, int value) {
        commandUsagePosition = value + Translate.getX(Layers.CHAT_INPUT);
    }

}