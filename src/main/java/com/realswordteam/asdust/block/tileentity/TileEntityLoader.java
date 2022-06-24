package com.realswordteam.asdust.block.tileentity;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.machine.tileentity.TileEntityMachineCraft;
import com.realswordteam.asdust.block.machine.tank.TileEntityBaseTank;
import com.realswordteam.asdust.block.machine.tank.TileEntityCeramicTank;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {

    public TileEntityLoader()
    {
        registerTileEntity(TileEntityMachineCraft.class, "machinecraft");
        registerTileEntity(TileEntityBaseTank.class, "tank");
        registerTileEntity(TileEntityCeramicTank.class, "ceramic_tank");
    }

    public static void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String name)
    {
        GameRegistry.registerTileEntity(tileEntityClass, new ResourceLocation(ASDUST.MODID + ":" + name));
    }
}
