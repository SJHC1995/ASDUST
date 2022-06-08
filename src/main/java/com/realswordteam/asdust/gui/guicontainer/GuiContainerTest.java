package com.realswordteam.asdust.gui.guicontainer;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.gui.GuiDrawFluid;
import com.realswordteam.asdust.gui.container.ContainerTest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class GuiContainerTest extends GuiContainer {
    private static final String TEXTURE_PATH = ASDUST.MODID + ":" + "textures/gui/container/furnace.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    protected ContainerTest inventory;
    private int totalBurnTime1;
    private FluidTank tank;

    public GuiContainerTest(ContainerTest inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 171;
        this.ySize = 166;
        this.inventory = inventorySlotsIn;
        this.totalBurnTime1 = inventorySlotsIn.getTotalBurnTime();
        this.tank = inventorySlotsIn.getFluidTank();
        int i = 1;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        GuiDrawFluid.guiDrawFluid.renderHoverTank(this, this.width, this.height, this.xSize, this.ySize, mouseX, mouseY, 36, 31, 52, 63, new FluidStack(FluidRegistry.WATER, this.inventory.fluidAmount));
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);

        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        int burnTime = this.inventory.getBurnTime();
        int textureWidth = 1 + (int) Math.ceil(51.0 * burnTime / this.totalBurnTime1);
        this.drawTexturedModalRect(offsetX + 54, offsetY + 36, 174, 0, textureWidth, 17);

        GuiDrawFluid.guiDrawFluid.drawFluid(mc, offsetX + 36, offsetY + 31, 2000, new FluidStack(FluidRegistry.WATER, this.inventory.fluidAmount), 32, 16);
//        this.drawFluid(mc, this.width - this.xSize, offsetY + 31, new FluidStack(FluidRegistry.WATER, 1000));
    }

}