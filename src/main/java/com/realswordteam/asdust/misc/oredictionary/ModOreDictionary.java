package com.realswordteam.asdust.misc.oredictionary;

import com.realswordteam.asdust.items.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ModOreDictionary {

    private void itemOreDictionary()
    {
        registerItemToOreDictionary(ItemLoader.STONE_ROD, "stickStone");
    }

    private void blockOreDictionary()
    {
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
