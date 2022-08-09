package com.realswordteam.asdust.items;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.items.itembase.*;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = ASDUST.MODID)
    public class ItemLoader
    {
        public static Item AD_MAIN = null;
        public static Item AD_CE = null;
        public static Item AD_MACHINE = null;
        public static Item GEAR_WOOD = null;
        public static Item GEAR_WOOD_GROUP = null;
        public static Item MIXED_MORTAR_BALL =null;
        public static Item IMPERFECTION = null;
        public static Item LOESS = null;
        public static Item UNFIRED_CLAY_BOWL = null;
        public static Item CERAMIC_BOWl = null;
        public static Item HEYC=null;
        public static Item SIMPLE_CALCIUM_OXIDE=null;
        public static Item SIMPLE_CALCIUM_HYDROXIDE=null;
        public static Item SIMPLE_BRICK_EMBRYO=null;
        public static Item CONDENSED_BRICK= null;
        public static Item FURNACE_BRICK=null;

        static Item[] items = {
                setItemName(AD_MAIN = new ItemMainBase(), "admain"),
                setItemName(AD_CE = new ItemMainBase(), "adce"),
                setItemName(AD_MACHINE = new ItemMainBase(), "admachine"),
                setItemName(GEAR_WOOD = new ItemPartBase(), "gearwood"),
                setItemName(GEAR_WOOD_GROUP = new ItemPartBase(), "gearwoodgroup"),
                setItemName(MIXED_MORTAR_BALL = new ItemRawMaterialBase(), "mixedmortarball"),
                setItemName(IMPERFECTION = new ItemMiscBase(), "imperfection"),
                setItemName(LOESS = new ItemRawMaterialBase(), "loess"),
                setItemName(UNFIRED_CLAY_BOWL = new ItemMiscBase(), "unfired_clay_bowl"),
                setItemName(CERAMIC_BOWl = new ItemFluidContainer(250), "ceramic_bowl"),
                setItemName(HEYC=new ItemRawMaterialBase(),"heyc"),
                setItemName(SIMPLE_CALCIUM_OXIDE=new ItemRawMaterialBase(),"simple_calcium_oxide"),
                setItemName(SIMPLE_CALCIUM_HYDROXIDE=new ItemRawMaterialBase(),"simple_calcium_hydroxide"),
                setItemName(SIMPLE_BRICK_EMBRYO=new ItemRawMaterialBase(),"simple_brick_embryo"),
                setItemName(CONDENSED_BRICK=new ItemRawMaterialBase(),"condensed_brick"),
                setItemName(FURNACE_BRICK=new ItemRawMaterialBase(),"furnace_brick")
        };


        @SubscribeEvent
        public static void registerItem(RegistryEvent.Register<Item> event)
        {
            event.getRegistry().registerAll(items);
            event.getRegistry().registerAll(ItemChemicalElementRegistry.chemicalElements);
        }

        public static Item[] getItems()
        {
            return items;
        }

        private static Item setItemName(Item item, String name)
        {
            return item.setRegistryName(ASDUST.MODID,name).setUnlocalizedName(ASDUST.MODID + "." + name);
        }

    }

