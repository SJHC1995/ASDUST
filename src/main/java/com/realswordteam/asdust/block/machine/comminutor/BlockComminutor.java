package com.realswordteam.asdust.block.machine.comminutor;

import com.realswordteam.asdust.block.machine.MachineBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockComminutor extends MachineBase {
    public BlockComminutor() {
        super(0, Material.ROCK);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nullable World world, int meta) {
        return new TileComminutor();
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }
}
