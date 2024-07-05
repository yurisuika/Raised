package dev.yurisuika.raised.commands.arguments;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.type.Sync;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.commands.arguments.StringRepresentableArgument;

public class SyncArgument extends StringRepresentableArgument<Sync> {

    public SyncArgument() {
        super(Sync.CODEC, Sync::values);
    }

    public static StringRepresentableArgument<Sync> sync() {
        return new SyncArgument();
    }

    public static Sync getSync(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Sync.class);
    }

}