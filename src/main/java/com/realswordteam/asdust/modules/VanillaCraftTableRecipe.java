package com.realswordteam.asdust.modules;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class VanillaCraftTableRecipe {
    /**
     * Use it in {@link com.realswordteam.asdust.recipes.RecipesLoader} and fill {@link #removeCraftTableRecipe(String)} name
     * @param name recipe name
     */
    public static void removeCraftTableRecipe(String name)
    {
        IForgeRegistryModifiable modifiable = (IForgeRegistryModifiable) ForgeRegistries.RECIPES;
        modifiable.remove(new ResourceLocation(name));
    }
}
