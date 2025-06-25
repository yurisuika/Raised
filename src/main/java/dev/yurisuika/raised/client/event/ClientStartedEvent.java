package dev.yurisuika.raised.client.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.Minecraft;

public final class ClientStartedEvent {

    public static final Event<ClientStarted> CLIENT_STARTED = EventFactory.createArrayBacked(ClientStarted.class, (callbacks) -> (client) -> {
        for (ClientStarted callback : callbacks) {
            callback.onClientStarted(client);
        }
    });

    public interface ClientStarted {

        void onClientStarted(Minecraft minecraft);

    }

}