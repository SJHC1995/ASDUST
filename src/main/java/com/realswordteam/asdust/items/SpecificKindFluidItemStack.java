package com.realswordteam.asdust.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;

public class SpecificKindFluidItemStack extends FluidHandlerItemStack {
    public SpecificKindFluidItemStack(ItemStack container, int capacity)
    {
        super(container, capacity);
    }

    @Override
    public int fill(FluidStack resource, boolean doFill)
    {
        FluidStack water = new FluidStack(FluidRegistry.WATER, resource.amount);
        if (resource.isFluidEqual(water))
        {
            return super.fill(resource, doFill);
        }
        return 0;
    }

}
