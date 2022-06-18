package com.realswordteam.asdust.modules;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class Vanilla {
    /**
     * Use it in {@link com.realswordteam.asdust.recipes.RecipesLoader} and fill {@link #removeCraftTableRecipe(String)} name
     * @param name recipe name
     */
    public static void removeCraftTableRecipe(String name)
    {
        IForgeRegistryModifiable modifiable = (IForgeRegistryModifiable) ForgeRegistries.RECIPES;
        modifiable.remove(new ResourceLocation(name));
    }
//    public static void removeBlock(String name)
//    {
//        IForgeRegistryModifiable modifiable = (IForgeRegistryModifiable) ForgeRegistries.BLOCKS;
//        modifiable.remove(new ResourceLocation(name));
//    }
}
