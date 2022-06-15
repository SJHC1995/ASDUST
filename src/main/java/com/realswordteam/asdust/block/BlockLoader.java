package com.realswordteam.asdust.block;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.machine.BlockMachineBase;
import com.realswordteam.asdust.block.machine.BlockTank;
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

@Mod.EventBusSubscriber(modid= ASDUST.MODID)
public class BlockLoader{
    public static Block blockconvert = null;
    public static Block machinecraft = null;
    public static Block  tankwoodwithleather = null;
    public static Block IRON_TANK = null;

    public static List<Block> blocklist = new ArrayList<>();
    public static List<Item> itemlist = new ArrayList<>();
/**
*
* @param event register block list event
*/
@SubscribeEvent
public static void registerBlock(RegistryEvent.Register<Block> event) {
    Block[] blocks = {

            setBlockName(blockconvert = new Block(Material.IRON), "blockconvert"),
            setBlockName(machinecraft = new BlockMachineBase(Material.IRON),"machinecraft"),
            setBlockName(tankwoodwithleather = new BlockTank(Material.WOOD, 2000),"tankwoodwithleather"),

            //create a new tank example: To create a new tank, create a new class and inherit the BlockTank class
            setBlockName(IRON_TANK = new BlockTank(Material.IRON, 16000), "iron_tank")

    };
    blocklist.addAll(Arrays.asList(blocks));

    event.getRegistry().registerAll(blocks);

    for (Block block : blocklist) {
        itemlist.add(setItemName(block));
    }
}

    @SubscribeEvent
    public static void regsiterItemFromBlock(RegistryEvent.Register<Item> event)
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