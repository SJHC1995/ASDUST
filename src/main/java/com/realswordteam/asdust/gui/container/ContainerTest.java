package com.realswordteam.asdust.gui.container;

import com.realswordteam.asdust.block.machine.tileentity.TileEntityMachineCraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.Map;

public class ContainerTest extends Container {
    private final IItemHandler inputItem;
    private final IItemHandler outputItem;
    protected TileEntityMachineCraft tileEntityMachineBase;
    protected int burnTime = 0;
    protected FluidTank tank;
    public int fluidAmount;
    public int fluidId;
    public int tankCapacity;
    public ContainerTest(EntityPlayer player, TileEntity tileEntity)
    {
        super();

        this.tileEntityMachineBase = (TileEntityMachineCraft) tileEntity;

        this.tank = tileEntityMachineBase.getFluidTank();
        this.fluidAmount = tank.getFluidAmount();
        this.tankCapacity = tank.getCapacity();

        this.inputItem = this.tileEntityMachineBase.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.outputItem = this.tileEntityMachineBase.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);

        this.addSlotToContainer(new SlotItemHandler(this.inputItem, 0, 54, 20));
        this.addSlotToContainer(new SlotItemHandler(this.inputItem, 1, 74, 18));
        this.addSlotToContainer(new SlotItemHandler(this.inputItem, 2, 54, 56));
        this.addSlotToContainer(new SlotItemHandler(this.inputItem, 3, 74, 58));



        this.addSlotToContainer(new OutputSlot(this.outputItem,0, 110, 20));
        this.addSlotToContainer(new OutputSlot(this.outputItem, 1, 110, 38));
        this.addSlotToContainer(new OutputSlot(this.outputItem, 2, 110, 56));


        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 6 + j * 18, 87 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i,  6 + i * 18, 145));
        }

    }

    public FluidTank getFluidTank()
    {
        return this.tank;
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        this.burnTime = tileEntityMachineBase.getBurnTime();
        this.fluidAmount = tileEntityMachineBase.getTankAmount();
        Map<Fluid, Integer> ids = FluidRegistry.getRegisteredFluidIDs();
        for (Map.Entry<Fluid, Integer> entry : ids.entrySet())
        {
            if (tileEntityMachineBase.getFluid() == entry.getKey())
            {
                this.fluidId = entry.getValue();
            }
        }

        for (IContainerListener i : this.listeners)
        {
//            i.sendProgressBarUpdate(this, 0, this.burnTime);
            i.sendWindowProperty(this, 0, this.burnTime);
            i.sendWindowProperty(this, 1, this.fluidAmount);
            i.sendWindowProperty(this, 2, this.fluidId);
            i.sendWindowProperty(this, 3, this.tankCapacity);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void updateProgressBar(int id, int data)
    {
        super.updateProgressBar(id, data);

        switch (id)
        {
            case 0:
                this.burnTime = data;
                break;
            case 1:
                this.fluidAmount = data;
                break;
            case 2:
                this.fluidId = data;
                break;
            case 3:
                this.tankCapacity = data;
                break;
            default:
                break;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        Slot slot = inventorySlots.get(index);

//假如没有物品槽的话
        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }
//创建Itemstack和oldstack
        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();
//创建boolean isMerged
        boolean isMerged = false;
//
        if (index >= 0 && index <= 3)
        {
            isMerged = mergeItemStack(newStack, 7, 43, true);
        }
        else if (index >= 4 && index <= 6)
        {
            isMerged = mergeItemStack(newStack, 7, 43, true);
//            isMerged = !goldSlot.getHasStack() && newStack.stackSize <= 16 && mergeItemStack(newStack, 0, 1, false)
//                    || !emeraldSlot.getHasStack() && mergeItemStack(newStack, 2, 3, false)
//                    || mergeItemStack(newStack, 31, 40, false);
        }
        else if (index >= 7 && index <= 43)
        {
            for (int i = 0; i<= 3; i++)
            {
                Slot slot1 = inventorySlots.get(i);
                if (!slot1.getHasStack())
                {
                    isMerged = mergeItemStack(newStack, 0,  4, false);
                }
            }
//            isMerged = !slot1.getHasStack() && mergeItemStack(newStack, 0,  1, false)
//            isMerged = !goldSlot.getHasStack() && newStack.stackSize <= 16 && mergeItemStack(newStack, 0, 1, false)
//                    || !emeraldSlot.getHasStack() && mergeItemStack(newStack, 2, 3, false)

        }
//如果都不符合当前条件就返回空
        if (!isMerged)
        {
            return ItemStack.EMPTY;
        }
//如果得知newStack没东西那设置为0（更新？）
        if (newStack.getMaxStackSize() == 0)
        {
            slot.putStack(ItemStack.EMPTY);
        }
        else
        {
            slot.onSlotChanged();
        }
//意义不明
//        slot.onPickupFromSlot(playerIn, newStack);
        slot.onTake(playerIn, newStack);

        return oldStack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return playerIn.getDistanceSq(this.tileEntityMachineBase.getPos()) <= 64;
    }
    public int getFluidAmount()
    {
        return this.fluidAmount;
    }

    public int getBurnTime()
    {
        return this.burnTime;
    }

    public int getTotalBurnTime()
    {
        return this.tileEntityMachineBase.getTotalBurnTime();
    }
    public ItemStack getStackFromSlot()
    {
        return this.inventorySlots.get(0).getStack();
    }
    public class OutputSlot extends SlotItemHandler
    {
        public OutputSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition)
        {
            super(itemHandler, index, xPosition, yPosition);
        }

        @Override
        public boolean isItemValid(ItemStack stack)
        {
            return false;
        }

    }
}
