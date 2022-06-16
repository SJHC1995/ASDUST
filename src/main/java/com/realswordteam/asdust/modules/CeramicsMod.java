package com.realswordteam.asdust.modules;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class CeramicsMod {
    public static Item Unfired_Clay_Bucket()
    {
        return Item.REGISTRY.getObject(new ResourceLocation("ceramics:unfired_clay"));
    }

}
