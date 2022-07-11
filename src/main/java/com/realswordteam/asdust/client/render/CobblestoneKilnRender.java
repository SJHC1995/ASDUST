package com.realswordteam.asdust.client.render;

import com.realswordteam.asdust.block.machine.kiln.TileEntityKiln;
import com.realswordteam.asdust.util.ConvertUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public class CobblestoneKilnRender extends TileEntitySpecialRenderer<TileEntityKiln> {
    public ResourceLocation TEXTURE = new ResourceLocation("textures/blocks/coal_block.png");
    public Minecraft mc = Minecraft.getMinecraft();

    @Override
    public void render(TileEntityKiln te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        mc.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        fastRenderSlag(x + ConvertUtil.convertPxToDouble(4),y,z + ConvertUtil.convertPxToDouble(4), 22.5F, 1);
        fastRenderSlag(x + ConvertUtil.convertPxToDouble(12),y,z + ConvertUtil.convertPxToDouble(3), 0, 1);
        fastRenderSlag(x + ConvertUtil.convertPxToDouble(9),y,z + ConvertUtil.convertPxToDouble(7), 45F, 1);


        fastRenderSlag(x + ConvertUtil.convertPxToDouble(9),y,z + ConvertUtil.convertPxToDouble(3), 0, 0.5);
        fastRenderSlag(x + ConvertUtil.convertPxToDouble(3),y,z + ConvertUtil.convertPxToDouble(8), 0, 0.5);
        fastRenderSlag(x + ConvertUtil.convertPxToDouble(6),y,z + ConvertUtil.convertPxToDouble(9), 45F, 0.5);
        fastRenderSlag(x + ConvertUtil.convertPxToDouble(12),y,z + ConvertUtil.convertPxToDouble(9), 22.5F, 0.5);

        fastRenderSlag(x + ConvertUtil.convertPxToDouble(12),y + ConvertUtil.convertPxToDouble(4.5F),z + ConvertUtil.convertPxToDouble(3), 0, 0.5);
        fastRenderSlag(x + ConvertUtil.convertPxToDouble(5),y + ConvertUtil.convertPxToDouble(4.5F),z + ConvertUtil.convertPxToDouble(3), 22.5F, 0.5);
        fastRenderSlag(x + ConvertUtil.convertPxToDouble(9),y + ConvertUtil.convertPxToDouble(4.5F),z + ConvertUtil.convertPxToDouble(7), 67.5F, 0.5);
    }

    private void fastRenderSlag(double x, double y, double z, float angle, double scale)
    {
        GlStateManager.pushMatrix();
        GlStateManager.color(1,1,1,1);

        GlStateManager.translate(x, y, z);
        GlStateManager.scale(scale, scale, scale);
        GlStateManager.rotate(angle,0,1,0);
        mc.getRenderItem().renderItem(new ItemStack(Blocks.CLAY), ItemCameraTransforms.TransformType.GROUND);

        GlStateManager.popMatrix();
    }

    private void renderKiln(double x, double y, double z, double partialTicks, double textureScale, List<TileEntityBeacon.BeamSegment> beamSegments, double totalWorldTime)
    {
        this.bindTexture(TEXTURE);
    }
}
