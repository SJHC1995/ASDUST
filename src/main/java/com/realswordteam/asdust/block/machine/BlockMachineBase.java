package com.realswordteam.asdust.block.machine;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.machine.tileentity.TileEntityMachineCraft;
import com.realswordteam.asdust.gui.GuiElementLoader;
import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import com.realswordteam.asdust.recipes.input.InputItemStack;
import com.realswordteam.asdust.recipes.machine.RecipeCraft;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
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
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.*;

public class BlockMachineBase extends BlockContainer {

    public BlockMachineBase(Material material){
        super(material);
        this.setCreativeTab(CreativeTabLoader.TAB_ASDUST_MACHINE);
        this.setDefaultState(this.getBlockState().getBaseState().withProperty(FACING, EnumFacing.EAST));
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileEntityMachineCraft te = (TileEntityMachineCraft) worldIn.getTileEntity(pos);
        IFluidHandler iFluidHandler = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
        if (!worldIn.isRemote)
        {
            InputItemStack inputItemStack = new InputItemStack();
            boolean flag = inputItemStack.isEmpty();
            if (!playerIn.isSneaking())
            {
                if (!FluidUtil.interactWithFluidHandler(playerIn, hand, iFluidHandler))
                {
                    int id = GuiElementLoader.GUI_DEBUG;
                    playerIn.openGui(ASDUST.instance, id, worldIn, pos.getX(), pos.getY(), pos.getZ());
                }
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntityMachineCraft te = (TileEntityMachineCraft) worldIn.getTileEntity(pos);

        IItemHandler up = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        IItemHandler down = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);

        for (int i = up.getSlots() - 1; i >= 0; --i)
        {
            if (up.getStackInSlot(i) != null)
            {
                Block.spawnAsEntity(worldIn, pos, up.getStackInSlot(i));
            }
        }

        for (int i = down.getSlots() - 1; i >= 0; --i)
        {
            if (down.getStackInSlot(i) != null)
            {
                Block.spawnAsEntity(worldIn, pos, down.getStackInSlot(i));
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityMachineCraft();
    }
    //change tileEntity RenderType
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state)
    {
        return EnumBlockRenderType.MODEL;
    }


    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
    }

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
    @Override
    public IBlockState getStateFromMeta(int meta){
      EnumFacing  facing  = EnumFacing.getHorizontal(meta & 3);

      return this.getDefaultState().withProperty(FACING,facing);

    }
    @Override
    public int getMetaFromState(IBlockState state){
        int facing = state.getValue(FACING).getHorizontalIndex();
        return facing;
    }
@Override
protected BlockStateContainer createBlockState(){return new BlockStateContainer(this,FACING);}
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }

}
