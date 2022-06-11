package com.realswordteam.asdust.block.tileentity;

import com.realswordteam.asdust.ASDUST;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
    public TileEntityLoader(FMLPreInitializationEvent event)
    {
        registerTileEntity(TileEntityMachineBase.class, "machinecraft");
    }

    public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String name)
    {
        GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(ASDUST.MODID + ":" + name));
    }
}
