package com.realswordteam.asdust.gui.guicontainer;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.gui.GuiDrawFluid;
import com.realswordteam.asdust.gui.container.ContainerTank;
import com.realswordteam.asdust.gui.container.ContainerTest;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

import java.util.Map;

public class GuiContainerTank extends GuiContainer {
    private static final String TEXTURE_PATH = ASDUST.MODID + ":" + "textures/gui/container/tank.png";
    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);
    protected ContainerTank inventory;
    protected FluidTank tank;
    protected String tankName;
    protected int tankCapacity;
    public GuiContainerTank(ContainerTank inventorySlotsIn) {
        super(inventorySlotsIn);
        this.xSize = 176;
        this.ySize = 166;
        this.inventory = inventorySlotsIn;
        this.tank = inventorySlotsIn.getFluidTank();
        this.tankName = inventorySlotsIn.blockName;
        this.tankCapacity = inventorySlotsIn.tankCapacity;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
        GuiDrawFluid.guiDrawFluid.renderHoverTank(this, this.width, this.height, this.xSize, this.ySize, mouseX, mouseY, 79, 16, 97, 70, createFluidStack(this.getFluid(this.inventory.fluidId), this.inventory.fluidAmount), this.tankCapacity, true);
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.tankName, 7, 5, 0xFF000000);
        this.fontRenderer.drawString(this.inventory.playerInventoryName,  7, 73, 0xFF000000);
    }

    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);

        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        int textureHeight = 1 + (int) Math.ceil(12.0 * this.inventory.processTime / 20);
        this.drawTexturedModalRect(offsetX + 57, offsetY + 36, 176, 0, 14, textureHeight);

        GuiDrawFluid.guiDrawFluid.drawFluid(mc, offsetX + 80, offsetY + 17, checkTankCapacity(this.inventory.tankCapacity), createFluidStack(this.getFluid(this.inventory.fluidId), this.inventory.fluidAmount), 52, 16);

//        this.drawFluid(mc, this.width - this.xSize, offsetY + 31, new FluidStack(FluidRegistry.WATER, 1000));
    }

    private int checkTankCapacity(int tankCapacity)
    {
        if (tankCapacity <= 0)
        {
            return 1000;
        }   else
        {
            return tankCapacity;
        }
    }

    private Fluid getFluid(int id)
    {
        Map<Fluid, Integer> ids = FluidRegistry.getRegisteredFluidIDs();
        for (Map.Entry<Fluid, Integer> entry : ids.entrySet())
        {
            if (id == entry.getValue())
            {
                return entry.getKey();
            }
        }
        return null;
    }
    private FluidStack createFluidStack(Fluid fluid, int fluidAmount)
    {
        if (fluid == null)
        {
            return null;
        }
        return new FluidStack(fluid, fluidAmount);
    }
}
