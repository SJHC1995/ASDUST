package com.realswordteam.asdust.block.ore;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.blockbase.BlockOreBase;
import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Mod.EventBusSubscriber(modid = ASDUST.MODID)
public class OreBlockLoader {
    public static Block CASSITERITE = null;
    public static List<Block> blocklist = new ArrayList<>();
    public static List<Item> itemlist = new ArrayList<>();



    @SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        Block[] blocks = {};
        setBlockName(CASSITERITE = new BlockOreBase (),"cassiterite");












        blocklist.addAll(Arrays.asList(blocks));

        event.getRegistry().registerAll(blocks);

        for (Block block : blocklist) {
            itemlist.add(setItemName(block));
        }
    }


    @SubscribeEvent
    public static void registerItemFromBlock(RegistryEvent.Register<Item> event)
    {
        for(Item item :itemlist){
            event.getRegistry().register(item);
        }
    }


    public static Block setBlockName(Block block, String name){
        block.setRegistryName(ASDUST.MODID, name).setUnlocalizedName(ASDUST.MODID + "." + name);
        return block;
    }


    public static Item setItemName(Block block){
        return new ItemBlock(block).setRegistryName(ASDUST.MODID, block.getUnlocalizedName()).setUnlocalizedName(block.getUnlocalizedName());
    }

}

