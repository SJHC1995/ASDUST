package com.realswordteam.asdust.modules;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class VanillaCraftTableRecipe {
    public static void removeCraftTableRecipe(String name)
    {
        IForgeRegistryModifiable modifiable = (IForgeRegistryModifiable) ForgeRegistries.RECIPES;
        modifiable.remove(new ResourceLocation(name));
    }
}
