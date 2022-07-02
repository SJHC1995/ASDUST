package com.realswordteam.asdust.modules.jei;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.BlockLoader;
import com.realswordteam.asdust.gui.guicontainer.GuiContainerTest;
import com.realswordteam.asdust.modules.jei.machinecraft.MachineCraftCategory;
import com.realswordteam.asdust.modules.jei.machinecraft.MachineCraftMaker;
import mezz.jei.api.*;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JeiPlugin implements IModPlugin {

    public static String MACHINE_CRAFT = ASDUST.MODID + "." + "machine_craft";

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();
        IGuiHelper guiHelper = jeiHelpers.getGuiHelper();

        registry.addRecipeCategories(
                new MachineCraftCategory(guiHelper)
        );

    }

    @Override
    public void register(IModRegistry registry)
    {
        IJeiHelpers jeiHelpers = registry.getJeiHelpers();

        registry.addRecipes(MachineCraftMaker.getRecipe(jeiHelpers), MACHINE_CRAFT);

        registry.addRecipeClickArea(GuiContainerTest.class, 54, 39, 14, 14, MACHINE_CRAFT);

        registry.addRecipeCatalyst(new ItemStack(BlockLoader.MACHINE_CRAFT), MACHINE_CRAFT);

    }
}
