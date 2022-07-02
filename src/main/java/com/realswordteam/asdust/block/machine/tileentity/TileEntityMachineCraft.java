package com.realswordteam.asdust.block.machine.tileentity;

import com.realswordteam.asdust.recipes.ChangeItemStack;
import com.realswordteam.asdust.recipes.input.InputItemStack;
import com.realswordteam.asdust.recipes.machine.RecipeCraft;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.state.IBlockState;
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

public class TileEntityMachineCraft extends TileEntity implements ITickable {
    public int burnTime;
    private int recordSlot,recordSlot2,recordSlot3,recordSlot4,recordSlot5,recordSlot6,recordSlot7;
    boolean flag = false, flag2 = false;
    boolean flag3 = false, flag4 = false;
    boolean flag5 = false, flag6 = false;
    boolean flag7 = false, flag8 = false;
    boolean flag9 = false, flag10 = false;
    boolean fluidFlag = false;
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
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
    {
        return oldState.getBlock() != newState.getBlock();
    }
    private boolean checkItemsIsEmptyIn_ITEM_IN()
    {
        for (int num = 0; num < ITEM_IN.getSlots(); num++)
        {
            if (!ITEM_IN.getStackInSlot(num).isEmpty())
            {
                return false;
            }
        }
        return true;
    }
    private ItemStack getItemStackFrom_ITEM_IN(int slotIndex)
    {
        return ITEM_IN.getStackInSlot(slotIndex);
    }
    //waiting for IMPROVING
    private boolean canStart()
    {
        if (!checkItemsIsEmptyIn_ITEM_IN() && !flag6)
        {
            for (RecipeCraft.RecipeT recipeT : RecipeCraft.getRecipeTList())
            {
                flag = recipeT.getInputItemStack().getSize() == 0;
                flag2 = recipeT.getInputItemStack().getSize() == 1;
                flag3 = recipeT.getInputItemStack().getSize() == 2;
                flag4 = recipeT.getInputItemStack().getSize() == 3;
                flag5 = recipeT.getInputItemStack().getSize() == 4;

                if (flag2)
                {
                    ItemStack fromRecipe = recipeT.getInputItemStack().getInput(0);
                    for (int num = 0; num < ITEM_IN.getSlots(); num++)
                    {
                        if (ITEM_IN.getStackInSlot(num).isItemEqual(fromRecipe))
                        {
                            ITEM_IN.getStackInSlot(num).shrink(fromRecipe.getCount());
                            this.recipeT = recipeT;
                            flag6 = true;
                        }
                    }
                }

                if (flag3)
                {
                    ItemStack fromRecipe_1 = recipeT.getInputItemStack().getInput(0);
                    ItemStack fromRecipe_2 = recipeT.getInputItemStack().getInput(1);
                    for (int num = 0; num < ITEM_IN.getSlots(); num++)
                    {
                        if (ITEM_IN.getStackInSlot(num).isItemEqual(fromRecipe_1))
                        {
                            for (int num1 = 0; num1 < ITEM_IN.getSlots(); num1++)
                            {
                                if (ITEM_IN.getStackInSlot(num1).isItemEqual(fromRecipe_2))
                                {
                                    getItemStackFrom_ITEM_IN(num).shrink(fromRecipe_1.getCount());
                                    getItemStackFrom_ITEM_IN(num1).shrink(fromRecipe_2.getCount());
                                    this.recipeT = recipeT;
                                    flag6 = true;
                                }
                            }
                        }
                    }
                }
                if (flag4)
                {
                    ItemStack fromRecipe_1 = recipeT.getInputItemStack().getInput(0);
                    ItemStack fromRecipe_2 = recipeT.getInputItemStack().getInput(1);
                    ItemStack fromRecipe_3 = recipeT.getInputItemStack().getInput(2);
                    for (int num = 0; num < ITEM_IN.getSlots(); num++)
                    {
                        if (ITEM_IN.getStackInSlot(num).isItemEqual(fromRecipe_1))
                        {
                            for (int num1 = 0; num1 < ITEM_IN.getSlots(); num1++)
                            {
                                if (ITEM_IN.getStackInSlot(num1).isItemEqual(fromRecipe_2))
                                {
                                    for (int num2 = 0; num2 < ITEM_IN.getSlots(); num2++)
                                    {
                                        if (ITEM_IN.getStackInSlot(num2).isItemEqual(fromRecipe_3))
                                        {
                                            getItemStackFrom_ITEM_IN(num).shrink(fromRecipe_1.getCount());
                                            getItemStackFrom_ITEM_IN(num1).shrink(fromRecipe_2.getCount());
                                            getItemStackFrom_ITEM_IN(num2).shrink(fromRecipe_3.getCount());
                                            this.recipeT = recipeT;
                                            flag6 = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                if (flag5)
                {
                    ItemStack fromRecipe_1 = recipeT.getInputItemStack().getInput(0);
                    ItemStack fromRecipe_2 = recipeT.getInputItemStack().getInput(1);
                    ItemStack fromRecipe_3 = recipeT.getInputItemStack().getInput(2);
                    ItemStack fromRecipe_4 = recipeT.getInputItemStack().getInput(3);
                    for (int num = 0; num < ITEM_IN.getSlots(); num++)
                    {
                        if (ITEM_IN.getStackInSlot(num).isItemEqual(fromRecipe_1))
                        {
                            for (int num1 = 0; num1 < ITEM_IN.getSlots(); num1++)
                            {
                                if (ITEM_IN.getStackInSlot(num1).isItemEqual(fromRecipe_2))
                                {
                                    for (int num2 = 0; num2 < ITEM_IN.getSlots(); num2++)
                                    {
                                        if (ITEM_IN.getStackInSlot(num2).isItemEqual(fromRecipe_3))
                                        {
                                            for (int num3 = 0; num3 < ITEM_IN.getSlots(); num3++)
                                            {
                                                if (ITEM_IN.getStackInSlot(num3).isItemEqual(fromRecipe_4))
                                                {
                                                    getItemStackFrom_ITEM_IN(num).shrink(fromRecipe_1.getCount());
                                                    getItemStackFrom_ITEM_IN(num1).shrink(fromRecipe_2.getCount());
                                                    getItemStackFrom_ITEM_IN(num2).shrink(fromRecipe_3.getCount());
                                                    getItemStackFrom_ITEM_IN(num3).shrink(fromRecipe_4.getCount());
                                                    this.recipeT = recipeT;
                                                    flag6 = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }



            }

        }

        if (!recipeT.isEmpty())
        {
            flag7 = recipeT.getOutputItemStack().getSize() == 1;
            flag8 = recipeT.getOutputItemStack().getSize() == 2;
            flag9 = recipeT.getOutputItemStack().getSize() == 3;
        }

        if (flag7)
        {
            ItemStack tempStack = recipeT.getOutputItemStack().getOutput(0);
            ItemStack fromRecipe_1 = tempStack.copy();
            for (int num = 0; num < ITEM_OUT.getSlots(); num++)
            {
                if (ITEM_OUT.getStackInSlot(num).isItemEqual(fromRecipe_1) || ITEM_OUT.getStackInSlot(num).isEmpty())
                {
                    recordSlot = num;
                    flag10 = true;
                }
            }
        }
        if (flag8) {
            ItemStack tempStack = recipeT.getOutputItemStack().getOutput(0);
            ItemStack fromRecipe_1 = tempStack.copy();
            ItemStack tempStack1 = recipeT.getOutputItemStack().getOutput(1);
            ItemStack fromRecipe_2 = tempStack1.copy();
            for (int num = 0; num < ITEM_OUT.getSlots(); num++) {
                if (ITEM_OUT.getStackInSlot(num).isItemEqual(fromRecipe_1) || ITEM_OUT.getStackInSlot(num).isEmpty()) {
                    for (int num1 = 0; num1 < ITEM_OUT.getSlots(); num1++) {
                        boolean temp = ITEM_OUT.getStackInSlot(num1).isItemEqual(fromRecipe_2) || ITEM_OUT.getStackInSlot(num1).isEmpty();
                        if (temp && num1 != num) {
                            recordSlot = num;
                            recordSlot2 = num1;
                            flag10 = true;
                        }
                    }
                }
            }
        }

        if (flag9) {
            ItemStack tempStack = recipeT.getOutputItemStack().getOutput(0);
            ItemStack fromRecipe_1 = tempStack.copy();
            ItemStack tempStack1 = recipeT.getOutputItemStack().getOutput(1);
            ItemStack fromRecipe_2 = tempStack1.copy();
            ItemStack tempStack2 = recipeT.getOutputItemStack().getOutput(2);
            ItemStack fromRecipe_3 = tempStack2.copy();
            for (int num = 0; num < ITEM_OUT.getSlots(); num++) {
                if (ITEM_OUT.getStackInSlot(num).isItemEqual(fromRecipe_1) || ITEM_OUT.getStackInSlot(num).isEmpty()) {
                    for (int num1 = 0; num1 < ITEM_OUT.getSlots(); num1++) {
                        boolean temp = ITEM_OUT.getStackInSlot(num1).isItemEqual(fromRecipe_2) || ITEM_OUT.getStackInSlot(num1).isEmpty();
                        if (temp && num1 != num) {
                            for (int num2 = 0; num2 < ITEM_OUT.getSlots(); num2++) {
                                boolean temp1 = ITEM_OUT.getStackInSlot(num2).isItemEqual(fromRecipe_3) || ITEM_OUT.getStackInSlot(num2).isEmpty();
                                if (temp1 && num2 != num1 && num2 != num) {
                                    recordSlot = num;
                                    recordSlot2 = num1;
                                    recordSlot3 = num2;
                                    flag10 = true;
                                }
                            }
                        }
                    }
                }
            }
        }


        if (!recipeT.isEmpty() && !fluidFlag)
        {
            if (recipeT.getInputFluidStack().getSize() != 0)
            {
                FluidStack tempFluidStack = recipeT.getInputFluidStack().getInput(0);
                FluidStack fluidStack = tempFluidStack.copy();
                FluidStack container = this.fluidTank.getFluid();
                if (fluidStack.amount == 0)
                {
                    fluidFlag = true;
                }
                boolean flag = fluidStack.amount == 0;
                if (!flag && fluidStack.isFluidEqual(container) && container.amount >= fluidStack.amount)
                {
                    this.fluidTank.drain(fluidStack.amount, true);
                    fluidFlag = true;
                }
            }   else
            {
                    fluidFlag = true;
            }
        }

        if (flag6 && flag10 && fluidFlag)
        {
            return true;
        }   else
        {
            return false;
        }
    }

    private void processFinish()
    {
        flag6 = false;
        flag10 = false;
        fluidFlag = false;
        this.burnTime = 0;

        ItemStack tempStack = recipeT.getOutputItemStack().getOutput(0);
        ItemStack fromRecipe_1 = tempStack.copy();
        ItemStack tempStack1 = recipeT.getOutputItemStack().getOutput(1);
        ItemStack fromRecipe_2 = tempStack1.copy();
        ItemStack tempStack2 = recipeT.getOutputItemStack().getOutput(2);
        ItemStack fromRecipe_3 = tempStack2.copy();

        if (!recipeT.isEmpty())
        {
            if (flag7)
            {
                if (!ITEM_OUT.getStackInSlot(recordSlot).isEmpty())
                {
                    ITEM_OUT.getStackInSlot(recordSlot).grow(fromRecipe_1.getCount());
                }
                if (ITEM_OUT.getStackInSlot(recordSlot).isEmpty())
                {
                    ITEM_OUT.insertItem(recordSlot, fromRecipe_1, false);
                }
            }
            if (flag8)
            {
                if (!ITEM_OUT.getStackInSlot(recordSlot).isEmpty())
                {
                    ITEM_OUT.getStackInSlot(recordSlot).grow(fromRecipe_1.getCount());
                }
                if (ITEM_OUT.getStackInSlot(recordSlot).isEmpty())
                {
                    ITEM_OUT.insertItem(recordSlot, fromRecipe_1, false);
                }
                if (!ITEM_OUT.getStackInSlot(recordSlot2).isEmpty())
                {
                    ITEM_OUT.getStackInSlot(recordSlot2).grow(fromRecipe_2.getCount());
                }
                if (ITEM_OUT.getStackInSlot(recordSlot2).isEmpty())
                {
                    ITEM_OUT.insertItem(recordSlot2, fromRecipe_2, false);
                }
            }
            if (flag9)
            {
                if (!ITEM_OUT.getStackInSlot(recordSlot).isEmpty())
                {
                    ITEM_OUT.getStackInSlot(recordSlot).grow(fromRecipe_1.getCount());
                }
                if (ITEM_OUT.getStackInSlot(recordSlot).isEmpty())
                {
                    ITEM_OUT.insertItem(recordSlot, fromRecipe_1, false);
                }
                if (!ITEM_OUT.getStackInSlot(recordSlot2).isEmpty())
                {
                    ITEM_OUT.getStackInSlot(recordSlot2).grow(fromRecipe_2.getCount());
                }
                if (ITEM_OUT.getStackInSlot(recordSlot2).isEmpty())
                {
                    ITEM_OUT.insertItem(recordSlot2, fromRecipe_2, false);
                }
                if (!ITEM_OUT.getStackInSlot(recordSlot3).isEmpty())
                {
                    ITEM_OUT.getStackInSlot(recordSlot3).grow(fromRecipe_3.getCount());
                }
                if (ITEM_OUT.getStackInSlot(recordSlot3).isEmpty())
                {
                    ITEM_OUT.insertItem(recordSlot3, fromRecipe_3, false);
                }
            }
        }
//        if (!recipeT.isEmpty())
//        {
//            //Output
//            ItemStack outputRecipe = ITEM_OUT.getStackInSlot(recordSlot3);
//            ItemStack tempOutputRecipe = recipeT.getOutput();
//            ItemStack outputItem = tempOutputRecipe.copy();
//            if (outputRecipe.isEmpty())
//            {
//                ITEM_OUT.insertItem(recordSlot3, outputItem, false);
//            }   else
//            {
//                outputRecipe.grow(outputItem.getCount());
//            }
//
//            //ByProduction
//            ItemStack byProductionRecipe = ITEM_OUT.getStackInSlot(recordSlot4);
//            ChangeItemStack tempByProductionRecipe = recipeT.getByProduction();
//            ItemStack tempByProductionRecipeItem = tempByProductionRecipe.getItemStack();
//            ItemStack byProductionItem = tempByProductionRecipeItem.copy();
//            int change = recipeT.getByProduction().getChange();
//            Random random = new Random();
//            int randomValue = random.nextInt(100);
//            boolean flag = randomValue < change;
//            if (byProductionRecipe.isEmpty() && flag)
//            {
//                ITEM_OUT.insertItem(recordSlot4, byProductionItem, false);
//            }   else if (flag)
//            {
//                byProductionRecipe.grow(byProductionItem.getCount());
//            }
//
//            //Core
//            ItemStack tempCoreRecipe = getInputFromRecipe(recipeT);
//            ItemStack coreItem = tempCoreRecipe.copy();
//            if (!coreItem.isEmpty())
//            {
//                ITEM_IN.getStackInSlot(recordSlot).shrink(coreItem.getCount());
//            }
//
//            //Input
//            ItemStack tempInputRecipe = recipeT.getInput();
//            ItemStack inputItem = tempInputRecipe.copy();
//            if (!inputItem.isEmpty())
//            {
//                ITEM_IN.getStackInSlot(recordSlot2).shrink(inputItem.getCount());
//            }
//
//            //Fluid
//            FluidStack recipeFluid = recipeT.getFluidStack();
//            if (recipeFluid != null)
//            {
//                this.fluidTank.drain(recipeT.getFluidStack().amount, true);
//            }
//
//        }
//        this.burnTime = 0;

    }

}
