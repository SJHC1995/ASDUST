package com.realswordteam.asdust.gui.container;

import com.realswordteam.asdust.block.machine.tank.TileEntityBaseTank;
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

public class ContainerTank extends Container {
    private IItemHandler tankInput;
    private IItemHandler tankOutput;
    protected TileEntityBaseTank tileEntityTank;
    public int processTime;
    protected FluidTank tank;
    public int fluidAmount;
    public int fluidId;
    public int tankCapacity;
    public String blockName;
    public String playerInventoryName;
    public ContainerTank(EntityPlayer player, TileEntity tileEntity)
    {
        super();

        this.tileEntityTank = (TileEntityBaseTank) tileEntity;

        this.tank = tileEntityTank.getTank();
        this.fluidAmount = tank.getFluidAmount();
        this.tankCapacity = tileEntityTank.getTankCapacity();
        this.processTime = tileEntityTank.processTime;
        this.blockName = tileEntityTank.getBlockName();
        this.playerInventoryName = player.inventory.getDisplayName().getUnformattedText();

        this.tankInput = this.tileEntityTank.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);
        this.tankOutput = this.tileEntityTank.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);

        this.addSlotToContainer(new SlotItemHandler(this.tankInput, 0, 56, 17));
        this.addSlotToContainer(new SlotItemHandler(this.tankOutput,0, 56, 53));
//        this.addSlotToContainer(new ContainerTank.OutputSlot(this.tankOutput,0, 56, 53));

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player.inventory, i,  8 + i * 18, 142));
        }
    }

    public ContainerTank() {
    }

    public FluidTank getFluidTank()
    {
        return this.tank;
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        this.fluidAmount = tileEntityTank.getTank().getFluidAmount();

        Map<Fluid, Integer> ids = FluidRegistry.getRegisteredFluidIDs();
        for (Map.Entry<Fluid, Integer> entry : ids.entrySet())
        {
            if (tileEntityTank.getFluid() == entry.getKey())
            {
                this.fluidId = entry.getValue();
            }
        }
        this.tankCapacity = tileEntityTank.getTankCapacity();
        this.processTime = tileEntityTank.getProcessTime();

        for (IContainerListener i : this.listeners)
        {
            i.sendWindowProperty(this, 0, this.fluidAmount);
            i.sendWindowProperty(this, 1, this.fluidId);
            i.sendWindowProperty(this, 2, this.tankCapacity);
            i.sendWindowProperty(this, 3, this.processTime);
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
                this.fluidAmount = data;
                break;
            case 1:
                this.fluidId = data;
                break;
            case 2:
                this.tankCapacity = data;
                break;
            case 3:
                this.processTime = data;
                break;
            default:
                break;
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        Slot slot = inventorySlots.get(index);
//
////假如没有物品槽的话
        if (slot == null || !slot.getHasStack())
        {
            return ItemStack.EMPTY;
        }
////创建Itemstack和oldstack
        ItemStack newStack = slot.getStack(), oldStack = newStack.copy();
////创建boolean isMerged
        boolean isMerged = false;
////
        if (index >= 0 && index <= 1)
        {
            isMerged = mergeItemStack(newStack, 2, 38, true);
        }
//        else if (index >= 4 && index <= 6)
//        {
//            isMerged = mergeItemStack(newStack, 7, 43, true);
////            isMerged = !goldSlot.getHasStack() && newStack.stackSize <= 16 && mergeItemStack(newStack, 0, 1, false)
////                    || !emeraldSlot.getHasStack() && mergeItemStack(newStack, 2, 3, false)
////                    || mergeItemStack(newStack, 31, 40, false);
//        }
        else if (index >= 2 && index <= 38)
        {
            isMerged = mergeItemStack(newStack, 0,  1, false);
        }
//            isMerged = !slot1.getHasStack() && mergeItemStack(newStack, 0,  1, false)
//            isMerged = !goldSlot.getHasStack() && newStack.stackSize <= 16 && mergeItemStack(newStack, 0, 1, false)
//                    || !emeraldSlot.getHasStack() && mergeItemStack(newStack, 2, 3, false)
////如果都不符合当前条件就返回空
        if (!isMerged)
        {
            return ItemStack.EMPTY;
        }
////如果得知newStack没东西那设置为0（更新？）
        if (newStack.getMaxStackSize() == 0)
        {
            slot.putStack(ItemStack.EMPTY);
        }
        else
        {
            slot.onSlotChanged();
        }
////意义不明
//        slot.onPickupFromSlot(playerIn, newStack);
        slot.onTake(playerIn, newStack);

        return oldStack;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return playerIn.getDistanceSq(this.tileEntityTank.getPos()) <= 64;
    }
    public int getFluidAmount()
    {
        return this.fluidAmount;
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
