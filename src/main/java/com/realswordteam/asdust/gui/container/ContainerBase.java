package com.realswordteam.asdust.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerBase extends Container {
    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return false;
    }

    protected void addPlayerInventory(EntityPlayer player, int x, int y)
    {
        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, x + j * 18, y + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i,  x + i * 18, 58 + y));
        }
    }
}
