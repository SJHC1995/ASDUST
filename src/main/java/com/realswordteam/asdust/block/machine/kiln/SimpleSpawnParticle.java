package com.realswordteam.asdust.block.machine.kiln;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SimpleSpawnParticle {
    public void spawnSimpleParticle(EnumFacing facing, World world, BlockPos pos, double x, double y, double z)
    {

        double x1 = this.fastCalculateParticlesX(facing, x - pos.getX(), z - pos.getZ());
        double z1 = this.fastCalculateParticlesZ(facing, x - pos.getX(), z - pos.getZ());
        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x1 + pos.getX(), y, z1 + pos.getZ(), 0.0D, 0.0D, 0.0D);
    }

    private double fastCalculateParticlesX(EnumFacing facing, double x, double z)
    {
        switch (facing)
        {
            case WEST:
                return z;
            case EAST:
                return 1.0D - z;
            case NORTH:
                return x;
            case SOUTH:
                return 1.0D - x;
        }
        return x;
    }

    private double fastCalculateParticlesZ(EnumFacing facing, double x, double z)
    {
        switch (facing)
        {
            case WEST:
                return 1.0D -x;
            case EAST:
                return x;
            case NORTH:
                return z;
            case SOUTH:
                return 1.0D - z;
        }
        return z;
    }
}
