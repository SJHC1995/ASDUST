package com.realswordteam.asdust.recipes;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.items.ItemLoader;
import com.realswordteam.asdust.modules.CeramicsMod;
import com.realswordteam.asdust.modules.Vanilla;
import com.realswordteam.asdust.recipes.input.InputFluidStack;
import com.realswordteam.asdust.recipes.input.InputItemStack;
import com.realswordteam.asdust.recipes.machine.RecipeCraft;
import com.realswordteam.asdust.recipes.output.OutputItemStack;
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
        addRecipeMachineCraft("dirt",
                new RecipeCraft.RecipeT(
                        new InputItemStack(new ItemStack(Item.getItemFromBlock(Blocks.DIRT))),
                        new InputFluidStack(),
                        new OutputItemStack(new ItemStack(Blocks.DIRT))
                ));

        addRecipeMachineCraft("loess",
                new RecipeCraft.RecipeT(
                        new InputItemStack(new ItemStack(Blocks.DIRT,3),  new ItemStack(Items.CLAY_BALL)),
                        new InputFluidStack(new FluidStack(FluidRegistry.WATER, 500)),
                        new OutputItemStack(new ItemStack(ItemLoader.LOESS,4),new ItemStack(ItemLoader.IMPERFECTION,4))
                ));
    }

    private void addSimpleShapedRecipe(String recipeName, ItemStack output, Object... params)
    {
        GameRegistry.addShapedRecipe(new ResourceLocation(recipeName), new ResourceLocation(ASDUST.NAME), output, params);
    }
    private void addRecipeMachineCraft(String name, RecipeCraft.RecipeT recipeT)
    {
        RecipeCraft.addRecipe(ASDUST.MODID + "." + "machine" + "." + "craft" + name, recipeT);
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
