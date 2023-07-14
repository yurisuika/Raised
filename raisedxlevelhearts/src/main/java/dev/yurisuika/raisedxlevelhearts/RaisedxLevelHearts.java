package dev.yurisuika.raisedxlevelhearts;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("raisedxlevelhearts")
public class RaisedxLevelHearts {

    public RaisedxLevelHearts() {
        MinecraftForge.EVENT_BUS.register(this);
    }

}