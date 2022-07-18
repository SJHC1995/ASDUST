package com.realswordteam.asdust.recipes;

import net.minecraft.item.ItemStack;

import java.util.Random;

public class ChangeItemStack {
    final ItemStack itemStack;
    final int max;
    final int min;

    static final ChangeItemStack Empty = new ChangeItemStack(ItemStack.EMPTY, 1,1);
    public ChangeItemStack(ItemStack itemStack, int max, int min)
    {
        this.itemStack = itemStack;
        this.max = max;
        this.min = min;
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

    public ItemStack spawnItemStack()
    {
        ItemStack itemStack = getItemStack();
        Random random = new Random();
        if (this.max == 1 && this.min == 1)
        {
            return itemStack;
        }   else
        {
            int num = random.nextInt(this.max - this.min) + this.min;
            if (num == 0)
            {
                return ItemStack.EMPTY;
            }   else
            {
                return new ItemStack(itemStack.getItem(), num);
            }
        }
    }
}
