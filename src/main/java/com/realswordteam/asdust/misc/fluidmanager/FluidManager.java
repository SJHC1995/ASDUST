/**
 * waits for future
 */
package com.realswordteam.asdust.misc.fluidmanager;

import net.minecraftforge.fluids.FluidRegistry;

public class FluidManager {
   public FluidManager()
    {printFluidId();   }
    public static void printFluidId()
   {
       System.out.println(FluidRegistry.getRegisteredFluids());
   }
}
