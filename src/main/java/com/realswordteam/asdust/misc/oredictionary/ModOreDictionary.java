package com.realswordteam.asdust.misc.oredictionary;

import com.realswordteam.asdust.block.ore.OreBlockLoader;
import com.realswordteam.asdust.items.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDictionary {

    private void itemOreDictionary()
    {

        registerItemToOreDictionary(ItemLoader.ROD_STONE, "stickStone");
        registerItemToOreDictionary(ItemLoader.GEAR_WOODEN,"gearWood");
        registerItemToOreDictionary(ItemLoader.GEAR_STONE,"gearStone");
        registerItemToOreDictionary(ItemLoader.GEARS_WOODEN,"gearsWood");
        registerItemToOreDictionary(ItemLoader.GEARS_STONE,"gearsStone");
        registerItemToOreDictionary(ItemLoader.DUST_COAL,"dustCoal");

    }


    private void blockOreDictionary()
    {
        registerBlockToOreDictionary(OreBlockLoader.CASSITERITE,"oreTin");
        registerBlockToOreDictionary(OreBlockLoader.MALACHITE,"oreCopper");
        registerBlockToOreDictionary(OreBlockLoader.GALENA,"oreLead");
    }

    private void itemStackOreDictionary()
    {
    }

    public ModOreDictionary()
    {
        itemOreDictionary();
        blockOreDictionary();
        itemStackOreDictionary();
    }

    private void registerItemToOreDictionary(Item item, String oreDictionaryName)
    {
        OreDictionary.registerOre(oreDictionaryName, item);
    }

    private void registerBlockToOreDictionary(Block block, String oreDictionaryName)
    {
        OreDictionary.registerOre(oreDictionaryName, block);
    }

    //for special item
    private void registerItemStackToOreDictionary(ItemStack itemStack, String oreDictionaryName)
    {
        OreDictionary.registerOre(oreDictionaryName, itemStack);
    }
}
