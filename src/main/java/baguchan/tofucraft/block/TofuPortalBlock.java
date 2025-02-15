package baguchan.tofucraft.block;

import baguchan.tofucraft.attachment.TofuLivingAttachment;
import baguchan.tofucraft.registry.TofuAttachments;
import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuDimensions;
import baguchan.tofucraft.registry.TofuParticleTypes;
import baguchan.tofucraft.world.TofuPortalForcer;
import baguchan.tofucraft.world.TofuPortalShape;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.DimensionTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Optional;

public class TofuPortalBlock extends Block implements Portal {
	public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
	protected static final VoxelShape X_AXIS_AABB = Block.box(0.0, 0.0, 6.0, 16.0, 16.0, 10.0);
	protected static final VoxelShape Z_AXIS_AABB = Block.box(6.0, 0.0, 0.0, 10.0, 16.0, 16.0);


	public TofuPortalBlock(Properties props) {
		super(props);
		this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_54935_) {
		p_54935_.add(AXIS);
	}

	@Override
	protected VoxelShape getShape(BlockState p_54942_, BlockGetter p_54943_, BlockPos p_54944_, CollisionContext p_54945_) {
		switch ((Direction.Axis) p_54942_.getValue(AXIS)) {
			case Z:
				return Z_AXIS_AABB;
			case X:
			default:
				return X_AXIS_AABB;
		}
	}

