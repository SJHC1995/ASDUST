package com.realswordteam.asdust.gui;

import com.realswordteam.asdust.ASDUST;
import com.realswordteam.asdust.gui.container.ContainerTest;
import com.realswordteam.asdust.gui.guicontainer.GuiContainerTest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiElementLoader implements IGuiHandler {

    public static final int GUI_DEBUG = 2;


    public GuiElementLoader(){
        NetworkRegistry.INSTANCE.registerGuiHandler(ASDUST.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
            case GUI_DEBUG:
                return new ContainerTest(player, world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (ID)
        {
//            case GUI_DEMO:
//                return new GuiContainerDemo(new ContainerDemo(player));
            case GUI_DEBUG:
                return new GuiContainerTest(new ContainerTest(player, world.getTileEntity(new BlockPos(x, y, z))));
            default:
                return null;
        }
    }
}
