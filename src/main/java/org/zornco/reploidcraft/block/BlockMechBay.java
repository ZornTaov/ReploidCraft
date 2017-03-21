package org.zornco.reploidcraft.block;

import java.util.List;

import org.zornco.reploidcraft.ReploidCraft;
import org.zornco.reploidcraft.init.RCBlocks;
import org.zornco.reploidcraft.item.EnumEnergyPelletStr;
import org.zornco.reploidcraft.item.ItemBlockMechBay;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockMechBay extends Block implements ITileEntityProvider,IMetaBlockName {

	public static String[] names = {"basic", "controller", "power"};
    public static final PropertyEnum<BlockMechBay.EnumType> VARIANT = PropertyEnum.<BlockMechBay.EnumType>create("variant", BlockMechBay.EnumType.class);
    public static final PropertyBool INVIS = PropertyBool.create("invis");
    protected static final AxisAlignedBB MECH_BAY_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D);
	public BlockMechBay() {
		super(Material.IRON);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockMechBay.EnumType.BASIC).withProperty(INVIS, false));
		setCreativeTab(ReploidCraft.reploidCraftTab);
		setRegistryName("mechbay");
		setUnlocalizedName("mechbay");
		setHardness(3.5F);
		GameRegistry.register(this);
		GameRegistry.register(new ItemBlockMechBay(this).setRegistryName(this.getRegistryName()));
//		GameRegistry.register(new ItemMultiTexture(this, this, new ItemMultiTexture.Mapper()
//        {
//            public String apply(ItemStack p_apply_1_)
//            {
//                return BlockMechBay.EnumType.byMetadata(p_apply_1_.getMetadata()).getUnlocalizedName();
//            }
//        }).setUnlocalizedName(this.getUnlocalizedName()).setRegistryName(this.getRegistryName()));
	}
	@Override
	public String getSpecialName(ItemStack stack) {
		// TODO Auto-generated method stub
		return EnumType.byMetadata(stack.getItemDamage()).toString().toLowerCase();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player) {
	    return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(world.getBlockState(pos)));
	}

	@Override
	public int damageDropped(IBlockState state)
    {
        return ((BlockMechBay.EnumType)state.getValue(VARIANT)).getMetadata();
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state) {
        return ((EnumType) state.getValue(VARIANT)).getMetadata();
    }
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{VARIANT,INVIS});
    }
    /**
     * Get the MapColor for this Block and the given BlockState
     */
    public MapColor getMapColor(IBlockState state) {
        return ((EnumType) state.getValue(VARIANT)).getMapColor();
    }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list)
	{
		for (int ix = 0; ix <  BlockMechBay.names.length; ix++) {
			list.add(new ItemStack(this, 1, ix));
		}
	}
	@Override
	public TileEntity createNewTileEntity(World world, int metadata) {
		switch (metadata) {
		case 1:
			return new TileEntityMechBayController();
		case 2:
			return new TileEntityMechBayEnergy();
		case 0:
		default:
			return new TileEntityMechBay();	
		}
	}
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return MECH_BAY_AABB;
    }
	@Override
	public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
	{
		int[][] sideOffset = {{0,1,0},{0,-1,0},{0,0,1},{0,0,-1},{1,0,0},{-1,0,0}};
		TileEntity tile = blockAccess.getTileEntity(pos);
		if(tile != null && tile instanceof TileEntityMechBay)
		{
			if(((TileEntityMechBay)tile).hasMaster())
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		TileEntity te = worldIn.getTileEntity(pos);
		ReploidCraft.logger.info(FMLCommonHandler.instance().getEffectiveSide() + " " + ((TileEntityMechBay)te).isMaster());
		if (te == null || !(te instanceof TileEntityMechBayController))
		{
			return false;
		}
		if (worldIn.isRemote)
		{
			return true;
		}
		if(((TileMultiBlock) te).hasMaster())
		{
			playerIn.openGui(ReploidCraft.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		else return false;
		return true;
	}
	@Override
	public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
	public static enum EnumType implements IStringSerializable
    {
        BASIC(0, "basic", MapColor.WOOD),
        CONTROLLER(1, "controller", MapColor.OBSIDIAN),
        POWER(2, "power", MapColor.SAND);

        private static final BlockMechBay.EnumType[] META_LOOKUP = new BlockMechBay.EnumType[values().length];
        private final int meta;
        private final String name;
        private final String unlocalizedName;
        /** The color that represents this entry on a map. */
        private final MapColor mapColor;

        private EnumType(int metaIn, String nameIn, MapColor mapColorIn)
        {
            this(metaIn, nameIn, nameIn, mapColorIn);
        }

        private EnumType(int metaIn, String nameIn, String unlocalizedNameIn, MapColor mapColorIn)
        {
            this.meta = metaIn;
            this.name = nameIn;
            this.unlocalizedName = unlocalizedNameIn;
            this.mapColor = mapColorIn;
        }

        public int getMetadata()
        {
            return this.meta;
        }

        /**
         * The color which represents this entry on a map.
         */
        public MapColor getMapColor()
        {
            return this.mapColor;
        }

        public String toString()
        {
            return this.name;
        }

        public static BlockMechBay.EnumType byMetadata(int meta)
        {
            if (meta < 0 || meta >= META_LOOKUP.length)
            {
                meta = 0;
            }

            return META_LOOKUP[meta];
        }

        public String getName()
        {
            return this.name;
        }

        public String getUnlocalizedName()
        {
            return this.unlocalizedName;
        }

        static
        {
            for (BlockMechBay.EnumType blockplanks$enumtype : values())
            {
                META_LOOKUP[blockplanks$enumtype.getMetadata()] = blockplanks$enumtype;
            }
        }
    }
}
