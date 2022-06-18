package com.realswordteam.asdust.items;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.BlockLoader;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = ASDUST.MODID)
public final class TextureLoader {
    @SubscribeEvent
    public static void onModelReg(ModelRegistryEvent event) {
        //Item
        for (Item item : ItemLoader.getItems())
        {
            registerSimpleItemModel(item);
        }
        //Block
        for (Block block : BlockLoader.blocklist)
        {
            registerSimpleBlockModel(block);
        }
    }

    private static void registerSimpleBlockModel(Block block) {
        if (block.getRegistryName() != null)
        {
            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
                    new ModelResourceLocation(block.getRegistryName(), "inventory"));
        }   else
        {
            throw new RuntimeException("Model register failed!: " + block.getUnlocalizedName());
        }

    }
    private static void registerSimpleItemModel(Item item)
    {
        if (item.getRegistryName() != null)
        {
            ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
        }   else
        {
            throw new RuntimeException("Model register failed!: " + item.getUnlocalizedName());
        }
    }
}

