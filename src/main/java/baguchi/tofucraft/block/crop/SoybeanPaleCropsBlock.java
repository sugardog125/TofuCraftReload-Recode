package baguchi.tofucraft.block.crop;

import baguchi.tofucraft.registry.TofuItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.TrailParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.CommonHooks;

public class SoybeanPaleCropsBlock extends CropBlock {
	private static final VoxelShape[] SHAPES = new VoxelShape[]{Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D), Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D)};

	public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
	public static final BooleanProperty BLOOM = BooleanProperty.create("bloom");

	public SoybeanPaleCropsBlock(Properties builder) {
		super(builder);
		this.registerDefaultState(this.stateDefinition.any().setValue(this.getAgeProperty(), 0).setValue(BLOOM, false));
	}

	@Override
	protected IntegerProperty getAgeProperty() {
		return AGE;
	}

	@Override
	public int getMaxAge() {
		return 3;
	}

	@Override
	public void animateTick(BlockState p_382850_, Level p_382933_, BlockPos p_382838_, RandomSource p_383190_) {
		boolean flag = p_382850_.getValue(BLOOM);
		int age = p_382850_.getValue(AGE);
		if (flag && age < 3 && p_383190_.nextInt(700) == 0) {
			BlockState blockstate = p_382933_.getBlockState(p_382838_.below());
			if (blockstate.is(Blocks.PALE_MOSS_BLOCK)) {
				p_382933_.playLocalSound(
						(double) p_382838_.getX(),
						(double) p_382838_.getY(),
						(double) p_382838_.getZ(),
						SoundEvents.EYEBLOSSOM_IDLE,
						SoundSource.BLOCKS,
						1.0F,
						1.0F,
						false
				);
			}
		}
	}

	@Override
	public void randomTick(BlockState state, ServerLevel worldIn, BlockPos pos, RandomSource random) {
		this.tryChangingState(state, worldIn, pos, random);
		if (!worldIn.isAreaLoaded(pos, 1))
			return;
		int i = getAge(state);
		if (i < getMaxAge()) {
			float f = getGrowthSpeed(state, worldIn, pos);
			if (CommonHooks.canCropGrow(worldIn, pos, state, (random.nextInt((int) (25.0F / f) + 1) == 0))) {
				worldIn.setBlock(pos, this.getStateForAge(i + 1), 2);
				CommonHooks.fireCropGrowPost(worldIn, pos, state);
			}
		}
	}

	@Override
	protected void tick(BlockState p_382808_, ServerLevel p_383005_, BlockPos p_383211_, RandomSource p_383088_) {
		if (this.tryChangingState(p_382808_, p_383005_, p_383211_, p_383088_)) {
		}

		super.tick(p_382808_, p_383005_, p_383211_, p_383088_);
	}

	private boolean tryChangingState(BlockState p_383235_, ServerLevel p_383091_, BlockPos p_383073_, RandomSource p_383038_) {
		boolean flag = p_383235_.getValue(BLOOM);
		int age = p_383235_.getValue(AGE);
		if (age < 3) {
			return false;
		} else if (!p_383091_.dimensionType().natural()) {
			return false;
		} else if (p_383091_.isDay() != flag) {
			return false;
		} else {
			p_383091_.setBlock(p_383073_, p_383235_.setValue(BLOOM, !flag), 3);
			p_383091_.gameEvent(GameEvent.BLOCK_CHANGE, p_383073_, GameEvent.Context.of(p_383235_));
			spawnTransformParticle(p_383091_, p_383073_, p_383038_, !flag);
			BlockPos.betweenClosed(p_383073_.offset(-3, -2, -3), p_383073_.offset(3, 2, 3)).forEach(p_383198_ -> {
				BlockState blockstate = p_383091_.getBlockState(p_383198_);
				if (blockstate == p_383235_) {
					double d0 = Math.sqrt(p_383073_.distSqr(p_383198_));
					int i = p_383038_.nextIntBetweenInclusive((int) (d0 * 5.0), (int) (d0 * 10.0));
					p_383091_.scheduleTick(p_383198_, p_383235_.getBlock(), i);
				}
			});
			if (flag) {
				p_383091_.playSound(null, p_383073_, SoundEvents.EYEBLOSSOM_CLOSE, SoundSource.BLOCKS, 1.0F, 1.0F);
			} else {
				p_383091_.playSound(null, p_383073_, SoundEvents.EYEBLOSSOM_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);
			}
			return true;
		}
	}

	public void spawnTransformParticle(ServerLevel p_383084_, BlockPos p_383042_, RandomSource p_382825_, boolean flag) {
		Vec3 vec3 = p_383042_.getCenter();
		double d0 = 0.5 + p_382825_.nextDouble();
		Vec3 vec31 = new Vec3(p_382825_.nextDouble() - 0.5, p_382825_.nextDouble() + 1.0, p_382825_.nextDouble() - 0.5);
		Vec3 vec32 = vec3.add(vec31.scale(d0));
		TrailParticleOption trailparticleoption = new TrailParticleOption(vec32, flag ? 16545810 : 6250335, (int) (20.0 * d0));
		p_383084_.sendParticles(trailparticleoption, vec3.x, vec3.y, vec3.z, 1, 0.0, 0.0, 0.0, 0.0);
	}

	protected static float getGrowthSpeed(BlockState plant, ServerLevel p_52274_, BlockPos p_52275_) {
		Block p_52273_ = plant.getBlock();
		float f = 1.0F;
		BlockPos blockpos = p_52275_.below();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				float f1 = 0.0F;
				BlockState blockstate = p_52274_.getBlockState(blockpos.offset(i, 0, j));
				net.neoforged.neoforge.common.util.TriState soilDecision = blockstate.canSustainPlant(p_52274_, blockpos.offset(i, 0, j), net.minecraft.core.Direction.UP, plant);
				if (soilDecision.isDefault() ? plant.canSurvive(p_52274_, blockpos.above().offset(i, 0, j)) : soilDecision.isTrue()) {
					f1 = 1.0F;
					if (blockstate.is(Blocks.PALE_MOSS_BLOCK)) {
						f1 = 3.0F;
					}
				}

				if (i != 0 || j != 0) {
					f1 /= 4.0F;
				}

				f += f1;
			}
		}

		BlockPos blockpos1 = p_52275_.north();
		BlockPos blockpos2 = p_52275_.south();
		BlockPos blockpos3 = p_52275_.west();
		BlockPos blockpos4 = p_52275_.east();
		boolean flag = p_52274_.getBlockState(blockpos3).is(p_52273_) || p_52274_.getBlockState(blockpos4).is(p_52273_);
		boolean flag1 = p_52274_.getBlockState(blockpos1).is(p_52273_) || p_52274_.getBlockState(blockpos2).is(p_52273_);
		if (flag && flag1) {
			f /= 2.0F;
		} else {
			boolean flag2 = p_52274_.getBlockState(blockpos3.north()).is(p_52273_)
					|| p_52274_.getBlockState(blockpos4.north()).is(p_52273_)
					|| p_52274_.getBlockState(blockpos4.south()).is(p_52273_)
					|| p_52274_.getBlockState(blockpos3.south()).is(p_52273_);
			if (flag2) {
				f /= 2.0F;
			}
		}

		return f;
	}

	@Override
	protected ItemLike getBaseSeedId() {
		return TofuItems.SEEDS_SOYBEANS_PALE.get();
	}

	@Override
	protected boolean mayPlaceOn(BlockState p_52302_, BlockGetter p_52303_, BlockPos p_52304_) {
		return p_52302_.is(Blocks.GRASS_BLOCK) || p_52302_.is(Blocks.PALE_MOSS_BLOCK);
	}

	@Override
	public VoxelShape getShape(BlockState p_52297_, BlockGetter p_52298_, BlockPos p_52299_, CollisionContext p_52300_) {
		return SHAPES[p_52297_.getValue(AGE)];
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_52286_) {
		p_52286_.add(AGE, BLOOM);
	}

	@Override
	protected boolean isRandomlyTicking(BlockState p_52288_) {
		return true;
	}
}
