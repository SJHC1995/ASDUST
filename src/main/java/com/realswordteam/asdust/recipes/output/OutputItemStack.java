package com.realswordteam.asdust.recipes.output;

import com.realswordteam.asdust.recipes.input.InputItemStack;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputItemStack {
    final List<ItemStack> outputs = new ArrayList<>();
    public static OutputItemStack EMPTY = new OutputItemStack(ItemStack.EMPTY);
    public OutputItemStack(ItemStack... itemStacks)
    {
        outputs.addAll(Arrays.asList(itemStacks));
    }
    public int getSize() {
        return outputs.size();
    }
    public List<ItemStack> getOutputs()
    {
        return outputs;
    }

    public ItemStack getOutput(int value)
    {
        if (value < outputs.size() && value >= 0)
        {
            return outputs.get(value);
        }
        return ItemStack.EMPTY;
    }
    public boolean isEmpty()
    {
        return outputs.isEmpty();
    }
}
