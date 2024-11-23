package com.example.toolmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.common.Mod;

@Mod("toolswapper")
public class ToolSwapperMod {
    public static final String MODID = "toolswapper";

    public ToolSwapperMod() {
      // Register event listeners
      MinecraftForge.EVENT_BUS.register(this);
      MinecraftForge.EVENT_BUS.register(new ToolBreakHandler());
    }
}
