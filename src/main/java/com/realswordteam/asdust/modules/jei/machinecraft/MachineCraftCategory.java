//package com.realswordteam.asdust.modules.jei.machinecraft;
//
//import com.realswordteam.asdust.ASDUST;
//import com.realswordteam.asdust.modules.jei.JeiPlugin;
//import mezz.jei.api.IGuiHelper;
//import mezz.jei.api.gui.IDrawable;
//import mezz.jei.api.gui.IRecipeLayout;
//import mezz.jei.api.ingredients.IIngredients;
//import mezz.jei.api.recipe.IRecipeCategory;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.resources.I18n;
//import net.minecraft.util.ResourceLocation;
//
//import javax.annotation.Nonnull;
//import javax.annotation.Nullable;
//import java.util.ArrayList;
//import java.util.List;
//
//public class MachineCraftCategory implements IRecipeCategory<MachineCraftWrapper> {
//
//    private final IDrawable background;
//    private final String localizedName;
//    private final IDrawable icon;
//    private final String RESOURCE_NAME = ASDUST.MODID + ":" + "textures/gui/jei/jei_machine_craft.png";
//
//    public MachineCraftCategory(IGuiHelper guiHelper) {
//        super();
//        ResourceLocation iconResource = new ResourceLocation(RESOURCE_NAME);
////        background = guiHelper.createBlankDrawable(151, 60);
//        background = guiHelper.createDrawable(iconResource, 0, 0, 151, 60);
//
//        localizedName = I18n.format("jei.asdust.machine_craft.name");
//        icon = guiHelper.createDrawable(iconResource, 0, 0, 16, 16);
//
//    }
//    @Nonnull
//    @Override
//    public String getUid() {
//        return JeiPlugin.MACHINE_CRAFT;
//    }
//    @Nonnull
//    @Override
//    public String getTitle() {
//        return localizedName;
//    }
//    @Nonnull
//    @Override
//    public IDrawable getBackground() {
//        return background;
//    }
//    @Nullable
//    @Override
//    public IDrawable getIcon() {
//        return icon;
//    }
//    @Override
//    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull MachineCraftWrapper recipeWrapper, @Nonnull IIngredients ingredients) {
//        int inputSlot = 0;
//        int outputSlot = 1;
//        recipeLayout.getItemStacks().init(0, true, 8, 10);
//        recipeLayout.getItemStacks().init(1, true, 28, 10);
//        recipeLayout.getItemStacks().init(2, false, 76, 21);
//        recipeLayout.getItemStacks().init(3, false, 100, 21);
//        recipeLayout.getFluidStacks().init(5, true, 126, 4, 16, 52, 2000, true, null);
//        recipeLayout.getFluidStacks().set(ingredients);
//        recipeLayout.getItemStacks().set(ingredients);
//    }
//    @Nonnull
//    @Override
//    public List<String> getTooltipStrings(int mouseX, int mouseY) {
//        return new ArrayList<>();
//    }
//    @Nonnull
//    @Override
//    public String getModName() {
//        return ASDUST.MODID;
//    }
//
////    @Override
////    public void drawExtras(Minecraft minecraft) {
////        ResourceLocation iconResource = new ResourceLocation(RESOURCE_NAME);
////        minecraft.getTextureManager().bindTexture(iconResource);
////        minecraft.ingameGUI.drawTexturedModalRect(0, 0, 0, 0, 151, 60);
////    }
//}
