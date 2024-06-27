package dev.yurisuika.raised.command.argument;

import com.mojang.brigadier.context.CommandContext;
import dev.yurisuika.raised.util.type.Sync;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.command.argument.EnumArgumentType;

public class SyncArgumentType extends EnumArgumentType<Sync> {

    public SyncArgumentType() {
        super(Sync.CODEC, Sync::values);
    }

    public static EnumArgumentType<Sync> sync() {
        return new SyncArgumentType();
    }

    public static Sync getSync(CommandContext<FabricClientCommandSource> context, String id) {
        return context.getArgument(id, Sync.class);
    }

}