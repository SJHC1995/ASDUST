package com.realswordteam.asdust.network;

import com.realswordteam.asdust.block.machine.kiln.TileEntityKiln;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageKiln implements IMessage{
        public BlockPos pos;

        public int slagNumber;

        public MessageKiln(TileEntityKiln te) {
            this.pos = te.getPos();
            this.slagNumber = te.getSlagNumber();
        }
        public MessageKiln() {
        }

        @Override
        public void toBytes(ByteBuf buf) {
            buf.writeInt(this.pos.getX());
            buf.writeInt(this.pos.getY());
            buf.writeInt(this.pos.getZ());
            buf.writeInt(this.slagNumber);
//            ByteBufUtils.writeItemStack(buf, this.itemStack);
        }

        @Override
        public void fromBytes(ByteBuf buf) {
            this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
            this.slagNumber = buf.readInt();
//            this.itemStack = ByteBufUtils.readItemStack(buf);
        }

        public static class Handler implements IMessageHandler<MessageKiln, IMessage>
        {
            @Override
            public IMessage onMessage(MessageKiln message, MessageContext ctx) {
                if (ctx.side == Side.CLIENT)
                {
                    World world = Minecraft.getMinecraft().world;
                    if (message.pos != null)
                    {
                        TileEntity tileEntity = world.getTileEntity(message.pos);
                        if (tileEntity instanceof TileEntityKiln)
                        {
                            ((TileEntityKiln) tileEntity).receiveMessageFromServer(message.slagNumber);
                        }
                    }
                }
                return null;
            }
        }

}
