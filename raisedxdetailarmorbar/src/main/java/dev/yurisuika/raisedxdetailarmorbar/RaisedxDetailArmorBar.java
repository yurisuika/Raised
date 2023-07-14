package dev.yurisuika.raisedxdetailarmorbar;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("raisedxdetailarmorbar")
public class RaisedxDetailArmorBar {

    public RaisedxDetailArmorBar() {
        MinecraftForge.EVENT_BUS.register(this);
    }

}