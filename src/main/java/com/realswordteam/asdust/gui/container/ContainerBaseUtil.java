//package com.realswordteam.asdust.gui.container;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.inventory.Slot;
//
//public class ContainerBaseUtil {
//    public void addPlayerInventorySlot(Container container, EntityPlayer player)
//    {
//        for (int i = 0; i < 3; ++i)
//        {
//            for (int j = 0; j < 9; ++j)
//            {
//                container.addSlotToContainer(new Slot(player.inventory, j + i * 9 + 9, 6 + j * 18, 87 + i * 18));
//            }
//        }
//
//        for (int i = 0; i < 9; ++i)
//        {
//            container.addSlotToContainer(new Slot(player.inventory, i,  6 + i * 18, 145));
//        }
//    }
//}
