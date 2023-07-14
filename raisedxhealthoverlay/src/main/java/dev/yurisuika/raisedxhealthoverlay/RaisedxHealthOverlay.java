package dev.yurisuika.raisedxhealthoverlay;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("raisedxhealthoverlay")
public class RaisedxHealthOverlay {

    public RaisedxHeathOverlay() {
        MinecraftForge.EVENT_BUS.register(this);
    }

}