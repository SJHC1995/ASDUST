package com.realswordteam.asdust.modules.geckolib;

import com.realswordteam.asdust.block.machine.comminutor.TileComminutor;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class ComminutorRender extends GeoBlockRenderer<TileComminutor> {
    public ComminutorRender() {
        super(new ComminutorModel());
    }
}
