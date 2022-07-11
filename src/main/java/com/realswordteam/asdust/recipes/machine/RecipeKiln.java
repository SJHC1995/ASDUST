package com.realswordteam.asdust.recipes.machine;

import com.realswordteam.asdust.recipes.recipe.RecipeSimple;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecipeKiln implements RecipeWrapper<RecipeSimple>{

    public Map<String, RecipeSimple> recipeSimpleMap = new Object2ObjectOpenHashMap<>();

    @Override
    public Map<String, RecipeSimple> getDataMap() {
        return recipeSimpleMap;
    }

    @Override
    public List<RecipeSimple> getRecipeList() {
        return new ArrayList<>(recipeSimpleMap.values());
    }

    @Override
    public void addRecipe(String name, RecipeSimple recipe) {
        recipeSimpleMap.put(name, recipe);
    }

    @Override
    public void refreshRecipe() {

    }
}
