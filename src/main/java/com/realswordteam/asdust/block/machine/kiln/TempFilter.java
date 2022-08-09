//package com.realswordteam.asdust.block.machine.kiln;
//
//import com.realswordteam.asdust.ASDUST;
//import com.realswordteam.asdust.block.machine.MachineBase;
//import com.realswordteam.asdust.gui.GuiElementLoader;
//import net.minecraft.block.material.Material;
//import net.minecraft.block.properties.PropertyBool;
//import net.minecraft.block.properties.PropertyDirection;
//import net.minecraft.block.state.BlockStateContainer;
//import net.minecraft.block.state.IBlockState;
//import net.minecraft.entity.EntityLivingBase;
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.init.SoundEvents;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.EnumFacing;
//import net.minecraft.util.EnumHand;
//import net.minecraft.util.EnumParticleTypes;
//import net.minecraft.util.SoundCategory;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import net.minecraftforge.fml.relauncher.Side;
//import net.minecraftforge.fml.relauncher.SideOnly;
//
//import java.util.Random;
//
//public class TempFilter {
//}
//package com.realswordteam.asdust.block.machine.kiln;
//
//        import com.realswordteam.asdust.ASDUST;
//        import com.realswordteam.asdust.block.machine.MachineBase;
//        import com.realswordteam.asdust.gui.GuiElementLoader;
//        import com.realswordteam.asdust.recipes.machine.RecipeCraft;
//        import net.minecraft.block.material.Material;
//        import net.minecraft.block.properties.PropertyBool;
//        import net.minecraft.block.properties.PropertyDirection;
//        import net.minecraft.block.state.BlockStateContainer;
//        import net.minecraft.block.state.IBlockState;
//        import net.minecraft.entity.EntityLivingBase;
//        import net.minecraft.entity.player.EntityPlayer;
//        import net.minecraft.init.SoundEvents;
//        import net.minecraft.item.ItemStack;
//        import net.minecraft.util.EnumFacing;
//        import net.minecraft.util.EnumHand;
//        import net.minecraft.util.EnumParticleTypes;
//        import net.minecraft.util.SoundCategory;
//        import net.minecraft.util.math.BlockPos;
//        import net.minecraft.world.World;
//        import net.minecraftforge.fml.relauncher.Side;
//        import net.minecraftforge.fml.relauncher.SideOnly;
//
//        import java.util.Map;
//        import java.util.Random;
//
//public class BlockSimpleKiln extends MachineBase {
//    public static boolean isWorking;
//    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
//    public static final PropertyBool WORKING = PropertyBool.create("working");
//    public BlockSimpleKiln(Material material)
//    {
//        super(2, material);
//        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(WORKING, false));
//    }
//
//
//
//
//    @Override
//    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
//    {
//        if (!worldIn.isRemote)
//        {
//            playerIn.openGui(ASDUST.instance, GuiElementLoader.GUI_KILN, worldIn, pos.getX(), pos.getY(), pos.getZ());
//            return true;
////            if (!state.getValue(WORKING))
////            {
////                worldIn.setBlockState(pos, state.withProperty(WORKING, true), 2);
////                return true;
////            }
////            else if (state.getValue(WORKING))
////            {
////                worldIn.setBlockState(pos, state.withProperty(WORKING, false), 2);
////                return true;
////            }
//
//        }
//        return false;
//    }
//
//    @Override
//    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
//    {
//        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
//    }
//
//    @Override
//    protected BlockStateContainer createBlockState()
//    {
//        return new BlockStateContainer(this, FACING, WORKING);
//    }
//
//    @Override
//    public IBlockState getStateFromMeta(int meta)
//    {
//        EnumFacing enumfacing = EnumFacing.getFront(meta);
//
//        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
//        {
//            enumfacing = EnumFacing.NORTH;
//        }
//
//        return this.getDefaultState().withProperty(FACING, enumfacing);
//    }
//
//    @Override
//    public int getMetaFromState(IBlockState state)
//    {
//        return (state.getValue(FACING)).getIndex();
//    }
//

//
//}
