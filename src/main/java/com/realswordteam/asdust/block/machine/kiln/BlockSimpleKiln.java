package com.realswordteam.asdust.block.machine.kiln;

import com.realswordteam.asdust.block.machine.MachineBase;
import com.realswordteam.asdust.recipes.machine.RecipeCraft;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Map;
import java.util.Random;

public class BlockSimpleKiln extends MachineBase {
    public static boolean isWorking;
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    public static final PropertyBool WORKING = PropertyBool.create("working");
    public BlockSimpleKiln(Material material)
    {
        super(1, material);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(WORKING, false));
    }



    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings("incomplete-switch")
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (stateIn.getValue(WORKING))
        {
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
                this.spawnSimpleParticle(enumfacing, worldIn, pos, d6, d5, d7 + 0.1875D);
            }
            if (randNum2 < 3)
            {
                this.spawnSimpleParticle(enumfacing, worldIn, pos, d6, d5, d7 + 0.4375D);
            }
            if (randNum3 < 3)
            {
                this.spawnSimpleParticle(enumfacing, worldIn, pos, d6, d5, d7 + 0.6875D);
            }
            if (randNum4 < 3)
            {
                this.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.3125D, d5, d7 + 0.4375D);
            }
            if (randNum5 < 3)
            {
                this.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.625D, d5, d7 + 0.1875D);
            }
            if (randNum6 < 3)
            {
                this.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.625D, d5, d7 + 0.4375D);
            }
            if (randNum7 < 3)
            {
                this.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.625D, d5, d7 + 0.6875D);
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

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            if (!state.getValue(WORKING))
            {
                worldIn.setBlockState(pos, state.withProperty(WORKING, true), 2);
                return true;
            }
//            else if (state.getValue(WORKING))
//            {
//                worldIn.setBlockState(pos, state.withProperty(WORKING, false), 2);
//                return true;
//            }

        }
        return false;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FACING, WORKING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(FACING)).getIndex();
    }

    private void spawnSimpleParticle(EnumFacing facing, World world, BlockPos pos, double x, double y, double z)
    {

        double x1 = this.fastCalculateParticlesX(facing, x - pos.getX(), z - pos.getZ());
        double z1 = this.fastCalculateParticlesZ(facing, x - pos.getX(), z - pos.getZ());
        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x1 + pos.getX(), y, z1 + pos.getZ(), 0.0D, 0.0D, 0.0D);
    }

    private double fastCalculateParticlesX(EnumFacing facing, double x, double z)
    {
        switch (facing)
        {
            case WEST:
                return z;
            case EAST:
                return 1.0D - z;
            case NORTH:
                return x;
            case SOUTH:
                return 1.0D - x;
        }
        return x;
    }

    private double fastCalculateParticlesZ(EnumFacing facing, double x, double z)
    {
        switch (facing)
        {
            case WEST:
                return 1.0D -x;
            case EAST:
                return x;
            case NORTH:
                return z;
            case SOUTH:
                return 1.0D - z;
        }
        return z;
    }
}
