package com.realswordteam.asdust.block;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.machine.bucket.BlockBucketWood;
import com.realswordteam.asdust.block.machine.kiln.BlockBaseKiln;
import com.realswordteam.asdust.block.machine.kiln.BlockCobbleStoneKiln;
import com.realswordteam.asdust.block.machine.tank.BlockCeramicTank;
import com.realswordteam.asdust.block.machine.BlockMachineCraft;
import com.realswordteam.asdust.block.machine.tank.BlockWoodenTank;
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
    public static Block WOODEN_BUCKET = null;
    public static Block MACHINE_CRAFT = null;
    public static Block TANK_WOOD_LEATHER = null;
    public static Block TANK_MIXED_MORTAR_CERAMICS = null;
    public static Block SIMPLE_KILN = null;
    public  static Block BLOCK_FURNACE_BRICK=null;
    public static List<Block> blocklist = new ArrayList<>();
    public static List<Item> itemlist = new ArrayList<>();
/**
*
* @param event register block list event
*/
@SubscribeEvent
    public static void registerBlock(RegistryEvent.Register<Block> event) {
        Block[] blocks = {
            setBlockName(MACHINE_CRAFT = new BlockMachineCraft(Material.IRON),"machinecraft"),
            setBlockName(TANK_WOOD_LEATHER = new BlockWoodenTank(),"tankwoodwithleather"),
            setBlockName(TANK_MIXED_MORTAR_CERAMICS = new BlockCeramicTank(), "tank_mixed_mortar_ceramics"),
            setBlockName(SIMPLE_KILN = new BlockCobbleStoneKiln(), "simple_kiln"),
            setBlockName(WOODEN_BUCKET=new BlockBucketWood(),"wooden_bucket"),
            setBlockName(BLOCK_FURNACE_BRICK=new Block(Material.ROCK),"block_furnace_brick")


        };
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