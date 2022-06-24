package com.realswordteam.asdust.items.itembase;

import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.item.Item;
    public class ItemPartBase extends Item {
        public ItemPartBase(){
            super();
            this.setCreativeTab(CreativeTabLoader.TAB_PARTS);
        }
    }


