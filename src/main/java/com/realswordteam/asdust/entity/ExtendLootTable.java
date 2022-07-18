package com.realswordteam.asdust.entity;

import com.realswordteam.asdust.recipes.ChangeItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtendLootTable {

    public ResourceLocation resourceLocation;
    public List<ChangeItemStack> list;

    public static List<ExtendLootTable> extendLootTables = new ArrayList<>();

    public static void spawnEntityItem(World world, EntityLivingBase entity)
    {
        List<ChangeItemStack> list = getChangeItemsFromEntity(entity);
        for (ChangeItemStack changeItemStack : list)
        {
            world.spawnEntity(new EntityItem(world, entity.posX, entity.posY, entity.posZ, changeItemStack.spawnItemStack()));
        }
    }
    public static List<ResourceLocation> getResources()
    {
        List<ResourceLocation> resourceLocations = new ArrayList<>();
        for (ExtendLootTable lootTable : extendLootTables)
        {
            resourceLocations.add(lootTable.resourceLocation);
        }
        return resourceLocations;
    }

    public static boolean compareEntity(ResourceLocation resource)
    {
        for (ResourceLocation listName : getResources())
        {
            if (listName.equals(resource))
            {
                return true;
            }
        }
        return false;
    }

    public static List<ChangeItemStack> getChangeItemsFromEntity(EntityLivingBase entity)
    {
        for (ExtendLootTable extendLootTable : extendLootTables)
        {
            ResourceLocation getResource = extendLootTable.resourceLocation;
            ResourceLocation resource = EntityList.getKey(entity);
            if (getResource.equals(resource))
            {
                return extendLootTable.list;
            }
        }
        return null;
    }

    public static void addEntityLootTable(ExtendLootTable extendLootTable)
    {
        extendLootTables.add(extendLootTable);
    }

    public ExtendLootTable(ResourceLocation resourceLocation, ChangeItemStack... changeItemStacks) {
        this.resourceLocation = resourceLocation;
        this.list = Arrays.asList(changeItemStacks);
    }
}
