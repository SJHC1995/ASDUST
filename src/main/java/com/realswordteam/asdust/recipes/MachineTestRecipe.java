package com.realswordteam.asdust.recipes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class MachineTestRecipe {
    public static MachineTestRecipe recipe = new MachineTestRecipe();
    public Map<List<Item>, List<ItemStack>> map = new HashMap<>();
    public final List<ItemStack> stacksEmpty = new ArrayList<>();
    public final List<Item> itemEmpty = new ArrayList<>();
    public void addMachineTestRecipe(Item[] inputItem, ItemStack[] outputItem)
    {
        List<Item> inputItemList = new ArrayList<>(Arrays.asList(inputItem));
        List<ItemStack> outputItemList = new ArrayList<>(Arrays.asList(outputItem));
        this.map.put(inputItemList, outputItemList);
    }
    public List<ItemStack> recipe(List<Item> getItemList)
    {
        for (Map.Entry<List<Item>, List<ItemStack>> entry : this.map.entrySet())
        {
            List<Item> list = entry.getKey();
            boolean flag = new HashSet<>(list).containsAll(getItemList);
            boolean flag1 = list.size() == getItemList.size();
            if (flag && flag1)
            {
                return entry.getValue();
            }
        }
        return this.stacksEmpty;
    }
    public List<Item> getOutputRecipe(List<Item> getItemList)
    {
        List<ItemStack> i = new ArrayList<>(recipe(getItemList));
        if (!i.isEmpty())
        {
            List<Item> j = new ArrayList<>();
            for (ItemStack a : i)
            {
                j.add(a.getItem());
            }
            return j;
        }
        return this.itemEmpty;
    }


}
