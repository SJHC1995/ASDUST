package com.realswordteam.asdust.block.machine.kiln;

import com.realswordteam.asdust.block.BlockLoader;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
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

    public static void setState(World worldIn, BlockPos pos, Boolean active)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        worldIn.setBlockState(pos, BlockLoader.SIMPLE_KILN.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)).withProperty(WORKING, active), 3);

        if (tileentity != null)
        {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
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
            if(stateIn.getValue(WORKING))
            {
                spawnParticles(worldIn, stateIn, rand, pos);
            }
        }
    }

    private void spawnParticles(World worldIn, IBlockState stateIn, Random rand, BlockPos pos)
    {
        SimpleSpawnParticle ssp = new SimpleSpawnParticle();

        EnumFacing enumfacing = stateIn.getValue(FACING);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = (double)pos.getZ() + 0.5D;
        double d3 = 0.52D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;
        double d5 = pos.getY() + 1.0D;
        double d6 = pos.getX() + 0.1875D;
        double d7 = pos.getZ();

        if (rand.nextDouble() < 0.1D)
        {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }

        int randNum = rand.nextInt(10), randNum2 = rand.nextInt(10), randNum3 = rand.nextInt(10),
                randNum4 = rand.nextInt(10), randNum5 = rand.nextInt(10), randNum6 = rand.nextInt(10), randNum7 = rand.nextInt(10);
        if (randNum < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6, d5, d7 + 0.1875D);
        }
        if (randNum2 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6, d5, d7 + 0.4375D);
        }
        if (randNum3 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6, d5, d7 + 0.6875D);
        }
        if (randNum4 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.3125D, d5, d7 + 0.4375D);
        }
        if (randNum5 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.625D, d5, d7 + 0.1875D);
        }
        if (randNum6 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.625D, d5, d7 + 0.4375D);
        }
        if (randNum7 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.625D, d5, d7 + 0.6875D);
        }

        switch (enumfacing)
        {
            case WEST:
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                break;
            case EAST:
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                break;
            case NORTH:
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                break;
            case SOUTH:
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
        }
    }
}
