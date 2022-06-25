package com.realswordteam.asdust.block.machine;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.machine.tank.TileEntityBaseTank;
import com.realswordteam.asdust.block.machine.tank.TileEntityCeramicTank;
import com.realswordteam.asdust.gui.GuiElementLoader;
import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class BlockBaseTank extends MachineBase {
    public BlockBaseTank(Material material)
    {
        super(GuiElementLoader.GUI_TANK, material);
        this.setCreativeTab(CreativeTabLoader.TAB_ASDUST_MACHINE);
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityCeramicTank();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (!worldIn.isRemote) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof TileEntityBaseTank) {

                IFluidHandler fluidHandler =((TileEntityBaseTank) tileEntity).getTank();

                if (!FluidUtil.interactWithFluidHandler(playerIn, hand, fluidHandler)) {
                    playerIn.openGui(ASDUST.instance, GuiElementLoader.GUI_TANK, worldIn, pos.getX(), pos.getY(), pos.getZ());
                }
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        TileEntity te = worldIn.getTileEntity(pos);

        if (te instanceof TileEntityBaseTank)
        {
            IItemHandler up = ((TileEntityBaseTank) te).getITEM_IN();
            IItemHandler down = ((TileEntityBaseTank) te).getITEM_OUT();
            Block.spawnAsEntity(worldIn, pos, up.getStackInSlot(0));
            Block.spawnAsEntity(worldIn, pos, down.getStackInSlot(0));
            super.breakBlock(worldIn, pos, state);
        }
    }
}
