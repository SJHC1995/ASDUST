package com.realswordteam.asdust.block.machine.kiln;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockCobbleStoneKiln extends BlockBaseKiln {
    public BlockCobbleStoneKiln()
    {
        super(Material.ROCK);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nullable World world, int meta) {
        return new TileEntityKiln();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            TileEntity te =  worldIn.getTileEntity(pos);
            if (te instanceof TileEntityKiln)
            {
                ((TileEntityKiln) te).onBlockActivatedInKiln(worldIn, playerIn, pos);
                return true;
            }
        }
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityKiln)
        {
            ((TileEntityKiln) te).spawnParticlesInWorking(worldIn, stateIn, rand, pos);
        }
    }
}
