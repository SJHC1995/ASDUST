package com.realswordteam.asdust.block.machine.kiln;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCobbleStoneKiln extends BlockSimpleKiln{
    public BlockCobbleStoneKiln()
    {
        super(Material.ROCK);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nullable World world, int meta) {
        return new TileEntityKiln();
    }
}
