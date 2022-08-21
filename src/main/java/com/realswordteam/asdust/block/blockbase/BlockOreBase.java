package com.realswordteam.asdust.block.blockbase;

import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockOreBase extends Block {


    public BlockOreBase(){
        super(Material.ROCK);
        this.setCreativeTab(CreativeTabLoader.TAB_ORE);
            this.setHarvestLevel();
    }


}
