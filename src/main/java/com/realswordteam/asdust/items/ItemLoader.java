package com.realswordteam.asdust.items;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.blockbase.BlockOreBase;
import com.realswordteam.asdust.items.itembase.*;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = ASDUST.MODID)
    public class ItemLoader
    {

        public static Item GEAR_WOODEN = null;
        public static Item GEARS_WOODEN = null;
        public static Item GEAR_STONE = null;
        public static Item GEARS_STONE =null;
        public static Item ROD_STONE = null;
        public static Item MIXED_MORTAR_BALL = null;
        public static Item IMPERFECTION = null;
        public static Item LOESS = null;
        public static Item UNFIRED_CLAY_BOWL = null;
        public static Item CERAMIC_BOWl = null;
        public static Item HEYC = null;
        public static Item SIMPLE_CALCIUM_OXIDE = null;
        public static Item SIMPLE_CALCIUM_HYDROXIDE = null;
        public static Item SIMPLE_BRICK_EMBRYO = null;
        public static Item CONDENSED_BRICK = null;
        public static Item FURNACE_BRICK = null;
        public static Item PLANT_ASH = null;
        public static Item ANIMAL_FAT = null;
        public static Item BRIQUETTE = null;
        public static Item CRUDE_SALT = null;
        public static Item DUST_COAL = null;
        public static Item ORE_CASSITERITE = null;
        public static Item POWDER_CASSITERITE = null;
        public static Item ORE_MALACHITE = null;
        public static Item POWDER_MALACHITE = null;
        public static Item ORE_GALENA = null;
        public static Item POWDER_GALENA = null;
        public static Item INGOT_ROUGH_BRONZE = null;

        public static Item MOLD_CLAY_INGOT = null;
        public static Item MOLD_HARDENED_CLAY_INGOT = null;
        public static Item MIXED_MORTAR_HARD_CERAMIC_POT = null;
        public static Item MIXED_MORTAR_HARD_CERAMIC_POT_EMBRYO = null;
        static Item[] items = {

                setItemName(GEAR_WOODEN = new ItemPartBase(), "gear_wooden"),
                setItemName(GEARS_WOODEN = new ItemPartBase(), "gears_wooden"),
                setItemName(GEAR_STONE = new ItemPartBase(),"gear_stone"),
                setItemName(GEARS_STONE = new ItemPartBase(),"gears_stone"),

                setItemName(ROD_STONE = new ItemRawMaterialBase(),"rod_stone"),
                setItemName(MIXED_MORTAR_BALL = new ItemRawMaterialBase(), "mixedmortarball"),
                setItemName(HEYC = new ItemRawMaterialBase(),"heyc"),
                setItemName(SIMPLE_CALCIUM_OXIDE = new ItemRawMaterialBase(),"simple_calcium_oxide"),
                setItemName(SIMPLE_CALCIUM_HYDROXIDE = new ItemRawMaterialBase(),"simple_calcium_hydroxide"),
                setItemName(SIMPLE_BRICK_EMBRYO = new ItemRawMaterialBase(),"simple_brick_embryo"),
                setItemName(CONDENSED_BRICK = new ItemRawMaterialBase(),"condensed_brick"),
                setItemName(FURNACE_BRICK = new ItemRawMaterialBase(),"furnace_brick"),
                setItemName(PLANT_ASH = new ItemRawMaterialBase(),"plant_ash"),
                setItemName(BRIQUETTE = new ItemRawMaterialBase(), "briquette"),
                setItemName(CRUDE_SALT = new ItemRawMaterialBase(),"crude_salt"),
                setItemName(DUST_COAL = new ItemRawMaterialBase(),"dust_coal"),
                setItemName(LOESS = new ItemRawMaterialBase(), "loess"),
                setItemName(INGOT_ROUGH_BRONZE = new ItemMetalBase(),"ingot_rough_bronze"),
                setItemName(ORE_MALACHITE = new ItemOreBase(),"ore_malachite"),
                setItemName(POWDER_MALACHITE = new ItemOreBase(),"powder_malachite"),
                setItemName(ORE_CASSITERITE = new ItemOreBase(),"ore_cassiterite"),
                setItemName(POWDER_CASSITERITE = new ItemOreBase(),"powder_cassiterite"),
                setItemName(ORE_GALENA = new ItemOreBase(),"ore_galena"),
                setItemName(POWDER_GALENA = new ItemOreBase(),"powder_galena"),
                setItemName(MIXED_MORTAR_HARD_CERAMIC_POT = new ItemPartBase(),"mixed_mortar_hard_ceramic_pot"),
                setItemName(MIXED_MORTAR_HARD_CERAMIC_POT_EMBRYO = new ItemPartBase(),"mixed_mortar_hard_ceramic_pot_embryo"),
                setItemName(UNFIRED_CLAY_BOWL = new ItemMiscBase(), "unfired_clay_bowl"),
                setItemName(IMPERFECTION = new ItemMiscBase(), "imperfection"),
                setItemName(ANIMAL_FAT = new ItemMiscBase(),"animal_fat"),

                setItemName(CERAMIC_BOWl = new ItemFluidContainer(250), "ceramic_bowl"),


                setItemName(MOLD_CLAY_INGOT = new ItemPartBase(),"mold_clay_ingot"),
                setItemName(MOLD_HARDENED_CLAY_INGOT = new ItemPartBase(),"mold_hardened_clay_ingot"),





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

