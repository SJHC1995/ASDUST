package com.realswordteam.asdust.recipes.machine;

import com.realswordteam.asdust.recipes.ChangeItemStack;
import com.realswordteam.asdust.recipes.input.InputFluidStack;
import com.realswordteam.asdust.recipes.input.InputItemStack;
import com.realswordteam.asdust.recipes.output.OutputItemStack;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeCraft {
    private static Map<String, RecipeT> recipeTMap = new Object2ObjectOpenHashMap<>();
    private static List<RecipeT> recipeTList = new ArrayList<>();
    public static RecipeT Empty = new RecipeCraft.RecipeT(new InputItemStack(), new InputFluidStack(), new OutputItemStack());

    public static List<RecipeT> getRecipeTList()
    {
        return recipeTList;
    }
    public static Map<String, RecipeT> getRecipeTMap()
    {
        return recipeTMap;
    }
    public static void addRecipe(String name, RecipeT recipeT)
    {
        recipeTMap.put(name, recipeT);
        recipeTList.add(recipeT);
    }

    public static void refreshRecipeT()
    {
        Map<String, RecipeT> tempRecipeT = new Object2ObjectOpenHashMap<>(recipeTMap.size());
        RecipeT recipeT;

        for (Map.Entry<String, RecipeT> entry : recipeTMap.entrySet())
        {
            recipeT = entry.getValue();
            String name = entry.getKey();
            tempRecipeT.put(name, recipeT);
        }

        recipeTMap.clear();

        recipeTMap = tempRecipeT;
    }
    public static class RecipeT
    {
        final InputItemStack inputItemStack;
        final InputFluidStack inputFluidStack;
        final OutputItemStack outputItemStack;
        public RecipeT(InputItemStack inputItemStack, InputFluidStack inputFluidStack, OutputItemStack outputItemStack)
        {
            this.inputItemStack = inputItemStack;
            this.inputFluidStack = inputFluidStack;
            this.outputItemStack = outputItemStack;
        }

        public InputItemStack getInputItemStack()
        {
            return inputItemStack;
        }

        public OutputItemStack getOutputItemStack()
        {
            return outputItemStack;
        }

        public InputFluidStack getInputFluidStack()
        {
            return inputFluidStack;
        }
        public boolean isEmpty()
        {
            return this.equals(Empty);
        }
    }

}
