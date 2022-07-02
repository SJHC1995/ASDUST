//package com.realswordteam.asdust.modules.jei.machinecraft;
//
//import com.realswordteam.asdust.recipes.ChangeItemStack;
//import com.realswordteam.asdust.recipes.machine.RecipeCraft;
//import mezz.jei.api.ingredients.IIngredients;
//import mezz.jei.api.ingredients.VanillaTypes;
//import mezz.jei.api.recipe.IRecipeWrapper;
//import net.minecraft.item.ItemStack;
//import net.minecraftforge.fluids.FluidStack;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//public class MachineCraftWrapper implements IRecipeWrapper {
//
//    public ItemStack coreItem;
//    public RecipeCraft.RecipeT recipeT;
//
//    public MachineCraftWrapper(ItemStack coreItem, RecipeCraft.RecipeT recipeT)
//    {
//        this.coreItem = coreItem;
//        this.recipeT = recipeT;
//    }
//
//    @Override
//    public void getIngredients(IIngredients ingredients) {
//        List<ItemStack> inputs = new ArrayList<>();
//        inputs.add(coreItem);
//        inputs.add(recipeT.getInput());
//        ingredients.setInputs(VanillaTypes.ITEM, inputs);
//
//        ingredients.setInput(VanillaTypes.FLUID, recipeT.getFluidStack());
//
//        List<ItemStack> outputs = new ArrayList<>();
//        outputs.add(recipeT.getOutput());
//        ChangeItemStack changeItemStack = recipeT.getByProduction();
//        outputs.add(changeItemStack.getItemStack());
//        ingredients.setOutputs(VanillaTypes.ITEM, outputs);
//
//
//    }
//}
