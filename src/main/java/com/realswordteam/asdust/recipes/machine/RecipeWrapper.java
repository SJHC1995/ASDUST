package com.realswordteam.asdust.recipes.machine;

import com.realswordteam.asdust.recipes.recipe.RecipeSimple;

import java.util.List;
import java.util.Map;

public interface RecipeWrapper<T extends RecipeSimple> {
    Map<String, T> getDataMap();

    List<T> getRecipeList();

    void addRecipe(String name, T recipe);

    void refreshRecipe();

}
