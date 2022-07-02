//package com.realswordteam.asdust.modules.jei.machinecraft;
//
//import com.realswordteam.asdust.recipes.machine.RecipeCraft;
//import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
//import mezz.jei.api.IJeiHelpers;
//import mezz.jei.api.recipe.IStackHelper;
//import net.minecraft.item.ItemStack;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class MachineCraftMaker {
//    public static List<MachineCraftWrapper> getRecipe(IJeiHelpers helpers)
//    {
//        IStackHelper stackHelper = helpers.getStackHelper();
//        Map<ItemStack, RecipeCraft.RecipeT> recipeTMap = RecipeCraft.getRecipeTMap();
//
//        List<MachineCraftWrapper> recipes = new ArrayList<>();
//
//        for (Map.Entry<ItemStack, RecipeCraft.RecipeT> entry : recipeTMap.entrySet()) {
//            ItemStack coreItem = entry.getKey();
//            RecipeCraft.RecipeT recipeT = entry.getValue();
//
//            MachineCraftWrapper recipeWrapper = new MachineCraftWrapper(coreItem, recipeT);
//            recipes.add(recipeWrapper);
//
//        }
//
//        return recipes;
//    }
//}
