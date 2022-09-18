package com.realswordteam.asdust.items.itembase;

import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.item.Item;


public class ItemMetalBase extends Item {
    public ItemMetalBase (){
        super();
        this.setCreativeTab(CreativeTabLoader.TAB_METAL);
    }
}
