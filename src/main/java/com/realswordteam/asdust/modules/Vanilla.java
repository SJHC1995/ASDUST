package com.realswordteam.asdust.modules;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class Vanilla {
    /**
     * Use it in {@link com.realswordteam.asdust.recipes.RecipesLoader} and fill name
     * @param name recipe name
     */
    public static void removeCraftTableRecipe(String name)
    {
        ResourceLocation location = new ResourceLocation(name);
        
        IForgeRegistryModifiable<IRecipe> modifiable = (IForgeRegistryModifiable<IRecipe>) ForgeRegistries.RECIPES;
        IRecipe recipe = modifiable.getValue(location);
        if (recipe != null)
        {
            modifiable.remove(new ResourceLocation(name));
            modifiable.register(DummyRecipe.from(recipe));
        }   else
        {
            FMLLog.bigWarning("Don`t find recipe for " + location + ", report to author");
        }
        
    }

    //remove information about causing some bug after removed recipe
    public static class DummyRecipe extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
        private final ItemStack output;

        public DummyRecipe(ItemStack output) {
            this.output = output;
        }

        public static IRecipe from(IRecipe other) {
            return new DummyRecipe(other.getRecipeOutput()).setRegistryName(other.getRegistryName());
        }

        @Override
        public boolean matches(InventoryCrafting inv, World worldIn) {
            return false;
        }

        @Override
        public ItemStack getCraftingResult(InventoryCrafting inv) {
            return ItemStack.EMPTY;
        }

        @Override
        public boolean canFit(int width, int height) {
            return false;
        }

        @Override
        public ItemStack getRecipeOutput() {
            return output;
        }
    }
}
