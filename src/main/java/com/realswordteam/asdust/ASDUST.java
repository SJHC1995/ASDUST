package com.realswordteam.asdust;

import com.realswordteam.asdust.block.tileentity.TileEntityLoader;
import com.realswordteam.asdust.gui.GuiElementLoader;
import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import com.realswordteam.asdust.misc.fluidmanager.FluidManager;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = ASDUST.MODID, name = ASDUST.NAME, version = ASDUST.VERSION)
public class ASDUST
{
    public static final String MODID = "asdust";
    public static final String NAME = "ASDUST";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @Mod.Instance(ASDUST.MODID)
    public static ASDUST instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        new CreativeTabLoader();
        new TileEntityLoader(event);
        new FluidManager();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());

        new GuiElementLoader();
    }
}
