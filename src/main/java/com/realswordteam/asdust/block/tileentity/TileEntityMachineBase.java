package com.realswordteam.asdust.block.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityMachineBase extends TileEntity implements ITickable {
    public int burnTime;
    protected ItemStackHandler ITEM_IN = new ItemStackHandler(4);
    protected ItemStackHandler ITEM_OUT = new ItemStackHandler(3);

    protected FluidTank fluidTank = new FluidTank(2000);


    public TileEntityMachineBase()
    {
        super();
    }
    public FluidTank getFluidTank()
    {
        return this.fluidTank;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            return true;
        }
        if (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.equals(capability))
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            if (facing == EnumFacing.DOWN) return (T) ITEM_OUT;
            else return (T) ITEM_IN;
        }
        if (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.equals(capability))
        {
            return (T) fluidTank;
        }
        return super.getCapability(capability, facing);
    }


    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.ITEM_IN.deserializeNBT(compound.getCompoundTag("Item_Put"));
        this.ITEM_OUT.deserializeNBT(compound.getCompoundTag("Item_Out"));
        this.burnTime = compound.getInteger("BurnTime");
        fluidTank.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound = super.writeToNBT(compound);
        compound.setTag("Item_Put", this.ITEM_IN.serializeNBT());
        compound.setTag("Item_Out", this.ITEM_OUT.serializeNBT());
        compound.setInteger("BurnTime", this.burnTime);
        fluidTank.writeToNBT(compound);
        return compound;
    }

    @Override
    public void update()
    {
        if (!this.world.isRemote)
        {
//对应的物品槽获取物品
            ItemStack itemStack = ITEM_IN.extractItem(0, 1, true);
//对应的物品槽塞入物品
            if (itemStack != ItemStack.EMPTY && ITEM_OUT.insertItem(0, itemStack, true) == ItemStack.EMPTY)
            {
                if (this.fluidTank.getFluidAmount() >= 100)
                {
                    int burnTotalTime = 100;

                    if (++this.burnTime >= burnTotalTime)
                    {
                        this.burnTime = 0;
                        itemStack = ITEM_IN.extractItem(0, 1, false);
                        ITEM_OUT.insertItem(0, itemStack, false);
                        this.fluidTank.drain(100,true);
                        this.markDirty();
                    }
                }

            }
            else
            {
                this.burnTime = 0;
            }

        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }

    public int getBurnTime()
    {
        return this.burnTime;
    }
    public int getTotalBurnTime()
    {
        return 100;
    }

}
