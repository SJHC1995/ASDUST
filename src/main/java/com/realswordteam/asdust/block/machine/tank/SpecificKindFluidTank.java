package com.realswordteam.asdust.block.machine.tank;

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class SpecificKindFluidTank extends FluidTank {
    public boolean specificKind;
    public SpecificKindFluidTank(int capacity)
    {
        super(capacity);
    }

    public void setSpecificKind(boolean value)
    {
        this.specificKind = value;
    }

    @Override
    public int fill(FluidStack resource, boolean doFill)
    {
        if (specificKind)
        {
            FluidStack water = new FluidStack(FluidRegistry.WATER, resource.amount);
            if (resource.isFluidEqual(water))
            {
                return super.fill(resource, doFill);
            }
            return 0;
        }   else
        {
            return super.fill(resource, doFill);
        }
    }
}
