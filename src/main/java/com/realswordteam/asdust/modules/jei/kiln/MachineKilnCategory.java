package com.realswordteam.asdust.modules.jei.kiln;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.BlockLoader;
import com.realswordteam.asdust.modules.jei.JeiPlugin;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class MachineKilnCategory implements IRecipeCategory<MachineKilnWrapper> {

    private final IDrawable background;

    private final String localizedName;

    private final IDrawable icon;

    public MachineKilnCategory(IGuiHelper guiHelper) {
        super();
        String RESOURCE_NAME = ASDUST.MODID + ":" + "textures/gui/jei/jei_machine_kiln.png";
        ResourceLocation iconResource = new ResourceLocation(RESOURCE_NAME);
        background = guiHelper.createDrawable(iconResource, 0, 0, 151, 60);
        localizedName = I18n.format("jei.asdust.machine_kiln.name");
        icon = guiHelper.createDrawableIngredient(new ItemStack(BlockLoader.SIMPLE_KILN));
    }

    @Nonnull
    @Override
    public String getUid() {
        return JeiPlugin.MACHINE_KILN;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return localizedName;
    }

    @Nonnull
    @Override
    public String getModName() {
        return ASDUST.MODID;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nonnull
    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull MachineKilnWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 36, 10);
        recipeLayout.getItemStacks().init(1, true, 36, 33);
        recipeLayout.getItemStacks().init(2, false, 89, 22);
        recipeLayout.getItemStacks().set(ingredients);
    }
}
