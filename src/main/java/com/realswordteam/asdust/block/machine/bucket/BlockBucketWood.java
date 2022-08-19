package com.realswordteam.asdust.block.machine.bucket;

import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockBucketWood extends BlockBaseBucket {
    public BlockBucketWood()
    {
        super(Material.WOOD);
        setCreativeTab(CreativeTabLoader.TAB_ASDUST_MACHINE);
    }
    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
    return new TileEntityWoodenBucket();
}
}
