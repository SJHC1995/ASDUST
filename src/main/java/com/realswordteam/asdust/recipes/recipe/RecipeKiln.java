package com.realswordteam.asdust.recipes.recipe;

import com.realswordteam.asdust.recipes.input.InputItemStack;
import com.realswordteam.asdust.recipes.output.OutputItemStack;

public class RecipeKiln extends RecipeSimple{
    public int time;

    public RecipeKiln(InputItemStack inputItemStack, OutputItemStack outputItemStack, int time) {
        super(inputItemStack, outputItemStack);
        this.time = time;
    }

    public int getTime()
    {
        return this.time;
    }

    @Override
    public boolean isEmpty(){
        return inputItemStack.isEmpty() && outputItemStack.isEmpty() && time <= 0;
    }

}
