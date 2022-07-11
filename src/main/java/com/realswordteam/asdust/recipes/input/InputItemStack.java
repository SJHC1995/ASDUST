package com.realswordteam.asdust.recipes.input;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputItemStack implements InputStack<ItemStack>{
    public final List<ItemStack> inputs = new ArrayList<>();

    public InputItemStack(ItemStack... itemStacks)
    {
        inputs.addAll(Arrays.asList(itemStacks));
    }
    @Override
    public boolean isEmpty() {
        return inputs.isEmpty();
    }

    @Override
    public int getSize() {
        return inputs.size();
    }

    @Override
    public List<ItemStack> getInputStackList() {
        return this.inputs;
    }

    @Override
    public ItemStack getInputStack(int value) {
        if (value < inputs.size() && value >= 0)
        {
            return inputs.get(value);
        }
        return ItemStack.EMPTY;
    }
}
