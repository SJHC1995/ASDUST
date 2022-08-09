package com.realswordteam.asdust.block.machine;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

public class SimpleUtil {

    public static boolean checkItemStackOfHandlerIsEmpty(ItemStackHandler handler)
    {
        for (int num = 0; num < handler.getSlots(); num++)
        {
            if (!handler.getStackInSlot(num).isEmpty())
            {
                return false;
            }
        }
        return true;
    }

    public static ItemStack getItemStackFromHandler(int slotIndex, ItemStackHandler handler)
    {
        if (handler == null)
        {
            return ItemStack.EMPTY;
        }
        if (slotIndex >= handler.getSlots() && slotIndex < 0)
        {
            return handler.getStackInSlot(slotIndex);
        }
        return ItemStack.EMPTY;
    }

}
