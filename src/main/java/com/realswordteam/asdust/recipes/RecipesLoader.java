package com.realswordteam.asdust.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RecipesLoader {
    public RecipesLoader()
    {
        MachineTestRecipe.recipe.addMachineTestRecipe(
                new Item[]{Items.APPLE, Items.IRON_INGOT},
                new ItemStack[]{new ItemStack(Items.GOLD_INGOT, 4), new ItemStack(Items.DIAMOND, 1)}
        );
    }

}
