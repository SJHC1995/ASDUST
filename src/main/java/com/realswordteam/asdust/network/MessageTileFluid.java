/**
 * waits for future
 */
//package com.realswordteam.asdust.network;
//
//import com.google.common.collect.BiMap;
//import com.google.common.collect.HashBiMap;
//import com.google.common.collect.Sets;
//import io.netty.buffer.ByteBuf;
//import net.minecraft.client.Minecraft;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.fluids.Fluid;
//import net.minecraftforge.fluids.FluidRegistry;
//import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
//import net.minecraftforge.fluids.capability.IFluidHandler;
//import net.minecraftforge.fml.common.FMLLog;
//import net.minecraftforge.fml.common.network.ByteBufUtils;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
//import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
//import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
//import net.minecraftforge.fml.relauncher.Side;
//
//import java.util.Map;
//import java.util.Set;
//
//public class MessageTileFluid extends IMessage{
//    public BlockPos pos;
//    BiMap<Fluid, Integer> fluidIds = HashBiMap.create();
//    Set<String> defaultFluids = Sets.newHashSet();
//    public MessageTileFluid(TileEntity te) {
//            this.pos = te.getPos();
//    }
//
//    public MessageTileFluid() {
//    }
//
//    @Override
//    public void toBytes(ByteBuf buf) {
//        buf.writeInt(this.pos.getX());
//        buf.writeInt(this.pos.getY());
//        buf.writeInt(this.pos.getZ());
//
//        Map<Fluid, Integer> ids = FluidRegistry.getRegisteredFluidIDs();
////        写入液体种类数量
//        buf.writeInt(ids.size());
//        for (Map.Entry<Fluid, Integer> entry : ids.entrySet())
//        {
//            ByteBufUtils.writeUTF8String(buf,entry.getKey().getName());
////            写入液体对应的id
//            buf.writeInt(entry.getValue());
//        }
//        for (Map.Entry<Fluid, Integer> entry : ids.entrySet())
//        {
//            String defaultName = FluidRegistry.getDefaultFluidName(entry.getKey());
////            写入液体对应的默认名称
//            ByteBufUtils.writeUTF8String(buf, defaultName);
//        }
//    }
//
//    @Override
//    public void fromBytes(ByteBuf buf) {
//        this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
//
////        读入液体种类数量
//        int listSize = buf.readInt();
//        for (int i = 0; i < listSize; i++) {
////            读入液体名称id
//            String fluidName = ByteBufUtils.readUTF8String(buf);
////            读入液体id
//            int fluidId = buf.readInt();
//            fluidIds.put(FluidRegistry.getFluid(fluidName), fluidId);
//        }
//        // do we have a defaults list?
//
//        if (buf.isReadable())
//        {
//            for (int i = 0; i < listSize; i++)
//            {
//                defaultFluids.add(ByteBufUtils.readUTF8String(buf));
//            }
//        }
//        else
//        {
//            FMLLog.log.info("Legacy server message contains no default fluid list - there may be problems with fluids");
//            defaultFluids.clear();
//        }
//    }
//
//
//    public static class Handler implements IMessageHandler<MessageTileFluid, IMessage>
//    {
//        @Override
//        public IMessage onMessage(MessageTileFluid message, MessageContext ctx) {
//            if (ctx.side == Side.CLIENT)
//            {
//                World world = Minecraft.getMinecraft().world;
//                TileEntity te = world.getTileEntity(message.pos);
//                if (te.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null))
//                {
//                    IFluidHandler iFluidHandler = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, null);
//                }
//            }
//            return null;
//        }
//    }
//}
