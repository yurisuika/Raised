package dev.yurisuika.raisedxinventoryprofilesnext;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("raisedxinventoryprofilesnext")
public class RaisedxInventoryProfilesNext {

    public RaisedxInventoryProfilesNext() {
        MinecraftForge.EVENT_BUS.register(this);
    }

}