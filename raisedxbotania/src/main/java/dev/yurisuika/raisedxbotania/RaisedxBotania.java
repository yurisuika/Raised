package dev.yurisuika.raisedxbotania;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("raisedxbotania")
public class RaisedxBotania {

    public RaisedxBotania() {
        MinecraftForge.EVENT_BUS.register(this);
    }

}