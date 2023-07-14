package dev.yurisuika.raisedxinventorio;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("raisedxinventorio")
public class RaisedxInventorio {

    public RaisedxInventorio() {
        MinecraftForge.EVENT_BUS.register(this);
    }

}