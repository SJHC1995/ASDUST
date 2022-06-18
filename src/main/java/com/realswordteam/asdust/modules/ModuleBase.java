package com.realswordteam.asdust.modules;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ModuleBase {
    public static Item getModuleItem(String resourceName)
    {
        return Item.REGISTRY.getObject(new ResourceLocation(resourceName));
    }
}
