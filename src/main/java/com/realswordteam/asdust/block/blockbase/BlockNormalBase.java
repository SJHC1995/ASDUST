package com.realswordteam.asdust.block.blockbase;

import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockNormalBase extends Block {
    public BlockNormalBase(){
        super(Material.ROCK);
        this.setCreativeTab(CreativeTabLoader.TAB_BASE_BLOCK);

    }
}
