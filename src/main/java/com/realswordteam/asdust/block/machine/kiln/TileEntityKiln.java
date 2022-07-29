package com.realswordteam.asdust.block.machine.kiln;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.BlockLoader;
import com.realswordteam.asdust.block.machine.SimpleUtil;
import com.realswordteam.asdust.block.machine.tileentity.TileEntityMachineBase;
import com.realswordteam.asdust.gui.GuiElementLoader;
import com.realswordteam.asdust.modules.PyrotechMod;
import com.realswordteam.asdust.network.MessageKiln;
import com.realswordteam.asdust.network.NetWorkLoader;
import com.realswordteam.asdust.recipes.RecipesLoader;
import com.realswordteam.asdust.recipes.machine.Kiln;
import com.realswordteam.asdust.recipes.recipe.RecipeSimple;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import java.util.Map;
import java.util.Random;

import static com.realswordteam.asdust.block.machine.kiln.BlockSimpleKiln.FACING;

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

    public void onBlockActivatedInKiln(World world, EntityPlayer player, BlockPos pos)
    {
        if (player.getHeldItemMainhand().getItem().equals(Items.STICK))
        {
            clearSlag(player.getHeldItemMainhand(), player, pos);
        }   else
        {
            player.openGui(ASDUST.instance, GuiElementLoader.GUI_KILN, world, pos.getX(), pos.getY(), pos.getZ());
        }
    }

    public boolean clearSlag(ItemStack handStack, EntityPlayer player, BlockPos pos)
    {
        if (slagNumber >= 1)
        {
            handStack.damageItem(3, player);

            EntityItem entitySlag = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(PyrotechMod.SLAG));
            world.spawnEntity(entitySlag);
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
        if (!isBurning()&&!canSmeltFuel(false))
        {
            this.kilnCookTime=0;
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
    public void spawnParticlesInWorking(World worldIn, IBlockState stateIn, Random rand, BlockPos pos)
    {
        if (isBurning())
        {
            this.spawnParticles(worldIn, stateIn, rand, pos);
        }
    }

    private void spawnParticles(World worldIn, IBlockState stateIn, Random rand, BlockPos pos)
    {
        SimpleSpawnParticle ssp = new SimpleSpawnParticle();

        EnumFacing enumfacing = stateIn.getValue(FACING);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = (double)pos.getZ() + 0.5D;
        double d3 = 0.52D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;
        double d5 = pos.getY() + 1.0D;
        double d6 = pos.getX() + 0.1875D;
        double d7 = pos.getZ();

        if (rand.nextDouble() < 0.1D)
        {
            worldIn.playSound((double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
        }

        int randNum = rand.nextInt(10), randNum2 = rand.nextInt(10), randNum3 = rand.nextInt(10),
                randNum4 = rand.nextInt(10), randNum5 = rand.nextInt(10), randNum6 = rand.nextInt(10), randNum7 = rand.nextInt(10);
        if (randNum < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6, d5, d7 + 0.1875D);
        }
        if (randNum2 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6, d5, d7 + 0.4375D);
        }
        if (randNum3 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6, d5, d7 + 0.6875D);
        }
        if (randNum4 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.3125D, d5, d7 + 0.4375D);
        }
        if (randNum5 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.625D, d5, d7 + 0.1875D);
        }
        if (randNum6 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.625D, d5, d7 + 0.4375D);
        }
        if (randNum7 < 3)
        {
            ssp.spawnSimpleParticle(enumfacing, worldIn, pos, d6 + 0.625D, d5, d7 + 0.6875D);
        }

        switch (enumfacing)
        {
            case WEST:
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 - 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                break;
            case EAST:
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + 0.52D, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                break;
            case NORTH:
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + d4, d1, d2 - 0.52D, 0.0D, 0.0D, 0.0D);
                break;
            case SOUTH:
                worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, d0 + d4, d1, d2 + 0.52D, 0.0D, 0.0D, 0.0D);
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
            if (canSmelt(true))
            {
                return true;
            }
        }
        if (!isBurning() && !isFilledWithSlag())
        {
            if (canSmelt(false))
            {
                canSmeltFuel(true);
            }
        }
        if (isFilledWithSlag())
        {
            this.kilnCookTime = 0;
        }
        return false;
    }

    private boolean canSmeltFuel(boolean canConsume)
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
                    if (canConsume){
                        this.kilnBurnTime = entry.getValue();
                        this.totalKilnBurnTime = this.kilnBurnTime;
                        containerFuel.shrink(getStack.getCount());
                        randomSpawnSlag();
                        sendPacket();
                        return true;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isFilledWithSlag()
    {
        return this.slagNumber == 10;
    }

    private boolean canSmelt(boolean canConsume)
    {
        boolean anotherWay = false, anotherWay2 = false;
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
                            if (canConsume){
                                inputsHandler.getStackInSlot(num).shrink(fromRecipe.getCount());
                                this.recipe = recipe;
                                flag = true;
                                break;
                            }
                            anotherWay = true;
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
                                    if (canConsume){
                                        SimpleUtil.getItemStackFromHandler(num, inputsHandler).shrink(fromRecipe_1.getCount());
                                        SimpleUtil.getItemStackFromHandler(num1, inputsHandler).shrink(fromRecipe_2.getCount());
                                        this.recipe = recipe;
                                        flag = true;
                                        break;
                                    }
                                    anotherWay2 = true;
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
        if (anotherWay || anotherWay2)
        {
            return true;
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
