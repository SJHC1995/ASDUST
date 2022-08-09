package com.realswordteam.asdust.modules.jei.kiln;

import com.realswordteam.asdust.recipes.RecipesLoader;
import com.realswordteam.asdust.recipes.machine.Kiln;
import com.realswordteam.asdust.recipes.recipe.RecipeSimple;
import mezz.jei.api.IJeiHelpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MachineKilnMaker {
    public static List<MachineKilnWrapper> getRecipe(IJeiHelpers helpers)
    {
        Map<String, RecipeSimple> map = RecipesLoader.kiln.getDataMap();

        List<MachineKilnWrapper> recipes = new ArrayList<>();

        for(Map.Entry<String, RecipeSimple> entry : map.entrySet())
        {
            String name = entry.getKey();
            RecipeSimple recipe = entry.getValue();

            MachineKilnWrapper recipeWrapper = new MachineKilnWrapper(name, recipe);
            recipes.add(recipeWrapper);
        }

        return  recipes;
    }
}
