package com.realswordteam.asdust.recipes.machine;

import com.realswordteam.asdust.recipes.ChangeItemStack;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Map;

public class RecipeCraft {
    private static Map<ItemStack, RecipeT> recipeTMap = new Object2ObjectOpenHashMap<>();

    public static RecipeT Empty = new RecipeCraft.RecipeT(ItemStack.EMPTY, ItemStack.EMPTY, null, new ChangeItemStack(ItemStack.EMPTY, 100));

    public static void refreshRecipeT()
    {
        Map<ItemStack, RecipeT> tempRecipeT = new Object2ObjectOpenHashMap<>(recipeTMap.size());
        RecipeT recipeT;

        for (Map.Entry<ItemStack, RecipeT> entry : recipeTMap.entrySet())
        {
            recipeT = entry.getValue();
            ItemStack input = entry.getKey();
            tempRecipeT.put(input, recipeT);
        }

        recipeTMap.clear();

        recipeTMap = tempRecipeT;
    }
    public static Map<ItemStack, RecipeT> getRecipeTMap()
    {
        return recipeTMap;
    }
    public static void addRecipe(ItemStack input, RecipeT recipeT)
    {
        recipeTMap.put(input, recipeT);
    }
    public static class RecipeT
    {
        final ItemStack input;
        final ItemStack output;
        final ChangeItemStack byProduction;
        final FluidStack fluidStack;
        public RecipeT(ItemStack input, ItemStack output, FluidStack fluidStack, ChangeItemStack byProduction)
        {
            this.input = input;
            this.output = output;
            this.fluidStack = fluidStack;
            this.byProduction = byProduction;
        }

        public ItemStack getInput()
        {
            return input;
        }

        public ItemStack getOutput()
        {
            return output;
        }
        public FluidStack getFluidStack()
        {
            return fluidStack;
        }
        public ChangeItemStack getByProduction()
        {
            return byProduction;
        }
        public boolean isEmpty()
        {
            return this.byProduction.isEmpty() && this.output.isEmpty() && this.input.isEmpty() && this.fluidStack == null;
        }
    }

}
