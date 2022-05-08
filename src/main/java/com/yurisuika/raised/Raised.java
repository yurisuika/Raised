package com.yurisuika.raised;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod("raised")
public class Raised
{
    private static final Logger LOGGER = LogUtils.getLogger();

    public static int distance = 2;

    public static void setDistance(int change) {
        distance += change;
    }

    public static int getDistance() {
        return distance;
    }

    public static KeyMapping down;
    public static KeyMapping up;

    public void input(InputEvent.KeyInputEvent event){
        if (down.consumeClick()) {
            setDistance(-1);
        }
        if (up.consumeClick()) {
            setDistance(1);
        }
    }

    public Raised()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void setup(final FMLClientSetupEvent event) {
        LOGGER.info("Loading Raised!");
        MinecraftForge.EVENT_BUS.addListener(this::input);

        down = new KeyMapping("raised.down", KeyConflictContext.IN_GAME, InputConstants.getKey("key.keyboard.minus"), "raised.title");
        up = new KeyMapping("raised.up", KeyConflictContext.IN_GAME, InputConstants.getKey("key.keyboard.equal"), "raised.title");

        ClientRegistry.registerKeyBinding(down);
        ClientRegistry.registerKeyBinding(up);
    }

}