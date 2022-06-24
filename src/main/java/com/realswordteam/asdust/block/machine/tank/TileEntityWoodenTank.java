package com.realswordteam.asdust.block.machine.tank;

import com.realswordteam.asdust.block.BlockLoader;

public class TileEntityWoodenTank extends TileEntityBaseTank {
    public TileEntityWoodenTank()
    {
        this.tank.setCapacity(2000);
        this.blockName = BlockLoader.TANK_WOOD_LEATHER.getLocalizedName();
    }
}
