package com.realswordteam.asdust.items;

import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemFluidContainer extends Item {
    private int capacity;
    public ItemFluidContainer(int capacity)
    {
        this.capacity = capacity;
        this.setCreativeTab(CreativeTabLoader.TAB_MISC);
    }

    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt)
    {
        return new SpecificKindFluidItemStack(stack, capacity);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        if (stack.hasTagCompound())
        {
            NBTTagCompound nbttagcompound = stack.getTagCompound();
            NBTTagCompound fluidTag = nbttagcompound.getCompoundTag("Fluid");
            String fluidName = fluidTag.getString("FluidName");
            int amount = fluidTag.getInteger("Amount");

            if (amount != 0)
            {
                tooltip.add(TextFormatting.GRAY + net.minecraft.client.resources.I18n.format("fluid_container.information", fluidName, amount));
            }
        }
    }
}
