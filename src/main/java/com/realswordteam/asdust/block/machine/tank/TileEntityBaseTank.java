package com.realswordteam.asdust.block.machine.tank;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBaseTank extends TileEntity implements ITickable {
    public int processTime;
    protected int totalProcessTime = 20;
    protected ItemStackHandler ITEM_IN = new ItemStackHandler(1);
    protected ItemStackHandler ITEM_OUT = new ItemStackHandler(1);
    protected boolean checkItemBool = false;
    protected ItemStack oldItemStack = ItemStack.EMPTY;
    protected int tankCapacity;
    protected String blockName = "null";
    protected FluidTank tank = new FluidTank(1000);
    public TileEntityBaseTank()
    {
    }
    public FluidTank getTank()
    {
        return this.tank;
    }
    public FluidStack getFluidStack()
    {
        return this.tank.getFluid();
    }
    public Fluid getFluid()
    {
        if (this.getFluidStack() != null)
        {
            return this.getFluidStack().getFluid();
        }
        return null;
    }
    public int getTankCapacity()
    {
        return this.tank.getCapacity();
    }
    public int getProcessTime()
    {
        return this.processTime;
    }
    public ItemStackHandler getITEM_IN()
    {
        return this.ITEM_IN;
    }
    public ItemStackHandler getITEM_OUT()
    {
        return this.ITEM_OUT;
    }
    public String getBlockName()
    {
        return this.blockName;
    }
    @Override
    public void update()
    {
        if (!world.isRemote)
        {
            if (!ITEM_IN.getStackInSlot(0).isEmpty())
            {
                ItemStack copyItemStack = this.ITEM_IN.getStackInSlot(0);

                this.checkItemStack(copyItemStack);

                FluidActionResult fillItemContainerResult = FluidUtil.tryFillContainerAndStow(this.ITEM_IN.getStackInSlot(0), this.tank, this.ITEM_OUT, this.getTankCapacity(), null, false);
                FluidActionResult emptyItemContainerResult = FluidUtil.tryEmptyContainerAndStow(this.ITEM_IN.getStackInSlot(0), this.tank, this.ITEM_OUT, this.getTankCapacity(), null, false);
                if (fillItemContainerResult.isSuccess())
                {
                    if (updateTime())
                    {
                        FluidUtil.tryFillContainerAndStow(copyItemStack, this.tank, this.ITEM_OUT, this.getTankCapacity(), null, true);
                        this.ITEM_IN.extractItem(0, 1, false);
                        this.ITEM_OUT.insertItem(0, fillItemContainerResult.getResult(), false);
                        this.processTime = 0;
                        this.markDirty();
                    }
                }   else if (emptyItemContainerResult.isSuccess())
                {
                    if (updateTime())
                    {
                        FluidUtil.tryEmptyContainerAndStow(copyItemStack, this.tank, this.ITEM_OUT, this.getTankCapacity(), null, true);
                        this.ITEM_IN.extractItem(0, 1, false);
                        this.ITEM_OUT.insertItem(0, emptyItemContainerResult.getResult(), false);
                        this.processTime = 0;
                        this.markDirty();
                    }
                }
            }
        }
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
            if (facing == EnumFacing.DOWN)
            {
                return  (T) ITEM_OUT;
            }   else
            {
                return (T) ITEM_IN;
            }
        }
        if (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.equals(capability))
        {
            return (T) tank;
        }
        return super.getCapability(capability, facing);
    }


    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.ITEM_IN.deserializeNBT(compound.getCompoundTag("Item_Put"));
        this.ITEM_OUT.deserializeNBT(compound.getCompoundTag("Item_Out"));
        this.tankCapacity = compound.getInteger("tankCapacity");
        tank.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound = super.writeToNBT(compound);
        compound.setTag("Item_Put", this.ITEM_IN.serializeNBT());
        compound.setTag("Item_Out", this.ITEM_OUT.serializeNBT());
        compound.setInteger("tankCapacity", this.tankCapacity);
        tank.writeToNBT(compound);
        return compound;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }

    private boolean updateTime()
    {
        return ++this.processTime >= this.totalProcessTime;
    }
    private void checkItemStack(ItemStack getItemStack)
    {
        if (this.oldItemStack != getItemStack)
        {
            this.checkItemBool = true;
            this.markDirty();
        }
        if (this.checkItemBool)
        {
            this.oldItemStack = getItemStack;
            this.checkItemBool = false;
            this.processTime = 0;
            this.markDirty();
        }
    }

}
