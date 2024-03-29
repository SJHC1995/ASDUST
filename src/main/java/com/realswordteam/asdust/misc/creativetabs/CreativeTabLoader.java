package com.realswordteam.asdust.misc.creativetabs;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.BlockLoader;
import com.realswordteam.asdust.block.ore.OreBlockLoader;
import com.realswordteam.asdust.items.ItemChemicalElementRegistry;
import com.realswordteam.asdust.items.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTabLoader {
    /**
     * Add your CreativeTabs
     */

    public static CreativeTabs TAB_ASDUST_CE;
    public static CreativeTabs TAB_ASDUST_MACHINE;
    public static CreativeTabs TAB_PARTS;
    public static CreativeTabs TAB_RAW_MATERIAL;

    public static CreativeTabs TAB_MISC;
    public static CreativeTabs TAB_BASE_BLOCK;
    public static CreativeTabs TAB_ORE;
    public static CreativeTabs TAB_METAL;
    public CreativeTabLoader() {

        TAB_ASDUST_CE = new CreativeTabs(ASDUST.MODID + ".element") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(ItemChemicalElementRegistry.ELEMENT_001_H);
            }

        };

        TAB_ASDUST_MACHINE = new CreativeTabs(ASDUST.MODID + ".machine") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(BlockLoader.MACHINE_CRAFT);
            }
        };
        TAB_PARTS = new CreativeTabs(ASDUST.MODID + ".gear_wood") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(ItemLoader.GEAR_WOODEN);
            }
        };
        TAB_RAW_MATERIAL = new CreativeTabs(ASDUST.MODID + ".mixedmortarball") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(ItemLoader.MIXED_MORTAR_BALL);
            }
        };
        TAB_MISC = new CreativeTabs(ASDUST.MODID + ".imperfection") {
            @Override
            public ItemStack getTabIconItem() {
                return new ItemStack(ItemLoader.IMPERFECTION);
            }
        };
        TAB_BASE_BLOCK = new CreativeTabs(ASDUST.MODID + ".block_furnace_brick") {
            @Override
            public ItemStack getTabIconItem() {return new ItemStack(BlockLoader.BLOCK_FURNACE_BRICK);}
        };
        TAB_ORE = new CreativeTabs(ASDUST.MODID + ".ore") {
            @Override
            public ItemStack getTabIconItem() {return new ItemStack(OreBlockLoader.CASSITERITE);}
        } ;
        TAB_METAL = new CreativeTabs(ASDUST.MODID + ".metal") {
            @Override
            public ItemStack getTabIconItem() {return new ItemStack(Items.IRON_INGOT);

            }
        };
    }
}

