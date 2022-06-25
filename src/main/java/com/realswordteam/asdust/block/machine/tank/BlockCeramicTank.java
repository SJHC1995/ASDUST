package com.realswordteam.asdust.block.machine.tank;

import com.realswordteam.asdust.block.machine.BlockBaseTank;
import com.realswordteam.asdust.block.machine.tank.TileEntityCeramicTank;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCeramicTank extends BlockBaseTank {
    public BlockCeramicTank()
    {
        super(Material.ROCK);
        this.setHardness(5.0f);
        this.setResistance(10.0F);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCeramicTank();
    }
}
