package baguchi.tofucraft.block.utils;

import baguchi.tofucraft.blockentity.TofuBedBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BedPart;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.apache.commons.lang3.ArrayUtils;

import javax.annotation.Nullable;
import java.util.Optional;

public class TofuBedBlock extends BedBlock {
	public static final EnumProperty<BedPart> PART;
	public static final BooleanProperty OCCUPIED;
	protected static final int HEIGHT = 9;
	protected static final VoxelShape BASE;
	private static final int LEG_WIDTH = 3;
	protected static final VoxelShape LEG_NORTH_WEST;
	protected static final VoxelShape LEG_SOUTH_WEST;
	protected static final VoxelShape LEG_NORTH_EAST;
	protected static final VoxelShape LEG_SOUTH_EAST;
	protected static final VoxelShape NORTH_SHAPE;
	protected static final VoxelShape SOUTH_SHAPE;
	protected static final VoxelShape WEST_SHAPE;
	protected static final VoxelShape EAST_SHAPE;

	public TofuBedBlock(Properties p_49455_) {
		super(DyeColor.WHITE, p_49455_);

		this.registerDefaultState((BlockState) ((BlockState) ((BlockState) this.stateDefinition.any()).setValue(PART, BedPart.FOOT)).setValue(OCCUPIED, false));
	}

	@Override
	protected BlockState updateShape(BlockState p_49525_, LevelReader p_374508_, ScheduledTickAccess p_374420_, BlockPos p_49529_, Direction p_49526_, BlockPos p_49530_, BlockState p_49527_, RandomSource p_374423_) {
		if (p_49526_ == getNeighbourDirection((BedPart) p_49525_.getValue(PART), (Direction) p_49525_.getValue(FACING))) {
			return p_49527_.is(this) && p_49527_.getValue(PART) != p_49525_.getValue(PART) ? (BlockState) p_49525_.setValue(OCCUPIED, (Boolean) p_49527_.getValue(OCCUPIED)) : Blocks.AIR.defaultBlockState();
		} else {

			return super.updateShape(p_49525_, p_374508_, p_374420_, p_49529_, p_49526_, p_49530_, p_49527_, p_374423_);
		}
	}

	private static Direction getNeighbourDirection(BedPart p_49534_, Direction p_49535_) {
		return p_49534_ == BedPart.FOOT ? p_49535_ : p_49535_.getOpposite();
	}

	@Override
	public BlockState playerWillDestroy(Level p_49505_, BlockPos p_49506_, BlockState p_49507_, Player p_49508_) {
		if (!p_49505_.isClientSide && p_49508_.isCreative()) {
			BedPart var5 = (BedPart) p_49507_.getValue(PART);
			if (var5 == BedPart.FOOT) {
				BlockPos var6 = p_49506_.relative(getNeighbourDirection(var5, (Direction) p_49507_.getValue(FACING)));
				BlockState var7 = p_49505_.getBlockState(var6);
				if (var7.is(this) && var7.getValue(PART) == BedPart.HEAD) {
					p_49505_.setBlock(var6, Blocks.AIR.defaultBlockState(), 35);
					p_49505_.levelEvent(p_49508_, 2001, var6, Block.getId(var7));
				}
			}
		}

		return super.playerWillDestroy(p_49505_, p_49506_, p_49507_, p_49508_);
	}

