package com.realswordteam.asdust.block.blockbase;

import com.realswordteam.asdust.block.ore.OreBlockLoader;

import com.realswordteam.asdust.items.ItemLoader;
import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.Random;

public class BlockOreBase extends Block {

    public BlockOreBase() {
        super(Material.ROCK);
        this.setCreativeTab(CreativeTabLoader.TAB_ORE);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (this == OreBlockLoader.CASSITERITE) {
            return ItemLoader.ORE_CASSITERITE;
        } else if
        (this == OreBlockLoader.MALACHITE) {
            return ItemLoader.ORE_MALACHITE;
        } else if
        (this == OreBlockLoader.GALENA) {
            return ItemLoader.ORE_GALENA;
        } else

            return Items.COOKIE;

    }
}



