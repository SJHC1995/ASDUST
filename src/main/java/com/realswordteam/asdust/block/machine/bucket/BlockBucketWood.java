package com.realswordteam.asdust.block.machine.bucket;

import com.realswordteam.asdust.block.machine.BlockBaseBucket;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBucketWood extends BlockBaseBucket {
    public BlockBucketWood()
    {
        super(Material.WOOD);}
@Nullable
@Override
public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityWoodenBucket();
}
}
