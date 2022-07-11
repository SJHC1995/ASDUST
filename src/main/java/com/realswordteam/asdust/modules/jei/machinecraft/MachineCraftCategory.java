package com.realswordteam.asdust.modules.jei.machinecraft;

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
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MachineCraftCategory implements IRecipeCategory<MachineCraftWrapper> {

    private final IDrawable background;
    private final String localizedName;
    private final IDrawable icon;

    public MachineCraftCategory(IGuiHelper guiHelper) {
        super();
        String RESOURCE_NAME = ASDUST.MODID + ":" + "textures/gui/jei/jei_machine_craft.png";
        ResourceLocation iconResource = new ResourceLocation(RESOURCE_NAME);
        background = guiHelper.createDrawable(iconResource, 0, 0, 151, 60);
        localizedName = I18n.format("jei.asdust.machine_craft.name");
        icon = guiHelper.createDrawableIngredient(new ItemStack(BlockLoader.MACHINE_CRAFT));
    }
    @Nonnull
    @Override
    public String getUid() {
        return JeiPlugin.MACHINE_CRAFT;
    }
    @Nonnull
    @Override
    public String getTitle() {
        return localizedName;
    }
    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }
    @Nullable
    @Override
    public IDrawable getIcon() {
        return icon;
    }
    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull MachineCraftWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
        recipeLayout.getItemStacks().init(0, true, 8, 10);
        recipeLayout.getItemStacks().init(1, true, 28, 10);
        recipeLayout.getItemStacks().init(2, true, 8, 32);
        recipeLayout.getItemStacks().init(3, true, 28, 32);
        recipeLayout.getItemStacks().init(4, false, 80, 10);
        recipeLayout.getItemStacks().init(5, false, 80, 32);
        recipeLayout.getItemStacks().init(6, false, 101, 21);
        recipeLayout.getFluidStacks().init(7, true, 126, 4, 16, 52, 2000, true, null);
        recipeLayout.getFluidStacks().set(ingredients);
        recipeLayout.getItemStacks().set(ingredients);
    }
    @Nonnull
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return new ArrayList<>();
    }
    @Nonnull
    @Override
    public String getModName() {
        return ASDUST.MODID;
    }

}
