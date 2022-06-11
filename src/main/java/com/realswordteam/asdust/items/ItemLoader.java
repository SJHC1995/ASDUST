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


        @SubscribeEvent
        public static void registerItem(RegistryEvent.Register<Item> event)
        {
            Item[] items = {
                    setItemName(admain = new ItemMainBase(),"admain"),
                    setItemName(adce = new ItemMainBase(),"adce"),
                    setItemName(admachine = new ItemMainBase(),"admachine"),
                    setItemName(yh = new ItemChemicalElementBase(),"yh"),
                    setItemName(yhe = new ItemChemicalElementBase(),"yhe"),
                    setItemName(yli = new ItemChemicalElementBase(),"yli"),
                    setItemName(ybe = new ItemChemicalElementBase(),"ybe"),
                    setItemName(yb = new ItemChemicalElementBase(),"yb"),
                    setItemName(yc = new ItemChemicalElementBase(),"yc"),
                    setItemName(yn = new ItemChemicalElementBase(),"yn"),
                    setItemName(yo = new ItemChemicalElementBase(),"yo"),
                    setItemName(yf = new ItemChemicalElementBase(),"yf"),
                    setItemName(yne = new ItemChemicalElementBase(),"yne"),
                    setItemName(yna = new ItemChemicalElementBase(),"yna"),
                    setItemName(ymg = new ItemChemicalElementBase(),"ymg"),
                    setItemName(yal = new ItemChemicalElementBase(),"yal"),
                    setItemName(ysi = new ItemChemicalElementBase(),"ysi"),
                    setItemName(yp = new ItemChemicalElementBase(),"yp"),
                    setItemName(ys = new ItemChemicalElementBase(),"ys"),
                    setItemName(ycl = new ItemChemicalElementBase(),"ycl"),
                    setItemName(yar = new ItemChemicalElementBase(),"yar"),
                    setItemName(yfe = new ItemChemicalElementBase(),"yfe"),
                    setItemName(gearwood = new ItemPartBase(),"gearwood"),
                    setItemName(gearwoodgroup = new ItemPartBase(),"gearwoodgroup"),
                    setItemName(mixedmortarball = new ItemRawMaterialBase(),"mixedmortarball"),





            };
            event.getRegistry().registerAll(items);
        }

public static  Item setItemName(Item item, String name){
        item.setRegistryName(ASDUST.MODID,name).setUnlocalizedName(ASDUST.MODID + "." + name);
       return item; }
    }

