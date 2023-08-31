package dev.yurisuika.raisedxtoughasnails;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("raisedxtoughasnails")
public class RaisedxToughAsNails {

    public RaisedxToughAsNails() {
        MinecraftForge.EVENT_BUS.register(this);
    }

}