package dev.yurisuika.raisedxquark;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("raisedxquark")
public class RaisedxQuark {

    public RaisedxQuark() {
        MinecraftForge.EVENT_BUS.register(this);
    }

}