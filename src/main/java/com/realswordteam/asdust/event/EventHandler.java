package com.realswordteam.asdust.event;

import com.google.common.eventbus.EventBus;
import com.realswordteam.asdust.entity.ExtendLootTable;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class EventHandler {

    public EventHandler()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }


    @SubscribeEvent
    public void extendEntityItemFromEntity(LivingDropsEvent event)
    {
        EntityLivingBase entity = event.getEntityLiving();
        World world = entity.getEntityWorld();
        ResourceLocation resourceLocation = EntityList.getKey(entity);
        if (ExtendLootTable.compareEntity(resourceLocation))
        {
            ExtendLootTable.spawnEntityItem(world, entity);
        }
    }

}
