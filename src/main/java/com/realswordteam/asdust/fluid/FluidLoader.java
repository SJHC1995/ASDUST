package com.realswordteam.asdust.fluid;

import com.realswordteam.asdust.ASDUST;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;

public class FluidLoader {

    public static Fluid PAINT_OIL = null;

    public static Fluid[] fluids = {
            PAINT_OIL = setFluidName("plant_oil")
    };

    public FluidLoader()
    {
        for (Fluid fluid : fluids)
        {
            FluidRegistry.registerFluid(fluid);
            FluidRegistry.addBucketForFluid(fluid);
        }
    }

    @SubscribeEvent
    public static void regFluidSpirit(TextureStitchEvent.Pre event) {
        TextureMap textureMap = event.getMap();
        for (Fluid fluid : fluids)
        {
            textureMap.registerSprite(fluid.getFlowing());
            textureMap.registerSprite(fluid.getStill());
        }
    }

    private static Fluid setFluidName(String name){
        return new Fluid(ASDUST.MODID + "." + name,
                new ResourceLocation(ASDUST.MODID + ":" + "blocks/" + name + "_still"),
                new ResourceLocation(ASDUST.MODID + ":" + "blocks/" + name + "_flowing"));
    }
}
