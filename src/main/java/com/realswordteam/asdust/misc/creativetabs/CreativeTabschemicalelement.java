package com.realswordteam.asdust.misc.creativetabs;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.items.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class CreativeTabschemicalelement extends CreativeTabs {
    public CreativeTabschemicalelement() { super(ASDUST.MODID);}
    @SideOnly(Side.CLIENT)

    @Nonnull
    @Override
    public ItemStack getTabIconItem() {return new ItemStack(ItemLoader.adce);}

    @SideOnly(Side.CLIENT)
    @Nonnull
    @Override
    public String getTranslatedTabLabel()
    {
       return "asdust ce";
    }
}



