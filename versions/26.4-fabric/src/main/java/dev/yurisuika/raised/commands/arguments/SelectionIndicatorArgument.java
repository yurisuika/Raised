package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.option.Settings;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class SelectionIndicatorArgument extends StringRepresentableArgument<Settings.SelectionIndicator> {

    public SelectionIndicatorArgument() {
        super(Settings.SelectionIndicator.CODEC, Settings.SelectionIndicator::values);
    }

    public static StringRepresentableArgument<Settings.SelectionIndicator> selectionIndicator() {
        return new SelectionIndicatorArgument();
    }

    public static Settings.SelectionIndicator getSelectionIndicator(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Settings.SelectionIndicator.class);
    }

}