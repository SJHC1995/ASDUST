package com.realswordteam.asdust.block.machine.tank;

import com.realswordteam.asdust.block.BlockLoader;

public class TileEntityCeramicTank extends TileEntityBaseTank {
    public TileEntityCeramicTank()
    {
        this.tank.setCapacity(8000);
        this.blockName = BlockLoader.TANK_MIXED_MORTAR_CERAMICS.getLocalizedName();
    }
}
