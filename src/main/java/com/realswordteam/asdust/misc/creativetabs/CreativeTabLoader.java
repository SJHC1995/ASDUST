package com.realswordteam.asdust.misc.creativetabs;

import net.minecraft.creativetab.CreativeTabs;

public class CreativeTabLoader {
    public static CreativeTabsadmain tabsadmain;
    public static CreativeTabs tabadmain;
    public static CreativeTabschemicalelement tabadce;

    public static CreativeTabs tabsmachine;

    public  CreativeTabLoader() {
        tabsadmain = new CreativeTabsadmain();
        tabadce = new CreativeTabschemicalelement();
        tabsmachine = new CreativeTabsmachine();
    }


}
