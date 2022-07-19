package com.realswordteam.asdust.block.machine.kiln;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.gui.GuiElementLoader;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemShears;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockCobbleStoneKiln extends BlockSimpleKiln{
    public BlockCobbleStoneKiln()
    {
        super(Material.ROCK);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(@Nullable World world, int meta) {
        return new TileEntityKiln();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote)
        {
            TileEntityKiln te = (TileEntityKiln) worldIn.getTileEntity(pos);
            ItemStack handStack = playerIn.getHeldItemMainhand();
            if (handStack.getItem().equals(Items.STICK))
            {
                te.shrinkSlagNumber();
                te.sendPacket();
                handStack.damageItem(1, playerIn);
            }   else
            {
                playerIn.openGui(ASDUST.instance, GuiElementLoader.GUI_KILN, worldIn, pos.getX(), pos.getY(), pos.getZ());
            }
            return true;
        }
        return false;
    }


}
