package com.realswordteam.asdust.recipes.input;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputItemStack {
    final List<ItemStack> inputs = new ArrayList<>();
    public static final InputItemStack EMPTY = new InputItemStack();
    public InputItemStack(ItemStack... itemStacks)
    {
        this.inputs.addAll(Arrays.asList(itemStacks));
    }

    public List<ItemStack> getInputs()
    {
        return this.inputs;
    }

    public ItemStack getInput(int value)
    {
        if (value < inputs.size() && value >= 0)
        {
            return inputs.get(value);
        }
        return ItemStack.EMPTY;
    }
    public int getSize()
    {
        return inputs.size();
    }
    public boolean isEmpty()
    {
        return inputs.isEmpty();
    }
}
