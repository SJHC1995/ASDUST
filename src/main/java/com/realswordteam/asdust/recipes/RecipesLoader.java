package com.realswordteam.asdust.recipes;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.modules.CeramicsMod;
import com.realswordteam.asdust.modules.VanillaCraftTableRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;

public class RecipesLoader {
    public RecipesLoader()
    {
        MachineTestRecipe.recipe.addMachineTestRecipe(
                new Item[]{Items.APPLE, Items.IRON_INGOT},
                new ItemStack[]{new ItemStack(Items.GOLD_INGOT, 4), new ItemStack(Items.DIAMOND, 1)}
        );

        //Vanilla
        for (String string : vanillaRecipe)
        {
            VanillaCraftTableRecipe.removeCraftTableRecipe(string);
        }

        //Ceramics
        if (Loader.isModLoaded("ceramics")) {

            VanillaCraftTableRecipe.removeCraftTableRecipe("ceramics:tools/unfired_clay_bucket");

            addSimpleShapedRecipe("asdust:unfired_clay_recipe",
                    new ItemStack(CeramicsMod.Unfired_Clay_Bucket(), 2),
                    "A A", "A A", "AAA", 'A', Item.getItemFromBlock(Blocks.CLAY));
        }
    }

    private void addSimpleShapedRecipe(String recipeName, ItemStack output, Object... params)
    {
        GameRegistry.addShapedRecipe(new ResourceLocation(recipeName), new ResourceLocation(ASDUST.NAME), output, params);
    }

    /**
     * Add the specified vanilla recipe name
     */
    public final String[] vanillaRecipe =
            {
                    "minecraft:stonebrick",
                    "minecraft:furnace"
            };
}
