package com.realswordteam.asdust.recipes.input;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InputFuel {

    Map<ItemStack, Integer> fuels = new Object2ObjectOpenHashMap<>();

    public void addFuel(ItemStack itemStack, int burnTime)
    {
        fuels.put(itemStack, burnTime);
    }

    public Map<ItemStack, Integer> getFuels()
    {
        return fuels;
    }
}
