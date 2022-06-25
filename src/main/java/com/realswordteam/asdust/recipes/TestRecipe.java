package com.realswordteam.asdust.recipes;

import net.minecraft.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class TestRecipe {
    public String name;
    public ItemStack input = ItemStack.EMPTY;
    public ItemStack output = ItemStack.EMPTY;
    public Map<String, ItemStack> maps = new HashMap<>();
    public TestRecipe(String name, ItemStack input, ItemStack output)
    {
        this.name = name;
        this.input = input;
        this.output = output;
    }

    public void put(String name, ItemStack input)
    {
        maps.put(name, input);
    }
}
