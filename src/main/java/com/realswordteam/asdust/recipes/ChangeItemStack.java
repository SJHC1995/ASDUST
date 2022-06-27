package com.realswordteam.asdust.recipes;

import net.minecraft.item.ItemStack;

public class ChangeItemStack {
    final ItemStack itemStack;
    final int change;
    static final ChangeItemStack Empty = new ChangeItemStack(ItemStack.EMPTY, 100);
    public ChangeItemStack(ItemStack itemStack, int change)
    {
        this.change = change;
        this.itemStack = itemStack;
    }
    public int getChange()
    {
        return change;
    }
    public boolean isEmpty()
    {
        return itemStack.isEmpty();
    }
    public ItemStack getItemStack()
    {
        if (!itemStack.isEmpty())
        {
            return itemStack.copy();
        }
        return ItemStack.EMPTY;
    }
}
