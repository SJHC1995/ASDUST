package com.realswordteam.asdust.proxy;

import com.realswordteam.asdust.block.machine.comminutor.TileComminutor;
import com.realswordteam.asdust.modules.geckolib.ComminutorRender;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends ProxyBase
{
    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(this);

        ClientRegistry.bindTileEntitySpecialRenderer(TileComminutor.class, new ComminutorRender());
    }

    @Override
    public void init() {
        MinecraftForge.EVENT_BUS.register(this);

    }
}
