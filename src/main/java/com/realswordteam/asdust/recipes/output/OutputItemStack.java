package com.realswordteam.asdust.recipes.output;

import com.realswordteam.asdust.recipes.input.InputItemStack;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OutputItemStack implements OutputStack<ItemStack>{
    final List<ItemStack> outputs = new ArrayList<>();

    public OutputItemStack(ItemStack... itemStacks)
    {
        outputs.addAll(Arrays.asList(itemStacks));
    }

    @Override
    public int getSize() {
        return outputs.size();
    }

    @Override
    public List<ItemStack> getOutputStackList() {
        return outputs;
    }

    @Override
    public ItemStack getOutputStack(int value) {
        if (value < outputs.size() && value >= 0)
        {
            return outputs.get(value);
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean isEmpty()
    {
        return outputs.isEmpty();
    }
}