	@Nullable
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext p_49479_) {
		Direction var2 = p_49479_.getHorizontalDirection();
		BlockPos var3 = p_49479_.getClickedPos();
		BlockPos var4 = var3.relative(var2);
		return p_49479_.getLevel().getBlockState(var4).canBeReplaced(p_49479_) ? (BlockState) this.defaultBlockState().setValue(FACING, var2) : null;
	}

	@Override
	public VoxelShape getShape(BlockState p_49547_, BlockGetter p_49548_, BlockPos p_49549_, CollisionContext p_49550_) {
		Direction var5 = getConnectedDirection(p_49547_).getOpposite();
		switch (var5) {
			case NORTH:
				return NORTH_SHAPE;
			case SOUTH:
				return SOUTH_SHAPE;
			case WEST:
				return WEST_SHAPE;
			default:
				return EAST_SHAPE;
		}
	}


	public static Direction getConnectedDirection(BlockState p_49558_) {
		Direction var1 = (Direction) p_49558_.getValue(FACING);
		return p_49558_.getValue(PART) == BedPart.HEAD ? var1.getOpposite() : var1;
	}

	public static DoubleBlockCombiner.BlockType getBlockType(BlockState p_49560_) {
		BedPart var1 = (BedPart) p_49560_.getValue(PART);
		return var1 == BedPart.HEAD ? DoubleBlockCombiner.BlockType.FIRST : DoubleBlockCombiner.BlockType.SECOND;
	}

	private static boolean isBunkBed(BlockGetter p_49542_, BlockPos p_49543_) {
		return p_49542_.getBlockState(p_49543_.below()).getBlock() instanceof BedBlock;
	}

	public static Optional<Vec3> findStandUpPosition(EntityType<?> p_49459_, CollisionGetter p_49460_, BlockPos p_49461_, float p_49462_) {
		Direction var4 = (Direction) p_49460_.getBlockState(p_49461_).getValue(FACING);
		Direction var5 = var4.getClockWise();
		Direction var6 = var5.isFacingAngle(p_49462_) ? var5.getOpposite() : var5;
		if (isBunkBed(p_49460_, p_49461_)) {
			return findBunkBedStandUpPosition(p_49459_, p_49460_, p_49461_, var4, var6);
		} else {
			int[][] var7 = bedStandUpOffsets(var4, var6);
			Optional<Vec3> var8 = findStandUpPositionAtOffset(p_49459_, p_49460_, p_49461_, var7, true);
			return var8.isPresent() ? var8 : findStandUpPositionAtOffset(p_49459_, p_49460_, p_49461_, var7, false);
		}
	}

	private static Optional<Vec3> findBunkBedStandUpPosition(EntityType<?> p_49464_, CollisionGetter p_49465_, BlockPos p_49466_, Direction p_49467_, Direction p_49468_) {
		int[][] var5 = bedSurroundStandUpOffsets(p_49467_, p_49468_);
		Optional<Vec3> var6 = findStandUpPositionAtOffset(p_49464_, p_49465_, p_49466_, var5, true);
		if (var6.isPresent()) {
			return var6;
		} else {
			BlockPos var7 = p_49466_.below();
			Optional<Vec3> var8 = findStandUpPositionAtOffset(p_49464_, p_49465_, var7, var5, true);
			if (var8.isPresent()) {
				return var8;
			} else {
				int[][] var9 = bedAboveStandUpOffsets(p_49467_);
				Optional<Vec3> var10 = findStandUpPositionAtOffset(p_49464_, p_49465_, p_49466_, var9, true);
				if (var10.isPresent()) {
					return var10;
				} else {
					Optional<Vec3> var11 = findStandUpPositionAtOffset(p_49464_, p_49465_, p_49466_, var5, false);
					if (var11.isPresent()) {
						return var11;
					} else {
						Optional<Vec3> var12 = findStandUpPositionAtOffset(p_49464_, p_49465_, var7, var5, false);
						return var12.isPresent() ? var12 : findStandUpPositionAtOffset(p_49464_, p_49465_, p_49466_, var9, false);
					}
				}
			}
		}
	}

	private static Optional<Vec3> findStandUpPositionAtOffset(EntityType<?> p_49470_, CollisionGetter p_49471_, BlockPos p_49472_, int[][] p_49473_, boolean p_49474_) {
		BlockPos.MutableBlockPos var5 = new BlockPos.MutableBlockPos();
		int[][] var6 = p_49473_;
		int var7 = p_49473_.length;

		for (int var8 = 0; var8 < var7; ++var8) {
			int[] var9 = var6[var8];
			var5.set(p_49472_.getX() + var9[0], p_49472_.getY(), p_49472_.getZ() + var9[1]);
			Vec3 var10 = DismountHelper.findSafeDismountLocation(p_49470_, p_49471_, var5, p_49474_);
			if (var10 != null) {
				return Optional.of(var10);
			}
		}

		return Optional.empty();
	}

	public PushReaction getPistonPushReaction(BlockState p_49556_) {
		return PushReaction.DESTROY;
	}

	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_49532_) {
		p_49532_.add(new Property[]{FACING, PART, OCCUPIED});
	}

	public BlockEntity newBlockEntity(BlockPos p_152175_, BlockState p_152176_) {
		return new TofuBedBlockEntity(p_152175_, p_152176_);
	}

	public void setPlacedBy(Level p_49499_, BlockPos p_49500_, BlockState p_49501_, @Nullable LivingEntity p_49502_, ItemStack p_49503_) {
		super.setPlacedBy(p_49499_, p_49500_, p_49501_, p_49502_, p_49503_);
		if (!p_49499_.isClientSide) {
			BlockPos var6 = p_49500_.relative((Direction) p_49501_.getValue(FACING));
			p_49499_.setBlock(var6, (BlockState) p_49501_.setValue(PART, BedPart.HEAD), 3);
			p_49499_.blockUpdated(p_49500_, Blocks.AIR);
			p_49501_.updateNeighbourShapes(p_49499_, p_49500_, 3);
		}

	}

	public long getSeed(BlockState p_49522_, BlockPos p_49523_) {
		BlockPos var3 = p_49523_.relative((Direction) p_49522_.getValue(FACING), p_49522_.getValue(PART) == BedPart.HEAD ? 0 : 1);
		return Mth.getSeed(var3.getX(), p_49523_.getY(), var3.getZ());
	}

	public boolean isPathfindable(BlockState p_49510_, BlockGetter p_49511_, BlockPos p_49512_, PathComputationType p_49513_) {
		return false;
	}

	private static int[][] bedStandUpOffsets(Direction p_49539_, Direction p_49540_) {
		return (int[][]) ArrayUtils.addAll(bedSurroundStandUpOffsets(p_49539_, p_49540_), bedAboveStandUpOffsets(p_49539_));
	}

	private static int[][] bedSurroundStandUpOffsets(Direction p_49552_, Direction p_49553_) {
		return new int[][]{{p_49553_.getStepX(), p_49553_.getStepZ()}, {p_49553_.getStepX() - p_49552_.getStepX(), p_49553_.getStepZ() - p_49552_.getStepZ()}, {p_49553_.getStepX() - p_49552_.getStepX() * 2, p_49553_.getStepZ() - p_49552_.getStepZ() * 2}, {-p_49552_.getStepX() * 2, -p_49552_.getStepZ() * 2}, {-p_49553_.getStepX() - p_49552_.getStepX() * 2, -p_49553_.getStepZ() - p_49552_.getStepZ() * 2}, {-p_49553_.getStepX() - p_49552_.getStepX(), -p_49553_.getStepZ() - p_49552_.getStepZ()}, {-p_49553_.getStepX(), -p_49553_.getStepZ()}, {-p_49553_.getStepX() + p_49552_.getStepX(), -p_49553_.getStepZ() + p_49552_.getStepZ()}, {p_49552_.getStepX(), p_49552_.getStepZ()}, {p_49553_.getStepX() + p_49552_.getStepX(), p_49553_.getStepZ() + p_49552_.getStepZ()}};
	}

	private static int[][] bedAboveStandUpOffsets(Direction p_49537_) {
		return new int[][]{{0, 0}, {-p_49537_.getStepX(), -p_49537_.getStepZ()}};
	}

	static {
		PART = BlockStateProperties.BED_PART;
		OCCUPIED = BlockStateProperties.OCCUPIED;
		BASE = Block.box(0.0D, 3.0D, 0.0D, 16.0D, 9.0D, 16.0D);
		LEG_NORTH_WEST = Block.box(0.0D, 0.0D, 0.0D, 3.0D, 3.0D, 3.0D);
		LEG_SOUTH_WEST = Block.box(0.0D, 0.0D, 13.0D, 3.0D, 3.0D, 16.0D);
		LEG_NORTH_EAST = Block.box(13.0D, 0.0D, 0.0D, 16.0D, 3.0D, 3.0D);
		LEG_SOUTH_EAST = Block.box(13.0D, 0.0D, 13.0D, 16.0D, 3.0D, 16.0D);
		NORTH_SHAPE = Shapes.or(BASE, new VoxelShape[]{LEG_NORTH_WEST, LEG_NORTH_EAST});
		SOUTH_SHAPE = Shapes.or(BASE, new VoxelShape[]{LEG_SOUTH_WEST, LEG_SOUTH_EAST});
		WEST_SHAPE = Shapes.or(BASE, new VoxelShape[]{LEG_NORTH_WEST, LEG_SOUTH_WEST});
		EAST_SHAPE = Shapes.or(BASE, new VoxelShape[]{LEG_NORTH_EAST, LEG_SOUTH_EAST});
	}
}
