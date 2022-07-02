package com.realswordteam.asdust.modules.jei.machinecraft;

import com.realswordteam.asdust.recipes.ChangeItemStack;
import com.realswordteam.asdust.recipes.input.InputItemStack;
import com.realswordteam.asdust.recipes.machine.RecipeCraft;
import com.realswordteam.asdust.recipes.output.OutputItemStack;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MachineCraftWrapper implements IRecipeWrapper {

    public String name;
    public RecipeCraft.RecipeT recipeT;

    public MachineCraftWrapper(String name, RecipeCraft.RecipeT recipeT)
    {
        this.name = name;
        this.recipeT = recipeT;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<ItemStack> inputs = new ArrayList<>();
        if (!name.isEmpty())
        {
            InputItemStack inputItemStack = recipeT.getInputItemStack();
            for (int num = 0; num < inputItemStack.getSize(); num++)
            {
                inputs.add(inputItemStack.getInput(num));
            }
        }
        ingredients.setInputs(VanillaTypes.ITEM, inputs);

        if (!name.isEmpty())
        {
            if (!recipeT.getInputFluidStack().isEmpty())
            {
                ingredients.setInput(VanillaTypes.FLUID, recipeT.getInputFluidStack().getInput(0));
            }
        }

        List<ItemStack> outputs = new ArrayList<>();
        if (!name.isEmpty())
        {
            OutputItemStack outputItemStack = recipeT.getOutputItemStack();
            for (int num = 0; num < outputItemStack.getSize(); num++)
            {
                outputs.add(outputItemStack.getOutput(num));
            }
        }

        ingredients.setOutputs(VanillaTypes.ITEM, outputs);
    }
}
