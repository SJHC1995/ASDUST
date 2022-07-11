package com.realswordteam.asdust.gui.container;

import com.realswordteam.asdust.block.machine.kiln.TileEntityKiln;
import com.realswordteam.asdust.gui.slot.OutputSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerKiln extends ContainerBase {

    private IItemHandler kilnInput;

    private IItemHandler kilnOutput;

    private IItemHandler fuelInput;

    public TileEntity te;

    public ContainerKiln(TileEntityKiln te, EntityPlayer player)
    {
        this.te = te;

        this.kilnInput = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.kilnOutput = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
        this.fuelInput = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);


        this.addSlotToContainer(new SlotItemHandler(this.kilnInput, 0, 43, 16));
        this.addSlotToContainer(new SlotItemHandler(this.kilnInput, 1, 67, 16));
        this.addSlotToContainer(new SlotItemHandler(this.fuelInput, 0, 56, 52));
        this.addSlotToContainer(new OutputSlot(this.kilnOutput, 0, 116, 34));

        super.addPlayerInventory(player, 8, 83);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getDistanceSq(this.te.getPos()) <= 64;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        Slot slot = inventorySlots.get(index);

        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }

        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();

        boolean isMerged = false;
//
        if (index >= 0 && index <= 3)
        {
            isMerged = mergeItemStack(newStack, 4, 40, true);
        }
        else if (index >= 4 && index <= 40)
        {
            for (int i = 0; i<= 3; i++)
            {
                Slot slot1 = inventorySlots.get(i);
                if (!slot1.getHasStack())
                {
                    isMerged = mergeItemStack(newStack, 0,  3, false);
                }
            }
        }

        if (!isMerged)
        {
            return ItemStack.EMPTY;
        }

        if (newStack.getMaxStackSize() == 0)
        {
            slot.putStack(ItemStack.EMPTY);
        }
        else
        {
            slot.onSlotChanged();
        }

        slot.onTake(playerIn, newStack);

        return oldStack;
    }
}
