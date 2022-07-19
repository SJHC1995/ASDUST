package com.realswordteam.asdust.block.machine.kiln;

import com.realswordteam.asdust.block.BlockLoader;
import com.realswordteam.asdust.block.machine.SimpleUtil;
import com.realswordteam.asdust.block.machine.tileentity.TileEntityMachineBase;
import com.realswordteam.asdust.network.MessageKiln;
import com.realswordteam.asdust.network.NetWorkLoader;
import com.realswordteam.asdust.recipes.RecipesLoader;
import com.realswordteam.asdust.recipes.machine.Kiln;
import com.realswordteam.asdust.recipes.recipe.RecipeSimple;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Map;
import java.util.Random;

public class TileEntityKiln extends TileEntityMachineBase implements ITickable {
    protected ItemStackHandler inputsHandler = new ItemStackHandler(2);

    protected ItemStackHandler outputsHandler = new ItemStackHandler();

    protected ItemStackHandler fuelHandler = new ItemStackHandler();

    public Kiln kiln = RecipesLoader.kiln;

    private int kilnCookTime;
    private int kilnBurnTime;
    private int totalKilnBurnTime;

    private int slagNumber;


    public TileEntityKiln() {
        super();
        this.blockName = BlockLoader.COBBLESTONE_KILN.getLocalizedName();
    }

    public int getKilnCookTime()
    {
        return kilnCookTime;
    }

    public int getKilnBurnTime()
    {
        return this.kilnBurnTime;
    }

    public int getTotalKilnBurnTime()
    {
        return this.totalKilnBurnTime;
    }

    public int getSlagNumber()
    {
        return this.slagNumber;
    }

    public boolean shrinkSlagNumber()
    {
        if (slagNumber >= 1)
        {
            this.slagNumber--;
            return true;
        }
        return false;
    }

    private boolean isBurning()
    {
        return this.kilnBurnTime > 0;
    }

    @Override
    public void update() {
        if (isBurning())
        {
            --this.kilnBurnTime;
        }
        if (!world.isRemote)
        {
            if (canStart())
            {
                if (this.kilnBurnTime == 0)
                {
                    this.kilnCookTime = 0;
                }
                if (this.kilnBurnTime > 0)
                {
                    ++this.kilnCookTime;
                }
                if (this.kilnCookTime > 200)
                {
                    sendPacket();
                    processFinish();
                    this.markDirty();
                    this.kilnCookTime = 0;
                }
            }
        }
    }

    private boolean flag;
    private boolean flag1, flag2, flag3, flag4, flag5;

    private RecipeSimple recipe;

    private int recordSlot;

    private boolean canStart()
    {
        if (isBurning() && !isFilledWithSlag())
        {
            if (canSmelt())
            {
                return true;
            }
        }
        if (!isBurning() && !isFilledWithSlag())
        {
            if (canSmelt())
            {
                if (!fuelHandler.getStackInSlot(0).isEmpty())
                {
                    ItemStack containerFuel = fuelHandler.getStackInSlot(0);
                    Map<ItemStack, Integer> getFuels = RecipesLoader.kileFuel.getFuels();
                    for (Map.Entry<ItemStack, Integer> entry : getFuels.entrySet())
                    {
                        ItemStack tempStack = entry.getKey();
                        ItemStack getStack = tempStack.copy();
                        if (containerFuel.isItemEqual(getStack))
                        {
                            this.kilnBurnTime = entry.getValue();
                            this.totalKilnBurnTime = this.kilnBurnTime;
                            containerFuel.shrink(getStack.getCount());
                            randomSpawnSlag();
                            sendPacket();
                            break;
                        }
                    }
                }
            }
        }
        if (isFilledWithSlag())
        {
            this.kilnCookTime = 0;
        }
        return false;
    }

    private boolean isFilledWithSlag()
    {
        return this.slagNumber == 10;
    }

    private boolean canSmelt()
    {
        if (!SimpleUtil.checkItemStackOfHandlerIsEmpty(inputsHandler) && !flag)
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
                            break;
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
                                    SimpleUtil.getItemStackFromHandler(num, inputsHandler).shrink(fromRecipe_1.getCount());
                                    SimpleUtil.getItemStackFromHandler(num1, inputsHandler).shrink(fromRecipe_2.getCount());
                                    this.recipe = recipe;
                                    flag = true;
                                    break;
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
                if (outputsHandler.getStackInSlot(num).isItemEqual(fromRecipe_1) && outputsHandler.getStackInSlot(num).getCount() < fromRecipe_1.getMaxStackSize()
                        || outputsHandler.getStackInSlot(num).isEmpty())
                {
                    recordSlot = num;
                    flag5 = true;
                }
            }
        }

        //third check
        if (flag && flag5)
        {
            return true;
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
        flag3 = false;
    }

    private void randomSpawnSlag()
    {
        Random random = new Random();
        int value = random.nextInt(2);
        if (this.slagNumber <= 9)
        {
            this.slagNumber = value + this.slagNumber;
        }
    }

    public void sendPacket()
    {
        int dimension = world.provider.getDimension();
        double x = getPos().getX();
        double y = getPos().getY();
        double z = getPos().getZ();
        NetWorkLoader.instance.sendToAllAround(new MessageKiln(this), new NetworkRegistry.TargetPoint(dimension, x, y, z, 128D));
    }

    //
    public void receiveMessageFromServer(int slagNumber)
    {
        this.slagNumber = slagNumber;
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

        this.kilnCookTime = compound.getInteger("cookTime");
        this.kilnBurnTime = compound.getInteger("burnTime");
        this.totalKilnBurnTime = compound.getInteger("totalBurnTime");

        this.slagNumber = compound.getInteger("slagNumber");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setTag("item_input", this.inputsHandler.serializeNBT());
        compound.setTag("item_output", this.outputsHandler.serializeNBT());
        compound.setTag("fuel", this.fuelHandler.serializeNBT());

        compound.setInteger("cookTime", this.kilnCookTime);
        compound.setInteger("burnTime", this.kilnBurnTime);
        compound.setInteger("totalBurnTime", this.totalKilnBurnTime);

        compound.setInteger("slagNumber", this.slagNumber);
        return compound;
    }


}
