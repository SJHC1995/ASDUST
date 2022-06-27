package com.realswordteam.asdust.block.machine.tileentity;

import com.realswordteam.asdust.recipes.ChangeItemStack;
import com.realswordteam.asdust.recipes.machine.RecipeCraft;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.state.IBlockState;
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

public class TileEntityMachineCraft extends TileEntity implements ITickable {
    public int burnTime;
    private int recordSlot,recordSlot2,recordSlot3,recordSlot4;
    protected ItemStackHandler ITEM_IN = new ItemStackHandler(4);
    protected ItemStackHandler ITEM_OUT = new ItemStackHandler(3);

    protected FluidTank fluidTank = new FluidTank(2000);
    protected Fluid fluid;
    protected RecipeCraft.RecipeT recipeT = RecipeCraft.Empty;

    public TileEntityMachineCraft()
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
        if (this.fluidTank.getFluid() != null)
        {
            return this.fluidTank.getFluid().getFluid();
        }
        return null;
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
        if (!world.isRemote)
        {
            if (canStart())
            {
                if (++burnTime > 100)
                {
                    processFinish();
                    this.markDirty();
                }
            }   else if (!canStart())
            {
                this.burnTime = 0;
            }
        }
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }

    private boolean canStart()
    {
        ItemStack coreStack = ItemStack.EMPTY;
        for (recordSlot = 0; recordSlot < ITEM_IN.getSlots(); recordSlot++)
        {
            ItemStack findStack = ITEM_IN.getStackInSlot(recordSlot);
            ItemStack tempStack = getInput(findStack);
            ItemStack inputStack = tempStack.copy();
            if (isRecipeInput(findStack) && findStack.getCount() >= inputStack.getCount())
            {
                coreStack = findStack;
                break;
            }   else
            {
                recipeT = RecipeCraft.Empty;
            }
        }

        if (!coreStack.isEmpty())
        {
            for (recordSlot2 = 0; recordSlot2 < ITEM_IN.getSlots(); recordSlot2++)
            {
                ItemStack findStack2 = ITEM_IN.getStackInSlot(recordSlot2);
                ItemStack tempStack2 = getRecipeT(coreStack).getInput();
                ItemStack inputStack2 = tempStack2.copy();
                boolean flag = inputStack2.isItemEqual(findStack2) || inputStack2.isEmpty();
                boolean flag2 = findStack2.getCount() >= inputStack2.getCount();
                if (flag && flag2)
                {
                    recipeT = getRecipeT(coreStack);
                    break;
                }   else
                {
                    recipeT = RecipeCraft.Empty;
                }
            }
        }

        if (!recipeT.isEmpty())
        {
            for (recordSlot3 = 0; recordSlot3 < ITEM_OUT.getSlots(); recordSlot3++)
            {
                ItemStack inSlotOutputStack = ITEM_OUT.getStackInSlot(recordSlot3);
                boolean flag1 = inSlotOutputStack.isItemEqual(recipeT.getOutput()) && inSlotOutputStack.getCount() < inSlotOutputStack.getMaxStackSize();
                if (flag1 || inSlotOutputStack.isEmpty())
                {
                    for (recordSlot4 = 0; recordSlot4 < ITEM_OUT.getSlots(); recordSlot4++)
                    {
                        ItemStack inSlotByProductionStack = ITEM_OUT.getStackInSlot(recordSlot4);
                        boolean flag2 = recordSlot3 != recordSlot4 && inSlotByProductionStack.isEmpty();
                        boolean flag3 = inSlotByProductionStack.isItemEqual(recipeT.getByProduction().getItemStack()) && inSlotByProductionStack.getCount() < inSlotByProductionStack.getMaxStackSize();
                        if (flag3 || flag2)
                        {
                            FluidStack recipeFluid = recipeT.getFluidStack();
                            FluidStack containerFluid = this.fluidTank.getFluid();
                            if (containerFluid != null && recipeFluid != null)
                            {
                                return containerFluid.amount >= recipeFluid.amount && containerFluid.isFluidEqual(recipeFluid);
                            }   else if (recipeFluid == null)
                            {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    private void processFinish()
    {
        if (!recipeT.isEmpty())
        {
            ItemStack outputRecipe = ITEM_OUT.getStackInSlot(recordSlot3);
            ItemStack tempOutputRecipe = recipeT.getOutput();
            ItemStack outputItem = tempOutputRecipe.copy();
            if (outputRecipe.isEmpty())
            {
                ITEM_OUT.insertItem(recordSlot3, outputItem, false);
            }   else
            {
                outputRecipe.grow(outputItem.getCount());
            }

            ItemStack byProductionRecipe = ITEM_OUT.getStackInSlot(recordSlot4);
            ChangeItemStack tempByProductionRecipe = recipeT.getByProduction();
            ItemStack tempByProductionRecipeItem = tempByProductionRecipe.getItemStack();
            ItemStack byProductionItem = tempByProductionRecipeItem.copy();
            int change = recipeT.getByProduction().getChange();
            Random random = new Random();
            int randomValue = random.nextInt(100);
            boolean flag = randomValue < change;
            if (byProductionRecipe.isEmpty() && flag)
            {
                ITEM_OUT.insertItem(recordSlot4, byProductionItem, false);
            }   else if (flag)
            {
                byProductionRecipe.grow(byProductionItem.getCount());
            }

            ItemStack tempCoreRecipe = getInputFromRecipe(recipeT);
            ItemStack coreItem = tempCoreRecipe.copy();
            if (!coreItem.isEmpty())
            {
                ITEM_IN.getStackInSlot(recordSlot).shrink(coreItem.getCount());
            }

            ItemStack tempInputRecipe = recipeT.getInput();
            ItemStack inputItem = tempInputRecipe.copy();
            if (!inputItem.isEmpty())
            {
                ITEM_IN.getStackInSlot(recordSlot2).shrink(inputItem.getCount());
            }

            FluidStack recipeFluid = recipeT.getFluidStack();
            if (recipeFluid != null)
            {
                this.fluidTank.drain(recipeT.getFluidStack().amount, true);
            }

        }
        this.burnTime = 0;

    }
    private ItemStack getInput(ItemStack itemStack)
    {
        for (Map.Entry<ItemStack, RecipeCraft.RecipeT> entry : RecipeCraft.getRecipeTMap().entrySet())
        {
            if (itemStack.isItemEqual(entry.getKey()))
            {
                return entry.getKey();
            }
        }
        return ItemStack.EMPTY;
    }
    private ItemStack getInputFromRecipe(RecipeCraft.RecipeT recipeT)
    {
        for (Map.Entry<ItemStack, RecipeCraft.RecipeT> entry : RecipeCraft.getRecipeTMap().entrySet())
        {
            if (recipeT.equals(entry.getValue()))
            {
                return entry.getKey();
            }
        }
        return ItemStack.EMPTY;
    }
    private boolean isRecipeInput(ItemStack input)
    {
        for (Map.Entry<ItemStack, RecipeCraft.RecipeT> entry : RecipeCraft.getRecipeTMap().entrySet())
        {
            if (input.isItemEqual(entry.getKey()))
            {
                return true;
            }
        }
        return false;
    }

    private RecipeCraft.RecipeT getRecipeT(ItemStack input)
    {
        for (Map.Entry<ItemStack, RecipeCraft.RecipeT> entry : RecipeCraft.getRecipeTMap().entrySet())
        {
            if (input.isItemEqual(entry.getKey()))
            {
                return entry.getValue();
            }
        }
        return RecipeCraft.Empty;
    }
    private Map<Integer, ItemStack> getITEM_INMap()
    {
        Map<Integer, ItemStack> map = new Object2ObjectOpenHashMap<>();
        for (int num = 0; num < ITEM_IN.getSlots(); num++)
        {
            map.put(num, ITEM_IN.getStackInSlot(num));
        }
        return map;
    }

    public int getTankAmount()
    {
        return this.fluidTank.getFluidAmount();
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
