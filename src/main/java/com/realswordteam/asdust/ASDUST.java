package com.realswordteam.asdust;

import com.realswordteam.asdust.block.tileentity.TileEntityLoader;
import com.realswordteam.asdust.gui.GuiElementLoader;
import com.realswordteam.asdust.misc.creativetabs.CreativeTabLoader;
import com.realswordteam.asdust.misc.oredictionary.ModOreDictionary;
import com.realswordteam.asdust.network.NetWorkLoader;
import com.realswordteam.asdust.recipes.RecipesLoader;
import com.realswordteam.asdust.recipes.machine.RecipeCraft;
import net.minecraft.init.Blocks;
import net.minecraftforge.client.model.ModelFluid;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModIdMappingEvent;
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
        new TileEntityLoader();
        new CreativeTabLoader();
        new NetWorkLoader(event);
//        new FluidManager();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        new com.realswordteam.asdust.event.EventHandler();
        new GuiElementLoader();
        new RecipesLoader();
        new ModOreDictionary();
    }

    @EventHandler
    public void refresh(FMLModIdMappingEvent event) {
        RecipeCraft.refreshRecipeT();
    }
}
