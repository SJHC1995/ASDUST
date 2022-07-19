package com.realswordteam.asdust.gui.guicontainer;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.gui.container.ContainerKiln;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiContainerKiln extends GuiContainer {

    private static final String TEXTURE_PATH = ASDUST.MODID + ":" + "textures/gui/container/simple_kiln.png";

    private static final ResourceLocation TEXTURE = new ResourceLocation(TEXTURE_PATH);

    public ContainerKiln inventory;

    public GuiContainerKiln(ContainerKiln inventory) {
        super(inventory);

        this.xSize = 176;
        this.ySize = 166;
        this.inventory = inventory;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        this.fontRenderer.drawString(this.inventory.getBlockName(), 7, 5, 0xFF000000);
        this.fontRenderer.drawString(this.inventory.getPlayerInventoryName(),  7, 73, 0xFF000000);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(TEXTURE);

        int offsetX = (this.width - this.xSize) / 2, offsetY = (this.height - this.ySize) / 2;

        this.drawTexturedModalRect(offsetX, offsetY, 0, 0, this.xSize, this.ySize);

        int textureBurnState = 1 + (int) Math.ceil(13.0 * this.inventory.kilnBurnTime/ this.inventory.totalKilnBurnTime);
        this.drawTexturedModalRect(offsetX + 57, offsetY + 36 + 13 - textureBurnState, 176, 13 - textureBurnState, 14, textureBurnState);

        int textureCookProcess = 1 + (int) Math.ceil(24.0 * this.inventory.kilnCookTime/ 200);
        this.drawTexturedModalRect(offsetX + 89, offsetY + 34, 176, 13, textureCookProcess, 17);

        if (this.inventory.slagNumber == 10)
        {
            this.drawTexturedModalRect(offsetX + 57, offsetY + 36, 190, 0, 14, 13);
        }
    }
}