	@Override
	@Deprecated
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
		return Shapes.empty();
	}


	@Override
	protected BlockState updateShape(BlockState p_54928_, Direction p_54929_, BlockState p_54930_, LevelAccessor p_54931_, BlockPos p_54932_, BlockPos p_54933_) {
		Direction.Axis direction$axis = p_54929_.getAxis();
		Direction.Axis direction$axis1 = p_54928_.getValue(AXIS);
		boolean flag = direction$axis1 != direction$axis && direction$axis.isHorizontal();
		return !flag && !p_54930_.is(this) && !new TofuPortalShape(p_54931_, p_54932_, direction$axis1).isComplete()
				? Blocks.AIR.defaultBlockState()
				: super.updateShape(p_54928_, p_54929_, p_54930_, p_54931_, p_54932_, p_54933_);
	}


	@Override
	protected void entityInside(BlockState p_54915_, Level level, BlockPos p_54917_, Entity entity) {
		if (entity.canUsePortal(false)) {
			entity.setAsInsidePortal(this, p_54917_);
			if (entity instanceof Player player) {
				TofuLivingAttachment portal = player.getData(TofuAttachments.TOFU_LIVING);
				portal.setInPortal(true);
				int waitTime = portal.getPortalTimer();
				if (waitTime >= this.getLevelPortalTransitionTime(level, player)) {
					portal.handlePortal(player);
					portal.setPortalTimer(0);
				}
			}
		}

	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState stateIn, Level worldIn, BlockPos pos, RandomSource rand) {
		int random = rand.nextInt(100);
		if (random == 0) {
			worldIn.playLocalSound(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, SoundEvents.PORTAL_AMBIENT, SoundSource.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
		}

		for (int i = 0; i < 4; ++i) {
			double d0 = (double) pos.getX() + rand.nextDouble();
			double d1 = (double) pos.getY() + rand.nextDouble();
			double d2 = (double) pos.getZ() + rand.nextDouble();
			double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
			int j = rand.nextInt(2) * 2 - 1;

			worldIn.addParticle(TofuParticleTypes.TOFU_PORTAL.get(), d0, d1, d2, d3, d4, d5);
		}
	}

	private int getLevelPortalTransitionTime(Level level, Entity entity) {
		return entity instanceof Player player
				? Math.max(
				1,
				level.getGameRules()
						.getInt(
								player.getAbilities().invulnerable
										? GameRules.RULE_PLAYERS_NETHER_PORTAL_CREATIVE_DELAY
										: GameRules.RULE_PLAYERS_NETHER_PORTAL_DEFAULT_DELAY
						)
		)
				: 0;
	}

	@Override
	public int getPortalTransitionTime(ServerLevel p_350689_, Entity p_350280_) {
		return getLevelPortalTransitionTime(p_350689_, p_350280_);
	}

	@javax.annotation.Nullable
	@Override
	public DimensionTransition getPortalDestination(ServerLevel p_350444_, Entity p_350334_, BlockPos p_350764_) {
		ResourceKey<Level> resourcekey = p_350444_.dimension() == TofuDimensions.tofu_world ? Level.OVERWORLD : TofuDimensions.tofu_world;
		ServerLevel serverlevel = p_350444_.getServer().getLevel(resourcekey);
		if (serverlevel == null) {
			return null;
		} else {
			boolean flag = serverlevel.dimension() == TofuDimensions.tofu_world;
			WorldBorder worldborder = serverlevel.getWorldBorder();
			double d0 = DimensionType.getTeleportationScale(p_350444_.dimensionType(), serverlevel.dimensionType());
			BlockPos blockpos = worldborder.clampToBounds(p_350334_.getX() * d0, p_350334_.getY(), p_350334_.getZ() * d0);
			return this.getExitPortal(serverlevel, p_350334_, p_350764_, blockpos, flag, worldborder);
		}
	}

	@javax.annotation.Nullable
	private DimensionTransition getExitPortal(
			ServerLevel p_350564_, Entity p_350493_, BlockPos p_350379_, BlockPos p_350747_, boolean p_350326_, WorldBorder p_350718_
	) {
		Optional<BlockPos> optional = new TofuPortalForcer(p_350564_).findClosestPortalPosition(p_350747_, p_350326_, p_350718_);
		BlockUtil.FoundRectangle blockutil$foundrectangle;
		DimensionTransition.PostDimensionTransition dimensiontransition$postdimensiontransition;
		if (optional.isPresent()) {
			BlockPos blockpos = optional.get();
			BlockState blockstate = p_350564_.getBlockState(blockpos);
			blockutil$foundrectangle = BlockUtil.getLargestRectangleAround(
					blockpos,
					blockstate.getValue(AXIS),
					21,
					Direction.Axis.Y,
					21,
					p_351970_ -> p_350564_.getBlockState(p_351970_) == blockstate
			);
			dimensiontransition$postdimensiontransition = DimensionTransition.PLAY_PORTAL_SOUND.then(p_351967_ -> p_351967_.placePortalTicket(blockpos));
		} else {
			Direction.Axis direction$axis = p_350493_.level().getBlockState(p_350379_).getOptionalValue(AXIS).orElse(Direction.Axis.X);
			Optional<BlockUtil.FoundRectangle> optional1 = new TofuPortalForcer(p_350564_).createPortal(p_350747_, direction$axis);

			blockutil$foundrectangle = optional1.get();
			dimensiontransition$postdimensiontransition = DimensionTransition.PLAY_PORTAL_SOUND.then(DimensionTransition.PLACE_PORTAL_TICKET);
		}

		return getDimensionTransitionFromExit(p_350493_, p_350379_, blockutil$foundrectangle, p_350564_, dimensiontransition$postdimensiontransition);
	}

	private static DimensionTransition getDimensionTransitionFromExit(
			Entity p_350906_, BlockPos p_350376_, BlockUtil.FoundRectangle p_350428_, ServerLevel p_350928_, DimensionTransition.PostDimensionTransition p_352093_
	) {
		BlockState blockstate = p_350906_.level().getBlockState(p_350376_);
		Direction.Axis direction$axis;
		Vec3 vec3;
		if (blockstate.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
			direction$axis = blockstate.getValue(BlockStateProperties.HORIZONTAL_AXIS);
			BlockUtil.FoundRectangle blockutil$foundrectangle = BlockUtil.getLargestRectangleAround(
					p_350376_, direction$axis, 21, Direction.Axis.Y, 21, p_351016_ -> p_350906_.level().getBlockState(p_351016_) == blockstate
			);
			vec3 = p_350906_.getRelativePortalPosition(direction$axis, blockutil$foundrectangle);
		} else {
			direction$axis = Direction.Axis.X;
			vec3 = new Vec3(0.5, 0.0, 0.0);
		}
		return createDimensionTransition(
				p_350928_, p_350428_, direction$axis, vec3, p_350906_, p_350906_.getDeltaMovement(), p_350906_.getYRot(), p_350906_.getXRot(), p_352093_
		);
	}

	private static DimensionTransition createDimensionTransition(
			ServerLevel p_350955_,
			BlockUtil.FoundRectangle p_350865_,
			Direction.Axis p_351013_,
			Vec3 p_351020_,
			Entity p_350578_,
			Vec3 p_350266_,
			float p_350648_,
			float p_350338_,
			DimensionTransition.PostDimensionTransition p_352441_
	) {
		BlockPos blockpos = p_350865_.minCorner;
		BlockState blockstate = p_350955_.getBlockState(blockpos);
		Direction.Axis direction$axis = blockstate.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
		double d0 = (double) p_350865_.axis1Size;
		double d1 = (double) p_350865_.axis2Size;
		EntityDimensions entitydimensions = p_350578_.getDimensions(p_350578_.getPose());
		int i = p_351013_ == direction$axis ? 0 : 90;
		Vec3 vec3 = p_351013_ == direction$axis ? p_350266_ : new Vec3(p_350266_.z, p_350266_.y, -p_350266_.x);
		double d2 = (double) entitydimensions.width() / 2.0 + (d0 - (double) entitydimensions.width()) * p_351020_.x();
		double d3 = (d1 - (double) entitydimensions.height()) * p_351020_.y();
		double d4 = 0.5 + p_351020_.z();
		boolean flag = direction$axis == Direction.Axis.X;
		Vec3 vec31 = new Vec3((double) blockpos.getX() + (flag ? d2 : d4), (double) blockpos.getY() + d3, (double) blockpos.getZ() + (flag ? d4 : d2));
		Vec3 vec32 = TofuPortalShape.findCollisionFreePosition(vec31, p_350955_, p_350578_, entitydimensions);
		return new DimensionTransition(p_350955_, vec32, vec3, p_350648_ + (float) i, p_350338_, p_352441_);
	}

	public static class Size {
		private static final int MAX_SIZE = 12;

		private static final int MIN_SIZE = 1;

		private final LevelAccessor world;

		private boolean valid = false;

		private BlockPos nw;

		private BlockPos se;

		public Size(LevelAccessor world, BlockPos pos) {
			this.world = world;
			int east = getDistanceUntilEdge(pos, Direction.EAST);
			int west = getDistanceUntilEdge(pos, Direction.WEST);
			int north = getDistanceUntilEdge(pos, Direction.NORTH);
			int south = getDistanceUntilEdge(pos, Direction.SOUTH);
			int width = east + west - 1;
			int length = north + south - 1;
			if (width > 12 || length > 12)
				return;
			if (width < 2 || length < 2)
				return;
			BlockPos neCorner = pos.east(east).north(north);
			BlockPos nwCorner = pos.west(west).north(north);
			BlockPos seCorner = pos.east(east).south(south);
			BlockPos swCorner = pos.west(west).south(south);
			this.nw = nwCorner.offset(1, 0, 1);
			this.se = seCorner.offset(-1, 0, -1);
			int wallWidth = width + 2;
			int wallLength = length + 2;
			for (int y = 0; y <= 1; y++) {
				for (int x = 0; x < wallWidth; x++) {
					for (int z = 0; z < wallLength; z++) {
						if (((y == 0 && x != 0 && z != 0 && x != wallWidth - 1 && z != wallLength - 1) || (y == 1 && (x == 0 && z < wallWidth - 1 && z > 0 || z == 0 && x < wallWidth - 1 && x > 0 || x == wallWidth - 1 && z > 0 && z < wallWidth - 1 || z == wallLength - 1 && x > 0 && x < wallWidth - 1))) &&
								!isTofuBlock(world.getBlockState(nwCorner.below().offset(x, y, z))))
							//TODO
							//!isTofuBlock(world.getBlockState(nwCorner.above().offset(x, y, z))))
							return;
					}
				}
			}
			this.valid = true;
		}

		int getDistanceUntilEdge(BlockPos pos, Direction facing) {
			int i;
			for (i = 0; i < 9; i++) {
				BlockPos blockpos = pos.relative(facing, i);
				if (!isEmptyBlock(this.world.getBlockState(blockpos)) || !isTofuBlock(this.world.getBlockState(blockpos.below())))
					break;
			}
			BlockState state = this.world.getBlockState(pos.relative(facing, i));
			return isTofuBlock(state) ? i : 0;
		}

		boolean isEmptyBlock(BlockState state) {
			return (state.getBlock() == TofuBlocks.SOYMILK.get());
		}

		boolean isTofuBlock(BlockState state) {
			return (state.getBlock() == TofuBlocks.GRILLEDTOFU.get());
		}

		public boolean isValid() {
			return this.valid;
		}

		void placePortalBlocks() {
			for (BlockPos portalPos : BlockPos.MutableBlockPos.betweenClosed(this.nw, this.se))
				this.world.setBlock(portalPos, TofuBlocks.TOFU_PORTAL.get().defaultBlockState(), 2);
		}
	}
}
