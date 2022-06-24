package com.realswordteam.asdust.block.machine.tank;

import com.realswordteam.asdust.block.machine.BlockBaseTank;
import com.realswordteam.asdust.block.machine.tank.TileEntityWoodenTank;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockWoodenTank extends BlockBaseTank {
    public BlockWoodenTank()
    {
        super(Material.WOOD);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityWoodenTank();
    }
}
