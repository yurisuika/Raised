package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.option.AdditionalSettings;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class HotbarSelectionFixArgument extends StringRepresentableArgument<AdditionalSettings.HotbarSelectionFix> {

    public HotbarSelectionFixArgument() {
        super(AdditionalSettings.HotbarSelectionFix.CODEC, AdditionalSettings.HotbarSelectionFix::values);
    }

    public static StringRepresentableArgument<AdditionalSettings.HotbarSelectionFix> hotbarSelectionFix() {
        return new HotbarSelectionFixArgument();
    }

    public static AdditionalSettings.HotbarSelectionFix getHotbarSelectionFix(CommandContext<CommandSourceStack> context, String id) {
        return context.getArgument(id, AdditionalSettings.HotbarSelectionFix.class);
    }

}