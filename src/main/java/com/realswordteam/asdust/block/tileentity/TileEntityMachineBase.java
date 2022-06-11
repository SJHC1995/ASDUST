package com.realswordteam.asdust.block.tileentity;

import com.realswordteam.asdust.recipes.MachineTestRecipe;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.*;

public class TileEntityMachineBase extends TileEntity implements ITickable {
    public int burnTime;
    protected ItemStackHandler ITEM_IN = new ItemStackHandler(4);
    protected ItemStackHandler ITEM_OUT = new ItemStackHandler(3);

    protected FluidTank fluidTank = new FluidTank(2000);
    protected Fluid fluid;

    public TileEntityMachineBase()
    {
        super();
        this.fluid = null;
    }
    public FluidTank getFluidTank()
    {
        return this.fluidTank;
    }
    public Fluid getFluid()
    {
        return this.fluid;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            return true;
        }
        if (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.equals(capability))
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            if (facing == EnumFacing.DOWN) return (T) ITEM_OUT;
            else return (T) ITEM_IN;
        }
        if (CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY.equals(capability))
        {
            return (T) fluidTank;
        }
        return super.getCapability(capability, facing);
    }


    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.ITEM_IN.deserializeNBT(compound.getCompoundTag("Item_Put"));
        this.ITEM_OUT.deserializeNBT(compound.getCompoundTag("Item_Out"));
        this.burnTime = compound.getInteger("BurnTime");
        fluidTank.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound = super.writeToNBT(compound);
        compound.setTag("Item_Put", this.ITEM_IN.serializeNBT());
        compound.setTag("Item_Out", this.ITEM_OUT.serializeNBT());
        compound.setInteger("BurnTime", this.burnTime);
        fluidTank.writeToNBT(compound);
        return compound;
    }

    @Override
    public void update()
    {
        if (!this.world.isRemote)
        {
            List<Item> getItemList = new ArrayList<>();
            for (int i = 0; i <= 3; i++)
            {
                if (!ITEM_IN.extractItem(i, 1, true).isEmpty())
                {
                    getItemList.add(ITEM_IN.extractItem(i, 1, true).getItem());
                }
            }

            boolean flag1 = getItemList.isEmpty();
            boolean flag2 = MachineTestRecipe.recipe.recipe(getItemList).isEmpty();

            List<Item> outputSlotItem = new ArrayList<>();
            for (int i = 0; i <= 2; i++)
            {
                if (!ITEM_OUT.extractItem(i, 1, true).isEmpty())
                {
                    outputSlotItem.add(ITEM_OUT.extractItem(i, 1, true).getItem());
                }
            }
            boolean flag3 = new HashSet<>(MachineTestRecipe.recipe.getOutputRecipe(getItemList)).containsAll(outputSlotItem);

            if (!flag1 && !flag2 && flag3)
            {

                    List<ItemStack> outputItemStack = new ArrayList<>(MachineTestRecipe.recipe.recipe(getItemList));
                    ItemStack[] outputItemStackArray = new ItemStack[outputItemStack.size()];
                    outputItemStack.toArray(outputItemStackArray);

                    if (this.fluidTank.getFluidAmount() >= 100)
                    {
                        int burnTotalTime = 100;

                        if (++this.burnTime >= burnTotalTime)
                        {
                            this.burnTime = 0;
                            for (int i = 0; i <= 3; i++)
                            {
                                if (!ITEM_IN.extractItem(i, 1, true).isEmpty())
                                {
                                    ITEM_IN.extractItem(i, 1, false);
                                }
                            }
                            if (checkSlotIsEmpty())
                            {
                                for (int i = 0; i < outputItemStackArray.length; i++)
                                {
                                    ItemStack stack = outputItemStackArray[i];
                                    ITEM_OUT.insertItem(i, stack, false);
                                    System.out.println(i);
                                }
                            }

                            Map<Integer, ItemStack> map = new HashMap<>();
                            for (int i = 0; i <= 2; i++)
                            {
                                map.put(i, ITEM_OUT.getStackInSlot(i));
                            }
                            if (!checkSlotIsEmpty())
                            {
                                for (ItemStack itemStack : outputItemStackArray)
                                {
                                    ItemStack stack = itemStack.copy();
                                    for (Map.Entry<Integer, ItemStack> entry : map.entrySet())
                                    {
                                        int a = entry.getKey();
                                        boolean flag = (entry.getValue().getCount() + itemStack.getCount()) <= entry.getValue().getMaxStackSize();
                                        if (itemStack.getItem().equals(entry.getValue().getItem()) && flag)
                                        {
                                            ITEM_OUT.insertItem(a, stack, false);
                                            break;
                                        } else if (entry.getValue().isEmpty()) {
                                            ITEM_OUT.insertItem(a, stack, false);
                                            break;
                                        }
                                    }
                                }
                            }
                            this.fluidTank.drain(100,true);
                            this.markDirty();
                        }
                    }
            }
            else
            {
                this.burnTime = 0;
            }

            if (this.fluidTank.getFluidAmount() != 0)
            {
                this.fluid = this.fluidTank.getFluid().getFluid();
            }
        }
    }
//    public List<ItemStack> getStacks(Map<List<ItemStack>, List<ItemStack>> map, List<ItemStack> list)
//    {
//        List<ItemStack> list1 = new ArrayList<>();
//        for(Map.Entry entry : map.entrySet())
//        {
//            if (entry.getKey().equals(list))
//            {
//                list1.add((ItemStack) entry.getValue());
//            }
//            return list1;
//        }
//        return list1;
//    }

    private boolean checkSlotIsEmpty()
    {
        if (ITEM_OUT.getStackInSlot(0).isEmpty())
        {
            if (ITEM_OUT.getStackInSlot(1).isEmpty())
            {
                if (ITEM_OUT.getStackInSlot(2).isEmpty())
                {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }

    public int getBurnTime()
    {
        return this.burnTime;
    }
    public int getTotalBurnTime()
    {
        return 100;
    }

}
