package com.realswordteam.asdust.items.itembase;

import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMiscBase extends Item {
    public ItemMiscBase(){
        super();
        this.setCreativeTab(CreativeTabLoader.TAB_MISC);
    }


}
