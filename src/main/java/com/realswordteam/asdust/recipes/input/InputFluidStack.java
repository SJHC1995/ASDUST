package com.realswordteam.asdust.recipes.input;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputFluidStack {
    final List<FluidStack> inputs = new ArrayList<>();
    public static InputFluidStack EMPTY = new InputFluidStack((FluidStack) null);
    public InputFluidStack(FluidStack... fluidStacks)
    {
        this.inputs.addAll(Arrays.asList(fluidStacks));
    }
    public int getSize()
    {
        return inputs.size();
    }
    public boolean isEmpty()
    {
        return inputs.isEmpty();
    }

    public List<FluidStack> getInputs()
    {
        return this.inputs;
    }

    public FluidStack getInput(int value)
    {
        if (value < inputs.size() && value >= 0)
        {
            return inputs.get(value);
        }
        return null;
    }
}
