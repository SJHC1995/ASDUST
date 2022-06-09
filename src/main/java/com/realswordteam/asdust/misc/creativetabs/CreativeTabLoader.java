package com.realswordteam.asdust.misc.creativetabs;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.items.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabLoader {
    /**
     * Add your CreativeTabs
     */
    public static CreativeTabs TAB_ASDUST_MAIN;
    public static CreativeTabs TAB_ASDUST_CE;
    public static CreativeTabs TAB_ASDUST_MACHINE;
    public static CreativeTabs TAB_WOODENGEAR;

    public CreativeTabLoader() {
        TAB_ASDUST_MAIN = new CreativeTabs(ASDUST.MODID + ".main") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(ItemLoader.admain);
            }
        };
        TAB_ASDUST_CE = new CreativeTabs(ASDUST.MODID + ".element") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(ItemLoader.adce);
            }
        };

        TAB_ASDUST_MACHINE = new CreativeTabs(ASDUST.MODID + ".machine") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(ItemLoader.admachine);
            }
        };
        TAB_WOODENGEAR = new CreativeTabs(ASDUST.MODID + ".gear_wood") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(ItemLoader.gearwood);
            }
        };
    }
}
