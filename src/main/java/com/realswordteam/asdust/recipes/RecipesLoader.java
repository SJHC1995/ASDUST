package com.realswordteam.asdust.recipes;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.items.ItemLoader;
import com.realswordteam.asdust.modules.CeramicsMod;
import com.realswordteam.asdust.modules.Vanilla;
import com.realswordteam.asdust.recipes.machine.RecipeCraft;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipesLoader {
    public RecipesLoader()
    {
        //ASDUST
        addSimpleShapedRecipe("asdust:dirt",
                new ItemStack(Blocks.DIRT),
                "ABA", "BCB", "ABA", 'A', ItemLoader.IMPERFECTION, 'B', ItemLoader.LOESS, 'C', Items.CLAY_BALL);
        addSimpleShapedRecipe("asdust:crafting_table",
        new ItemStack(Blocks.CRAFTING_TABLE),
        "AAA","ABA","AAA",'A',Items.AIR,'B',Blocks.BEDROCK
    );
        addSimpleShapedRecipe("asdust:unfired_clay_bowl",
                new ItemStack(ItemLoader.UNFIRED_CLAY_BOWL),
                "   ", "A A", " A ", 'A', Items.CLAY_BALL);

        //Vanilla
        for (String string : vanillaRecipe)
        {
            Vanilla.removeCraftTableRecipe(string);
        }

        //Ceramics
        if (Loader.isModLoaded("ceramics")) {

            Vanilla.removeCraftTableRecipe("ceramics:tools/unfired_clay_bucket");

            addSimpleShapedRecipe("asdust:unfired_clay_recipe",
                    new ItemStack(CeramicsMod.Unfired_Clay_Bucket, 2),
                    "A A", "A A", "AAA", 'A', Item.getItemFromBlock(Blocks.CLAY));
        }

        //ASDUST - CraftRecipes
        RecipeCraft.addRecipe(new ItemStack(Items.IRON_INGOT),
                new RecipeCraft.RecipeT(
                        new ItemStack(Item.getItemFromBlock(Blocks.DIRT)),
                        new ItemStack(Items.APPLE, 2),
                        new FluidStack(FluidRegistry.WATER, 100),
                        new ChangeItemStack(new ItemStack(ItemLoader.IMPERFECTION), 75))
        );
        RecipeCraft.addRecipe(new ItemStack(Items.CLAY_BALL, 4),
                new RecipeCraft.RecipeT(
                        ItemStack.EMPTY,
                        new ItemStack(Item.getItemFromBlock(Blocks.CLAY)),
                        null,
                        ChangeItemStack.Empty
                ));
        RecipeCraft.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.DIRT), 2),
                new RecipeCraft.RecipeT(
                        new ItemStack(Item.getItemFromBlock(Blocks.SAND), 8),
                        new ItemStack(Item.getItemFromBlock(Blocks.CLAY), 4),
                        new FluidStack(FluidRegistry.WATER, 500),
                        new ChangeItemStack(new ItemStack(ItemLoader.LOESS, 8), 25)
                ));
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
                    "minecraft:furnace",
                    "minecraft:crafting_table"

            };
}
