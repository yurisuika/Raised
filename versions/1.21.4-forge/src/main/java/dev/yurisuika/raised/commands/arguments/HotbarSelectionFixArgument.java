package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.option.Settings;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class HotbarSelectionFixArgument extends StringRepresentableArgument<Settings.HotbarSelectionFix> {

    public HotbarSelectionFixArgument() {
        super(Settings.HotbarSelectionFix.CODEC, Settings.HotbarSelectionFix::values);
    }

    public static StringRepresentableArgument<Settings.HotbarSelectionFix> hotbarSelectionFix() {
        return new HotbarSelectionFixArgument();
    }

    public static Settings.HotbarSelectionFix getHotbarSelectionFix(CommandContext<CommandSourceStack> context, String id) {
        return context.getArgument(id, Settings.HotbarSelectionFix.class);
    }

}