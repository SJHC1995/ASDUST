package com.realswordteam.asdust.modules.jei.kiln;

import com.realswordteam.asdust.recipes.input.InputItemStack;
import com.realswordteam.asdust.recipes.machine.Kiln;
import com.realswordteam.asdust.recipes.output.OutputItemStack;
import com.realswordteam.asdust.recipes.recipe.RecipeSimple;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class MachineKilnWrapper implements IRecipeWrapper {

    public String name;

    public RecipeSimple recipe;

    public MachineKilnWrapper(String name,RecipeSimple recipe)
    {
        this.name = name;
        this.recipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        List<ItemStack> inputs = new ArrayList<>();
        List<ItemStack> outputs = new ArrayList<>();
        if (!name.isEmpty())
        {
            InputItemStack inputItemStack = recipe.getInputItemStack();
            for (int num = 0; num < inputItemStack.getSize(); num++)
            {
                inputs.add(inputItemStack.getInputStack(num));
            }
            ingredients.setInputs(VanillaTypes.ITEM, inputs);

            OutputItemStack outputItemStack = recipe.getOutputItemStack();
            for (int num = 0; num < inputItemStack.getSize(); num++)
            {
                outputs.add(outputItemStack.getOutputStack(num));
            }
            ingredients.setOutputs(VanillaTypes.ITEM, outputs);
        }
    }
}
