package com.realswordteam.asdust.block.machine.kiln;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityKiln extends TileEntity {
    protected ItemStackHandler inputsHandler = new ItemStackHandler(2);

    protected ItemStackHandler outputsHandler = new ItemStackHandler();

    protected ItemStackHandler fuelHandler = new ItemStackHandler();

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
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
            if (facing == EnumFacing.DOWN) return (T) outputsHandler;
            if (facing == EnumFacing.EAST) return (T) fuelHandler;
            else return (T) inputsHandler;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.inputsHandler.deserializeNBT(compound.getCompoundTag("item_input"));
        this.outputsHandler.deserializeNBT(compound.getCompoundTag("item_output"));
        this.fuelHandler.deserializeNBT(compound.getCompoundTag("fuel"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setTag("item_input", this.inputsHandler.serializeNBT());
        compound.setTag("item_output", this.outputsHandler.serializeNBT());
        compound.setTag("fuel", this.fuelHandler.serializeNBT());
        return compound;
    }
}
