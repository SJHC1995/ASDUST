package com.realswordteam.asdust.recipes;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.modules.CeramicsMod;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesLoader {
    public RecipesLoader()
    {
        MachineTestRecipe.recipe.addMachineTestRecipe(
                new Item[]{Items.APPLE, Items.IRON_INGOT},
                new ItemStack[]{new ItemStack(Items.GOLD_INGOT, 4), new ItemStack(Items.DIAMOND, 1)}
        );
        if (Loader.isModLoaded("ceramics"))
        {
            GameRegistry.addShapedRecipe(new ResourceLocation("unfired_clay"), new ResourceLocation(ASDUST.NAME),
                    new ItemStack(CeramicsMod.Unfired_Clay_Bucket(), 2),
                    "A A", "A A", "AAA", 'A', Item.getItemFromBlock(Blocks.CLAY));
        }
    }
}
