package com.realswordteam.asdust.recipes.recipe;

import com.realswordteam.asdust.recipes.input.InputFluidStack;
import com.realswordteam.asdust.recipes.input.InputItemStack;
import com.realswordteam.asdust.recipes.output.OutputItemStack;

public class RecipeCraft
{
    public InputItemStack inputItemStack;

    public InputFluidStack inputFluidStack;

    public OutputItemStack outputItemStack;

    public RecipeCraft(InputItemStack inputItemStack, InputFluidStack inputFluidStack, OutputItemStack outputItemStack)
    {
        this.inputItemStack = inputItemStack;
        this.inputFluidStack = inputFluidStack;
        this.outputItemStack = outputItemStack;
    }
}
