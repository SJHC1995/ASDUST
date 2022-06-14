package com.realswordteam.asdust.block.machine;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.machine.tileentity.TileEntityTank;
import com.realswordteam.asdust.gui.GuiElementLoader;
import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class BlockTank extends MachineBase {
    public static final PropertyBool PROCESSING = PropertyBool.create("process");
    public BlockTank()
    {
        super(GuiElementLoader.GUI_TANK);
        this.setCreativeTab(CreativeTabLoader.TAB_ASDUST_MACHINE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(PROCESSING, false));
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTank();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            if (!playerIn.isSneaking())
            {
                TileEntityTank te = (TileEntityTank) worldIn.getTileEntity(pos);
                IFluidHandler iFluidHandler = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
                if (!FluidUtil.interactWithFluidHandler(playerIn, hand, iFluidHandler))
                {
                    playerIn.openGui(ASDUST.instance, GuiElementLoader.GUI_TANK, worldIn, pos.getX(), pos.getY(), pos.getZ());
                }
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntityTank te = (TileEntityTank) worldIn.getTileEntity(pos);

        IItemHandler up = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);
        IItemHandler down = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);

        if (up.getStackInSlot(0) != null)
        {
            Block.spawnAsEntity(worldIn, pos, up.getStackInSlot(0));
        }

        if (down.getStackInSlot(0) != null)
        {
            Block.spawnAsEntity(worldIn, pos, down.getStackInSlot(0));
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        if (meta != 0)
        {
            return this.getDefaultState().withProperty(PROCESSING, false);
        }   else
        {
            return this.getDefaultState().withProperty(PROCESSING,true);
        }
    }
    @Override
    public int getMetaFromState(IBlockState state){
        if (state.getValue(PROCESSING))
        {
            return 1;
        }   else
        {
            return 0;
        }
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, PROCESSING);
    }
}
