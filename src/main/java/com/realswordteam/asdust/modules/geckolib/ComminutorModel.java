package com.realswordteam.asdust.modules.geckolib;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.block.machine.comminutor.TileComminutor;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.GeckoLib;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ComminutorModel extends AnimatedGeoModel<TileComminutor>
{

    @Override
    public ResourceLocation getAnimationFileLocation(TileComminutor animatable) {
        return new ResourceLocation(ASDUST.MODID, "animations/comminutor.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(TileComminutor object) {
        return new ResourceLocation(ASDUST.MODID, "geo/comminutor_model.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TileComminutor object) {
        return new ResourceLocation(ASDUST.MODID,"textures/blocks/block_furnace_brick.png");
    }
}
