package com.realswordteam.asdust.recipes.recipe;

import com.realswordteam.asdust.recipes.input.InputItemStack;
import com.realswordteam.asdust.recipes.output.OutputItemStack;

public class RecipeSimple {
    public InputItemStack inputItemStack;

    public OutputItemStack outputItemStack;

    RecipeSimple()
    {

    }

    RecipeSimple(InputItemStack inputItemStack, OutputItemStack outputItemStack)
    {
        this.inputItemStack = inputItemStack;
        this.outputItemStack = outputItemStack;
    }
}
