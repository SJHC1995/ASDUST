package com.realswordteam.asdust.items;

import com.realswordteam.asdust.block.BlockLoader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = "asdust")
public final class TextureLoader {
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {

        ModelLoader.setCustomModelResourceLocation(ItemLoader.admain, 0, new ModelResourceLocation(ItemLoader.admain.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.adce, 0, new ModelResourceLocation(ItemLoader.adce.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.admachine, 0, new ModelResourceLocation(ItemLoader.admachine.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yh, 0, new ModelResourceLocation(ItemLoader.yh.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yhe, 0, new ModelResourceLocation(ItemLoader.yhe.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yli, 0, new ModelResourceLocation(ItemLoader.yli.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.ybe, 0, new ModelResourceLocation(ItemLoader.ybe.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yb, 0, new ModelResourceLocation(ItemLoader.yb.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yc, 0, new ModelResourceLocation(ItemLoader.yc.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yn, 0, new ModelResourceLocation(ItemLoader.yn.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yo, 0, new ModelResourceLocation(ItemLoader.yo.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yf, 0, new ModelResourceLocation(ItemLoader.yf.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yne, 0, new ModelResourceLocation(ItemLoader.yne.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yna, 0, new ModelResourceLocation(ItemLoader.yna.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.ymg, 0, new ModelResourceLocation(ItemLoader.ymg.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yal, 0, new ModelResourceLocation(ItemLoader.yal.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.ysi, 0, new ModelResourceLocation(ItemLoader.ysi.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yp, 0, new ModelResourceLocation(ItemLoader.yp.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.ys, 0, new ModelResourceLocation(ItemLoader.ys.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.ycl, 0, new ModelResourceLocation(ItemLoader.ycl.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yar, 0, new ModelResourceLocation(ItemLoader.yar.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.yfe, 0, new ModelResourceLocation(ItemLoader.yfe.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.gearwood, 0, new ModelResourceLocation(ItemLoader.gearwood.getRegistryName(), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ItemLoader.gearwoodgroup, 0, new ModelResourceLocation(ItemLoader.gearwoodgroup.getRegistryName(), "inventory"));
        registerBlockModel(BlockLoader.blockconvert, 0);
        registerBlockModel(BlockLoader.machinecraft,0);
        registerBlockModel(BlockLoader.tankwoodwithleather,0);
    }

    public static void registerBlockModel(Block block, int meta) {
        net.minecraftforge.client.model.ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
                new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}

