package com.realswordteam.asdust.gui.container;

import com.realswordteam.asdust.block.machine.kiln.TileEntityKiln;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerKiln extends ContainerBase {

    private IItemHandler kilnInput;

    private IItemHandler kilnOutput;

    private IItemHandler fuelInput;

    public TileEntity te;

    public ContainerKiln(TileEntityKiln te, EntityPlayer player)
    {
        this.te = te;

        this.kilnInput = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
        this.kilnOutput = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
        this.fuelInput = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.EAST);

        super.addPlayerInventory(player, 8, 83);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return playerIn.getDistanceSq(this.te.getPos()) <= 64;
    }
}
