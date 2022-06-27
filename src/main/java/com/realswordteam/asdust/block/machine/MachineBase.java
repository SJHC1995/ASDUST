package com.realswordteam.asdust.block.machine;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;

public class MachineBase extends BlockContainer {
    private final int gui;
    public MachineBase(int gui, Material material)
    {
        super(material);
        this.gui = gui;
        this.setCreativeTab(CreativeTabLoader.TAB_ASDUST_MACHINE);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nullable World world, int meta) {
        return null;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            if (!playerIn.isSneaking())
            {
                playerIn.openGui(ASDUST.instance, this.gui, worldIn, pos.getX(), pos.getY(), pos.getZ());
                return true;
            }
        }
        return false;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @Nonnull
    public EnumBlockRenderType getRenderType(@Nullable IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public void onBlockPlacedBy(@Nullable World worldIn,@Nullable BlockPos pos,@Nullable IBlockState state,@Nullable EntityLivingBase placer,@Nullable ItemStack stack)
    {
    }

    @Override
    @Nonnull
    public Item getItemDropped(@Nullable IBlockState state,@Nullable Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }

}
