package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.option.AdditionalSettings;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class HotbarSelectionFixArgument extends StringRepresentableArgument<AdditionalSettings.HotbarSelectionFix> {

    public HotbarSelectionFixArgument() {
        super(AdditionalSettings.HotbarSelectionFix.CODEC, AdditionalSettings.HotbarSelectionFix::values);
    }

    public static StringRepresentableArgument<AdditionalSettings.HotbarSelectionFix> hotbarSelectionFix() {
        return new HotbarSelectionFixArgument();
    }

    public static AdditionalSettings.HotbarSelectionFix getHotbarSelectionFix(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, AdditionalSettings.HotbarSelectionFix.class);
    }

}