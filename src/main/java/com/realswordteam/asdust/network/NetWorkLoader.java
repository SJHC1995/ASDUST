/**
 * waits for future
 */
//package com.realswordteam.asdust.network;
//
//import com.realswordteam.asdust.ASDUST;
//import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
//import net.minecraftforge.fml.common.network.NetworkRegistry;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
//import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
//import net.minecraftforge.fml.relauncher.Side;
//
//public class NetWorkLoader {
//    public static SimpleNetworkWrapper instance = NetworkRegistry.INSTANCE.newSimpleChannel(ASDUST.MODID);
//    private static int nextID = 0;
//
//    public NetWorkLoader(FMLPreInitializationEvent event)
//    {
//        registerMessage(MessageTileFluid.Handler.class, MessageTileFluid.class, Side.CLIENT);
//    }
//
//    private static <REQ extends IMessage, REPLY extends IMessage> void registerMessage(
//            Class<? extends IMessageHandler<REQ, REPLY>> messageHandler, Class<REQ> requestMessageType, Side side)
//    {
//        instance.registerMessage(messageHandler, requestMessageType, nextID++, side);
//    }
//}
