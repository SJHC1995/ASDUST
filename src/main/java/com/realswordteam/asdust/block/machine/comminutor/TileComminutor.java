package com.realswordteam.asdust.block.machine.comminutor;

import net.minecraft.tileentity.TileEntity;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class TileComminutor extends TileEntity implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);

    @SuppressWarnings("unchecked")
    private <E extends TileEntity & IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().transitionLengthTicks = 0;
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.comminutor.comminutor_run", true));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(
                new AnimationController<TileComminutor>(this, "controller", 0, this::predicate)
        );
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
