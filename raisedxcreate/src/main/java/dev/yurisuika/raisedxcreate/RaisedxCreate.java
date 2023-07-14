package dev.yurisuika.raisedxcreate;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("raisedxcreate")
public class RaisedxCreate {

    public RaisedxCreate() {
        MinecraftForge.EVENT_BUS.register(this);
    }

}