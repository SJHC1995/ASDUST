package com.realswordteam.asdust.items;

import com.realswordteam.asdust.ASDUST;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@Mod.EventBusSubscriber(modid = ASDUST.MODID)
    public class ItemLoader
    {
        public static Item admain = null;
        public static Item adce = null;
        public static Item admachine = null;
        public static Item yh = null;
        public static Item yhe = null;
        public static Item yli = null;
        public static Item ybe = null;
        public static Item yb = null;
        public static Item yc = null;
        public static Item yn = null;
        public static Item yo =null;
        public static Item yf =null;
        public static Item yne =null;
        public static Item yna =null;
        public static Item ymg =null;
        public static Item yal =null;
        public static Item ysi =null;
        public static Item yp =null;
        public static Item ys =null;
        public static Item ycl =null;
        public static Item yar =null;
        public static Item yfe = null;
        public static Item gearwood = null;
        public static Item gearwoodgroup = null;
        public static Item mixedmortarball =null;
        public static Item imperfection = null;
        public static Item loess = null;
        public static Item unfired_clay_bowl = null;
        public static Item ceramic_bowl = null;
        public static Item simple_calcium_oxide =null;
        public static Item simple_calcium_hydroxide=null;
        public static Item mixed_mortar_hard_ceramic_pot_embryo=null;
public static Item mixed_sand_hard_ceramic_pot=null;

















        static Item[] items = {
                setItemName(admain = new ItemMainBase(), "admain"),
                setItemName(adce = new ItemMainBase(), "adce"),
                setItemName(admachine = new ItemMainBase(), "admachine"),
                setItemName(yh = new ItemChemicalElementBase(), "yh"),
                setItemName(yhe = new ItemChemicalElementBase(), "yhe"),
                setItemName(yli = new ItemChemicalElementBase(), "yli"),
                setItemName(ybe = new ItemChemicalElementBase(), "ybe"),
                setItemName(yb = new ItemChemicalElementBase(), "yb"),
                setItemName(yc = new ItemChemicalElementBase(), "yc"),
                setItemName(yn = new ItemChemicalElementBase(), "yn"),
                setItemName(yo = new ItemChemicalElementBase(), "yo"),
                setItemName(yf = new ItemChemicalElementBase(), "yf"),
                setItemName(yne = new ItemChemicalElementBase(), "yne"),
                setItemName(yna = new ItemChemicalElementBase(), "yna"),
                setItemName(ymg = new ItemChemicalElementBase(), "ymg"),
                setItemName(yal = new ItemChemicalElementBase(), "yal"),
                setItemName(ysi = new ItemChemicalElementBase(), "ysi"),
                setItemName(yp = new ItemChemicalElementBase(), "yp"),
                setItemName(ys = new ItemChemicalElementBase(), "ys"),
                setItemName(ycl = new ItemChemicalElementBase(), "ycl"),
                setItemName(yar = new ItemChemicalElementBase(), "yar"),
                setItemName(yfe = new ItemChemicalElementBase(), "yfe"),
                setItemName(gearwood = new ItemPartBase(), "gearwood"),
                setItemName(gearwoodgroup = new ItemPartBase(), "gearwoodgroup"),
                setItemName(mixedmortarball = new ItemRawMaterialBase(), "mixedmortarball"),
                setItemName(imperfection = new ItemMiscBase(), "imperfection"),
                setItemName(loess = new ItemRawMaterialBase(), "loess"),
                setItemName(unfired_clay_bowl = new ItemMiscBase(), "unfired_clay_bowl"),
                setItemName(ceramic_bowl = new ItemFluidContainer(250), "ceramic_bowl"),
                setItemName(simple_calcium_oxide=new ItemRawMaterialBase(),"simple_calcium_oxide"),
                setItemName(simple_calcium_hydroxide=new ItemRawMaterialBase(),"simple_calcium_hydroxide"),
                setItemName(mixed_mortar_hard_ceramic_pot_embryo=new ItemPartBase(),"mixed_mortar_hard_ceramic_pot_embryo"),
                setItemName(mixed_sand_hard_ceramic_pot=new ItemPartBase(),"mixed_sand_hard_ceramic_pot"),

























    };


        @SubscribeEvent
        public static void registerItem(RegistryEvent.Register<Item> event)
        {
            event.getRegistry().registerAll(items);
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

