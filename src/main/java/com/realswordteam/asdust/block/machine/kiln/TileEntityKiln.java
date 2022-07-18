package com.realswordteam.asdust.block.machine.kiln;

import com.realswordteam.asdust.block.machine.Util;
import com.realswordteam.asdust.recipes.RecipesLoader;
import com.realswordteam.asdust.recipes.machine.Kiln;
import com.realswordteam.asdust.recipes.recipe.RecipeSimple;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Map;

public class TileEntityKiln extends TileEntity implements ITickable {
    protected ItemStackHandler inputsHandler = new ItemStackHandler(2);

    protected ItemStackHandler outputsHandler = new ItemStackHandler();

    protected ItemStackHandler fuelHandler = new ItemStackHandler();

    public Kiln kiln = RecipesLoader.kiln;

    private int burnTime;

    @Override
    public void update() {
        if (!world.isRemote)
        {
            if (canStart())
            {
                if (canBurn())
                {
                    if (++burnTime >= getFuelBurnTime)
                    {
                        processFinish();
                        this.markDirty();
                    }
                }
            }
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    private boolean flag;
    private boolean flag1, flag2, flag3, flag4, flag5;

    private boolean isBurning;

    private RecipeSimple recipe;

    private int recordSlot;

    private boolean canStart()
    {
        // first check
        if (!Util.checkInputItemStackIsEmpty(inputsHandler) && !flag)
        {
            for (RecipeSimple recipe : kiln.getRecipeList())
            {
                flag1 = recipe.inputItemStack.getSize() == 1;
                flag2 = recipe.inputItemStack.getSize() == 2;

                if (flag1)
                {
                    ItemStack fromRecipe = recipe.inputItemStack.getInputStack(0);
                    for (int num = 0; num < inputsHandler.getSlots(); num++)
                    {
                        if (inputsHandler.getStackInSlot(num).isItemEqual(fromRecipe))
                        {
                            inputsHandler.getStackInSlot(num).shrink(fromRecipe.getCount());
                            this.recipe = recipe;
                            flag = true;
                        }
                    }
                }

                if (flag2)
                {
                    ItemStack fromRecipe_1 = recipe.inputItemStack.getInputStack(0);
                    ItemStack fromRecipe_2 = recipe.inputItemStack.getInputStack(1);
                    for (int num = 0; num < inputsHandler.getSlots(); num++)
                    {
                        if (inputsHandler.getStackInSlot(num).isItemEqual(fromRecipe_1))
                        {
                            for (int num1 = 0; num1 < inputsHandler.getSlots(); num1++)
                            {
                                if (inputsHandler.getStackInSlot(num1).isItemEqual(fromRecipe_2))
                                {
                                    Util.getItemStackFromHandler(num, inputsHandler).shrink(fromRecipe_1.getCount());
                                    Util.getItemStackFromHandler(num1, inputsHandler).shrink(fromRecipe_2.getCount());
                                    this.recipe = recipe;
                                    flag = true;
                                }
                            }
                        }
                    }
                }
            }
        }

        // second check
        if (!(recipe == null))
        {
            flag4 = recipe.outputItemStack.getSize() == 1;
        }

        if (flag4)
        {
            ItemStack tempStack = recipe.outputItemStack.getOutputStack(0);
            ItemStack fromRecipe_1 = tempStack.copy();
            for (int num = 0; num < outputsHandler.getSlots(); num++)
            {
                if (outputsHandler.getStackInSlot(num).isItemEqual(fromRecipe_1) || outputsHandler.getStackInSlot(num).isEmpty())
                {
                    recordSlot = num;
                    flag5 = true;
                }
            }
        }

        //third check
        if (flag && flag5 && isBurning)
        {
            return true;
        }
        return false;
    }

    private int getFuelBurnTime;

    private boolean canBurn()
    {
        if (!(recipe == null) && !isBurning)
        {
            ItemStack fuelSlotStack = fuelHandler.getStackInSlot(0);
            for (Map.Entry<ItemStack, Integer> entry : RecipesLoader.kileFuel.getFuels().entrySet())
            {
                if (fuelSlotStack.isItemEqual(entry.getKey()))
                {
                    getFuelBurnTime = entry.getValue();
                    fuelHandler.getStackInSlot(0).shrink(fuelSlotStack.getCount());
                    isBurning = true;
                    return true;
                }
            }
        }
        return false;
    }

    private void processFinish()
    {
        flag = false;
        flag5 = false;

        ItemStack tempStack = recipe.outputItemStack.getOutputStack(0);
        ItemStack fromRecipe_1 = tempStack.copy();

        if (!(recipe == null))
        {
            if (flag4)
            {
                if (!outputsHandler.getStackInSlot(recordSlot).isEmpty())
                {
                    outputsHandler.getStackInSlot(recordSlot).grow(fromRecipe_1.getCount());
                }
                if (outputsHandler.getStackInSlot(recordSlot).isEmpty())
                {
                    outputsHandler.insertItem(recordSlot, fromRecipe_1, false);
                }
            }
        }
        burnTime = 0;
        flag3 = false;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if (CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.equals(capability))
        {
            if (facing == EnumFacing.DOWN) return (T) outputsHandler;
            if (facing == EnumFacing.EAST) return (T) fuelHandler;
            else return (T) inputsHandler;
        }
        return super.getCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.inputsHandler.deserializeNBT(compound.getCompoundTag("item_input"));
        this.outputsHandler.deserializeNBT(compound.getCompoundTag("item_output"));
        this.fuelHandler.deserializeNBT(compound.getCompoundTag("fuel"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setTag("item_input", this.inputsHandler.serializeNBT());
        compound.setTag("item_output", this.outputsHandler.serializeNBT());
        compound.setTag("fuel", this.fuelHandler.serializeNBT());
        return compound;
    }


}
